import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author joyeYang
 * @date 2020-05-10 13:02
 */

class RWCacheTest {
    private volatile Map<String, String> map = new HashMap<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    // 读操作用读锁，写操作用写锁
    public void put(String key, String value) {
        lock.writeLock().lock();

        try {
            TimeUnit.MILLISECONDS.sleep(300);
            System.out.println(Thread.currentThread().getName() + " ---- 写入数据");
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + " ---- 写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void get(String key) {
        lock.readLock().lock();

        try {
            TimeUnit.MILLISECONDS.sleep(300);
            System.out.println(Thread.currentThread().getName() + " ---- get数据");
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + " ---- get完成 " + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }


    // 这样写有问题，读写不一致
//    public void put(String key, String value) {
//        System.out.println(Thread.currentThread().getName() + " ---- 写入数据");
//        map.put(key, value);
//        System.out.println(Thread.currentThread().getName() + " ---- 写入完成");
//    }
//
//    public void get(String key) {
//        System.out.println(Thread.currentThread().getName() + " ---- get数据");
//        Object result = map.get(key);
//        System.out.println(Thread.currentThread().getName() + " ---- get完成 " + result);
//    }
}

public class Demo_10_ReadWriteLock {

    public static void main(String[] args) {
        RWCacheTest test = new RWCacheTest();

        for (int i = 1; i <= 5; i++) {
            final int tempI = i;
            new Thread(() -> {
                test.put(tempI + "", "数据 " + tempI);
            }, String.valueOf(i)).start();
        }

        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 1; i <= 5; i++) {
            final int tempI = i;
            new Thread(() -> {
                test.get(tempI + "");
            }, String.valueOf(i)).start();
        }
    }
}
