package com.cos.photogramstart.web;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor //final이 걸려있는 모든 생성자를 만들어줌 DI할때 사용
@Controller //1.IoC 2.파일을 리턴하는 컨트롤러
public class AuthController {

    private static final Logger log = (Logger) LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;

    // @RequiredArgsConstructor를 대신 넣어서 주석처리
//    public AuthController(AuthService authService){
//        this.authService=authService;  //의존성 주입
//    }

    @GetMapping("/auth/signin")
    public String siginForm() {
        return"auth/signin";
    }

    @GetMapping("/auth/signup")
    public String signupForm() {
        return"auth/signup";
    }

    //회원가입버튼 -> /auth/signup ->/auth/signin
    @PostMapping("/auth/signup")
    public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) {//key=value(x-www-form-urlencoded)
//        if(signupDto.getUsername().length()>20){
//            System.out.println("너 길익 초과했어"); //validation 체크
//        }
        if(bindingResult.hasGlobalErrors()) {
            Map<String,String> errorMap =new HashMap<>();

            for(FieldError error:bindingResult.getFieldErrors()){
                errorMap.put(error.getField(),error.getDefaultMessage());
                System.out.println("======================================");
                System.out.println(error.getDefaultMessage());
                System.out.println("======================================");
            }
            throw new CustomValidationException("유효성검사 실패",errorMap);
        }
        //User <- SignupDto
        User user = signupDto.toEntity();
//        User userEntity = authService.signup(user);
//        System.out.println(userEntity);
//        log.info(user.toString());
        return"auth/signup";
    }
}
