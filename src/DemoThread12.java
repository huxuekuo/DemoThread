import java.util.ArrayList;
import java.util.List;

/**
 * 堵塞队列
 */
public class DemoThread12 {

    private List<String> result = new ArrayList<>();
    private Object lock = new Object();


    private int maxSize;

    public DemoThread12(int maxSize) {
        this.maxSize = maxSize;
    }

    public void put(String value) {
        synchronized (lock) {
            if (this.result.size() == maxSize) {
                System.out.println("线程:" + Thread.currentThread().getName() + "数据存储已满,无法添加数据,put等待....");
                try {
                    lock.wait(); //释放资源
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.result.add(value);
            lock.notifyAll();
            System.out.println("线程:" + Thread.currentThread().getName() + " 添加元素" + value + ",并且发送通知其他线程可以操作了");

        }
    }

    public void take() {
        synchronized (lock) {
            if (this.result.size() == 0) {
                System.out.println("线程:" + Thread.currentThread().getName() + "数据为空,无法取出数据,take等待....");
                try {
                    lock.wait(); //释放资源
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String result = this.result.get(0);
            this.result.remove(0);
            lock.notifyAll();
            System.out.println("线程:" + Thread.currentThread().getName() + "取出元素" + result + ",并且发送通知其他线程可以操作了");
        }
    }

    public static void main(String[] args) {
        DemoThread12 demo = new DemoThread12(5);
      new Thread(() -> {
            demo.put("1");
            demo.put("2");
            demo.put("3");
            demo.put("4");
            demo.put("5");
            demo.put("6");
        }, "t1>>>PUT").start();
 /*       new Thread(() -> {
            demo.put("11");
            demo.put("22");
            demo.put("33");
            demo.put("44");
            demo.put("55");
            demo.put("66");
            demo.put("77");
        }, "t2>>>PUT").start();*/
        new Thread(() -> {
            demo.take();
            demo.take();
            demo.take();
            demo.take();
            demo.take();
            demo.take();
            demo.take();
        }, "t3>>>TAKE").start();
        new Thread(() -> {
            demo.take();
            demo.take();
            demo.take();
            demo.take();
            demo.take();
            demo.take();
        }, "t4>>>TAKE").start();
    }
}
