public class DemoThread08 {

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public void execute(){
        synchronized (lock1){
            System.out.println("线程"+Thread.currentThread().getName()+"开始,获取到了lock1的锁,执行execute方法");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock2){
                System.out.println("线程"+Thread.currentThread().getName()+"开始,获取到了lock2的锁,执行execute方法");
            }
        }
    }

    public void execute_backup(){
        synchronized (lock2){
            System.out.println("线程"+Thread.currentThread().getName()+"开始,获取到了lock1的锁,执行execute_backup方法");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock1){
                System.out.println("线程"+Thread.currentThread().getName()+"开始,获取到了lock2的锁,执行execute_backup方法");
            }
        }
    }

    /**
     * 是指两个或者两个以上的进程在执行过程中,由于竞争资源或者由于彼此通信而造成的一种堵塞的现象
     * ,若无外力作用,他们将无法推进下去,此时称系统处于死锁状态或者系统产生了死锁,这些永远在互相的等待
     * 的进程称为死锁进程
     *
     * @param args
     */
    public static void main(String[] args) {
        DemoThread08 demo = new DemoThread08();
        new Thread(() -> {
            demo.execute();
        }).start();


        new Thread(() -> {
            demo.execute_backup();
        }).start();

    }
}
