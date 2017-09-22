package com.borrow.supermarket.jedis;

import com.borrow.supermarket.util.SystemProperty;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.exceptions.JedisConnectionException;

@Component
public class JedisUtil
{
  private static final Logger log = Logger.getLogger(JedisUtil.class);

  @Resource
  JedisPool jedisPool;

  public boolean initBaseData(String sign, Map<String, String> baseDataHash) { Jedis jedis = null;
    try {
      jedis = (Jedis)this.jedisPool.getResource();
      jedis.auth(SystemProperty.getProperty("redis.password"));

      int i = 5;
      do {
        Transaction t = jedis.multi();
        t.hmset(sign, baseDataHash);
        List allResults = t.exec();
        t.save();
        i--;
        if (allResults != null) break;  } while (i > 0);
    } catch (JedisConnectionException e) {
      e.printStackTrace();
      log.error("JedisConnectionException: " + e.toString());
      if (jedis != null) {
        this.jedisPool.returnBrokenResource(jedis);
        jedis = null;
      }
    }
    catch (Exception e)
    {
      int i;
      e.printStackTrace();
      log.error("Redis exception: " + e.toString());
      return false;//mjw
    } finally {
      if (jedis != null)
        this.jedisPool.returnResource(jedis);
    }
    return true; }

  public Map<String, String> getObjectHash(String sign)
  {
    Jedis jedis = null;
    Map hash = null;
    try {
      jedis = (Jedis)this.jedisPool.getResource();
      jedis.auth(SystemProperty.getProperty("redis.password"));
      hash = jedis.hgetAll(sign);
    } catch (JedisConnectionException e) {
      log.error("JedisConnectionException: " + e.toString());
      if (jedis != null) {
        this.jedisPool.returnBrokenResource(jedis);
        jedis = null;
      }
    } catch (Exception e) {
      log.error("Redis get connection: " + e.toString());
    } finally {
      if (jedis != null) {
        this.jedisPool.returnResource(jedis);
      }
    }
    return hash;
  }
}