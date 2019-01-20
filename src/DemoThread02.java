/**
 * 对象的同步锁与异步锁
 */
public class DemoThread02 {

    /**
     *prin1 为同步执行,
     * prin2 为异步执行
     *
     * 对象锁只会对加了synchronized的方法加锁,不影响print2执行,但是当prin2添加的synchronized关键字,prin2就会等待prin1执行完毕后执行.
     *
     * 也就是说一个对象中,只有加了synchronized关键字的方法会互斥,不影响异步方法
     *
     * 类中有两个方法添加了synchronized关键字,两个线程同时调用两个方法相互之间还是会有所竞争的,因为两个方法是在同一个对象中,而我们是在对象上加锁
     *
     */

    public synchronized void prin1() {
        System.out.println(Thread.currentThread().getName() + "hello!");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void prin2() {
        System.out.println(Thread.currentThread().getName() + "hello!");
    }

    public static void main(String[] args) {
        DemoThread02 thread03 = new DemoThread02();

        new Thread(() -> {
            thread03.prin1();
        }).start();
        new Thread(() -> {
            thread03.prin2();
        }).start();
    }

}
