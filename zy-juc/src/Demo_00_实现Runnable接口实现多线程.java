/**
 * @author joyeYang
 * @date 2020-05-10 08:46
 */

class TestRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}

public class Demo_00_实现Runnable接口实现多线程 {

    public static void main(String[] args) {
        TestRunnable testRunnable = new TestRunnable();
        for (int i = 0; i < 10; i++) {
            new Thread(testRunnable, String.valueOf(i)).start();
        }
    }
}
