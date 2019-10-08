import java.util.ArrayList;
import java.util.List;

/**
 * 线程之间的通讯(原始通信方式)
 */
public class DemoThread09 {

    private volatile List<Integer> list = new ArrayList<>();

    private volatile boolean canGet = false;


    public void put() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(i);
            System.out.println("线程:"+Thread.currentThread().getName()+" 添加到第"+i+"元素");

            if(i == 5){
                canGet = true;
                System.out.println("线程:"+Thread.currentThread().getName()+"发出通讯");
            }

        }
    }

    public void get(){
        while (true){
            if(canGet){
                for (int i:
                     list) {
                    System.out.println("线程: "+Thread.currentThread().getName()+" 获取元素"+i);
                }
                break;
            }
        }
    }

    /**
     * 原始通信方式
     * @param args
     */
    public static void main(String[] args) {
        DemoThread09 demo = new DemoThread09();
        new Thread(() -> {
            demo.put();
        }).start();
        new Thread(() -> {
            demo.get();
        }).start();
    }

}

