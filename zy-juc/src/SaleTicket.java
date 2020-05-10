import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author joyeYang
 * @date 2020-05-09 18:21
 */

// 高内聚低耦合
class Ticket {
    private int number = 100;
    private Lock lock = new ReentrantLock();

    public synchronized void saleTicket() {

        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "\t卖出第: " + number-- + "\t还剩下: " + number);
            }
        }finally {
            lock.unlock();
        }

    }
}

public class SaleTicket {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        // Thread(Runnable target, String name)
        new Thread(() -> {for (int i=0; i < 40; i++) ticket.saleTicket(); }, "AAA").start();
        new Thread(() -> {for (int i=0; i < 40; i++) ticket.saleTicket(); }, "BBB").start();
        new Thread(() -> {for (int i=0; i < 40; i++) ticket.saleTicket(); }, "CCC").start();



        // 匿名内部类,不用去implement Runnable接口了，但是代码太多了这样
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 40; i++) {
//                    ticket.saleTicket();
//                }
//            }
//        }, "AAA").start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 30; i++) {
//                    ticket.saleTicket();
//                }
//            }
//        }, "BBB").start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 30; i++) {
//                    ticket.saleTicket();
//                }
//            }
//        }, "CCC").start();

    }
}
