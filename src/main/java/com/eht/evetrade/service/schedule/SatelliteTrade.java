package com.eht.evetrade.service.schedule;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.eht.evetrade.dto.MarketHistoryDTO;
import com.eht.evetrade.dto.MarketOrderDTO;
import com.eht.evetrade.enums.SatelliteEnum;
import com.ejlchina.okhttps.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;

@Service
public class SatelliteTrade {

    private static final Logger logger = LoggerFactory.getLogger(SatelliteTrade.class);

    private static final String REGIONID = "10000002";

    @Autowired
    private HTTP http;

    public void schedule1() {
        List<SatelliteEnum> list = Arrays.asList(SatelliteEnum.values());
        Map<SatelliteEnum, BigDecimal> minSellMap = new LinkedHashMap<>();
        for (SatelliteEnum m : list) {
            List<MarketOrderDTO> sellList = http.sync("/latest/markets/" + REGIONID + "/orders/?datasource=serenity&order_type=sell&page=1&type_id=" + m.getId()).get().getBody().toList(MarketOrderDTO.class);
            for (MarketOrderDTO dto : sellList) {
                BigDecimal tmp = minSellMap.get(m);
                if (tmp == null) {
                    minSellMap.put(m, dto.getPrice());
                } else {
                    if (dto.getPrice().compareTo(tmp) < 0) {
                        minSellMap.put(m, dto.getPrice());
                    }
                }
            }
        }
        for (SatelliteEnum s : minSellMap.keySet()) {
            System.out.println(s.getName() + "\t" + minSellMap.get(s));
        }
    }


    @Scheduled(fixedDelay = 600000)
    public void schedule() {
        Map<SatelliteEnum, BigDecimal> minSellMap = new LinkedHashMap<>();
        Map<SatelliteEnum, BigDecimal> maxBuyMap = new LinkedHashMap<>();
        Map<SatelliteEnum, BigDecimal> min7Map = new LinkedHashMap<>();
        Map<SatelliteEnum, BigDecimal> min30Map = new LinkedHashMap<>();
        List<SatelliteEnum> list = Arrays.asList(SatelliteEnum.values());
        for (SatelliteEnum m : list) {
            List<MarketOrderDTO> sellList = http.sync("/latest/markets/" + REGIONID + "/orders/?datasource=serenity&order_type=sell&page=1&type_id=" + m.getId()).get().getBody().toList(MarketOrderDTO.class);
            for (MarketOrderDTO dto : sellList) {
                BigDecimal tmp = minSellMap.get(m);
                if (tmp == null) {
                    minSellMap.put(m, dto.getPrice());
                } else {
                    if (dto.getPrice().compareTo(tmp) < 0) {
                        minSellMap.put(m, dto.getPrice());
                    }
                }
            }

            List<MarketOrderDTO> buyList = http.sync("/latest/markets/" + REGIONID + "/orders/?datasource=serenity&order_type=buy&page=1&type_id=" + m.getId()).get().getBody().toList(MarketOrderDTO.class);
            for (MarketOrderDTO dto : buyList) {
                BigDecimal tmp = maxBuyMap.get(m);
                if (tmp == null) {
                    maxBuyMap.put(m, dto.getPrice());
                } else {
                    if (dto.getPrice().compareTo(tmp) > 0) {
                        maxBuyMap.put(m, dto.getPrice());
                    }
                }
            }

            List<MarketHistoryDTO> hisList = http.sync("/latest/markets/" + REGIONID + "/history/?datasource=serenity&type_id=" + m.getId()).get().getBody().toList(MarketHistoryDTO.class);
            for (MarketHistoryDTO dto : hisList) {
                Date date2 = DateUtil.parse(dto.getDate());
                long betweenDay = DateUtil.between(new Date(), date2, DateUnit.DAY);
                if (betweenDay <= 8) {
                    BigDecimal tmp = min7Map.get(m);
                    if (tmp == null) {
                        min7Map.put(m, dto.getLowest());
                    } else {
                        if (dto.getLowest().compareTo(tmp) < 0) {
                            min7Map.put(m, dto.getLowest());
                        }
                    }
                }
                if (betweenDay <= 31) {
                    BigDecimal tmp = min30Map.get(m);
                    if (tmp == null) {
                        min30Map.put(m, dto.getLowest());
                    } else {
                        if (dto.getLowest().compareTo(tmp) < 0) {
                            min30Map.put(m, dto.getLowest());
                        }
                    }
                }

            }
        }
        for (SatelliteEnum s : minSellMap.keySet()) {
            BigDecimal minSell = minSellMap.get(s);
            BigDecimal maxBuy = maxBuyMap.get(s);
            BigDecimal min7 = min7Map.get(s);
            BigDecimal min30 = min30Map.get(s);
            System.out.println(s.getName() + ":" + minSellMap.get(s) + ":"
                    + maxBuyMap.get(s) + "(" + maxBuy.subtract(minSell).divide(minSellMap.get(s), 2, RoundingMode.HALF_UP) + "):"
                    + min7Map.get(s) + "(" + minSell.subtract(min7).divide(minSellMap.get(s), 2, RoundingMode.HALF_UP) + "):"
                    + min30Map.get(s) + "(" + minSell.subtract(min30).divide(minSellMap.get(s), 2, RoundingMode.HALF_UP) + ")");
        }
    }
}
