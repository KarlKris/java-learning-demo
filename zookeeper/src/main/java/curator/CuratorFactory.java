package curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.ServiceInstanceBuilder;
import org.apache.zookeeper.CreateMode;

/**
 * @Description CuratorFactory
 * @Author li-yuanwen
 * @Date 2021/3/19 16:38
 */
public class CuratorFactory {

    private static final RetryPolicy policy = new ExponentialBackoffRetry(1000, 3);

    public static CuratorFramework newCuratorFramework(String connectString) {
        return CuratorFrameworkFactory.newClient(connectString, policy);
    }


    public static void main(String[] args) throws Exception {
        String nodeName = "/java-learning-demo4";
        String nodeValue = "Zookeeper Curator Test";
        String serviceName = "service-name-test";

        CuratorFramework framework = null;
        ServiceRegistry registry = null;
        try {


            framework = newCuratorFramework("192.168.11.83:2181");
            framework.start();

//            framework.create().withMode(CreateMode.PERSISTENT).forPath(nodeName, nodeValue.getBytes());
//
//            System.out.println(nodeValue.equalsIgnoreCase(new String(framework.getData().forPath(nodeName))));


            registry = new ServiceRegistry(framework, nodeName);
            registry.start();

            // 注册服务
            ServiceInstance<ServiceDetail> instance1 = ServiceInstance.<ServiceDetail>builder()
                    .id("service1")
                    .name(serviceName)
                    .port(14111)
                    .address("192.168.11.83")
                    .payload(new ServiceDetail("host1"))
                    .build();


            ServiceInstance<ServiceDetail> instance2 = ServiceInstance.<ServiceDetail>builder()
                    .id("service2")
                    .name(serviceName)
                    .port(15111)
                    .address("192.168.11.83")
                    .payload(new ServiceDetail("host2"))
                    .build();

            registry.registerService(instance1);
            registry.registerService(instance2);

            System.out.println("Zookeeper Register Success!");

            for (ServiceInstance<ServiceDetail> instance : registry.queryForInstances(serviceName)) {
                System.out.println("name : " + instance.getName()
                        + " id : " + instance.getId()
                        + " address : " + instance.getAddress()
                        + " serviceDetail : " + instance.getPayload().getName());
            }

            Thread.sleep(100000);
        } finally {
            if (registry != null) {
                registry.close();
            }
            if (framework != null) {
                framework.close();
            }
        }

    }

}
