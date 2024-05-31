package com.cos.photogramstart.domain.image;

import com.cos.photogramstart.domain.subscribe.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {
}
