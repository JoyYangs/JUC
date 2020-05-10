import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author joyeYang
 * @date 2020-05-10 08:44
 */
public class Demo_08_CyclicBarrier {

    public static void main(String[] args) {
//        beforeBarrier();
        useBarrier();
    }

    public static void beforeBarrier() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 来了");
            },String.valueOf(i)).start();
        }

        System.out.println(Thread.currentThread().getName() + "\t 大家都来了");
    }

    public static void useBarrier() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10, () ->{System.out.println("大家都来了");});

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 来了");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }

}
