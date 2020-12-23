package org.example.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: madala
 * Date: 2020-12-14;   Time: 22:22
 */
public class JSONUtil {
    //jackson框架中，对json字符串和java对象之间互相转换的类
    private static final ObjectMapper MAPPER = new ObjectMapper();
    public static String serialize(Object o){//序列化操作：java对象序列化为json字符串
        try{
            return MAPPER.writeValueAsString(o);
        }catch (JsonProcessingException e){
            e.printStackTrace();
            throw new RuntimeException("java对象序列化出错"+o,e);
        }
    }
    /**
     * 反序列化输入流/字符串为java对象
     * @param is 输入流
     * @param clazz 指定要反序列化的java类型
     * @param <T>
     * @return 反序列后的java对象
     */
    public static <T> T deserialize(InputStream is, Class<T> clazz){//反序列化需要泛型
        try{
            return MAPPER.readValue(is,clazz);
        }catch(IOException e){
            e.printStackTrace();
            throw new RuntimeException("序列化失败");
        }
    }
}
