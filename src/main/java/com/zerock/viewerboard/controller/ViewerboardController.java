package com.zerock.viewerboard.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/viewerboard/")
@Log4j2
public class ViewerboardController {

    //게시글 목록 요청
    @GetMapping("/list")
    public void getList(){
        log.info("[C] getList()....");

    }





}
