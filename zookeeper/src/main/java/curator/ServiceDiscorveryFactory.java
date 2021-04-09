package curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.ServiceProvider;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;
import org.apache.curator.x.discovery.strategies.RandomStrategy;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description 描述
 * @Author li-yuanwen
 * @Date 2021/3/24 20:30
 */
public class ServiceDiscorveryFactory {

    private ServiceDiscovery<ServiceDetail> serviceDiscovery;
    private final ConcurrentHashMap<String, ServiceProvider<ServiceDetail>> serviceProviderMap = new ConcurrentHashMap<>();

    public ServiceDiscorveryFactory(CuratorFramework client, String basePath) {
        serviceDiscovery = ServiceDiscoveryBuilder.builder(ServiceDetail.class)
                .client(client)
                .basePath(basePath)
                .serializer(new JsonInstanceSerializer<ServiceDetail>(ServiceDetail.class))
                .build();
    }

    /**
     * Note: When using Curator 2.x (Zookeeper 3.4.x) it's essential that service provider objects are cached by your application and reused.
     * Since the internal NamespaceWatcher objects added by the service provider cannot be removed in Zookeeper 3.4.x,
     * creating a fresh service provider for each call to the same service will eventually exhaust the memory of the JVM.
     */
    public ServiceInstance<ServiceDetail> getServiceProvider(String serviceName) throws Exception {
        ServiceProvider<ServiceDetail> provider = serviceProviderMap.get(serviceName);
        if (provider == null) {
            provider = serviceDiscovery.serviceProviderBuilder().
                    serviceName(serviceName).
                    providerStrategy(new RandomStrategy<ServiceDetail>())
                    .build();

            ServiceProvider<ServiceDetail> oldProvider = serviceProviderMap.putIfAbsent(serviceName, provider);
            if (oldProvider != null) {
                provider = oldProvider;
            } else {
                provider.start();
            }
        }

        return provider.getInstance();
    }

    public void start() throws Exception {
        serviceDiscovery.start();
    }

    public Collection<ServiceInstance<ServiceDetail>> getInstances(String instanceName) throws Exception {
        return serviceDiscovery.queryForInstances(instanceName);
    }

    public void close() throws IOException {

        for (Map.Entry<String, ServiceProvider<ServiceDetail>> me : serviceProviderMap.entrySet()) {
            try {
                me.getValue().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        serviceDiscovery.close();
    }
}
