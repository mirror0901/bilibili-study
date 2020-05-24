package com.keda.jpa.jpademo.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: mirror
 * @Date: 2019/6/5 20:03
 */
@Slf4j
public class JsonPathTest {

    public static void main(String[] args) {

        String jsonStr = "{ \"store\": {\"book\": [{ \"category\": \"reference\"," +
                "\"author\": \"Nigel Rees\",\"title\": \"Sayings of the Century\"," +
                "\"price\": 8.95},{ \"category\": \"fiction\",\"author\": \"Evelyn Waugh\"," +
                "\"title\": \"Sword of Honour\",\"price\": 12.99,\"isbn\": \"0-553-21311-3\"" +
                "}],\"bicycle\": {\"color\": \"red\",\"price\": 19.95}}}";
        //Object jsonObject = JSON.parse(jsonStr); // 先解析JSON数据
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        log.info("\njson对象为:{}", jsonObject);
        // 如果是json格式的字符串，则先解析为JSONObject，然后就能直接使用JSONPath了。
        System.out.println("\n Book数目：" + JSONPath.eval(jsonObject, "$.store.book.size()"));
        System.out.println("第一本书title：" + JSONPath.eval(jsonObject, "$.store.book[0].title"));
        System.out.println("price大于10元的book：" + JSONPath.eval(jsonObject, "$.store.book[price > 10]"));
        System.out.println("price大于10元的title：" + JSONPath.eval(jsonObject, "$.store.book[price > 10][0].title"));
        System.out.println("category(类别)为fiction(小说)的book：" + JSONPath.eval(jsonObject, "$.store.book[category = 'fiction']"));
        System.out.println("bicycle的所有属性值" + JSONPath.eval(jsonObject, "$.store.bicycle.*"));
        System.out.println("bicycle的color和price属性值" + JSONPath.eval(jsonObject, "$.store.bicycle['color','price']"));

    }

}
