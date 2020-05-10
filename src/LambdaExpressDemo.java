/**
 * @author joyeYang
 * @date 2020-05-09 18:49
 */

// 这个注解如果不写但是这个接口符合函数式接口的话，就会自己默认在底层加上这个注解
// java8 之后允许接口中有方法实现，default
@FunctionalInterface
interface Foo {
//    public void sayHello();
    public int sum(int a, int b);

    default int multi(int a, int b) {
        return a * b;
    }

    default int div(int a, int b) {
        return a / b;
    }

    public static int mul(int a, int b) {
        return a * b;
    }

    public static int mul2(int a, int b) {
        return a * b;
    }
}

/**
 * @FunctionalInterface
 * default
 */
public class LambdaExpressDemo {
    public static void main(String[] args) {

//        Foo foo = new Foo() {
//            @Override
//            public void sayHello() {
//                System.out.println("hello world");
//            }
//        };
//        foo.sayHello();

//        Foo foo = () -> { System.out.println("hello world"); };
//        foo.sayHello();

        Foo foo = (int x, int y) -> {
            return x + y;
        };
        System.out.println(foo.sum(3, 5));
        System.out.println(foo.multi(10,2));
        // 静态方法用类名调用
        System.out.println(Foo.mul(5, 8));

    }
}
