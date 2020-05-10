import java.util.concurrent.*;

/**
 * @author joyeYang
 * @date 2020-05-10 15:31
 */
public class Demo_12_自定义线程池 {

    public static void main(String[] args) {

        // 自定义线程池
        ExecutorService threadPool = new ThreadPoolExecutor(2,
                5,
                3L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        /**
         * 当队列和线程池都满了之后会采用拒绝策略来处理新来的任务
         * AbortPolicy(默认值)策略就会抛出 java.util.concurrent.RejectedExecutionException
         * CallerRunsPolicy: 既不抛异常，也不处理任务，把任务返回给调用者，所以会出现main线程的打印数据
         * DiscardPolicy: 直接抛弃任务
         * DiscardOldestPolicy: 抛弃等待最久的任务，将新的任务加入队列中再处理
         */
        try {
            for (int i = 1; i <= 10; i++) {
                final int tempI = i;
                threadPool.execute(() -> {
                    System.out.println( Thread.currentThread().getName() + " -----" + tempI);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }

    }
}
