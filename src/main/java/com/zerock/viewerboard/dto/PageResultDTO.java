package com.zerock.viewerboard.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResultDTO<DTO,EN> {

    //페이지 처리결과를 처리하고 페이징 데이터 저장하는 객체
    //생성자를 통해 쿼리 결과값(Page<Entity>)을 dto타입의 List에 저장시키고 페이징 설정 정보를 저장한다


    private List<DTO> dtoList;

    private int totalPage;//총 페이지 번호
    private int page;//현재 페이지 번호
    private int size;//현재 목록 사이즈
    private int start,end;//시작페이지 번호, 끝 페이지 번호
    private boolean prev, next;//이전, 다음 여부
    private List<Integer> pageList;//페이지 번호 목록


    //생성자를 통해 2가지의 초기화 작업을 진행
    //이 객체는 생성하면서 쿼리 결과값(Page<Entity>)과 쿼리 결과를 dto로 변환시켜줄 기능을 가진 Function객체를 매개변수로 받는다
    public PageResultDTO(Page<EN> result, Function<EN,DTO> fn) {

        //쿼리 리턴값(entity) -> dto타입으로 변환 후 자료구조(List)에 저장
        dtoList = result.stream().map(fn).collect(Collectors.toList());

        //총 페이지 번호
        this.totalPage = result.getTotalPages();
        //페이징 설정 정보 저장
        makePageList(result.getPageable());

    }

    //페이징 설정 정보(결과값의 Pageable객체에 有)를 멤버변수에 저장
    private void makePageList(Pageable pageable){
        this.page = pageable.getPageNumber()+1;//JPA는 0부터 시작하니까
        this.size = pageable.getPageSize();
        int tempEnd = (int)Math.ceil(page/10.0)*10;//페이지당 10개의 게시글을 보여주기에 10을 곱함 (변동가능)
        this.start = tempEnd-9; //10개니까 -9
        this.end = tempEnd>totalPage? totalPage:tempEnd;
        this.prev = start>1;
        this.next = totalPage>tempEnd;
        this.pageList = IntStream.rangeClosed(start,end).boxed().collect(Collectors.toList());

    }


}
