package com.wei.mqttservice.controller;

import com.wei.mqttservice.util.MqttPushClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author bam
 * 2020年3月5日
 * TestController.java
 *
 */
@RestController
@RequestMapping("/")
public class MqttController {

    @Autowired
    private MqttPushClient mqttPushClient;

    @GetMapping(value = "/publishTopic")
    public String publishTopic() {
        String topicString = "test";
        mqttPushClient.publish(0, false, topicString, "测试一下发布消息");
        return "ok";
    }
    // 发送自定义消息内容（使用默认主题）
    @RequestMapping("/publishTopic/{data}")
    public String test1(@PathVariable("data") String data) {
        String topicString = "test";
        mqttPushClient.publish(0,false,topicString, data);
        return "ok";
    }

    // 发送自定义消息内容，且指定主题
    @RequestMapping("/publishTopic/{topic}/{data}")
    public String test2(@PathVariable("topic") String topic, @PathVariable("data") String data) {
        mqttPushClient.publish(0,false,topic, data);
        return "ok";
    }
}
