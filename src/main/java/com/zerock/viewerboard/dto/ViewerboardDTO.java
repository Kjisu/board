package com.zerock.viewerboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ViewerboardDTO {

    //게시글 번호,제목,내용,작성자,등록일자,수정일자,조회수

    private Long sno;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime regDate, modDate;
    private Long cnum;
}
