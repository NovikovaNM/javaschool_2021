import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputChoice {
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
                    System.out.println("Ошибка! Это не целое число");
                }
            } while (result != 0) ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
