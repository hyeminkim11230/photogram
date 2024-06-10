package com.cos.photogramstart.domain.image;

import com.cos.photogramstart.domain.subscribe.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {

    @Query(value = "SELECT * FROM image WHERE userId IN (SELECT toUserId FROM subscribe WHERE fromWuserId = :principalled);",nativeQuery = true)
    List<Image> mStory(int principalled);
}
