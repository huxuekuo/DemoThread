/**
 * synchronized代码块 1
 */
public class DemoThread06 {

    /**
     * 当前对象锁
     */
    public void run1(){
        synchronized (this){
            System.out.println(Thread.currentThread().getName()+"当前对象锁..");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 类锁
     */
    public void run2(){
        synchronized (DemoThread06.class){
            System.out.println(Thread.currentThread().getName()+"当前类锁..");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 任意对象锁
     */
    private Object objectLock = new Object();
    public void run3(){
        synchronized (objectLock){
            System.out.println(Thread.currentThread().getName()+"Object对象锁");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void test(int type) throws InterruptedException {
        DemoThread06 demoThread06 = new DemoThread06();
        DemoThread06 demoThread061 = new DemoThread06();
        new Thread(() -> {
            if(type == 1){
                demoThread06.run1();
            }else if(type == 2){
                demoThread06.run2();
            }else{
                demoThread06.run3();
            }
        },"t1").start();

        Thread.sleep(900);

        new Thread(() -> {
            if(type == 1){
                demoThread061.run1();
            }else if(type == 2){
                demoThread061.run2();
            }else{
                demoThread061.run3();
            }
        },"t2").start();
    }

    /**
     * 可以看出相同类型锁之间互斥,不同类型锁之间互不干扰
     * 不会有类锁比对象锁级别高的情况,没有级别高低
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        //test测试方法都存在互斥
        // test(1);
       // test(2);
       // test(3);

        //互不干扰
        DemoThread06 demoThread06 = new DemoThread06();
        DemoThread06 demoThread061 = new DemoThread06();
        new Thread(() -> {
            demoThread06.run2();


        }).start();
            Thread.sleep(100);
        new Thread(() -> {
            demoThread061.run1();


        }).start();
    }

}
