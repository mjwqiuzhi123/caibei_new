package com.borrow.supermarket.mq;

import java.io.PrintStream;

public class MessageConsumer
{
	  public void listen(String foo)
	  {
	    System.out.println("监听到的信息是:" + foo);
	  }
}