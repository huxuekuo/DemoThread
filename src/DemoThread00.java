public class DemoThread00 {




    public static void main(String[] args) {

        UserServlet userServlet = new UserServlet();

        new Thread(() -> {
            userServlet.setPass("lisi", "77777");
        }).start();

        new Thread(() -> {
            userServlet.setPass("wangwu", "8888");
        }).start();

    }
}

class User {

    String name;
    String pass;

    public User(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    public synchronized void setPass(String name, String pass) {
        this.name = name;
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.pass = pass;
        System.out.println(Thread.currentThread().getName() + " name=" + this.name + " pass=" + this.pass);
    }
}

class UserServlet {

    private User user;

    public UserServlet() {
          user = new User("linda", "123456");
    }

    public void setPass(String name, String pass) {
        user.setPass(name, pass);
    }

}
