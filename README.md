# message-frame-sample
a message-frame sample

### 使用例子(最低版本1.6.0.RELEASE)
###### https://github.com/xiaobingzhou/message-frame

> 解析字符串body：14041D08383B00FFFF

> 解析完成后：{"date":1588121819000,"type":"00","other":"FFFF"}

`@Handler` 需要配合 `@CommandCode` 注解一起使用
```java
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
```

```java
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
     * @param request
     * @param bodyJson body解码后数据
     * @param custome 自定义字段 使用自定义参数绑定器 BindParamConfig#customeBindParam()
     * @see com.github.xiaobingzhou.messageframe.enums.ParameterNameEnum
     * @see com.github.xiaobingzhou.messageframe.bind.impl*
     * @see com.github.xiaobingzhou.messageframe.bind.BindParam
     * @see com.github.xiaobingzhou.messageframe.codec.BodyCodec
     * @see com.github.xiaobingzhou.messageframe.codec.BodyCodecBuilder
     */
    @CommandCode({"1111"})// 标注这个方法处理指令码：1111
    public void handle(HandlerRequest request, JSONObject bodyJson, String custome) {
        // 处理指令
        System.out.println(request);
    }

    /**
     * 处理方法
     * @param request
     */
    @CommandCode({"2222"})// 标注这个方法处理指令码：2222
    public void heartBeat(HandlerRequest request) {
        // 处理指令
        System.out.println(request);
    }

}
```

```java
/**
 * 解码器配置类
 */
@Configuration
public class BodyCodecConfig {

    MapperField date = MapperField.builder()
            .length(12)// 该字段截取的长度
            .name("date")// 设置字段名
            .postHandler(MapperFieldEnum.datetime.getPostHandler())// 字段的后置处理器
            .build();
    MapperField type = MapperField.builder()
            .length(2)
            .name("type")
            .postHandler(MapperFieldEnum.original.getPostHandler()).build();
    MapperField other = MapperField.builder()
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
```

```java
/**
 * 自定义参数绑定器
 */
@Configuration
public class BindParamConfig {

    @Bean
    public BindParam<String> customeBindParam() {
        // 自定义参数绑定器，绑定参数名为：custome 和参数类型为：String.class 的参数，参数设置的值为 this is my custome param
        return new BindParam<String>() {
            @Override
            public boolean support(String parameterName) {
                return "custome".equals(parameterName);
            }

            @Override
            public String bind(HandlerRequest request) {
                return "this is my custome param";
            }

        };
    }
}
```

