package factorybean;

import model.Animal;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import properties.Server;

/**
 * @Description 描述
 * @Author li-yuanwen
 * @Date 2021/4/8 11:56
 */
public class TestFactoryBean implements FactoryBean<Animal>, InitializingBean {

    private Animal bean;

    @Autowired
    private Server server;

    public Animal getObject() throws Exception {
        return bean;
    }

    public Class<?> getObjectType() {
        return Animal.class;
    }

    public void afterPropertiesSet() throws Exception {
        this.bean = new Animal("dog");
    }
}
