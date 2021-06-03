package test;

import com.alibaba.fastjson.JSON;
import com.jsoniter.JsonIterator;
import com.jsoniter.JsonIteratorPool;
import com.jsoniter.output.JsonStream;
import com.jsoniter.output.JsonStreamPool;
import com.jsoniter.spi.JsoniterSpi;
import method.Bean;
import method.JavaBean;

import java.util.*;

/**
 * @Description FastJson 测试
 * @Author li-yuanwen
 * @Date 2021/3/26 14:49
 */
public class JsonTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        JavaBean bean = new JavaBean();
        bean.setName("bean");
        bean.setList(list);

        String str = JsonStream.serialize(bean);
        System.out.println(str);

        String s = JSON.toJSONString(bean);
        System.out.println(s.getBytes().length == str.getBytes().length);

    }
}
