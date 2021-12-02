package com.zerock.viewerboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Data
@Builder
@AllArgsConstructor
public class PageRequestDTO {

    //역할
    //1. (뷰에서 넘어오는) 페이징 처리할 때 필요한 데이터(현재 페이지 번호, 한 페이지 당 출력할 게시글 갯수, 타입, 키워드) 저장
    //2. JPA에서 사용할 Pageable 객체 생성

    private int page;
    private int size;
    private String type;
    private String keyword;

    //처음에는 임의로 페이지 번호, 페이지 당 게시글 수 지정해야
    public PageRequestDTO() {
        this.page = 1;
        this.size = 10;
    }

    //Pageable 객체 생성 - jpa로 작업할 때 필요함
    public Pageable getPageable(Sort sort){
        return PageRequest.of(page-1,size,sort);
    }
}
