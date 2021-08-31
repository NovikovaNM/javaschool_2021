public class RunnableAdd implements Runnable{
    public static volatile int ten = 0;
    @Override
    public void run() {
        try {
            while (!Sync.stopFlag) {
                if (Sync.list.isEmpty()) {
                    synchronized (Sync.list) {
                        for (long i = 0; i < 5; i++) {
                            Thread.sleep(10);
                            Sync.list.add(i + ten);
                            System.out.println("added " + (i + ten));
                        }
                        ten +=10;
                        Sync.list.notify();
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println("interrupted add");
        }
    }
}
