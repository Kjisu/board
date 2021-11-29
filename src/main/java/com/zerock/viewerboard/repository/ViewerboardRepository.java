package com.zerock.viewerboard.repository;

import com.zerock.viewerboard.entity.Viewerboard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViewerboardRepository extends JpaRepository<Viewerboard,Long> {
}
