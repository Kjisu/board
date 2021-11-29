package com.zerock.viewerboard.repository;

import com.zerock.viewerboard.entity.Viewerboard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
public class ViewerboardRepositoryTests {

    @Autowired
    private ViewerboardRepository repository;

    //데이터 넣기
    @Test
    public void insertDummies(){

        for(int i=1;i<=100;i++){
            Viewerboard viewerboard = Viewerboard.builder()
                    .title("제목..." + i)
                    .content("내용..." + i)
                    .writer("user" + i)
                    .build();

            repository.save(viewerboard);
        }

    }

    //데이터 수정
    @Test
    public void updateTest(){
        Optional<Viewerboard> result = repository.findById(100L);
        if(result.isPresent()){
            Viewerboard viewerboard = result.get();
            viewerboard.changeTitle("----제목수정");
            viewerboard.changeContent("내용수정");
            repository.save(viewerboard);
            System.out.println("수정완료");
        }else{
            System.out.println("데이터 없음 !! ");
        }
    }

    //데이터 삭제
    @Test
    public void removeTest(){
        repository.deleteById(100L);
        System.out.println("삭제 완료 ! ");
    }

}
