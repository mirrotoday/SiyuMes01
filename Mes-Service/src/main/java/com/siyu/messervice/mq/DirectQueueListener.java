package com.siyu.messervice.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Duration;

/**
 * @className: DirectQueueListener
 * @description: 直连交换机的监听器
 * @date: 2021-08-23 16:03
 * @author SiYu
 */
@Slf4j
@Component
public class DirectQueueListener {
    @Resource
    private RedisTemplate redisTemplate;

    //3. 自动创建队列，Exchange 与 Queue绑定
    @RabbitListener(bindings = @QueueBinding(value = @Queue("myQueue"),exchange = @Exchange("TestExChanges")))
    @RabbitHandler
    public void processOne(String message)  throws InterruptedException{
        log.info("MqReceiver消费者1: {}", message+",被消费");
        Thread.sleep(4000);
        redisTemplate.opsForValue().set("MqReceiver消费者1",message);
        redisTemplate.expire("MqReceiver消费者1", Duration.ofHours(1));

    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue("myQueue2"),exchange = @Exchange("my-fanoutRoute")))
    @RabbitHandler
    public void processTwo(String message) {
        log.info("MqReceiver消费者2: {}", message+",被消费");
        redisTemplate.opsForValue().set("MqReceiver消费者2",message);
        redisTemplate.expire("MqReceiver消费者2", Duration.ofHours(1));
    }
    @RabbitListener(bindings = @QueueBinding(value = @Queue("myQueue3"),exchange = @Exchange("my-fanoutRoute")))
    @RabbitHandler
    public void processThree(String message){
        log.info("MqReceiver消费者3: {}", message+",被消费");
        redisTemplate.opsForValue().set("MqReceiver消费者3",message);
        redisTemplate.expire("MqReceiver消费者3", Duration.ofHours(1));
    }



}
