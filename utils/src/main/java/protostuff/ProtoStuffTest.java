package protostuff;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.*;

/**
 * @author li-yuanwen
 * @date 2021/12/13
 */
public class ProtoStuffTest {

    public static void main(String[] args) throws JsonProcessingException {
        Map<Object, Object> map = new HashMap<>(7);
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            map.put(String.valueOf(i), i);
            set.add(i);
        }

        KeyWrapper mapWrapper = new KeyWrapper();
        mapWrapper.key0 = 0;
        mapWrapper.key1 = 1;
        mapWrapper.key2 = 2;
        mapWrapper.key3 = 3;
        mapWrapper.key4 = 4;
        mapWrapper.key5 = 5;
        mapWrapper.key6 = 6;
        mapWrapper.key7 = 7;
        mapWrapper.key8 = 8;
        mapWrapper.key9 = 9;

        List<Map<Object, Object>> list1 = new LinkedList<>();
        List<KeyWrapper> list2 = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list1.add(map);
            list2.add(mapWrapper);
        }

        Wrapper wrapper = new Wrapper();
        wrapper.i = 0;
        wrapper.j = 1L;
        wrapper.z = map;

        serialize(list2);

        long time = System.currentTimeMillis();
        long end = 2051193600000L;

        long t = end - time;
        System.out.println(t);

        long score = 50000000000L;

        long s = score * 10000000000L + time;
        System.out.println(s);

    }

    private static void serialize(Object obj) throws JsonProcessingException {
        System.out.println(JSONUtil.toJsonStr(obj));
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] bytes1 = objectMapper.writeValueAsBytes(obj);

        byte[] bytes2 = ProtoStuffUtils.serialize(obj);

        System.out.println("obj className=" + obj.getClass().getSimpleName()+ " bytes1.length=" + bytes1.length);
        System.out.println("obj className=" + obj.getClass().getSimpleName()+ " bytes2.length=" + bytes2.length);


        Object target = ProtoStuffUtils.deserialize(bytes2, obj.getClass());
        System.out.println("target className=" + target.getClass().getSimpleName());
        System.out.println(JSONUtil.toJsonStr(target));


    }

    @Data
    public static class KeyWrapper {
        private Object key0;
        private Object key1;
        private Object key2;
        private Object key3;
        private Object key4;
        private Object key5;
        private Object key6;
        private Object key7;
        private Object key8;
        private Object key9;
    }

    @Data
    public static class Wrapper {
        private int i;
        private long j;
        private Map<Object, Object> z;
    }
}
