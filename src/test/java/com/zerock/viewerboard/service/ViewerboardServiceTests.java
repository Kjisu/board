package com.zerock.viewerboard.service;

import com.zerock.viewerboard.dto.ViewerboardDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ViewerboardServiceTests {

    @Autowired
    private ViewerboardService service;

    //등록 서비스 테스트
    @Test
    public void registerTest(){
        //given
        ViewerboardDTO viewerboardDTO = ViewerboardDTO.builder()
                .title("제목...101")
                .content("내용...101")
                .writer("user101")
                .build();
        //when
        Long resultNum = service.register(viewerboardDTO);
        System.out.println("등록된 게시글의 번호 = "+resultNum);
    }
}
