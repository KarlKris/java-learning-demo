package method;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description 描述
 * @Author li-yuanwen
 * @Date 2021/3/26 15:02
 */
@Data
public class Bean {

    private String str;
    private int num;
    private List<JavaBean> list;
    private Set<JavaBean> set;
    private Map<String, JavaBean> map;
    private JavaBean[] objs;

    public Bean(String str, int num, List<JavaBean> list, Set<JavaBean> set, Map<String, JavaBean> map, JavaBean[] objs) {
        this.str = str;
        this.num = num;
        this.list = list;
        this.set = set;
        this.map = map;
        this.objs = objs;
    }
}
