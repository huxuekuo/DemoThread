/**
 * 锁重入
 */
public class DemoThread04 {

    public synchronized void run1() {
        System.out.println(Thread.currentThread().getName() + "run1...");
        run2();
    }

    public synchronized void run2() {
        System.out.println(Thread.currentThread().getName() + "run2...");
    }

    public static void main(String[] args) {
        //同一个对象的锁重入
     /*   DemoThread04 demoThread04 = new DemoThread04();
        new Thread(() -> {
            demoThread04.run1();
        }).start();*/

        //父子类锁重入

        new Thread(() -> {
            Child child = new Child();
            child.runChild();
        }).start();
    }

}


class Parent {
    public int i = 10;

    public synchronized void runParent() {
        try {
            i--;
            System.out.println("Parent>>>>i=" + i);
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Child extends Parent {

        public synchronized void runChild() {
            while (i > 0) {
                i--;
                System.out.println("child>>>>i=" + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                super.runParent();
            }
        }

    }

