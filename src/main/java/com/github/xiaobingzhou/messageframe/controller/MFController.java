package com.github.xiaobingzhou.messageframe.controller;

import com.github.xiaobingzhou.messageframe.Dispatcher;
import com.github.xiaobingzhou.messageframe.annotation.EnableHandler;
import com.github.xiaobingzhou.messageframe.bean.MessageFrame;
import com.github.xiaobingzhou.messageframe.handler.HandlerException;
import com.github.xiaobingzhou.messageframe.request.HandlerRequest;
import com.github.xiaobingzhou.messageframe.request.HandlerRequestImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/mf")
@EnableHandler// 启用处理器功能
public class MFController {

    /**
     * 自动装配com.github.xiaobingzhou.messageframe.Dispatcher实现类
     * 默认是实现是com.github.xiaobingzhou.messageframe.DispatcherImpl
     * 通过配置类自动配置HandlerAutoConfiguration
     * @see com.github.xiaobingzhou.messageframe.HandlerAutoConfiguration
     * @see com.github.xiaobingzhou.messageframe.DispatcherImpl
     */
    @Autowired
    Dispatcher dispatcher;

    @PostMapping("/{deviceId}")
    public String dispatcher(@RequestBody MessageFrame messageFrame,
                             @PathVariable String deviceId) {
        HandlerRequest handlerRequest = HandlerRequestImpl
                .builder()
                .deviceId(deviceId)
                .messageFrame(messageFrame)
                .systemDate(new Date())
                .build();
        try {
            // 自动分发到到对应的处理器进行处理，底层使用反射方式调用处理方法
            dispatcher.dispatch(handlerRequest);
        } catch (HandlerException e) {
            e.printStackTrace();
        }
        return "ok";
    }

}
