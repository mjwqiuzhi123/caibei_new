package com.borrow.supermarket.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.Serializable;

public class CommonMsg
  implements Serializable
{
  public String toJson()
    throws Exception
  {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.writeValueAsString(this);
  }
}