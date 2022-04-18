package com.eht.evetrade.controller;

import com.eht.evetrade.dto.MarketOrderDTO;
import com.eht.evetrade.service.cache.MarketOrderCache;
import com.eht.evetrade.service.run.WorkerThreadManager;
import com.eht.evetrade.service.schedule.MoniOrderSche;
import com.eht.evetrade.service.schedule.SatelliteTrade;
import com.ejlchina.okhttps.HTTP;
import com.ejlchina.okhttps.HttpResult;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ESIController {

    @Autowired
    private SatelliteTrade satelliteTrade;

    @Autowired
    private WorkerThreadManager workerThreadManager;


    @Autowired
    private MarketOrderCache marketOrderCache;

    @Autowired
    private MoniOrderSche moniOrderSche;


    @RequestMapping(value = "/schedule")
    public String schedule() {
        satelliteTrade.schedule();
        return "0k";
    }

    @RequestMapping(value = "/schedule1")
    public String schedule1() {
        satelliteTrade.schedule1();
        return "0k";
    }

    @RequestMapping(value = "/test")
    public String test() {
        moniOrderSche.schedule();
        return "0k";
    }

}
