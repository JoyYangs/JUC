import java.util.concurrent.CountDownLatch;

/**
 * @author joyeYang
 * @date 2020-05-10 08:43
 */


public class Demo_07_CountDownLatch {

    public static void main(String[] args) {

        beforeCountDown();

//        useCountDown();
    }

    public static void useCountDown() {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 0; i < 6; i++) {
//            final int tempInt = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t useCountDown离开");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " useCountDown大家都走了");
    }

    public static void beforeCountDown() {
        for (int i = 0; i < 6; i++) {
//            final int tempInt = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t beforeCountDown离开");
            }, String.valueOf(i)).start();
        }
        System.out.println(Thread.currentThread().getName() + "大家都走了");
    }
}
