import java.util.ArrayList;

public class Sync {
    public static volatile boolean stopFlag = false;
    public static ArrayList<Long> list = new ArrayList<>();

    public static void main(String[] args) {
        int sec;
        sec = InputChoice.choiceInt("Введите сколько секунд будет длиться демонстрация, от 1 до 60 ",1,60);
        if (sec !=0) {
            RunnableAdd runnableAdd = new RunnableAdd();
            Thread threadAdd = new Thread(runnableAdd);
            RunnablePop runnablePop = new RunnablePop();
            Thread threadPop = new Thread(runnablePop);
            try {
                threadAdd.start();
                threadPop.start();
                Thread.sleep(sec * 1000);
                stopFlag = true;
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }
        }
        System.out.println("The end");
    }
}
