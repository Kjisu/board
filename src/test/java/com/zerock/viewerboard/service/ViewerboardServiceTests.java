package com.zerock.viewerboard.service;

import com.zerock.viewerboard.dto.PageRequestDTO;
import com.zerock.viewerboard.dto.PageResultDTO;
import com.zerock.viewerboard.dto.ViewerboardDTO;
import com.zerock.viewerboard.entity.Viewerboard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ViewerboardServiceTests {

    @Autowired
    private ViewerboardService service;

    //등록 메서드 테스트
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

    //목록 메서드 테스트
    @Test
    public void getListTest(){
        //given
        PageRequestDTO dto = new PageRequestDTO();
        //when
        PageResultDTO<ViewerboardDTO, Viewerboard> result = service.getList(dto);
        //then
        System.out.println("총 페이지 = "+result.getDtoList());
        System.out.println("페이지당 게시글 수 = "+result.getSize());
    }
}
