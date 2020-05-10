import java.util.concurrent.TimeUnit;

/**
 * @author joyeYang
 * @date 2020-05-09 21:00
 */

class Phone {

    public static synchronized void sendEmail() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("------sendEmail");
    }

    public synchronized void sendSMS() {
        System.out.println("--------sendSMS");
    }

    public void hello() {
        System.out.println("-----hello");
    }
}

/**
 * 1- 标准访问：先邮件还是短信 ： 邮件
 * 2- 邮件暂停4秒，先邮件还是短信 ：邮件
 * 3- 新增一个普通方法，先邮件还是普通方法: hello
 * 4- 两个手机，先邮件还是短信 : 短信
 * 5- 两个静态同步方法，先邮件还是短信 : 邮件
 * 6- 两个静态同步方法，两个手机，先邮件还是短信 : 邮件
 * 7- 1个静态同步方法sendEmail，一个普通同步方法sendSMS，先邮件还是短信 : 短信
 * 8- 1个静态同步方法sendEmail，一个普通同步方法sendSMS，两个手机，先邮件还是短信 : 短信
 */
public class Demo_04_8锁 {

    public static void main(String[] args) {
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            phone1.sendEmail();
        }, "A").start();

        new Thread(() -> {
            phone1.sendSMS();
//            phone1.hello();
//            phone2.sendSMS();
        }, "B").start();
    }
}
