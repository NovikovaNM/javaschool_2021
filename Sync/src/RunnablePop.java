public class RunnablePop implements Runnable{
    @Override
    public void run() {
        try {
            while (!Sync.stopFlag) {
                synchronized (Sync.list) {
                    if (!Sync.list.isEmpty()) {
                        System.out.print("pop:");
                        for (int i = 0; i < Sync.list.size(); i++) {
                            System.out.print(Sync.list.get(i) + " ");
                            Thread.sleep(1);
                        }
                        System.out.println();
                        Sync.list.clear();
                    }
                    Sync.list.wait();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("interrupted pop");
        }
    }
}
