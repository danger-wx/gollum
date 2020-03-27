package com.dangerousarea.gollum.common.result;

import java.util.HashMap;
import java.util.Map;

public class ErrorCodes {
    private static class UniqueHash<K,V> extends HashMap<K,V> {
        public V put(K key, V value) {
            if (this.containsKey(key)){
                System.out.println("key:"+key+"->value:"+this.get(key));
                throw new RuntimeException("key already exist " + key);
            }

            if(this.containsValue(value)){
                System.out.println("key:"+key+"->value:"+value);
                throw new RuntimeException("value already exist " + value);
            }

            return super.put(key, value);
        }

        public UniqueHash(){}

        public UniqueHash(Map<? extends K, ? extends V> m) {
            if (m == null || m.size() <= 0)
                return;

            m.forEach((k,v)->{
                put(k,v);
            });
        }
    }
    private static Map<Integer, String> errors=new HashMap<>();

    /**
     * 参数错误
     */
    public static final int PARAMETER_ERROR = 510;
    /**
     * 系统异常
     */
    public static final int SYSTME_ERROR = 501;

    /**
     * 未知错误
     */
    public static final int UNKNOWN_ERROR = 530;

    /**
     * 数据没有找到
     */
    public static final int DATA_NOT_FOUND = 550;

    /**
     * 账号不存在
     */
    public static final int ACCOUNT_NOT_EXISTS = 600;

    /**
     * 账号不存在
     */
    public static final int ERROR_PASSWORD = 601;

    /**
     * 品牌不存在
     */
    public static final int BRAND_NOT_EXISTS = 6000;


    private static void putMessages(Integer code,String message){
        errors.put(code, message);
    }

    static {
        putMessages(PARAMETER_ERROR, "参数错误");
        putMessages(UNKNOWN_ERROR, "未知错误");
        putMessages(DATA_NOT_FOUND, "数据没有找到");
        putMessages(ACCOUNT_NOT_EXISTS, "账号不存在");
        putMessages(ERROR_PASSWORD, "密码错误");
        putMessages(BRAND_NOT_EXISTS, "品牌不存在");
    }

    public static  String getMessageByCode(int code){
        if (errors.containsKey(code))
            return errors.get(code);
        return "";
    }
}
