package com.borrow.supermarket.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class HttpUtil
{
	  private static final Logger log = Logger.getLogger(HttpUtil.class.getName());
	  private static Map<String, String> httpCfgParamMap = null;

	  public Map sendHttpRequest(String targetSys, String targetService, String paramsStr, String sendType)
	  {
	    if (null == httpCfgParamMap)
	    {
	      loadHttpCfgParamMap();
	    }
	    Map map = new HashMap();
	    String responseString = null;

	    String targetSysStr = (String)httpCfgParamMap.get(targetSys);
	    String targetServiceStr = (String)httpCfgParamMap.get(targetService);
	    String url = targetSysStr + targetServiceStr;

	    DefaultHttpRequestRetryHandler dhr = new DefaultHttpRequestRetryHandler(5, true);
	    HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
	    CloseableHttpClient httpClient = httpClientBuilder.setRetryHandler(dhr).build();

	    if ((null != sendType) && (sendType.equals("GET"))) {
    paramsStr = paramsStr.toString().replaceAll("\"", "%22").replaceAll("\\{", "%7b").replaceAll("\\}", "%7d");

    url = url + "?" + paramsStr;
    log.info("http服务接口 urlget:" + url);
    HttpGet httpget = new HttpGet(url);
    httpget.addHeader(new BasicHeader("", ""));
    log.info("http服务接口 httpgetURI:" + httpget.getURI());
    System.out.println("======================================" + httpget.getAllHeaders());
    CloseableHttpResponse httpResponse = null;
    try {
      httpResponse = httpClient.execute(httpget);
      StatusLine statusLine = httpResponse.getStatusLine();
      HttpEntity entity = httpResponse.getEntity();
      if (entity != null) {
        responseString = EntityUtils.toString(entity);
      }
      map.put("respStatus", Integer.valueOf(statusLine.getStatusCode()));
      map.put("respContent", responseString);
      log.info("http服务接口 statusLine_get:" + statusLine.toString());
      log.info("http服务接口 statusLineCode_get:" + statusLine.getStatusCode());
      log.debug("http服务接口 responseContent_get:" + responseString);
    }
    catch (ClientProtocolException e) {
      log.error("http服务接口 http连接异常！");
      e.printStackTrace();
    } catch (IOException e) {
      log.error("http服务接口 http连接发生网络异常！");
      e.printStackTrace();
    } finally {
      try {
        httpResponse.close();
      } catch (IOException e) {
        log.error("http服务接口 断开http连接异常！");
        e.printStackTrace();
      }
    }
  } else if ((null != sendType) && (sendType.equals("POST"))) {
    CloseableHttpResponse httpResponse = null;
    try {
      HttpPost httpPost = new HttpPost(url);
      System.out.println("httpPostURI111:" + httpPost.getURI());

      RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(900000).setConnectTimeout(900000).build();
      httpPost.setConfig(requestConfig);
      if (targetSys.equals("sms.server.host"))
        httpPost.addHeader("Content-Type", "application/json");
      else if (targetSys.equals("pay.gateway"))
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
      else if (targetSys.equals("pay.gateway")) {
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
      }

      String params_noEncode = paramsStr;
      log.info("http服务接口 params_noEncode:" + params_noEncode);
      Object httpEntity = new StringEntity(params_noEncode, "UTF-8");
      log.info("http服务接口 HttpEntityStr:" + EntityUtils.toString((HttpEntity)httpEntity));
      httpPost.setEntity((HttpEntity)httpEntity);

      httpResponse = httpClient.execute(httpPost);
      StatusLine statusLine = httpResponse.getStatusLine();
      HttpEntity entity = httpResponse.getEntity();
      if (entity != null)
      {
        responseString = EntityUtils.toString(entity);
      }
      map.put("respStatus", Integer.valueOf(statusLine.getStatusCode()));
      map.put("respContent", responseString);
      log.info("http服务接口 statusLine_post:" + statusLine.toString());
      log.info("http服务接口 statusLineCode_post:" + statusLine.getStatusCode());
      log.info("http服务接口 responseFlag_post:" + String.valueOf(statusLine.getStatusCode()).equals("200"));
      log.info("http服务接口 responseContent_post:" + responseString);

      System.out.println("http服务接口 statusLine_post:" + statusLine.toString());
      System.out.println("http服务接口 statusLineCode_post:" + statusLine.getStatusCode());
      System.out.println("http服务接口 responseFlag_post:" + String.valueOf(statusLine.getStatusCode()).equals("200"));
      System.out.println("http服务接口 responseContent_post:" + responseString);
    }
    catch (ClientProtocolException e) {
      log.error("http服务接口 http连接异常！");
      e.printStackTrace();
    } catch (IOException e) {
      log.error("http服务接口 http连接发生网络异常！");
      e.printStackTrace();
    } finally {
      try {
        if (httpResponse != null)
          httpResponse.close();
      }
      catch (IOException e) {
        log.error("http服务接口 断开http连接异常！");
        e.printStackTrace();
      }
    }
  }

  return map;
}

private void loadHttpCfgParamMap() {
  Properties prop = new Properties();
  try {
    InputStream in = getClass().getClassLoader().getResourceAsStream("yj_admin.properties");
    prop.load(in);
    Set set = prop.stringPropertyNames();
    Iterator it = set.iterator();
    httpCfgParamMap = new HashMap();
    while (it.hasNext()) {
      String key = (String)it.next();
      log.debug("http服务接口 httpCfgParamMap key:" + key);
      httpCfgParamMap.put(key, prop.getProperty(key).trim());
    }
    log.debug("http服务接口 httpCfgParamMap:" + httpCfgParamMap.toString());
  }
  catch (IOException e) {
    log.error("http服务接口 初始化http配置map异常！");
    e.printStackTrace();
  }
}

public boolean sendMsg(String phone, String msgContent)
  throws Exception
{
  String smsEnable = SystemProperty.getProperty("sms.enable");
  if ((smsEnable == null) || (smsEnable.isEmpty()) || (!smsEnable.equalsIgnoreCase("true"))) {
    log.info("SMS发送被关闭，如需打开，请配置sms.enable=true(yj_admin.properties)");
    return true;
  }

  boolean flag = true;
  try {
    MsgEntity msg = new MsgEntity(phone, msgContent);
    System.out.println(msg.toJson());
    log.info("短信发送json串:" + msg.toJson());
    Map result = sendHttpRequest("sms.server.host", "sms.server.url", msg.toJson(), "POST");
    String returnStatus = result.get("respStatus").toString();
    log.info("短信发送状态:" + returnStatus);
    String returnContent = result.get("respContent").toString();
    log.info("短信发送的内容主体:" + returnContent);
    System.out.println(returnStatus);
    System.out.println(returnContent);
  } catch (Exception e) {
    flag = false;
    log.error("短信发送失败---原因是:" + e.getMessage() + "时间是:手机号是:" + phone);
  }
  return flag;
}

public Map<String, Object> sendHttpRequestVersionTwo(String targetSys, String targetService, Object paramEntity)
{
  List postPairs = add("", paramEntity, null);

  if (null == httpCfgParamMap)
  {
    loadHttpCfgParamMap();
  }
  Map map = new HashMap();
  String responseString = null;

  String targetSysStr = (String)httpCfgParamMap.get(targetSys);
  String targetServiceStr = (String)httpCfgParamMap.get(targetService);
  String url = targetSysStr + targetServiceStr;

  DefaultHttpRequestRetryHandler dhr = new DefaultHttpRequestRetryHandler(5, true);
  HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
  CloseableHttpClient httpClient = httpClientBuilder.setRetryHandler(dhr).build();

  CloseableHttpResponse httpResponse = null;
  try {
    HttpPost httpPost = new HttpPost(url);
    log.info("httpPostURI:" + httpPost.getURI());

    RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(900000).setConnectTimeout(900000).build();
    httpPost.setConfig(requestConfig);
    if (targetSys.equals("sms.server.host"))
      httpPost.addHeader("Content-Type", "application/json");
    else if (targetSys.equals("paycore"))
      httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
    else if (targetSys.equals("pay.gateway")) {
      httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
    }

    UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postPairs, "UTF-8");
    log.info("formEntity:" + EntityUtils.toString(formEntity));
    httpPost.setEntity(formEntity);

    httpResponse = httpClient.execute(httpPost);
    StatusLine statusLine = httpResponse.getStatusLine();
    HttpEntity entity = httpResponse.getEntity();
    if (entity != null) {
      responseString = EntityUtils.toString(entity);
    }

    map.put("respStatus", Integer.valueOf(statusLine.getStatusCode()));
    map.put("respContent", responseString);
    log.info("http服务接口 statusLine_post:" + statusLine.toString());
    log.info("http服务接口 statusLineCode_post:" + statusLine.getStatusCode());
    log.info("http服务接口 responseFlag_post:" + String.valueOf(statusLine.getStatusCode()).equals("200"));
    log.info("http服务接口 responseContent_post:" + responseString);
  } catch (ClientProtocolException e) {
    log.error("http服务接口 http连接异常！");
    e.printStackTrace();
  } catch (IOException e) {
    log.error("http服务接口 http连接发生网络异常！");
    e.printStackTrace();
  } finally {
    try {
      if (httpResponse != null)
        httpResponse.close();
    }
    catch (IOException e) {
      log.error("http服务接口 断开http连接异常！");
      e.printStackTrace();
    }
  }
  return map;
}

