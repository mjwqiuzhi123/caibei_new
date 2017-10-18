package com.borrow.supermarket.mq;

import java.io.PrintStream;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.BindingBuilder.DestinationConfigurer;
import org.springframework.amqp.core.BindingBuilder.DirectExchangeRoutingKeyConfigurer;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;

public class MessageProducer
{
	  public static void sendMsg()
			    throws InterruptedException
			  {
			    CachingConnectionFactory cf = new CachingConnectionFactory("127.0.0.1");
			    cf.setUsername("admin");
			    cf.setPassword("123456");
			    Connection connection = cf.createConnection();

			    RabbitAdmin admin = new RabbitAdmin(cf);
			    Queue queue = new Queue("smsquence");
			    admin.declareQueue(queue);
			    DirectExchange exchange = new DirectExchange("cbcdExchange");
			    admin.declareExchange(exchange);
			    admin.declareBinding(BindingBuilder.bind(queue).to(exchange).with("sms_key"));

			    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(cf);

			    Object listener = new Object() {
			      public void handleMessage(String foo) {
			        System.out.println("发送的消息是:" + foo);
			      }
			    };
			    MessageListenerAdapter adapter = new MessageListenerAdapter(listener);
			    container.setMessageListener(adapter);
			    container.setQueueNames(new String[] { "smsquence" });
			    container.start();

			    RabbitTemplate template = new RabbitTemplate(cf);
			    template.convertAndSend("cbcdExchange", "sms_key", "Hello, world!");
			    Thread.sleep(1000L);
			    container.stop();
			  }
}