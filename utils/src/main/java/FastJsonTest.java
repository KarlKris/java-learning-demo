import com.alibaba.fastjson.JSON;
import method.Bean;
import method.JavaBean;

import java.util.*;

/**
 * @Description FastJson 测试
 * @Author li-yuanwen
 * @Date 2021/3/26 14:49
 */
public class FastJsonTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        JavaBean bean = new JavaBean();
        bean.setName("bean");
        bean.setList(list);

        List<JavaBean> beanList = new ArrayList<JavaBean>();
        beanList.add(bean);

        Set<JavaBean> set = new HashSet<JavaBean>();
        set.add(bean);

        Map<String, JavaBean> map = new HashMap<String, JavaBean>();
        map.put("beannn", bean);

        JavaBean[] beans = new JavaBean[] {bean};

        Bean tset = new Bean("tset", 1, beanList, set, map, beans);

        String str = JSON.toJSONString(tset);




    }
}
