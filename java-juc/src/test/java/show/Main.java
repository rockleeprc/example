package show;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class Main {
    private volatile long stage;
    private static long stageOffset ;
    private static Unsafe unsafe;

    static {
        try {
            Field theUnsafeField = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafeField.setAccessible(true);
            unsafe = (Unsafe) theUnsafeField.get(null);
            stageOffset = unsafe.objectFieldOffset(Main.class.getDeclaredField("stage"));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Main m = new Main();
        boolean result = unsafe.compareAndSwapInt(m, stageOffset, 0, 10);
        System.out.println(result);
        System.out.println(m.stage);
    }
}
