/**
 * 脏读
 */
public class DemoThread03 {

    private String name ="lishi";
    private String addree = "daxing";

    public synchronized void setValue(String name,String addree) {
        this.name = name;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.addree = addree;
        System.out.println("setValue name="+this.name+" addree="+this.addree);
    }

    public synchronized void getValue() {
        System.out.println("getValue name="+this.name+" addree="+this.addree);
    }


    /**
     * 当setValue方法延迟两秒中,进行了读取操作,就会产生脏读,将getValue方法加上synchronized关键字,当getValue进行读取操作时就会等待setValue方法结束进行读取操作
     *
     *  也可以理解为:
     *              同步操作添加操作没有完成时,异步的读取操作结束,会产生脏读
     *
     *  由于同步操作与异步操作执行的个性,如果不从全局上进行并发设计很可能会引起数据的不一致,也就是所谓的脏读
     *
     *  多个线程访问同一个资源,在一个线程修改数据的过程中,有另外的线程来读取数据,就会引起脏读的产生
     *
     *  为了避免脏读,我们一定保证数据的操作的原子性,并且对读取操作进行同步控制
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        DemoThread03 thread03 = new DemoThread03();

        new Thread(() -> {
            thread03.setValue("hello","shijiaz");
        }).start();

        Thread.sleep(1000);
        thread03.getValue();
    }
}
