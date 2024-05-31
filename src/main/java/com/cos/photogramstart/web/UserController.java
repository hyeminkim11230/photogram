package com.cos.photogramstart.web;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class UserController {


private final UserService userService;
    @GetMapping("/user/{id}")
    public String profile(@PathVariable int id, Model model) {
        User userEntity = userService.회원프로필(id);
        model.addAttribute("user", userEntity);
        return "user/profile";
    }
//    @GetMapping("/user/profile")
//    public String profile(){
//        return "user/profile";
//    }

    @GetMapping("/user/update")
    public String update(){
//        @PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails
        //1)추천
//        System.out.println("세션 정보:"+principalDetails.getUser());

        //2)비추
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        PrincipalDetails mPrincipalDetails = (PrincipalDetails)auth.getPrincipal();
        System.out.println("직접 찾은 세션 정보:" + mPrincipalDetails.getUser());
        //인증된 사용자 :principal
//        model.addAttribute("principal",principalDetails.getUser());
        return "user/update";
    }

    @GetMapping("/user/upload")
    public String upload(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails){
        return "user/upload";
    }
}