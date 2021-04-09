import model.Animal;
import model.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Description 描述
 * @Author li-yuanwen
 * @Date 2021/3/25 10:28
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Animal person = (Animal) context.getBean("animal");
        System.out.println(person.getName());

//        createProxyClassFile();
    }

    private static void createProxyClassFile() {
        String name = "Person";
        byte[] data = ProxyGenerator.generateProxyClass(name, new Class[]{Person.class});
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(name + ".class");
            System.out.println((new File("Person")).getAbsolutePath());
            out.write(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != out) try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
