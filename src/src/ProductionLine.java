import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProductionLine {
    public static ArrayBlockingQueue<String> queueA = new ArrayBlockingQueue<>(1, true);
    public static ArrayBlockingQueue<String> queueB = new ArrayBlockingQueue<>(1, true);
    public static ArrayBlockingQueue<String> queueC = new ArrayBlockingQueue<>(1, true);
    public static ArrayBlockingQueue<String> queueModule = new ArrayBlockingQueue<>(1, true);
    private static ExecutorService service = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {
        int workTime = InputChoice.choiceInt("Введите сколько секунд будет длиться демонстрация, от 2 до 60", 2, 60);
        if (workTime>0) {
        Runnable taskComponentA = new ComponentA();
        Runnable taskComponentB = new ComponentB();
        Runnable taskComponentC = new ComponentC();
        Runnable taskModuleMaker = new ModuleMaker();
        Runnable taskWidgetMaker = new WidgetMaker();
        service.submit(taskComponentA);
        service.submit(taskComponentB);
        service.submit(taskComponentC);
        service.submit(taskModuleMaker);
        service.submit(taskWidgetMaker);
        try {
            Thread.sleep(workTime * 1000);
        } catch (InterruptedException e) {
            System.out.println("main interrupted");
        }
        service.shutdownNow();
    }
        System.out.println("The end");
    }
}
