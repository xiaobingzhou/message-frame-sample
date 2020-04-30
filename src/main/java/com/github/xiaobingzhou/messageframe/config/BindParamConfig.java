package com.github.xiaobingzhou.messageframe.config;

import com.github.xiaobingzhou.messageframe.bind.BindParam;
import com.github.xiaobingzhou.messageframe.request.HandlerRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BindParamConfig {

    @Bean
    public BindParam<String> myBindParam() {
        // 自定义参数绑定器，绑定参数名为：custome 和参数类型为：String.class 的参数，参数设置的值为 this is my custome param
        return new BindParam<String>() {
            @Override
            public boolean support(String parameterName, Class<String> parameterType) {
                return "custome".equals(parameterName) && String.class.isAssignableFrom(parameterType);
            }

            @Override
            public String bind(HandlerRequest request) {
                return "this is my custome param";
            }

        };
    }
}
