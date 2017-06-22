package com.kpuswebup.comom.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.PropertyFilter;

public final class JsonUtil
{
    static final String dateFormat = "yyyy-MM-dd";
    static final String timeFormat = "HH:mm:ss";
    static final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";
    static final String timestampFormat = "yyyy-MM-dd HH:mm:ss.SSS";

    @SuppressWarnings("unused")
    private JSONObject jsonObject = new JSONObject();

    private static JsonConfig defaultConfig = null;

    static
    {
        init();
    }

    private JsonUtil()
    {
    }

    private static void init()
    {
        defaultConfig = instanceJsonConfig();
    }

    public static JsonConfig instanceJsonConfig()
    {
        JsonConfig config = new JsonConfig();
        config.registerJsonValueProcessor(java.util.Date.class, new JsonValueProcessor()
        {

            @Override
            public Object processObjectValue(String key, Object value, JsonConfig config)
            {
                if(value instanceof Date)
                {
                    String str = new SimpleDateFormat(timestampFormat).format((Date) value);
                    return str;
                }
                return value;
            }

            @Override
            public Object processArrayValue(Object value, JsonConfig config)
            {

                String[] obj = {};
                if(value instanceof Date[])
                {
                    SimpleDateFormat sf = new SimpleDateFormat(timestampFormat);
                    Date[] dates = (Date[]) value;
                    obj = new String[dates.length];
                    for(int i = 0; i < dates.length; i++)
                    {
                        obj[i] = sf.format(dates[i]);
                    }
                }
                return obj;
            }
        });
        return config;
    }

    public static JsonConfig getDefaultConfig()
    {
        return defaultConfig;
    }

    public static JSONObject fromObject(Object obj, JsonConfig config)
    {
        return JSONObject.fromObject(obj, config);
    }

    public static JSONObject fromObject(Object obj, PropertyFilter ignoreImpl)
    {
        JsonConfig config = getConfigForFilter(ignoreImpl);
        return fromObject(obj, config);
    }


    public static JSONArray fromArrayObject(Object obj)
    {
        JSONArray jsonArray = JSONArray.fromObject(obj, defaultConfig);
        return jsonArray;
    }

    public static JSONArray fromArrayObject(Object obj, JsonConfig config)
    {
        JSONArray jsonArray = JSONArray.fromObject(obj, config);
        return jsonArray;
    }

 

    /** 
     * 
     * @param ignoreImpl
     * @return
     * @throws 
     */
    private static JsonConfig getConfigForFilter(PropertyFilter ignoreImpl)
    {
        JsonConfig config = null;
        try
        {
            // config = (JsonConfig) BeanUtils.cloneBean(defaultConfig);
            config = instanceJsonConfig();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        config.setJsonPropertyFilter(ignoreImpl);
        return config;
    }

    /** */
    /**
    * 从一个JSON 对象字符格式中得到一个java对象
    * @param jsonString
    * @param pojoCalss
    * @return
    */
    public static Object getObjectJsonString(String jsonString, Class<?> pojoCalss)
    {
        Object pojo;
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        pojo = JSONObject.toBean(jsonObject, pojoCalss);
        return pojo;
    }

    /** */
    /**
    * 从json HASH表达式中获取一个map，改map支持嵌套功能
    * @param jsonString
    * @return
    */

    public static Map<String, Object> getMap4Json(String jsonString)
    {
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        Iterator<?> keyIter = jsonObject.keys();
        String key;
        Object value;
        Map<String, Object> valueMap = new HashMap<String, Object>();

        while(keyIter.hasNext())
        {
            key = (String) keyIter.next();
            value = jsonObject.get(key);
            valueMap.put(key, value);
        }

        return valueMap;
    }

    /** */
    /**
    * 从json数组中得到相应java数组
    * @param jsonString
    * @return
    */
    public static Object[] getObjectArray4Json(String jsonString)
    {
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        return jsonArray.toArray();

    }

    /** */
    /**
    * 从json对象集合表达式中得到一个java对象列表
    * @param jsonString
    * @param pojoClass
    * @return
    */
    public static List<?> getListJson(String jsonString, Class<?> pojoClass)
    {

        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        JSONObject jsonObject;
        Object pojoValue;
        List<Object> list = new ArrayList<Object>();
        for(int i = 0; i < jsonArray.size(); i++)
        {

            jsonObject = jsonArray.getJSONObject(i);
            pojoValue = JSONObject.toBean(jsonObject, pojoClass);
            list.add(pojoValue);

        }
        return list;

    }

    /** */
    /**
    * 从json数组中解析出java字符串数组
    * @param jsonString
    * @return
    */
    public static String[] getStringArray4Json(String jsonString)
    {

        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        String[] stringArray = new String[jsonArray.size()];
        for(int i = 0; i < jsonArray.size(); i++)
        {
            stringArray[i] = jsonArray.getString(i);

        }

        return stringArray;
    }

    /** */
    /**
    * 从json数组中解析出javaLong型对象数组
    * @param jsonString
    * @return
    */
    public static Long[] getLongArray4Json(String jsonString)
    {

        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        Long[] longArray = new Long[jsonArray.size()];
        for(int i = 0; i < jsonArray.size(); i++)
        {
            longArray[i] = jsonArray.getLong(i);

        }
        return longArray;
    }

    /** */
    /**
    * 从json数组中解析出java Integer型对象数组
    * @param jsonString
    * @return
    */
    public static Integer[] getIntegerArray4Json(String jsonString)
    {

        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        Integer[] integerArray = new Integer[jsonArray.size()];
        for(int i = 0; i < jsonArray.size(); i++)
        {
            integerArray[i] = jsonArray.getInt(i);

        }
        return integerArray;
    }

    /** */
    /**
    * 从json数组中解析出java Integer型对象数组
    * @param jsonString
    * @return
    */
    public static Double[] getDoubleArray4Json(String jsonString)
    {

        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        Double[] doubleArray = new Double[jsonArray.size()];
        for(int i = 0; i < jsonArray.size(); i++)
        {
            doubleArray[i] = jsonArray.getDouble(i);

        }
        return doubleArray;
    }

    /** */
    /**
    * 将java对象转换成json字符串
    * @param javaObj
    * @return
    */
    public static String getJsonString4JavaPOJO(Object javaObj)
    {

        JSONObject json;
        json = JSONObject.fromObject(javaObj);
        return json.toString();

    }

}
