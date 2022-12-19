package com.library.controller;

import com.library.service.ReaderTraceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ReaderTraceController {

    @Autowired
    private ReaderTraceService readerTraceService;

    @RequestMapping("/get_all_traces.html")
    public ModelAndView lendList() {
        ModelAndView modelAndView = new ModelAndView("admin_reader_trace");
        modelAndView.addObject("trace_list", readerTraceService.getAllTraces());
        return modelAndView;
    }
}
