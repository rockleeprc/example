package show;

public class Person {
    static {
        System.out.println(Thread.currentThread().getName() + " init");
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
