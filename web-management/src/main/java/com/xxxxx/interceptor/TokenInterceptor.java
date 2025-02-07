package com.xxxxx.interceptor;

import com.xxxxx.utils.CurrentHolder;
import com.xxxxx.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //在單元測試時放行
        String testToken = request.getHeader("Authorization");
        if(testToken != null && !testToken.isEmpty()){
            if (testToken.equals("vhtrg3t54t45yv54vyw54y54vy54")) {
                return true;
            }else{
                log.info("單元測試：憑證為空或不合法，回應401狀態碼");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
        }
        //獲取request header中的token
        String token = request.getHeader("token");
        //判斷token是否存在，如果不存在，說明用戶沒有登入，返回錯誤訊息(回應401狀態碼)
        if(token == null || token.isEmpty()){
            log.info("憑證為空，回應401狀態碼");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        //如果token存在，檢驗憑證。如果檢驗失敗，返回錯誤訊息(回應401狀態碼)
        try {
            Claims claims = JwtUtils.parseToken(token);
            Integer empId = Integer.valueOf(claims.get("id").toString());
            CurrentHolder.setCurrentId(empId); //將empId存入ThreadLocal
            log.info("當前登入員工ID：{}，將其存入ThreadLocal", empId);
        } catch (Exception e) {
            log.info("憑證非法，回應401狀態碼");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        //如果檢驗成功，放行
        log.info("憑證合法，放行");
        return true;
    }

    //刪除ThreadLocal中的資料
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        CurrentHolder.remove();
    }

}
