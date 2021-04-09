package curator;

import lombok.Data;

/**
 * @Description 服务信息
 * @Author li-yuanwen
 * @Date 2021/3/24 20:10
 */
@Data
public class ServiceDetail {

    /** 服务名称 **/
    private String name;

    public ServiceDetail() {
    }

    public ServiceDetail(String name) {
        this.name = name;
    }

}
