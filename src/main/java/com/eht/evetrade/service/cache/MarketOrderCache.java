package com.eht.evetrade.service.cache;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eht.evetrade.dto.MarketOrderDTO;
import com.eht.evetrade.mybatis.entity.EveMonimarketInfo;
import com.eht.evetrade.mybatis.mapper.EveMonimarketInfoMapper;
import com.eht.evetrade.service.MarketOrderService;
import com.ejlchina.okhttps.HTTP;
import com.ejlchina.okhttps.HttpResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class MarketOrderCache {

    private static final Logger logger = LoggerFactory.getLogger(MarketOrderCache.class);

    @Autowired
    private HTTP http;

    @Autowired
    private EveMonimarketInfoMapper eveMonimarketInfoMapper;

    @Autowired
    private ObjectMapper mapper;

    private static final String REGIONID = "10000002";

    @Cacheable(value = "MarketPrice", key = "#typeId")
    public List<MarketOrderDTO> getPrice(String typeId) {
        List<MarketOrderDTO> retList = new LinkedList<>();
        int i = 1;
        while (true) {
            HttpResult httpResult = null;
            try {
                httpResult = http.sync("/latest/markets/" + REGIONID + "/orders/?datasource=serenity&order_type=all&page=" + i + "&type_id=" + typeId).get();
                if (httpResult != null && httpResult.getStatus() != 200) {
                    break;
                }
                String json = httpResult.getBody().toString();
                retList.addAll(mapper.readValue(json, new TypeReference<List<MarketOrderDTO>>(){}));
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
        logger.info("retList = " + retList.size());
        return retList;
    }



    @Cacheable(value = "MoniTypeIdPrice")
    public List<String> getMoniTypeIdList() {
        List<String> retList = new LinkedList();
        try {
            QueryWrapper wq = new QueryWrapper<EveMonimarketInfo>();
            wq.orderByAsc("TYPE_ID");
            List<EveMonimarketInfo> infoList = eveMonimarketInfoMapper.selectList(wq);
            if (infoList != null) {
                infoList.forEach(item -> retList.add(item.getTypeId()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retList;
    }

}
