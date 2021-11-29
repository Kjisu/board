package com.zerock.viewerboard.service;

import com.zerock.viewerboard.dto.ViewerboardDTO;
import com.zerock.viewerboard.entity.Viewerboard;
import com.zerock.viewerboard.repository.ViewerboardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ViewerboardServiceImpl implements ViewerboardService {

    //디비 작업 담당하는 객체 가져오기
    private final ViewerboardRepository repository;

    //////////////////////////////////////////////////////////////////// 게시글 등록
    @Override
    public Long register(ViewerboardDTO dto) {
        //엔티티로 변환
        Viewerboard viewerboard = dtoToEntity(dto);
        //디비에 저장
        repository.save(viewerboard);
        //등록된 게시글 번호 리턴
        return viewerboard.getSno();
    }

    ////////////////////////////////////////////////////////////////////////목록 데이터 가져오기



}
