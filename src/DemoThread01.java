/**
 * 一个对象一把锁,同一个对象的锁互斥,两个对象没有关系
 */
public class DemoThread01 {

    private static int count = 0;

    public synchronized static void add(){
        count++;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName()+"count="+count);
    }

    /**
     * 当有两个对象的时候
     * @param args
     */
    public static void main(String[] args) {
        twoObject();
    }

    /**
     * 当两个对象使用add方法是,没有任何关系,一个对象一个锁
     * 但是当count变成static变量时,单纯的加synchronized是没有用的需要升级为class锁,将add方法添加static关键字
     */
    public static void twoObject(){
        DemoThread01 thread0 = new DemoThread01();
        DemoThread01 thread1 = new DemoThread01();

        new Thread(() -> {
            thread0.add();
        }).start();
        new Thread(() -> {
            thread1.add();
        }).start();
    }

    /**
     * 当使用一个对象时加上synchronized,升级为对象锁
     *
     */
    public static void oneObject(){
        DemoThread01 thread0 = new DemoThread01();
        new Thread(() -> {
            thread0.add();
        }).start();
        new Thread(() -> {
            thread0.add();
        }).start();
    }
}
