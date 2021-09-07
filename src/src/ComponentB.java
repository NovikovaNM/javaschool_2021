import java.time.ZonedDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class ComponentB implements Runnable {
    public static AtomicInteger countB = new AtomicInteger();

    @Override
    public void run() {
            try {
                while (true) {
                    Thread.sleep(2000);
                    countB.incrementAndGet();
                    ProductionLine.queueB.put("B" + countB);
                    System.out.println("B" + countB + " added");
                }
            } catch (InterruptedException e) {
                System.out.println("runnable B interrupted");
            }
        }
    }
