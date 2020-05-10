import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author joyeYang
 * @date 2020-05-09 21:58
 */
public class Demo_05_集合类不安全 {

    public static void main(String[] args) {

//        Map<String, String> map = new HashMap<>();
        // 实际生产中用这个指定容量的

        // java.util.ConcurrentModificationException
        Map<String, String> map1 = new ConcurrentHashMap<>(); // Collections.synchronizedMap(new HashMap<>());// new HashMap<>(1000);
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map1.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,8));
                System.out.println(map1);
            }, String.valueOf(i)).start();
        }

        new HashMap<>();

    }

    public void setNotSafe() {
        // HashSet底层调的是HashMap
        // set的add(x)就是map的put(x, obj) value是一个固定的Object对象，key才是关键
        // set的add(x) --> return map.put(e, PRESENT)==null;
        Set<String> set = new CopyOnWriteArraySet<>();//Collections.synchronizedSet(new HashSet<>());//new HashSet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            },"A").start();
        }
    }

    public void listNotSafe() {
//        List<String> list = new ArrayList<>(); // 线程不安全
//        List<String> list = new Vector<>(); // 线程安全，但是性能低，因为add方法加了synchronized
//        List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList<>();

        // 多线程操作集合报错：并发修改异常 java.util.ConcurrentModificationException
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }

}
