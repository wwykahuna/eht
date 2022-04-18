package com.eht.evetrade.service;


import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Pair;
import com.eht.evetrade.dto.MarketHistoryDTO;
import com.eht.evetrade.dto.MarketOrderDTO;
import com.eht.evetrade.enums.SatelliteEnum;
import com.eht.evetrade.service.schedule.SatelliteTrade;
import com.eht.evetrade.vo.TradeItemVO;
import com.ejlchina.okhttps.HTTP;
import com.ejlchina.okhttps.HttpResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class MarketOrderService {


    private static final Logger logger = LoggerFactory.getLogger(MarketOrderService.class);

    private static final String REGIONID = "10000002";

    @Autowired
    private HTTP http;

    @Autowired
    private ObjectMapper mapper;

    public MarketOrderDTO getMinSellPrice(String typeId) {
        MarketOrderDTO dto = null;
        List<MarketOrderDTO> sellList = http.sync("/latest/markets/" + REGIONID + "/orders/?datasource=serenity&order_type=sell&page=1&type_id=" + typeId).get().getBody().toList(MarketOrderDTO.class);
        for (MarketOrderDTO item : sellList) {
            if (dto == null) {
                dto = item;
            } else if (item.getPrice().compareTo(dto.getPrice()) < 0) {
                dto = item;
            }
        }
        return dto;
    }

    public List<TradeItemVO> getTradeList() {
        List<TradeItemVO> list = new LinkedList<>();
        List<SatelliteEnum> enumList = Arrays.asList(SatelliteEnum.values());
        for (SatelliteEnum m : enumList) {
            TradeItemVO vo = new TradeItemVO();
            Pair<MarketOrderDTO, MarketOrderDTO> cur = getPrice(String.valueOf(m.getId()));
            Pair<BigDecimal, BigDecimal> his = getHisPrice(String.valueOf(m.getId()));
            vo.setId(m.getId());
            vo.setName(m.getName());
            BigDecimal min = cur.getKey().getPrice();
            BigDecimal max = cur.getValue().getPrice();
            BigDecimal m7 = his.getKey();
            BigDecimal m30 = his.getValue();

            vo.setMinSellPrice(min.intValue());
            vo.setMinSellVolumn(cur.getKey().getVolumeTotal());

            vo.setMaxBuyPrice(max.intValue());
            vo.setMaxBuyVolumn(cur.getValue().getVolumeTotal());


            vo.setBuyToSellPrice(max.subtract(min).intValue());
            vo.setBuyToSellPer(max.subtract(min).divide(min, 2, RoundingMode.HALF_UP).toString());
            vo.setMinSell7Price(m7.intValue());
            vo.setSellTo7Price(m7.subtract(min).intValue());
            vo.setSellTo7Per(m7.subtract(min).divide(min, 2, RoundingMode.HALF_UP).toString());
            vo.setMinSell30Price(m30.intValue());
            vo.setSellTo30Price(m30.subtract(min).intValue());
            vo.setSellTo30Per(m30.subtract(min).divide(min, 2, RoundingMode.HALF_UP).toString());
            vo.setPoint(" ");
            vo.setType(0);
            list.add(vo);
        }
        return list;
    }


    public Pair<MarketOrderDTO, MarketOrderDTO> getPrice(String typeId) {
        int i = 1;
        MarketOrderDTO min = null;
        MarketOrderDTO max = null;
        while (true) {
            HttpResult httpResult = null;
            try {
                httpResult = http.sync("/latest/markets/" + REGIONID + "/orders/?datasource=serenity&order_type=all&page=" + i + "&type_id=" + typeId).get();
                if (httpResult != null && httpResult.getStatus() != 200) {
                    break;
                }
                String json = httpResult.getBody().toString();
                List<MarketOrderDTO> tList = mapper.readValue(json, new TypeReference<List<MarketOrderDTO>>() {
                });
                for (MarketOrderDTO dto : tList) {
                    if (dto.getIsBuyOrder().equalsIgnoreCase("true")) {
                        if (max == null) {
                            max = dto;
                        } else {
                            if (dto.getPrice().compareTo(max.getPrice()) > 0) {
                                max = dto;
                            }
                        }
                    } else {
                        if (min == null) {
                            min = dto;
                        } else {
                            if (dto.getPrice().compareTo(min.getPrice()) < 0) {
                                min = dto;
                            }
                        }
                    }
                }
                i++;
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } finally {
                if (httpResult != null) {
                    httpResult.close();
                }
            }
        }
        return Pair.of(min, max);
    }

    public Pair<BigDecimal, BigDecimal> getHisPrice(String typeId) {
        List<MarketHistoryDTO> hisList = http.sync("/latest/markets/" + REGIONID + "/history/?datasource=serenity&type_id=" + typeId).get().getBody().toList(MarketHistoryDTO.class);
        BigDecimal min7 = null;
        BigDecimal min30 = null;
        for (MarketHistoryDTO dto : hisList) {
            Date date2 = DateUtil.parse(dto.getDate());
            long betweenDay = DateUtil.between(new Date(), date2, DateUnit.DAY);
            if (betweenDay <= 8) {
                if (min7 == null) {
                    min7 = dto.getLowest();
                } else {
                    if (dto.getLowest().compareTo(min7) < 0) {
                        min7 = dto.getLowest();
                    }
                }
            }
            if (betweenDay <= 31) {
                if (min30 == null) {
                    min30 = dto.getLowest();
                } else {
                    if (dto.getLowest().compareTo(min30) < 0) {
                        min30 = dto.getLowest();
                    }
                }
            }
        }
        return Pair.of(min7, min30);
    }
}
