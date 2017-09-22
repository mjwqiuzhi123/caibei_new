package com.borrow.supermarket.mq;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageSend
{
	  private RabbitTemplate rabbitTemplate;

	  public void sendMsg(String directExchangeName, String jsonStr)
	  {
	    System.out.println("消息发送---------开始----------时间是:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	    this.rabbitTemplate.convertAndSend(directExchangeName, jsonStr);
	    System.out.println("消息发送---------结束----------时间是:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	  }
}