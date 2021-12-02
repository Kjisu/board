package com.zerock.viewerboard.repository;

import com.zerock.viewerboard.entity.Viewerboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface ViewerboardRepository extends JpaRepository<Viewerboard,Long> {

    //조회수 증가
    @Query("update Viewerboard v set v.cnum = v.cnum+1 where v.sno = :sno")
    @Modifying //조회 쿼리를 제외하곤 변경,삭제가 발생하는 쿼리에는 다 붙여줘야 함
    void addCount(Long sno);
}
