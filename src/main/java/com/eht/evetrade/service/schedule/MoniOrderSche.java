package com.eht.evetrade.service.schedule;

import cn.hutool.core.util.IdUtil;
import com.eht.evetrade.dto.MarketOrderDTO;
import com.eht.evetrade.mybatis.entity.EveMarketorderRc;
import com.eht.evetrade.mybatis.mapper.EveMarketorderRcMapper;
import com.eht.evetrade.service.cache.MarketOrderCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class MoniOrderSche {

    @Autowired
    private MarketOrderCache marketOrderCache;

    @Autowired
    private EveMarketorderRcMapper eveMarketorderRcMapper;

    @Scheduled(cron = "0 0,30 * * * ?")
    public void schedule() {
        List<String> typeIdList = marketOrderCache.getMoniTypeIdList();
        if (typeIdList != null && typeIdList.size() > 0) {
            for (String typeId : typeIdList) {
                EveMarketorderRc rc = new EveMarketorderRc();
                rc.setTypeId(typeId);
                rc.setUid(IdUtil.getSnowflakeNextIdStr());
                rc.setOpTime(new Date());
                Integer sellCnt = 0;
                Integer sellSum = 0;
                List<MarketOrderDTO> dtoList = marketOrderCache.getPrice(typeId);
                if (dtoList != null && !dtoList.isEmpty()) {
                    for (MarketOrderDTO dto : dtoList) {
                        if (dto.getIsBuyOrder().equalsIgnoreCase("true")) {
                            if (rc.getBuyPrice() == null) {
                                rc.setBuyOrderId(String.valueOf(dto.getOrderId()));
                                rc.setBuyPrice(dto.getPrice());
                                rc.setBuyTotal(dto.getVolumeTotal());
                            } else if (dto.getPrice().compareTo(rc.getBuyPrice()) > 0) {
                                rc.setBuyOrderId(String.valueOf(dto.getOrderId()));
                                rc.setBuyPrice(dto.getPrice());
                                rc.setBuyTotal(dto.getVolumeTotal());
                            }
                        } else {
                            if (rc.getSellPrice() == null) {
                                rc.setSellOrderId(String.valueOf(dto.getOrderId()));
                                rc.setSellPrice(dto.getPrice());
                                rc.setSellTotal(dto.getVolumeTotal());
                            } else if (dto.getPrice().compareTo(rc.getSellPrice()) < 0) {
                                rc.setSellOrderId(String.valueOf(dto.getOrderId()));
                                rc.setSellPrice(dto.getPrice());
                                rc.setSellTotal(dto.getVolumeTotal());
                            }
                            sellCnt++;
                            sellSum = sellSum + dto.getVolumeTotal();
                        }
                    }
                }
                rc.setSellOrderSum(sellCnt);
                rc.setSellOrderTotal(sellSum);
                eveMarketorderRcMapper.insert(rc);
            }
        }
    }
}
