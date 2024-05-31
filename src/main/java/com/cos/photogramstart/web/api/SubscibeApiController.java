package com.cos.photogramstart.web.api;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.service.SubscribeSeivice;
import com.cos.photogramstart.web.dto.auth.CMRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor
@RestController
public class SubscibeApiController {

    private final SubscribeSeivice subscribeService;
    @PostMapping("/api/subscibe/{toUserId}")
    public ResponseEntity<?> subscribe(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable int toUserId){
//        int result = subscribeService.구독하기(principalDetails.getUser().getId(),toUserId);
        return new ResponseEntity<>(new CMRespDto<>(1,"구독성공",null), HttpStatus.OK);

    }

    @DeleteMapping("/api/subscribe/{fromUserId}")
    public ResponseEntity<?> unSubscribe(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable int toUserId){
        return new ResponseEntity<>(new CMRespDto<>(1,"구독성공",null), HttpStatus.OK);
    }
}
