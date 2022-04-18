package com.eht.evetrade.service;


import com.eht.evetrade.dto.MarketOrderDTO;
import com.eht.evetrade.service.schedule.SatelliteTrade;
import com.ejlchina.okhttps.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class MarketOrderService {


    private static final Logger logger = LoggerFactory.getLogger(MarketOrderService.class);

    private static final String REGIONID = "10000002";

    @Autowired
    private HTTP http;

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

//    public MarketOrderDTO getPrice(String typeId,String regionId,String tradeType,){
//
//    }
}
