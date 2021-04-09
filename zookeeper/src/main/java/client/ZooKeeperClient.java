package client;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * @Description Java ZooKeeper客户端
 * @Author li-yuanwen
 * @Date 2021/3/18 16:32
 */
public class ZooKeeperClient {

    /** 连接字符串 **/
    private String connectString;
    /** 会话超时时间（毫秒） **/
    private int sessionTimeOut;
    /** Watcher时间通知器 **/
    private Watcher watcher;
    /** 会话id **/
    private long sessionId;
    /** 会话秘钥 **/
    private byte[] sessionPwd;

    private ZooKeeper zooKeeper;

    public ZooKeeperClient(String connectString, int sessionTimeOut, Watcher watcher) {
        this.connectString = connectString;
        this.sessionTimeOut = sessionTimeOut;
        this.watcher = watcher;

    }

    private void connect() throws IOException {
        this.zooKeeper =  new ZooKeeper(this.connectString, this.sessionTimeOut, this.watcher);
        this.sessionId = zooKeeper.getSessionId();
        this.sessionPwd = zooKeeper.getSessionPasswd();
    }

    public void close() throws InterruptedException {
        if (this.zooKeeper == null) {
            return;
        }

        this.zooKeeper.close();
    }

    private void reConnect() throws IOException {
        if (this.zooKeeper != null) {
            return;
        }

        if (sessionId > 0 && sessionPwd != null) {
            this.zooKeeper = new ZooKeeper(connectString, sessionTimeOut, watcher, sessionId, sessionPwd);
        }
    }


    public static void main(String[] args) throws IOException {
        ZooKeeperClient client = new ZooKeeperClient("127.0.0.1:2181", 100000, null);
    }

}
