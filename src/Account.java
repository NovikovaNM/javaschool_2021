public class Account {
    public int balance;
    public Account (int balance){
        this.balance = balance;
    }

    public boolean transfer(Account accTo, int sum) {
        if (sum>0) {
            if (this.balance >= sum) {
                synchronized (this) {
                    try {
                        Thread.sleep(2000);
                        synchronized (accTo) {
                            this.balance -= sum;
                            accTo.balance += sum;
                            return true;
                        }
                    } catch(InterruptedException e) {
                        return false;
                    }
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}

