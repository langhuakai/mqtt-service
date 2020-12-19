package com.wei.mqttservice.config;

import com.wei.mqttservice.util.MqttPushClient;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Classname MqttConfig
 * @Description mqtt相关配置信息
 * @Date 2020/3/5 11:00
 * @Created by bam
 */
@Component
@Data
public class MqttConfig {
    @Autowired
    private MqttPushClient mqttPushClient;

    /**
     * 用户名
     */
    @Value("${mqtt.username}")
    private String username;
    /**
     * 密码
     */
    @Value("${mqtt.password}")
    private String password;
    /**
     * 连接地址
     */
    @Value("${mqtt.hostUrl}")
    private String hostUrl;
    /**
     * 客户Id
     */
    @Value("${mqtt.clientID}")
    private String clientID;
    /**
     * 默认连接话题
     */
    @Value("${mqtt.defaultTopic}")
    private String defaultTopic;
    /**
     * 超时时间
     */
    @Value("${mqtt.timeout}")
    private int timeout;
    /**
     * 保持连接数
     */
    @Value("${mqtt.keepalive}")
    private int keepalive;

    @Bean
    public MqttPushClient getMqttPushClient() {
        System.out.println("hostUrl: "+ hostUrl);
        System.out.println("clientID: "+ clientID);
        System.out.println("username: "+ username);
        System.out.println("password: "+ password);
        System.out.println("timeout: "+timeout);
        System.out.println("keepalive: "+ keepalive);
        mqttPushClient.connect(hostUrl, clientID, username, password, timeout, keepalive);
        // 以/#结尾表示订阅所有以test开头的主题
        mqttPushClient.subscribe(defaultTopic, 0);
        return mqttPushClient;
    }
}
