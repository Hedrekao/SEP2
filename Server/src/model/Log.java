package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class Log
{
  private String filename;
  private static Map<String, Log> map = new HashMap<>();

  private Log(String filename)
  {
    this.filename = filename + ".txt";
  }

  public void addLog(String text)
  {
    addToFile(text);
    System.out.println(text);
  }


  public static Log getInstance(String key)
  {
    Log instance = map.get(key);
    if (instance == null)
    {
      synchronized (map)
      {
        instance = map.get(key);
        if (instance == null)
        {
          instance= new Log(key);
          map.put(key,instance);
        }
      }
    }
    return instance;
  }

  private void addToFile(String log)
  {
    if (log == null)
    {
      return;
    }
    BufferedWriter out = null;
    try
    {

      out = new BufferedWriter(new FileWriter(filename, true));
      out.write(log + "\n");
    }
    catch (Exception e) {e.printStackTrace();}
    finally
    {
      try
      {
        out.close();
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
  }
}
