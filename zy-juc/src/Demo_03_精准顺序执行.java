import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author joyeYang
 * @date 2020-05-09 20:38
 */

// 用Lock实现可以精准控制唤醒执行顺序
// 适用于 下单，减库存，扣款 等严格按照顺序执行的链路业务场景
// 实现的需求：三个线程按顺序打印A-B-C
// A打印5次，切换B打印10次，切换C打印15次，再切换回A

/**
 * 注意的一点是一定要在通知之前修改标志位，不然下一个没法正常执行
 */
class TestPrint {
    // 1-A 2-B 3-C
    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void doPrint5() throws InterruptedException {
        lock.lock();
        try {
            // 1- 判断
            while (number != 1) {
                // 等待
                condition1.await();
            }
            // 2- 干活
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            number = 2;
            // 3- 通知
            condition2.signal();
        }finally {
            lock.unlock();
        }
    }

    public void doPrint10() throws InterruptedException {
        lock.lock();
        try {
            // 1- 判断
            while (number != 2) {
                // 等待
                condition2.await();
            }
            // 2- 干活
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            number = 3;
            // 3- 通知
            condition3.signal();
        }finally {
            lock.unlock();
        }
    }

    public void doPrint15() throws InterruptedException {
        lock.lock();
        try {
            // 1- 判断
            while (number != 3) {
                // 等待
                condition3.await();
            }
            // 2- 干活
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            // 注意的一点是要修改标志位
            number = 1;
            // 3- 通知
            condition1.signal();
        }finally {
            lock.unlock();
        }
    }
}

public class Demo_03_精准顺序执行 {
    public static void main(String[] args) {
        TestPrint testPrint = new TestPrint();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    testPrint.doPrint5();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    testPrint.doPrint10();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    testPrint.doPrint15();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
    }
}
