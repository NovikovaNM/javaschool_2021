import java.time.ZonedDateTime;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class ComponentA implements Runnable {
    public static AtomicInteger countA = new AtomicInteger();

    @Override
    public void run() {
            try {
                while (true) {
                    Thread.sleep(1000);
                    countA.incrementAndGet();
                    ProductionLine.queueA.put("A" + countA);
                    System.out.println("A" + countA + "added "+ ZonedDateTime.now().toString());
                }
            } catch (InterruptedException e) {
                System.out.println("runnable A interrupted");
            }
        }
    }
