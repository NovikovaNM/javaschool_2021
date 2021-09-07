import java.time.ZonedDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class ModuleMaker implements Runnable {
    public static AtomicInteger countModule = new AtomicInteger();

    @Override
    public void run() {
            try {
                while (true) {
                    if (!ProductionLine.queueA.isEmpty() && !ProductionLine.queueB.isEmpty()) {
                        ProductionLine.queueModule.put("Module" + countModule.incrementAndGet() + "(" + ProductionLine.queueA.take() + ProductionLine.queueB.take() + ")");
                        System.out.println("queueModule" + countModule + " added" );
                    } else {
                        Thread.sleep(500);
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("ModuleMaker interrupted");
            }
        }
    }