private List<BasicNameValuePair> addListEveryFieldsToMap(String prefix, List listObject, List<BasicNameValuePair> list) {
  if (listObject == null) {
    return list;
  }
  if (list == null) {
    list = new ArrayList();
  }
  for (int i = 0; i < listObject.size(); i++) {
    Object o = listObject.get(i);
    list = add(prefix + "[" + i + "].", o, list);
  }

  return list;
}

private List<BasicNameValuePair> add(String prefix, Object object, List<BasicNameValuePair> list)
{
  if (list == null) {
    list = new ArrayList();
  }
  int num = 0;
  Class clazz = object.getClass();
  while (num < 3)
  {
    if ((num >= 3) || (clazz == Object.class)) {
      break;
    }
    Field[] fields = clazz.getDeclaredFields();
    for (Field field : fields) {
      field.setAccessible(true);
      try {
        Object tem = field.get(object);
        if (tem != null)
        {
          if (((tem instanceof List)) && (!((List)tem).isEmpty()))
            list = addListEveryFieldsToMap(field.getName(), (List)tem, list);
          else
            list.add(new BasicNameValuePair(prefix + field.getName(), tem.toString()));
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    num++;
    clazz = clazz.getSuperclass();
  }
  return list;
}

public static void main(String[] args) throws Exception
{
  try {
    new HttpUtil().sendMsg("13616218253", "您购买的宝1号已操作还款，本息共4.00元将归还至您的银行卡上");
  } catch (Exception e) {
    e.printStackTrace();
  }
}
}