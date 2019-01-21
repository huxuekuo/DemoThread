/**
 * 抛异常与锁的关系
 */
public class DemoThread05 {

    public int i = 0;

    public synchronized void run1() {
        while (true) {
            i++;

            System.out.println("run1>>>>i=" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i == 10) {
               throw new RuntimeException();
            }
        }
    }

    public synchronized void run2() {
        System.out.println("run2>>>>i=" + i);
    }

    /**
     *synchronized修饰的方法抛出异常以后主动释放锁
     * 当程序异常时可以防止死锁,无法释放
     * 异常释放锁可能导致数据不一致
     * @param args
     */
    public static void main(String[] args) {
        DemoThread05 thread05 = new DemoThread05();
        new Thread(() -> {

            thread05.run1();
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread05.run2();

    }
}
