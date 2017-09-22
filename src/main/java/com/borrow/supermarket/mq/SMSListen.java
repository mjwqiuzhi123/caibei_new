package com.borrow.supermarket.mq;

import com.borrow.supermarket.response.result.SendMsgDTOResult;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

public class SMSListen
{
  private static final Logger log = Logger.getLogger(SMSListen.class);

  public void listen(String str) throws Exception {
    log.info("监听到短信消息的发送------------时间是:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "消息传过来参数是:" + str);
    JSONObject jsonobject = JSONObject.fromObject(str);
    SendMsgDTOResult msgAuthor = (SendMsgDTOResult)JSONObject.toBean(jsonobject, SendMsgDTOResult.class);
    System.out.println("监听到的信息是:========================" + msgAuthor.toJson());
  }
}