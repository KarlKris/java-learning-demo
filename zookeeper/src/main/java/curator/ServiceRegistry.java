package curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;

import java.util.Collection;

/**
 * @Description zookeeper服务注册
 * @Author li-yuanwen
 * @Date 2021/3/24 20:08
 */
public class ServiceRegistry {

    /** 服务信息 **/
    private final ServiceDiscovery<ServiceDetail> serviceDiscovery;

    private final CuratorFramework curatorFramework;

    public ServiceRegistry(CuratorFramework curatorFramework, String basePath) {
        this.curatorFramework = curatorFramework;
        this.serviceDiscovery = ServiceDiscoveryBuilder.builder(ServiceDetail.class)
                .client(this.curatorFramework)
                .serializer(new JsonInstanceSerializer<ServiceDetail>(ServiceDetail.class))
                .basePath(basePath).build();
    }

    public void updateService(ServiceInstance<ServiceDetail> serviceInstance) throws Exception {
        this.serviceDiscovery.updateService(serviceInstance);
    }

    public void registerService(ServiceInstance<ServiceDetail> serviceInstance) throws Exception {
        this.serviceDiscovery.registerService(serviceInstance);
    }

    public void unregisterService(ServiceInstance<ServiceDetail> serviceInstance) throws Exception {
        this.serviceDiscovery.unregisterService(serviceInstance);
    }

    public Collection<ServiceInstance<ServiceDetail>> queryForInstances(String instanceName) throws Exception {
        return this.serviceDiscovery.queryForInstances(instanceName);
    }

    public void start() throws Exception {
        this.serviceDiscovery.start();
    }

    public void close() throws Exception {
        this.serviceDiscovery.close();
    }

}
