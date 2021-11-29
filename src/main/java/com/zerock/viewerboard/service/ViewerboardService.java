package com.zerock.viewerboard.service;

import com.zerock.viewerboard.dto.PageRequestDTO;
import com.zerock.viewerboard.dto.ViewerboardDTO;
import com.zerock.viewerboard.entity.Viewerboard;

import java.util.List;

public interface ViewerboardService {

    //게시글 등록 작업
    Long register(ViewerboardDTO dto);

    //목록 데이터 가져오기


    //특정 게시글 가져오는 작업

    //특정 게시글 수정 작업

    //특정 게시글 삭제 작업

    //dto-> entity로 변환
    default Viewerboard dtoToEntity(ViewerboardDTO dto){

        Viewerboard viewerboard = Viewerboard.builder()
                .sno(dto.getSno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .cnum(dto.getCnum())
                .build();
        return viewerboard;
    }

    //entity -> dto로 변환

}
