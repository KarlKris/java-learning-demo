package happensbefore;

/**
 * @Description 先行发生原则测试
 * @Author li-yuanwen
 * @Date 2021/1/29 17:18
 */
public class HappensbeforeTest {


    static class Value {
        private int v;

        public int getV() {
            return v;
        }

        public void setV(int v) {
            this.v = v;
        }
    }

    public static void main(String[] args) {
        Value value = new Value();
        Thread athread = new Thread(() -> {
            value.setV(1);
            System.out.println("--------------athread end-------------");
        });
        Thread bthread = new Thread(() -> {
            try{
                Thread.sleep(10);
                System.out.println(value.getV());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        athread.start();
        bthread.start();
    }
}
