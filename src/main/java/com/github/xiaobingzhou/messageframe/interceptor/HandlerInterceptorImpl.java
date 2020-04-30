package com.github.xiaobingzhou.messageframe.interceptor;

import com.github.xiaobingzhou.messageframe.interceptor.HandlerInterceptor;
import com.github.xiaobingzhou.messageframe.request.HandlerRequest;
import org.springframework.stereotype.Component;

@Component
public class HandlerInterceptorImpl implements HandlerInterceptor {

    @Override
    public void preHandle(HandlerRequest request) {
        System.out.println(this.getClass() + " preHandle");
    }
    @Override
    public void postHandle(HandlerRequest request) {
        System.out.println(this.getClass() + " postHandle");
    }

}
