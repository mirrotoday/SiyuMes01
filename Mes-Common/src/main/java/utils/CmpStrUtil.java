package utils;

import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class CmpStrUtil
{
  public static String convertListToString(List<String> list)
  {
    Object[] array = list.toArray();
    return convertListToString(array);
  }
  
  public static String convertSetToSqlString(Set<String> personIdSet)
  {
    String result = "";
    
    StringBuffer sbr = new StringBuffer();
    if ((personIdSet != null) && (personIdSet.size() > 0))
    {
      Iterator<String> ite = personIdSet.iterator();
      while (ite.hasNext())
      {
        String personId = (String)ite.next();
        sbr.append("'" + personId + "',");
      }
      result = sbr.toString();
      result = result.substring(0, result.length() - 1);
    }
    return result;
  }
  
  public static String convertListToString(Object[] array)
  {
    StringBuffer sb = new StringBuffer();
    int i = 0;
    for (int size = array.length; i < size; i++) {
      if (size == 1)
      {
        sb.append("'");
        sb.append((String)array[i]);
        sb.append("'");
      }
      else if (i != size - 1)
      {
        sb.append("'");
        sb.append((String)array[i]);
        sb.append("', ");
      }
      else
      {
        sb.append("'");
        sb.append((String)array[i]);
        sb.append("'");
      }
    }
    return sb.toString();
  }
  
  public static String converMapToString(Map<?, ?> map)
  {
    StringBuffer sb = new StringBuffer();
    for (Object entry : map.entrySet()) {
      sb.append(",'").append(((Entry)entry).getKey()).append("'");
    }
    String sqlId = sb.toString();
    return sqlId.replaceFirst(",", "");
  }
  
  public static String converCollectionToSql(Collection<String> coll)
  {
    StringBuffer sb = new StringBuffer();
    if ((null == coll) || (coll.size() < 1)) {
      return "''";
    }
    for (String id : coll) {
      sb.append(",'").append(id).append("'");
    }
    return sb.substring(1);
  }
  
  public static String converStringToSqlString(String str, String splitKey)
  {
    if ((str.contains("'")) || (str.toLowerCase().contains("select"))) {
      return str;
    }
    StringBuilder sb = new StringBuilder();
    if (isEmpty(str)) {
      return "''";
    }
    String[] array = str.split(splitKey);
    for (int i = 0; i < array.length; i++) {
      sb.append("'").append(array[i]).append("',");
    }
    return sb.substring(0, sb.length() - 1);
  }
  
  public static boolean isInteger(Object obj)
  {
    boolean res = true;
    if (obj == null) {
      return false;
    }
    String value = obj.toString();
    if (!value.matches("[+-]?\\d*")) {
      res = false;
    }
    return res;
  }
  
  public static final boolean isEmpty(String s)
  {
    return (s == null) || (s.trim().length() == 0);
  }
  
  public static final boolean isNotEmpty(String s)
  {
    return !isEmpty(s);
  }

  
  public static Integer toInt(Object obj)
  {
    Integer res = Integer.valueOf(0);
    if (obj == null) {
      return res;
    }
    String str = String.valueOf(obj);
    if (!StringUtils.isEmpty(str)) {
      try
      {
        res = Integer.valueOf(new BigDecimal(str).intValue());
      }
      catch (Exception e5)
      {
        res = Integer.valueOf(0);
      }
    }
    return res;
  }
  
  public static BigDecimal toBigDecimal(Object obj)
  {
    BigDecimal res = new BigDecimal(0);
    if (obj == null) {
      return null;
    }
    String str = String.valueOf(obj);
    if ("0E-10".equalsIgnoreCase(str)) {
      return res;
    }
    if (!StringUtils.isEmpty(str)) {
      try
      {
        res = new BigDecimal(str);
      }
      catch (Exception e)
      {
        res = new BigDecimal(0);
      }
    }
    return res;
  }
  
  public static java.util.Date toFirstDateFromMonthStr(String monthDataStr)
  {
    Calendar c = toMonthDateFromMOnthStr(monthDataStr);
    if (c == null) {
      return null;
    }
    c.set(5, 1);
    return c.getTime();
  }
  
  public static java.util.Date toLastDateFromMonthStr(String monthDataStr)
  {
    Calendar c = toMonthDateFromMOnthStr(monthDataStr);
    if (c == null) {
      return null;
    }
    c.set(5, c.getActualMaximum(5));
    return c.getTime();
  }
  
  public static Calendar toMonthDateFromMOnthStr(String monthDataStr)
  {
    String[] splitStr = monthDataStr.split(".");
    if (splitStr.length < 2)
    {
      splitStr = monthDataStr.split("-");
      if (splitStr.length < 2)
      {
        splitStr = monthDataStr.split("/");
        if (splitStr.length < 2)
        {
          splitStr = monthDataStr.split(":");
          if (splitStr.length < 2) {
            return null;
          }
        }
      }
    }
    String yearStr = splitStr[0];
    String monthStr = splitStr[1];
    if (monthStr.startsWith("0")) {
      monthStr = monthStr.replace("0", "");
    }
    Calendar instance = Calendar.getInstance();
    instance.set(1, Integer.parseInt(yearStr));
    instance.set(2, Integer.parseInt(monthStr) - 1);
    return instance;
  }
  
  public static java.util.Date toDate(Object obj)
  {
    java.util.Date res = null;
    if (obj == null) {
      return res;
    }
    String str = String.valueOf(obj);
    if (!StringUtils.isEmpty(str))
    {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      try
      {
        res = sdf.parse(str);
      }
      catch (ParseException e)
      {
        sdf = new SimpleDateFormat("yyyy/M/d");
        try
        {
          res = sdf.parse(str);
        }
        catch (ParseException e1)
        {
          sdf = new SimpleDateFormat("yyyy/MM/dd");
          try
          {
            res = sdf.parse(str);
          }
          catch (ParseException e2)
          {
            sdf = new SimpleDateFormat("yyyy-M-d");
            try
            {
              res = sdf.parse(str);
            }
            catch (ParseException e3)
            {
              sdf = new SimpleDateFormat("EEE MMM d H:m:s z y");
              try
              {
                res = sdf.parse(str);
              }
              catch (ParseException e4)
              {
                sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyy", Locale.US);
                try
                {
                  res = sdf.parse(str);
                }
                catch (ParseException e5)
                {
                  res = null;
                }
              }
            }
          }
        }
      }
    }
    return res;
  }
  

  
  public static String toString(Object obj)
  {
    try
    {
      if (obj == null) {
        return "";
      }
      if ((obj instanceof String)) {
        return obj.toString();
      }
      if (((obj instanceof java.util.Date)) || ((obj instanceof java.sql.Date)))
      {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(obj);
      }
      return String.valueOf(obj);
    }
    catch (Exception e) {}
    return null;
  }
  
  public static String getRandomString(int length)
  {
    String base = "abcdefghijklmnopqrstuvwxyz0123456789";
    Random random = new Random();
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < length; i++)
    {
      int number = random.nextInt(base.length());
      sb.append(base.charAt(number));
    }
    return sb.toString();
  }
  
  public static String decodeSorter(String str)
  {
    if (StringUtils.isEmpty(str)) {
      return null;
    }
    String[] split = str.split(",");
    StringBuffer res = new StringBuffer();
    for (String s : split) {
      if ((!StringUtils.isEmpty(s)) && (s.trim() != "")) {
        if ((s.contains(".")) && (!s.contains("\"")))
        {
          res.append(",\"" + s.trim().split("\\s")[0] + "\"");
          if (s.trim().split("\\s").length > 1) {
            res.append(" " + s.trim().split("\\s")[1]);
          }
        }
        else
        {
          res.append("," + s.trim());
        }
      }
    }
    return res.toString().startsWith(",") ? res.toString().substring(1) : res.toString();
  }
  
  public static String buildInSql(String ids)
  {
    if (ids.toLowerCase().contains("select")) {
      return "(select \"cmp_ctb.id\" from (" + ids + ") as dynamicSql)";
    }
    return "('" + ids.replace(",", "','") + "')";
  }
  
  public static boolean isNumber(String str)
  {
    if (isEmpty(str)) {
      return false;
    }
    Pattern pattern = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){1,})?$");
    Matcher match = pattern.matcher(str);
    return match.matches();
  }
  
  public static boolean isPercentNumber(String str)
  {
    Pattern pattern = Pattern.compile("^\\d+\\.?\\d*\\%?$");
    Matcher match = pattern.matcher(str);
    if (!match.matches()) {
      return false;
    }
    return true;
  }
  
  public static String MD5(String s)
  {
    char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    try
    {
      byte[] btInput = s.getBytes();
      
      MessageDigest mdInst = MessageDigest.getInstance("MD5");
      
      mdInst.update(btInput);
      
      byte[] md = mdInst.digest();
      
      int j = md.length;
      char[] str = new char[j * 2];
      int k = 0;
      for (int i = 0; i < j; i++)
      {
        byte byte0 = md[i];
        str[(k++)] = hexDigits[(byte0 >>> 4 & 0xF)];
        str[(k++)] = hexDigits[(byte0 & 0xF)];
      }
      return new String(str);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  public static Boolean isDouble(Object obj)
  {
    try
    {
      String str = toString(obj);
      if ((str.startsWith("0")) && (!str.startsWith("0."))) {
        return Boolean.valueOf(false);
      }
      if (str.split("\\.")[0].length() > 9) {
        return Boolean.valueOf(false);
      }
      double b = Double.parseDouble(str);
      return Boolean.valueOf(true);
    }
    catch (Exception e) {}
    return Boolean.valueOf(false);
  }
  
  public static Double toDouble(Object obj)
  {
    Double res = Double.valueOf(toBigDecimal(obj).doubleValue());
    return res;
  }
  
  public static boolean isDate(Object obj)
  {
    if (obj == null) {
      return false;
    }
    String str = obj.toString();
    if ((!str.matches("\\d{4}/\\d{1,2}/\\d{1,2}")) && (!str.matches("\\d{4}\\.\\d{1,2}\\.\\d{1,2}")) && (!str.matches("\\d{4}-\\d{1,2}-\\d{1,2}")) && 
      (toDate(str) == null)) {
      return false;
    }
    return true;
  }
  
  public static String subZeroAndDot(String s)
  {
    if (isEmpty(s)) {
      return s;
    }
    if (s.indexOf(".") > 0)
    {
      s = s.replaceAll("0+?$", "");
      s = s.replaceAll("[.]$", "");
    }
    return s;
  }
  
  public static String buildSelectSql(String billId)
  {
    String buildSelectSql = "";
    if (billId.toLowerCase().contains("select")) {
      buildSelectSql = "(" + billId + ")";
    } else {
      buildSelectSql = "('" + billId.replace(",", "','") + "')";
    }
    return buildSelectSql;
  }
}
