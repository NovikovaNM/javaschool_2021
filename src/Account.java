public class Account {
    public int balance;
    public Account (int balance){
        this.balance = balance;
    }

    public boolean transfer(Account accountTo, int sum) {
        if (sum>0 && this.balance >= sum) {
                synchronized (this) {
                    try {
                        Thread.sleep(2000);
                        synchronized (accountTo) {
                            this.balance -= sum;
                            accountTo.balance += sum;
                            return true;
                        }
                    } catch(InterruptedException e) {
                        return false;
                    }
                }
            } else {
                return false;
            }
    }
}

