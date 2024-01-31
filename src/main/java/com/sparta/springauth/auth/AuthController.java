package com.sparta.springauth.auth;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/api")
public class AuthController {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    @GetMapping("/create-cookie")
    public String createCookie(HttpServletResponse res) {
        addCookie("Robbie Auth", res);

        return "createCookie";
    }

    @GetMapping("/get-cookie")
    public String getCookie(@CookieValue(AUTHORIZATION_HEADER) String value) { //쿠키를 가져오는 방법
        //매핑된 메서드의 매개변수에 @CookieValue 어노테이션을 이용하여 변수를가져옴
        //@CookieValue(쿠키이름) String 변수명 -> 을 이용하여 해당 매개변수에 자동으로 해당 쿠키의 값을 넣어줌
        System.out.println("value = " + value);

        return "getCookie : " + value;
    }

    public static void addCookie(String cookieValue, HttpServletResponse res) {
        try {
            cookieValue = URLEncoder.encode(cookieValue, "utf-8").replaceAll("\\+", "%20"); // Cookie Value 에는 공백이 불가능해서 encoding 진행
            //URLEncoder.encode(쿠키값, "포멧").replaceAll("\\+", "%20"); 를 이용하여 공백 제거

            Cookie cookie = new Cookie(AUTHORIZATION_HEADER, cookieValue); // Name-Value
            cookie.setPath("/");//경로 설정
            cookie.setMaxAge(30 * 60);//Expires 만료기한 설정

            // Response 객체에 Cookie 추가
            res.addCookie(cookie);// HttpServletResponse 객체에 쿠키를 담음
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/create-session")
    public String createSession(HttpServletRequest req) {
        // 세션이 존재할 경우 세션 반환, 없을 경우 새로운 세션을 생성한 후 반환
        HttpSession session = req.getSession(true);// getSession 이 존재하면 세션 가져옴 없다면 새로운 세션 생성하여 반환

        // 세션에 저장될 정보 Name - Value 를 추가합니다.
        session.setAttribute(AUTHORIZATION_HEADER, "Robbie Auth");

        return "createSession";
    }

    @GetMapping("/get-session")
    public String getSession(HttpServletRequest req) {
        // 세션이 존재할 경우 세션 반환, 없을 경우 null 반환
        HttpSession session = req.getSession(false); // 세션이 존재 하면 반환 없으면 널을 반환

        String value = (String) session.getAttribute(AUTHORIZATION_HEADER); // 가져온 세션에 저장된 Value 를 Name 을 사용하여 가져옵니다. Object타입으로 반환 따라서 캐스팅 필요
        System.out.println("value = " + value);

        return "getSession : " + value;
    }
}