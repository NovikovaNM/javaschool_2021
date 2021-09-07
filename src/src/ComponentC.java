import java.time.ZonedDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class ComponentC implements Runnable {
    public static AtomicInteger countC = new AtomicInteger();

    @Override
    public void run() {
            try {
                while (true) {
                    Thread.sleep(3000);
                    countC.incrementAndGet();
                    ProductionLine.queueC.put("C" + countC);
                    System.out.println("C" + countC + " added");
                }
            } catch (InterruptedException e) {
                System.out.println("runnable C interrupted");
            }
        }
    }