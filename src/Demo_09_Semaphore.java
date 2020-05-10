import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author joyeYang
 * @date 2020-05-10 08:44
 */
public class Demo_09_Semaphore {

    // 用于高并发
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3); // 模拟资源类，有3个空车位
        for (int i = 1; i <= 7; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t 占了车位");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName() + "\t 离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
