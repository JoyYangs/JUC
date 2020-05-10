/**
 * @author joyeYang
 * @date 2020-05-09 19:15
 */

// 生产者消费者模式
// 多线程交互的要防止多线程的虚假唤醒
// 需求：两个线程操作同一数据初始值为0，+1-1，操作10次，最终还是0
class Resource {
    private int number = 0;
    public synchronized void increment() throws InterruptedException {
        if (number != 0) {
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName() + "\t"+ number);
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        if (number == 0) {
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        this.notifyAll();
    }
}
public class Demo_01_生产者消费者的场景 {
    public static void main(String[] args) {
        Resource resource = new Resource();
        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                try {
                    Thread.sleep(100);
                    resource.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                try {
                    Thread.sleep(200);
                    resource.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                try {
                    Thread.sleep(300);
                    resource.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                try {
                    Thread.sleep(400);
                    resource.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}
