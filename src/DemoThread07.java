/**
 * synchronized代码块 2
 */
public class DemoThread07 {

    private String lock = "lock handler";

    /**
     * 不要在线程中修改对象锁的引用,引用被修改会导致锁失效
     */
    public void method(){
        synchronized (lock){
            System.out.println(Thread.currentThread().getName()+"开始");
            //锁的引用被改变,则其他线程可获得锁,引起并发问题
            lock = "change lock handler";
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"结束");
        }
    }

    /**
     * 在对象中修改对象的属性,而不修改对象的引入则不会产生线程安全问题
     */
    private  Person person = new Person();
    public void method1(String name,int age){
       synchronized (person){
           System.out.println(Thread.currentThread().getName()+"开始 修改前"+person.toString());
           //打开注解:对象的引用发生变化,t1,t2线程同时进入方法,导致线程安全问题
           //person =new Person();
           person.setAge(age);
           person.setName(name);
           System.out.println(person.toString());
           try {
               Thread.sleep(1000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           System.out.println(Thread.currentThread().getName()+"结束 修改后"+person.toString());
       }
    }

    public static void main(String[] args) {
        DemoThread07 demo = new DemoThread07();

        new Thread(() -> {demo.method1("xiaobai",12);}).start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {demo.method1("libai",18);}).start();
    }

}

class Person{

    private String name;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "name="+this.name+" age="+this.age;
    }
}


