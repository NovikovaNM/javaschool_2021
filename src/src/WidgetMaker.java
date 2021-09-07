import java.util.concurrent.atomic.AtomicInteger;

public class WidgetMaker implements Runnable{
    public static AtomicInteger countWidget = new AtomicInteger();

    @Override
    public void run() {
            try {
                while (true) {
                    if (!ProductionLine.queueC.isEmpty() && !ProductionLine.queueModule.isEmpty()) {
                        System.out.println("Widjet" + countWidget.incrementAndGet() + ": " + ProductionLine.queueModule.take() + ProductionLine.queueC.take());
                    } else {
                        Thread.sleep(500);
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("WidgetMaker interrupted");
            }
        }
    }