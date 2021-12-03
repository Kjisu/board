package com.zerock.viewerboard.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.zerock.viewerboard.dto.PageRequestDTO;
import com.zerock.viewerboard.dto.PageResultDTO;
import com.zerock.viewerboard.dto.ViewerboardDTO;
import com.zerock.viewerboard.entity.QViewerboard;
import com.zerock.viewerboard.entity.Viewerboard;
import com.zerock.viewerboard.repository.ViewerboardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.function.Function;


@Service
@RequiredArgsConstructor
@Log4j2
public class ViewerboardServiceImpl implements ViewerboardService {

    //디비 작업 담당하는 객체 가져오기
    private final ViewerboardRepository repository;

    //////////////////////////////////////////////////////////////////// 게시글 등록
    @Override
    public Long register(ViewerboardDTO dto) {

        log.info("[Service] register()... called ");
        //조회수 0으로 설정
        dto.setCnum(0L);
        //엔티티로 변환
        Viewerboard viewerboard = dtoToEntity(dto);
        //디비에 저장
        repository.save(viewerboard);
        //등록된 게시글 번호 리턴
        return viewerboard.getSno();
    }

    ////////////////////////////////////////////////////////////////////////목록 데이터 가져오기(검색조건 없을 때)
 /*   @Override
    public PageResultDTO<ViewerboardDTO,Viewerboard> getList(PageRequestDTO dto) {

        log.info("[Service] getList() ...called ");

        //Pageable 객체 생성
        Pageable pageable = dto.getPageable(Sort.by("sno").descending());

        //디비 작업 호출
        Page<Viewerboard> result = repository.findAll(pageable);

        //entity -> dto로의 변환기능을 Function객체에 담기
        Function<Viewerboard,ViewerboardDTO> fn = (entity -> entityToDTO(entity));//람다식!!

        return new PageResultDTO<>(result,fn);
    }*/

    ////////////////////////////////////////////////////////////////////////목록 데이터 가져오기(검색조건 있을 때-querydsl사용)
    @Override
    public PageResultDTO<ViewerboardDTO,Viewerboard> getList(PageRequestDTO dto) {

        log.info("[Service] getList() ...called ");

        //Pageable 객체 생성
        Pageable pageable = dto.getPageable(Sort.by("sno").descending());

        //검색 조건 객체 가져오기
        BooleanBuilder booleanBuilder = getSearch(dto);

        //QuerydslPredicateExecutor 인터페이스의 findAll()를 호출함으로써 쿼리 실행
        Page<Viewerboard> result = repository.findAll(booleanBuilder, pageable);

        //entity -> dto로의 변환기능을 Function객체에 담기
        Function<Viewerboard,ViewerboardDTO> fn = (entity -> entityToDTO(entity));//람다식!!

        return new PageResultDTO<>(result,fn);
    }


    ////////////////////////////////////////////////////////////////////// 조회 데이터 가져오기
    @Override
    @Transactional
    public ViewerboardDTO read(Long sno,PageRequestDTO pageRequestDTO) {
        log.info("[S] read()...");
        repository.addCount(sno);//조회수 증가
        Optional<Viewerboard> result = repository.findById(sno);
        return result.isPresent()? entityToDTO(result.get()):null;
    }

    /////////////////////////////////////////////////////////////////////////////// 게시글 수정 작업
    @Override
    public void modify(ViewerboardDTO viewerboardDTO) {
        log.info("[S] modify()...");

        //수정할 엔티티 꺼내기
        Optional<Viewerboard> result = repository.findById(viewerboardDTO.getSno());

        if(result.isPresent()){
            Viewerboard viewerboard = result.get();
            viewerboard.changeTitle(viewerboardDTO.getTitle());
            viewerboard.changeContent(viewerboardDTO.getContent());
            //수정 쿼리 실행
            repository.save(viewerboard);
        }
    }

    //////////////////////////////////////////////////////////////////////////////// 삭제 작업
    @Override
    public void remove(Long sno) {
        log.info("[S] remove()...");
        repository.deleteById(sno);
    }


    ///////////////////////////////////////////////////////////////////////////////// 검색 조건 객체 생성
    private BooleanBuilder getSearch(PageRequestDTO dto){

        String type = dto.getType();
        String keyword = dto.getKeyword();

        QViewerboard qViewerboard = QViewerboard.viewerboard;
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        //조건문1
        BooleanExpression ex1 = qViewerboard.sno.gt(0L);
        booleanBuilder.and(ex1);

        //상세 검색 조건(type)이 있는지 확인
        if(type == null || type.trim().length() == 0){
            return booleanBuilder;
        }

        //상세 검색 조건이 있는 경우,
        BooleanBuilder conditionBuilder = new BooleanBuilder();
        if(type.contains("t")){
            conditionBuilder.or(qViewerboard.title.contains(keyword));
        }
        if(type.contains("c")){
            conditionBuilder.or(qViewerboard.content.contains(keyword));
        }
        if(type.contains("w")){
            conditionBuilder.or(qViewerboard.writer.contains(keyword));
        }

        booleanBuilder.and(conditionBuilder);

        return booleanBuilder;

    }


}
