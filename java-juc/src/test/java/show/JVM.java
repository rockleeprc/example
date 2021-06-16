package show;

import sun.misc.Launcher;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class JVM {
    static int i = 0;

    static {
        i = 10;
//        System.out.println(j);
        j = 10;
    }

    static int j;

    static final Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        t16();
    }


    /**
     * 生成代理类
     */
    public static void t16(){
        byte[] classFile = ProxyGenerator.generateProxyClass("$DataInputImplProxy0", DataInputImpl.class.getInterfaces());
        String path = "/Users/admin/WorkSpace/github/prc/learning-c/DataInputImplProxy.class";
        try(FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(classFile);
            fos.flush();
            System.out.println("代理类class文件写入成功");
        } catch (Exception e) {
            System.out.println("写文件错误");
        }
    }
    public void t15(boolean flag) {
        System.out.println(flag);
    }

    /**
     * 软引用被GC
     * -Xms10m -Xmx10m
     */
    public void t14() {
        SoftReference softReference = new SoftReference(new Person());
        System.out.println(softReference.get());

        try {
            byte[] buffer = new byte[1024 * 1024 * 7]; // 资源紧张直接溢出
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            System.out.println(softReference.get()); // 被GC掉

        }
    }

    public void t13() {
        String s1 = new String("a");
        s1.intern();
        String s2 = "a";
        /*
        s1 是new String的地址，两个对象
        s2 是常量池中的地址，一个对象
         */
        System.out.println(s1 == s2);

        String s3 = new String("2") + new String("2");
        s3.intern();
        String s4 = "22";
        /*
            s3进入常量池，但是没有实际内容，引用的是s3使用的地址，
            即：常量池在堆空间中，常量池不仅可以保存内容，也可以使用引用地址，没必要在常量池中在保存11，直接使用s3的地址
            s4使用常量池中的22，常量池中的22有引用的s3的地址
         */
        System.out.println(s3 == s4);
    }

    /**
     * 字符串拼接，查看字节码文件
     */
    public void t12() {
        String s1 = "a";// final
        String s2 = "b";// final
        String s3 = s1 + s2;
        String s4 = "ab";
        System.out.println(s3 == s4);
    }

    public static void t11() {
        String s1 = "a" + "b" + "c"; // 编译期拼接为abc
        String s2 = "abc";
        System.out.println(s1 == s2);
    }

    /**
     * 逃逸分析
     * 1、开启逃逸分析、关闭逃逸分析，通过visualVM采样查看内存对象存活个数
     * 2、开启逃逸分析、关闭逃逸分析，观察程序执行时间
     * -Xms256m -Xmx256m -XX:-DoEscapeAnalysis -XX:+PrintGCDetails
     */
    public static void t10() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            alloc();
        }
        long end = System.currentTimeMillis();
        System.out.println("alloc ending. it cost " + (end - start) + " ms");
        try {
            TimeUnit.SECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void alloc() {
        Person p = new Person();
    }


    /**
     * 逃逸分析
     *
     * @return
     */
    public StringBuffer t9() {
        StringBuffer sb = new StringBuffer();
        sb.append("A");
        sb.append("B");
//        return sb.toString();// sb 对象没有逃逸，可能被分配到栈上
        return sb;// sb 对象逃逸
    }

    /**
     * 大对象直接进入老年代
     * -Xms60m -Xmx60m -XX:NewRatio=2 -XX:SurvivorRatio=8 -XX:+PrintGCDetails
     */
    public static void t8() {
        byte[] buffer = new byte[1024 * 1024 * 20];
    }

    /**
     * oom演示
     * -Xms600m -Xmx600m
     */
    public static void t7() {
        List<byte[]> list = new ArrayList<>();
        while (true) {
            list.add(new byte[1024 * 1024 * 10]);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 默认堆内存空间大小
     */
    public static void t6() {
        long initial = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        long max = Runtime.getRuntime().maxMemory() / 1024 / 1024;
        System.out.println(initial + "m");
        System.out.println(max + "m");
    }

    public void t5(int i, int j) {
        long w = 10L;
        int y = 1;
        int x = 2;
    }

    public void t4() {
        int i1 = 1;
        i1++;

        int i2 = 2;
        ++i2;
    }


    /**
     * 局部变量表slot复用
     */
    public void slot() {
        char c = 'c';
        {
            char a = 'a';
            int b = 0, j = 1;
            byte y = 127;
        }
        byte j = 9;
        float x = 0.0F;
    }

    /**
     * 局部变量表索引0位置存放this
     */
    public void slotThis() {
        String s1 = "A";
        String s2 = s1;
        int i = 0;
        int j = 9;
        double d = 10.0D;
        long g = 1L;
        int y = 1;
    }


    private static int count = 0;

    /**
     * main调用main 栈空间溢出
     * 默认值 count 10821
     * -Xss256 count 1871
     * 设置 -Xss128k时抛 The stack size specified is too small, Specify at least 160k
     */
    public static void stackOverflowError() {
//        System.out.println(count);
//        count++;
//        main(args);
    }

    /**
     * clinit 只会被加载一次，多线程被加锁
     */
    public static void t3() {
        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + " will init Person");
            Person p = new Person();
            System.out.println("task ending");
        };

        Thread t1 = new Thread(task, "t1");
        Thread t2 = new Thread(task, "t2");

        t1.start();
        t2.start();
    }

    /**
     * ClassLoader 加载方式
     */
    public static void t2() {
        // 通过Class对象获取
        String.class.getClassLoader();
        // 通过Thread上下文
        Thread.currentThread().getContextClassLoader();
        // 通过ClassLoader直接获取
        ClassLoader.getSystemClassLoader();
        ClassLoader.getSystemClassLoader().getParent();
    }

    /**
     * 类加载器 加载路径
     */
    public static void t1() {
        // bootstrap Classloader 加载路径
        URL[] urls = Launcher.getBootstrapClassPath().getURLs();
        for (URL url : urls) {
            System.out.println(url);
        }

        // ext  Classloader加载路径
        String extDirs = System.getProperty("java.ext.dirs");
        for (String dir : extDirs.split(";")) {
            System.out.println(dir);
        }
    }

}
