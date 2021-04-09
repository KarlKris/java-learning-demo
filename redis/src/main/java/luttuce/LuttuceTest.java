package luttuce;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.sentinel.api.StatefulRedisSentinelConnection;
import io.lettuce.core.sentinel.api.sync.RedisSentinelCommands;
import model.JavaBean;

import java.net.SocketAddress;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description Redis 客户端 Luttuce 测试
 * @Author li-yuanwen
 * @Date 2021/3/29 16:01
 */
public class LuttuceTest {

    public static void main(String[] args) {
        testSentinel();
    }

    public static void testLuttuce() {
        RedisClient client = null;
        StatefulRedisConnection<String, String> redisConnection = null;
        try {
            client = RedisClient.create("redis://localhost");

            // 设置超时时间
            client.setDefaultTimeout(Duration.ofMillis(5000));

            redisConnection = client.connect();

            RedisCommands<String, String> redisCommands = redisConnection.sync();

            String key = redisCommands.get("myKey");
            System.out.println(key);

            List<Integer> list = new ArrayList<>(4);
            list.add(1);
            list.add(2);

            redisCommands.set("hp", JSON.toJSONString(new JavaBean("fight", list)));

            JavaBean bean = JSONObject.parseObject(redisCommands.get("hp"), JavaBean.class);
            System.out.println(bean);
        } finally {
            redisConnection.close();
            client.shutdown();
        }

    }


    public static void testSentinel() {
        RedisClient client = null;
        StatefulRedisSentinelConnection<String, String> connection = null;
        try {
            client = RedisClient.create("redis://localhost");
            connection = client.connectSentinel();

            RedisSentinelCommands<String, String> sentinelCommands = connection.sync();

            SocketAddress addrByName = sentinelCommands.getMasterAddrByName("mymaster");

            System.out.println(addrByName);
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (client != null) {
                client.shutdown();
            }
        }
    }
}
