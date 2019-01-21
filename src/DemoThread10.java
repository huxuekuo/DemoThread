import java.util.ArrayList;
import java.util.List;

/**
 * 线程通信方式(wait/notify)
 */
public class DemoThread10 {
    private volatile List<Integer> list = new ArrayList<>();
    private Object lock = new Object();

    public void put() {
        synchronized (lock){
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                list.add(i);
                System.out.println("线程:"+Thread.currentThread().getName()+" 添加到第"+i+"元素");
                if(i == 5){
                    lock.notify();//不释放锁
                    System.out.println("线程:"+Thread.currentThread().getName()+"发出通讯");
                }

            }
        }

    }

    public void get(){
       synchronized (lock){
           if(list.size() == 0){
               System.out.println("线程:"+Thread.currentThread().getName()+" 业务数据还没有准备好,则发起等待");
               try {
                   lock.wait(); //释放锁
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
           for (int i:
                   list) {
               System.out.println("线程:"+Thread.currentThread().getName()+"获取的元素="+i);
           }
       }
    }

    /**
     * wait/notify方式通讯,
     *
     * 每个线程都是独立运行的个体,线程通讯能让多个线程之间协调工作
     *
     * Object类中wait/notify方法可以实现线程的通讯
     *
     * wait与notify必须与synchronized一同使用
     *
     * wait释放锁,notify不释放锁
     *
      *
     * @param args
     */
    public static void main(String[] args) {
        DemoThread10 demo = new DemoThread10();
        new Thread(() -> {
            demo.get();
        },"get").start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            demo.put();
        },"put").start();
    }
}
