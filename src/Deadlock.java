import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

public class Deadlock {

    public static int choiceInt(String msg, int min, int max) {
        int result = -1;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            do {
                System.out.println(msg + " Для выхода введите 0");
                try {
                String str = reader.readLine();
                    result = Integer.parseInt(str);
                    if (result >= min && result <= max) {
                        return result;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка! Нужно ввести целое число");
                }
        } while (result != 0) ;
    } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
        Account acc1 = new Account(5000);
        Account acc2 = new Account(200);
        Runnable runnable1 = () -> {
                acc1.transfer(acc2,100);
            };
        Runnable runnable2 = ()-> {
                acc2.transfer(acc1,50);
        };
        //int choice = choiceInt("Для демонстрации дедлока нажмите 1, для демонстрации прерывания нажмите 2",1,2);
        Optional<Integer> choice = Optional.of(choiceInt("Для демонстрации дедлока нажмите 1, для демонстрации прерывания нажмите 2",1,2));
        if (choice.get() != 0) {
            Thread thread1 = new Thread(runnable1);
            Thread thread2 = new Thread(runnable2);
            thread1.start();
            thread2.start();
            try {
                if (choice.get() == 2){
                    thread1.interrupt();
                } else {
                    System.out.println("взаимная блокировка...");
                }
                thread1.join();
                thread2.join();
            } catch (InterruptedException e) {
                System.out.println("main interrupted");
            }
            System.out.println("acc1.balance = " + acc1.balance);
            System.out.println("acc2.balance = " + acc2.balance);
        }
    }

}
