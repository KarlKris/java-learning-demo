package method;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description 描述
 * @Author li-yuanwen
 * @Date 2021/3/26 14:50
 */
public class MethodProvider {


    public static void process(String str, int num
            , List<JavaBean> list, Set<JavaBean> set, Map<String, JavaBean> map, JavaBean[] objs) {
        System.out.println(str);
        System.out.println(num);
        System.out.println("-------------list-------------");
        for (JavaBean bean : list) {
            System.out.println(bean.getName());
            for (int i : bean.getList()) {
                System.out.println(i);
            }
        }
        System.out.println("-------------set-------------");
        for (JavaBean bean : set) {
            System.out.println(bean.getName());
            for (int i : bean.getList()) {
                System.out.println(i);
            }
        }
        System.out.println("-------------map-------------");
        for (Map.Entry<String, JavaBean> enrtry : map.entrySet()) {
            String key = enrtry.getKey();
            System.out.println(key);
            JavaBean bean = enrtry.getValue();
            System.out.println(bean.getName());
            for (int i : bean.getList()) {
                System.out.println(i);
            }
        }
        System.out.println("-------------array-------------");
        for (JavaBean bean : objs) {
            System.out.println(bean.getName());
            for (int i : bean.getList()) {
                System.out.println(i);
            }
        }
    }

}
