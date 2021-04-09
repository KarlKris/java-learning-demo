package curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.x.discovery.ServiceInstance;

import static curator.CuratorFactory.newCuratorFramework;

/**
 * @Description 服务消耗
 * @Author li-yuanwen
 * @Date 2021/3/24 20:29
 */
public class ServiceConsumer {

    public static void main(String[] args) throws Exception {
        String nodeName = "/java-learning-demo4";
        String nodeValue = "Zookeeper Curator Test";
        String serviceName = "service-name-test";

        CuratorFramework framework = newCuratorFramework("192.168.11.83:2181");
        framework.start();

        ServiceDiscorveryFactory factory = new ServiceDiscorveryFactory(framework, nodeName);
        factory.start();

        for (int i = 0; i < 10; i++) {
            ServiceInstance<ServiceDetail> instance = factory.getServiceProvider(serviceName);

            System.out.println("name : " + instance.getName()
                    + " id : " + instance.getId()
                    + " address : " + instance.getAddress()
                    + " serviceDetail : " + instance.getPayload().getName());

            Thread.sleep(2000);
        }

        factory.close();

    }
}
