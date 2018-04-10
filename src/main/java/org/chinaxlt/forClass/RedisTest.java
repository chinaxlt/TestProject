package org.chinaxlt.forClass;

import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class RedisTest {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功,服务正在运行: " + jedis.ping());

        jedis.set("data", "CZJ");
        System.out.println("data:" + jedis.get("data"));
        System.out.println("----------");

        jedis.lpush("site-list", "Runoob");
        jedis.lpush("site-list", "Google");
        jedis.lpush("site-list", "Taobao");

        List<String> list = jedis.lrange("site-list", 0, 2);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("列表项为: " + list.get(i));
        }
        System.out.println("----------");

        Set<String> keys = jedis.keys("*");
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            String key = it.next();
            System.out.println("key:" + key);
        }
        System.out.println("----------");

        Sample sa = new Sample();
        System.out.println("----------");
    }
}
