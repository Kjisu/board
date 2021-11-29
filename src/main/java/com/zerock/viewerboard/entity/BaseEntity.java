package com.zerock.viewerboard.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@EntityListeners(value={AuditingEntityListener.class}) //AuditingEntityListener: 엔티티 객체가 생성,변경되는 것을 감지하는 역할, 반드시 Application클래스에 @EnableJpaAuditing을 추가함으로써 활성화 시켜줘야 한다
abstract class BaseEntity {

    //등록일자, 수정일자 컬럼

    @CreatedDate
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "moddate")
    private LocalDateTime modDate;

}
