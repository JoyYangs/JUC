/**
 * @author joyeYang
 * @date 2020-05-09 19:26
 */
/**
 * @author joyeYang
 * @date 2020-05-09 19:15
 */

// 生产者消费者模式之防止虚假唤醒 while
// 多线程交互的要防止多线程的虚假唤醒(解决方法就是一直判断，也就是if换成while)
// 需求：两个线程操作同一数据初始值为0，+1-1，操作10次，最终还是0  --- 用if没有问题
// 但是多个线程操作的时候就不能用if
class Resource1 {
    private int number = 0;
    public synchronized void increment() throws InterruptedException {
        while (number != 0) {
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName() + "\t"+ number);
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        while (number == 0) {
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        this.notifyAll();
    }
}
public class Demo_02_防止虚假唤醒 {
    public static void main(String[] args) {
        Resource1 resource = new Resource1();
        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                try {
//                    Thread.sleep(100);
                    resource.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                try {
//                    Thread.sleep(200);
                    resource.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                try {
//                    Thread.sleep(300);
                    resource.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                try {
//                    Thread.sleep(400);
                    resource.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}

