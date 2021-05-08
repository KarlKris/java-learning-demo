package synchronizedtool;

import java.util.concurrent.Phaser;

/**
 * @Description Phaser(CyclicBarrier和CountDownLatch)的替代品
 * @Author li-yuanwen
 * @Date 2021/4/28 11:34
 */
public class PhaserDemo {

    public static void main(String[] args) {
        Phaser phaser = new Phaser(0);
        phaser.awaitAdvance(phaser.getPhase());
        System.out.println("------主线程-------");
    }

}
