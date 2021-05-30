package show;

public class AllocOnStack {
    public static class User {
        public int id;
        public String name;
    }

    public static void alloc() {
        User u = new User();
        u.id = 1;
        u.name = "java";

    }

    /**
     * -Xmx10m -Xms10m -XX:-UseTLAB -XX:+DoEscapeAnalysis -XX:+EliminateAllocations -XX:+PrintGC
     * UseTLAB：关闭tlab
     * DoEscapeAnalysis：逃逸分析
     * EliminateAllocations：标量替换，默认开启
     */
    public static void main(String[] args) {
        long b = System.currentTimeMillis();
        for(int i=0;i<100000000;i++){
            alloc();
        }
        long e = System.currentTimeMillis();
        System.out.println(e - b);
    }
}
