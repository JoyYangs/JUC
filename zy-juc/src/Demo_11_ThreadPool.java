import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author joyeYang
 * @date 2020-05-10 14:25
 */
public class Demo_11_ThreadPool {

    /**
     * TimeUnit
     * LinkedBlockingQueue / SynchronousQueue
     *
     */
    public static void main(String[] args) {
        // 一池1个工作线程
        /**
         * return new FinalizableDelegatedExecutorService
         *             (new ThreadPoolExecutor(1, 1,
         *                                     0L, TimeUnit.MILLISECONDS,
         *                                     new LinkedBlockingQueue<Runnable>()));
         * */
        ExecutorService threadPool_01 = Executors.newSingleThreadExecutor();
        // 一池5个工作线程
        /**
        * return new ThreadPoolExecutor(nThreads, nThreads,
                                      0L, TimeUnit.MILLISECONDS,
                                      new LinkedBlockingQueue<Runnable>());
        * */
        /**
         * public LinkedBlockingQueue() {
         *         this(Integer.MAX_VALUE);
         *     }
         */
        ExecutorService threadPool_02 = Executors.newFixedThreadPool(5);
        // 一池n个工作线程，支持复用和扩容
        /**
        * return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                                      60L, TimeUnit.SECONDS,
                                      new SynchronousQueue<Runnable>());
        * */
        ExecutorService threadPool_03 = Executors.newCachedThreadPool();


        /**
         * return new ScheduledThreadPoolExecutor(corePoolSize);
         *
         *
         * public ScheduledThreadPoolExecutor(int corePoolSize) {
         *         super(corePoolSize, Integer.MAX_VALUE,
         *               DEFAULT_KEEPALIVE_MILLIS, MILLISECONDS,
         *               new DelayedWorkQueue());
         *     }
         * */
//        ExecutorService threadPool_03 = Executors.newScheduledThreadPool(5);


        try {
            for (int i = 0; i < 9; i++) {
                // 只有一个工作线程来一次处理
                TimeUnit.MILLISECONDS.sleep(300);
                threadPool_01.execute(() -> {
                    System.out.println( Thread.currentThread().getName() + " -----");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool_01.shutdown();
        }

        try {
            for (int i = 0; i < 9; i++) {
                // 5个工作线程依次处理，谁先处理完了就可以继续处理新的任务了
                TimeUnit.MILLISECONDS.sleep(300);
                threadPool_02.execute(() -> {
                    System.out.println( Thread.currentThread().getName() + " -----");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool_02.shutdown();
        }

        try {
            for (int i = 0; i < 9; i++) {
                // 支持扩容Integer.MAX_VALUE，
                // 当处理完之后可以继续处理其他任务的时候就会复用同一个
                // 不然就有多少任务开多少线程对应处理
//                TimeUnit.MICROSECONDS.sleep(1);
                threadPool_03.execute(() -> {
                    System.out.println( Thread.currentThread().getName() + " -----");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool_03.shutdown();
        }

        /**
         *
         * public ThreadPoolExecutor(int corePoolSize,
         *                               int maximumPoolSize,
         *                               long keepAliveTime,
         *                               TimeUnit unit,
         *                               BlockingQueue<Runnable> workQueue,
         *                               ThreadFactory threadFactory,
         *                               RejectedExecutionHandler handler) {
         *         if (corePoolSize < 0 ||
         *             maximumPoolSize <= 0 ||
         *             maximumPoolSize < corePoolSize ||
         *             keepAliveTime < 0)
         *             throw new IllegalArgumentException();
         *         if (workQueue == null || threadFactory == null || handler == null)
         *             throw new NullPointerException();
         *         this.acc = (System.getSecurityManager() == null)
         *             ? null
         *             : AccessController.getContext();
         *         this.corePoolSize = corePoolSize;
         *         this.maximumPoolSize = maximumPoolSize;
         *         this.workQueue = workQueue;
         *         this.keepAliveTime = unit.toNanos(keepAliveTime);
         *         this.threadFactory = threadFactory;
         *         this.handler = handler;
         *     }
         *
         *
         *
         */
    }
}
