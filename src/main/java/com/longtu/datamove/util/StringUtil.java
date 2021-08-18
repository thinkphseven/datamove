package com.longtu.datamove.util;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class StringUtil {
    public static Date parseDate (String s, String format) {
        SimpleDateFormat sd = new SimpleDateFormat(format);
        try {
            return sd.parse(s);
        } catch(ParseException e) {
            return new Date();
        }
    }

    public static String parseString (String s) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        String format = "yyyyMMdd";
        if (s.length() == 10) {
            if (s.indexOf("-") != -1) {
                format = "yyyy-MM-dd";
            } else if (s.indexOf("/") != -1) {
                format = "yyyy/MM/dd";
            }
        } else if (s.length() > 10) {
            s = s.substring(0, 10);
        }
        try {
            Date date = new SimpleDateFormat(format).parse(s);
            return sd.format(date);
        } catch(ParseException e) {
            return sd.format(new Date());
        }
    }

    public static String parseTimeStamp (String s) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format = "yyyy-MM-dd hh:mm:ss";
        if (s.length() == 8) {
            format = "yyyyMMdd";
        } else if (s.length() == 10) {
            if (s.indexOf("-") != -1) {
                format = "yyyy-MM-dd";
            } else if (s.indexOf("/") != -1) {
                format = "yyyy/MM/dd";
            }
        } else if (s.length() > 19) {
            s = s.substring(0, 19);
        }
        try {
            Date date = new SimpleDateFormat(format).parse(s);
            return sd.format(date);
        } catch(ParseException e) {
            return sd.format(new Date());
        }
    }

    public static BigDecimal parseBigDecimal(String s) {
        BigDecimal b = new BigDecimal(s);
        return b.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 转换类型
     * @param prest
     * @param obj
     * @param mscolumn
     * @param i
     */
    public static void getTypeConversion(PreparedStatement prest,Object obj,String mscolumn,int i){
        try {
            if ("java.sql.Date".equals(mscolumn)){
                prest.setDate(i, java.sql.Date.valueOf(StringUtil.parseString((String) obj)));
            } else if ("java.lang.String".equals(mscolumn)){
                prest.setString(i, (String) obj);
            } else if ("java.lang.Integer".equals(mscolumn)){
                String val = (String) obj;
                val = (val == null || "null".equals(val)) ? "0" : val;
                prest.setInt(i, Integer.parseInt(val));
            } else if ("java.sql.Timestamp".equals(mscolumn)){
                prest.setTimestamp(i, Timestamp.valueOf(StringUtil.parseTimeStamp((String) obj)));
            } else if ("java.math.BigDecimal".equals(mscolumn)){
                prest.setBigDecimal(i, StringUtil.parseBigDecimal((String) obj));
            } else {
                prest.setObject(i, obj);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
