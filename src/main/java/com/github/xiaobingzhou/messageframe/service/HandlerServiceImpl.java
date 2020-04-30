package com.github.xiaobingzhou.messageframe.service;

import com.alibaba.fastjson.JSONObject;
import com.github.xiaobingzhou.messageframe.annotation.CommandCode;
import com.github.xiaobingzhou.messageframe.annotation.Handler;
import com.github.xiaobingzhou.messageframe.bean.MessageFrame;
import com.github.xiaobingzhou.messageframe.request.HandlerRequest;
import org.springframework.stereotype.Service;

@Service
@Handler// 标注这是一个处理器类
public class HandlerServiceImpl {

    /**
     * 处理方法
     * 默认支持参数绑定器 com.github.xiaobingzhou.messageframe.bind.impl*
     * 自定义参数绑定器需要实现 com.github.xiaobingzhou.messageframe.bind.BindParam 并将其添加到spring容器中
     *
     * 参数bodyJson需要BodyCodec解码器才能正常工作，如果指令码1111没有解码器则bodyJson为null
     * 需要自定义实现BodyCodec解码器并添加到spring容器中
     * @param request 请求
     * @param bodyJson body解码后数据
     * @param custome 自定义字段 使用自定义参数绑定器 BindParamConfig#customeBindParam()
     * @see com.github.xiaobingzhou.messageframe.enums.ParameterNameEnum
     * @see com.github.xiaobingzhou.messageframe.bind.impl*
     * @see com.github.xiaobingzhou.messageframe.bind.BindParam
     * @see com.github.xiaobingzhou.messageframe.codec.BodyCodec
     * @see com.github.xiaobingzhou.messageframe.codec.BodyCodecBuilder
     */
    @CommandCode({"1111", "3333"})// 标注这个方法处理指令码：1111
    public void handle(HandlerRequest request, JSONObject bodyJson, String custome, MessageFrame messageFrame) {
        // 处理指令
        System.out.println(request);
    }

    /**
     * 处理方法
     * @param request 请求
     */
    @CommandCode({"2222"})// 标注这个方法处理指令码：2222
    public void heartBeat(HandlerRequest request) {
        // 处理指令
        System.out.println(request);
    }

}
