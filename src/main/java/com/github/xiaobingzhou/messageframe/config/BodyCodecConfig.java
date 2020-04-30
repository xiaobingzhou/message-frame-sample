package com.github.xiaobingzhou.messageframe.config;

import com.github.xiaobingzhou.messageframe.codec.BodyCodec;
import com.github.xiaobingzhou.messageframe.codec.BodyCodecBuilder;
import com.github.xiaobingzhou.messageframe.mapper.MapperField;
import com.github.xiaobingzhou.messageframe.mapper.MapperFieldEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 解码器配置类
 */
@Configuration
public class BodyCodecConfig {

    private MapperField date = MapperField.builder()
            .length(12)// 该字段截取的长度
            .name("date")// 设置字段名
            .postHandler(MapperFieldEnum.datetime.getPostHandler())// 字段的后置处理器
            .build();
    private MapperField type = MapperField.builder()
            .length(2)
            .name("type")
            .postHandler(MapperFieldEnum.original.getPostHandler()).build();
    private MapperField other = MapperField.builder()
            .length(0)// 可变长度字段，长度设置为0，表示截取剩余的部分
            .name("other")
            .postHandler(MapperFieldEnum.original.getPostHandler()).build();


    @Bean
    public BodyCodec bodyCodec() {// 自定义bodyCodec解码器
        // 解码前：14041D08383B00FFFFFFFFFFFFFFFF , 解码后：{"date":1588121819000,"type":"00","other":"FFFFFFFFFFFFFFFF"}
        // 解码前：14041D08383B00FFFF , 解码后：{"date":1588121819000,"type":"00","other":"FFFF"}
        return BodyCodecBuilder.build()
                .commandCode("1111")// 这个解码器支持的指令码
                .nextField(date)// 字段有前后顺序关系
                .nextField(type)
                .nextField(other);
    }
}
