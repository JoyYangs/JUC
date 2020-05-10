import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author joyeYang
 * @date 2020-05-10 16:16
 *
 * 集合讲的是数据，流讲的是计算
 */

public class Demo_13_Stream {

    /** 链式编程 + 流式计算 */
    public static void main(String[] args) {
        testFourInnerCoreFunctionInterface();

//        list.stream.filter().map().sorted().limit()
    }


    public static void testFourInnerCoreFunctionInterface() {
        // 普通写法
//        Consumer<String> consumer = new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                System.out.println(s);
//            }
//        };
//
//        Supplier<String> supplier = new Supplier<String>() {
//            @Override
//            public String get() {
//                return "test";
//            }
//        };
//
//        Function<String, Integer> function = new Function<String, Integer>() {
//            @Override
//            public Integer apply(String s) {
//                return s.length();
//            }
//        };
//
//        Predicate<String> predicate = new Predicate<String>() {
//            @Override
//            public boolean test(String s) {
//                return s.isEmpty();
//            }
//        };

        Consumer<String> consumer = s -> { System.out.println(s); };
        consumer.accept("joye");

        Supplier<Integer> supplier = () -> { return 10; };
        System.out.println(supplier.get());

        Function<String, Integer> function = s -> { return s.length(); };
        System.out.println(function.apply("joye"));

        Predicate<String> predicate = s -> { return s.isEmpty(); };
        System.out.println(predicate.test("joye"));

    }
}
