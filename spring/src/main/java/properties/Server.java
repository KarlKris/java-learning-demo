package properties;

import org.springframework.context.annotation.PropertySource;

/**
 * @Description 描述
 * @Author li-yuanwen
 * @Date 2021/4/8 11:57
 */
@PropertySource(value = "classpath:server.properties")
public class Server {

    private String name;

    private int age;

    private String sex;
}
