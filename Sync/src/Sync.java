import java.util.ArrayList;

public class Sync {
    public static volatile boolean stopFlag = false;
    public static ArrayList<Long> list = new ArrayList<>();

    public static void main(String[] args) {
        RunnableAdd runnableAdd = new RunnableAdd();
        Thread threadAdd = new Thread(runnableAdd);
        RunnablePop runnablePop = new RunnablePop();
        Thread threadPop = new Thread(runnablePop);
        try {
            threadAdd.start();
            threadPop.start();
            Thread.sleep(4000);
            stopFlag = true;
        } catch (InterruptedException e){
            System.out.println("interrupted");
        }
        System.out.println("The end");
    }
}
