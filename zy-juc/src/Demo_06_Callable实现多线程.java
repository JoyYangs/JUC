import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author joyeYang
 * @date 2020-05-10 07:48
 */

/**
 * 多线程第三种方法：实现Callable接口
 */
class MyThread implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("--- callable ---");
        TimeUnit.SECONDS.sleep(4);
        return "fanhuizhi";
    }
}

public class Demo_06_Callable实现多线程 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread myThread = new MyThread();

        // public Thread(Runnable target, String name)
        // 这里不能接收Callable类型的参数
        // 所以需要找到一个继承了Runnable接口的同时又支持Callable接口的类
//        Thread thread = new Thread(myThread);

        FutureTask futureTask = new FutureTask(myThread);
        // 两个线程操作的是一个FutureTask对象，只会调用一次，相当于有了缓存数据
        Thread thread = new Thread(futureTask, "A");
        Thread thread2 = new Thread(futureTask, "B");
        thread.start();
        thread2.start();
        System.out.println(Thread.currentThread().getName() + "******");
        System.out.println(futureTask.get());
        System.out.println("--------");

    }
}
