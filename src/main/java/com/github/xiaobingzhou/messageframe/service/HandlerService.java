package com.github.xiaobingzhou.messageframe.service;

import com.alibaba.fastjson.JSONObject;
import com.github.xiaobingzhou.messageframe.request.HandlerRequest;

public interface HandlerService {

    void handle(HandlerRequest request, JSONObject bodyJson, String custome);

    void heartBeat(HandlerRequest request);

}