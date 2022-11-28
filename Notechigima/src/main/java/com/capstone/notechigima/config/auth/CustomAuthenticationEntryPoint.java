package com.capstone.notechigima.config.auth;

import com.capstone.notechigima.config.ExceptionCode;
import org.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        Integer code = (Integer) request.getAttribute("exception");

        if (code == null) {
            setResponse(response, ExceptionCode.ERROR_UNKNOWN);
        }
        else if (code == ExceptionCode.WRONG_TYPE_TOKEN.getCode()) {
            setResponse(response, ExceptionCode.WRONG_TYPE_TOKEN);
        }
        else if (code == ExceptionCode.EXPIRED_TOKEN.getCode()) {
            setResponse(response, ExceptionCode.EXPIRED_TOKEN);
        }
        else {
            setResponse(response, ExceptionCode.ERROR_AUTH_DENIED);
        }

    }

    private void setResponse(HttpServletResponse response, ExceptionCode exceptionCode) throws IOException {
        response.setContentType("application/json; charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        JSONObject responseJson = new JSONObject();
        responseJson.put("status", false);
        responseJson.put("code", exceptionCode.getResponseCode());
        responseJson.put("message", exceptionCode.getMessage());

        response.getWriter().print(responseJson);
    }
}
