package show;

class SuperClass {
    public static int value = 10;

    static {
        value = 2;
    }
}

class SubClass extends SuperClass {
    public static int result = value;
}


public class NotInit {
    public static void main(String[] args) {
        System.out.println(System.getProperty("java.ext.dirs"));
    }
}
