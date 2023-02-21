package annotation.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @className: DirectQueueListener
 * @description: 直连交换机的监听器
 * @author: sh.Liu
 * @date: 2021-08-23 16:03
 */
@Slf4j
@Component
public class DirectQueueListener {

    //3. 自动创建队列，Exchange 与 Queue绑定
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("myQueue"),
            exchange = @Exchange("TestExChanges1")
    ))
    @RabbitHandler
    public void process(String message){
        log.info("MqReceiver3: {}", message);
    }



}
