package com.zerock.viewerboard.controller;

import com.zerock.viewerboard.dto.PageRequestDTO;
import com.zerock.viewerboard.dto.PageResultDTO;
import com.zerock.viewerboard.dto.ViewerboardDTO;
import com.zerock.viewerboard.entity.Viewerboard;
import com.zerock.viewerboard.service.ViewerboardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/viewerboard/")
@RequiredArgsConstructor
@Log4j2
public class ViewerboardController {

    private final ViewerboardService service;

    //게시글 목록 요청
    @GetMapping("/list")
    public void getList(@ModelAttribute PageRequestDTO pageRequestDTO, Model model){

        log.info("[C] getList()....");

        //서비스 호출
        PageResultDTO<ViewerboardDTO, Viewerboard> result = service.getList(pageRequestDTO);
        //결과값 model에 저장
        model.addAttribute("result",result);
    }

    //등록 페이지 요청
    @GetMapping("/register")
    public void getRegister(){

        log.info("[C] getRegister()...go to register.html");
    }

    //게시글 등록 처리 요청
    @PostMapping("/register")
    public String postRegister(@ModelAttribute ViewerboardDTO viewerboardDTO, RedirectAttributes redirectAttributes){
        log.info("[C] postRegister()...");
        Long sno = service.register(viewerboardDTO);
        redirectAttributes.addFlashAttribute("msg",sno);
        return "redirect:/viewerboard/list";
    }

    //게시글 조회,수정 페이지 요청
    @GetMapping({"/read","/modify"})
    public void read(@RequestParam Long sno,@ModelAttribute PageRequestDTO pageRequestDTO,Model model){
        log.info("[C] read()...");
        ViewerboardDTO result = service.read(sno, pageRequestDTO);
        model.addAttribute("dto",result);
    }

    //수정 작업 요청(POST)
    @PostMapping("/modify")
    public String postModify(@ModelAttribute ViewerboardDTO viewerboardDTO,@ModelAttribute PageRequestDTO pageRequestDTO,RedirectAttributes redirectAttributes){
        log.info("[C] postModify()...");
        service.modify(viewerboardDTO);
        redirectAttributes.addAttribute("sno",viewerboardDTO.getSno());
        redirectAttributes.addAttribute("page",pageRequestDTO.getPage());
        redirectAttributes.addAttribute("type",pageRequestDTO.getType());
        redirectAttributes.addAttribute("keyword",pageRequestDTO.getKeyword());
        return "redirect:/viewerboard/read";
    }

    //삭제 작업 요청(post) _ 삭제 완료 후 리스트 페이지로 이동
    @PostMapping("/remove")
    public String remove(Long sno,RedirectAttributes redirectAttributes){
        log.info("[S] remove()...");
        service.remove(sno);
        redirectAttributes.addFlashAttribute("delMsg",sno);
        return "redirect:/viewerboard/list";
    }





}
