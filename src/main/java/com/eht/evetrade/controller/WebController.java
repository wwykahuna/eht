package com.eht.evetrade.controller;

import com.eht.evetrade.service.MarketOrderService;
import com.eht.evetrade.vo.TradeItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;
import java.util.List;

@Controller
public class WebController {

    @Autowired
    private MarketOrderService marketOrderService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/table")
    public ModelAndView table() {
        ModelAndView modelAndView = new ModelAndView("table");
        List<TradeItemVO> list = marketOrderService.getTradeList();
        modelAndView.addObject("tradeList", list);
        return modelAndView;
    }
}
