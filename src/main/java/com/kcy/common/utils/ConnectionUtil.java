package com.kcy.common.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ConnectionUtil
{
  public static String Connect(String address)
  {
    HttpURLConnection conn = null;
    URL url = null;
    InputStream in = null;
    BufferedReader reader = null;
    StringBuffer stringBuffer = null;
    try
    {
      url = new URL(address);
      conn = (HttpURLConnection)url.openConnection();
      conn.setConnectTimeout(5000);
      conn.setReadTimeout(5000);
      conn.setDoInput(true);
      conn.connect();
      in = conn.getInputStream();
//      OutputStreamWriter out = new OutputStreamWriter(conn
//              .getOutputStream(), "UTF-8");
      reader = new BufferedReader(new InputStreamReader(in,"utf-8"));
      stringBuffer = new StringBuffer();
      String line = null;
      while ((line = reader.readLine()) != null) {
        stringBuffer.append(line);
      }
      return stringBuffer.toString();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      conn.disconnect();
      try
      {
        in.close();
        reader.close();
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
	return stringBuffer.toString();
  }
  
  public static String sendHttpPost(String url, HashMap<String, String> params)
  {
    URL realUrl = null;
    
    InputStream in = null;
    
    HttpURLConnection conn = null;
    
    StringBuffer sb = new StringBuffer();
    if (params != null)
    {
      for (Map.Entry<String, String> e : params.entrySet())
      {
        sb.append((String)e.getKey());
        sb.append("=");
        sb.append((String)e.getValue());
        sb.append("&");
      }
      sb = sb.deleteCharAt(sb.length() - 1);
    }
    String stringParams = sb.toString();
    try
    {
      realUrl = new URL(url);
      conn = (HttpURLConnection)realUrl.openConnection();
      conn.setDoOutput(true);
      conn.setRequestMethod("POST");
      PrintWriter pw = new PrintWriter(conn.getOutputStream());
      pw.print(stringParams);
      pw.flush();
      pw.close();
      in = conn.getInputStream();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    BufferedReader br = new BufferedReader(new InputStreamReader(in));
    StringBuffer buffer = new StringBuffer();
    String line = "";
    try
    {
      while ((line = br.readLine()) != null) {
        buffer.append(line);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    String result = buffer.toString();
    
    return result;
  }
  
  public static String sendGetHttpPost(String url, String coding)
    throws UnsupportedEncodingException
  {
    URL realUrl = null;
    
    InputStream in = null;
    
    HttpURLConnection conn = null;
    try
    {
      realUrl = new URL(url);
      conn = (HttpURLConnection)realUrl.openConnection();
      conn.setDoOutput(true);
      conn.setRequestMethod("GET");
      PrintWriter pw = new PrintWriter(conn.getOutputStream());
      pw.flush();
      pw.close();
      in = conn.getInputStream();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    BufferedReader br = new BufferedReader(new InputStreamReader(in, coding));
    StringBuffer buffer = new StringBuffer();
    
    String line = "";
    try
    {
      while ((line = br.readLine()) != null) {
        buffer.append(line);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    String result = buffer.toString();
    
    return result;
  }
  
  public static String simulateSendGetHttpPost(String url, String coding, HashMap<String, String> requestHeaders)
    throws UnsupportedEncodingException
  {
    URL realUrl = null;
    
    InputStream in = null;
    
    HttpURLConnection conn = null;
    try
    {
      realUrl = new URL(url);
      conn = (HttpURLConnection)realUrl.openConnection();
      conn.setDoOutput(true);
      conn.setRequestMethod("GET");
      if (requestHeaders != null) {
        for (Map.Entry<String, String> e : requestHeaders.entrySet()) {
          conn.setRequestProperty((String)e.getKey(), (String)e.getValue());
        }
      }
      PrintWriter pw = new PrintWriter(conn.getOutputStream());
      pw.flush();
      pw.close();
      in = conn.getInputStream();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    BufferedReader br = new BufferedReader(new InputStreamReader(in, coding));
    StringBuffer buffer = new StringBuffer();
    
    String line = "";
    try {
      while ((line = br.readLine()) != null) {
        buffer.append(line);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    String result = buffer.toString();
    
    return result;
  }
}
