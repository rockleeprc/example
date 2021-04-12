package show;

class Father {
    // 属性不具有多态性
    int age; // 默认初始化

    public Father() {
        print();
        age = 10;
    }

    public void print() {
        System.out.println("father's age " + age);
    }
}

class Son extends Father {
    int age = 20;

    public Son() {
        this.print();
        age = 40;
    }

    public void print() {
        System.out.println("son's age " + age);
    }

}

public class SonTest {
    public static void main(String[] args) {
        Father f = new Son();
        System.out.println(f.age);
    }
}
