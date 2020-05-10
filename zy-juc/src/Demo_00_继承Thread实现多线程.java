/**
 * @author joyeYang
 * @date 2020-05-10 08:45
 */

class TestThread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"test---");
    }
}

public class Demo_00_继承Thread实现多线程 {

    public static void main(String[] args) {
        TestThread testThread = new TestThread();
        for (int i = 0; i < 10; i++) {
            new Thread(testThread, String.valueOf(i)).start();
        }
    }
}
