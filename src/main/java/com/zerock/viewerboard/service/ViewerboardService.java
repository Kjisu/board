package com.zerock.viewerboard.service;

import com.zerock.viewerboard.dto.PageRequestDTO;
import com.zerock.viewerboard.dto.PageResultDTO;
import com.zerock.viewerboard.dto.ViewerboardDTO;
import com.zerock.viewerboard.entity.Viewerboard;

import java.util.List;

public interface ViewerboardService {

    //게시글 등록 작업
    Long register(ViewerboardDTO dto);

    //목록 데이터 가져오기
    PageResultDTO<ViewerboardDTO,Viewerboard> getList(PageRequestDTO dto);

    //특정 게시글 가져오는 작업
    ViewerboardDTO read(Long sno,PageRequestDTO pageRequestDTO);

    //특정 게시글 수정 작업
    void modify(ViewerboardDTO viewerboardDTO);

    //특정 게시글 삭제 작업
    void remove(Long sno);

    //dto-> entity로 변환
    //default 메서드(Java8~) 사용 : 해당 인터페이스를 구현하는 객체들마다 이 메서드를 오버라이드 하지 않아도 되게 해주는 !!
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
    default ViewerboardDTO entityToDTO(Viewerboard entity){

        ViewerboardDTO viewerboardDTO = ViewerboardDTO.builder()
                .sno(entity.getSno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .cnum(entity.getCnum())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();

        return viewerboardDTO;
    }
}
