/**
 * 线程之间通信(notifyAll)
 */
public class DemoThread11 {

    public synchronized void run1() {
        System.out.println("enter the run1 method");
//        this.notify();
        this.notifyAll();
        System.out.println("run1 send notification");
    }

    public synchronized void run2() {
        System.out.println("enter the run2 method");
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("run2 execute end");
//        this.notify();
//        System.out.println("run2 send notification");
    }

    public synchronized void run3() {
        System.out.println("enter the run3 method");
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("run3 execute end");
//        this.notify();
//        System.out.println("run3 send notification");
    }


    /**
     * notify只会通知一个wait进程,并把锁给他,不会产生锁竞争的关系
     * 但是当有多个wait时,就需要对应多个notify,完成类似链操作
     *
     * 解决方式,可以使用一个notifyAll通知所有wait中的线程,会产生所竞争的问题
     *
     * @param args
     */
    public static void main(String[] args) {
        DemoThread11 demo = new DemoThread11();

        new Thread(() -> {
            demo.run2();
        }).start();
        new Thread(() -> {
            demo.run3();
        }).start();
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            demo.run1();
        }).start();
    }
}
