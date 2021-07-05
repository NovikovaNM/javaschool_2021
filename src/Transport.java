import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.ZonedDateTime;

public abstract class Transport {
    protected final String vin;
    protected final ZonedDateTime ReleaseDate;
    protected final Integer length, width, height, maxSpeed;
   // protected final TypeTransport typeTransport;

    public Transport(String vin, Integer length, Integer width, Integer height, Integer maxSpeed/*, TypeTransport typeTransport*/) {
        this.vin = vin;
        ReleaseDate = ZonedDateTime.now();
        this.length = length;
        this.width = width;
        this.height = height;
        this.maxSpeed = maxSpeed;
      //  this.typeTransport = typeTransport;
    }

    public String getVin() {
        return vin;
    }

    public ZonedDateTime getReleaseDate() {
        return ReleaseDate;
    }

    public Integer getLength() {
        return length;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getMaxSpeed() {
        return maxSpeed;
    }
    public String toString() {
        return "Vin - "+ this.vin +"\n" +
                "Дата выпуска - " + this.ReleaseDate + "\n" +
                "Размеры - " + this.length + "x" + this.width + "x" + this.height + "\n" +
                "Макс скорость - " + this.maxSpeed;
    }

    public abstract TypeTransport getType();

    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String strOut = "Какой транспорт создать? 1-Автобус, 2-Автомобиль, 3-Мотоцикл. Для выхода нажмите 0";
        TransportBuilder<?> transportBuilder;
        Transport transport;
        System.out.println(strOut);
        String strIn = reader.readLine();
        while (!strIn.equals("0")) {
            try {
                int choice = Integer.parseInt(strIn);
                switch (choice) {
                    case (1):
                        transportBuilder = new BusBuilder();
                        transport = choiceProperties(transportBuilder);
                        System.out.println(transport.toString());
                        break;
                    case (2):
                        transportBuilder = new CarBuilder();
                        transport = choiceProperties(transportBuilder);
                        System.out.println(transport.toString());
                        break;
                    case (3):
                        transportBuilder = new MotorcycleBuilder();
                        transport = choiceProperties(transportBuilder);
                        System.out.println(transport.toString());
                        break;
                    default:
                        System.out.println("Некорректное число");
                }
            } catch (NumberFormatException e) {
                System.out.println("Некорректное число");
            }
            System.out.println(strOut);
            strIn = reader.readLine();
        }
    }

    public static Transport choiceProperties(TransportBuilder<?> transportBuilder) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String strOut = "Какое свойство задать? 1-vin, 2-макс скорость, 3-длина, 4-ширина, 5-высота. Чтобы завершить создание нажмите 0";
        System.out.println(strOut);
        String strIn = reader.readLine();
        while (!strIn.equals("0")) {
            try {
                int choice = Integer.parseInt(strIn);
                switch (choice) {
                    case (1):
                        System.out.println("Введите VIN");
                        transportBuilder.vin = reader.readLine();
                        break;
                    case (2):
                        transportBuilder.maxSpeed = readInt(reader, "Введите макс скорость");
                        break;
                    case (3):
                        transportBuilder.length = readInt(reader, "Введите длину");
                        break;
                    case (4):
                        transportBuilder.width = readInt(reader, "Введите ширину");
                        break;
                    case (5):
                        transportBuilder.height = readInt(reader, "Введите высоту");
                        break;
                    default:
                        System.out.println("Некорректное число");
                }
            } catch (NumberFormatException e) {
                    System.out.println("Некорректное число");
            }
            System.out.println(strOut);
            strIn = reader.readLine();
        }
        return transportBuilder.build();
    }
    public static int readInt (BufferedReader reader, String text) {
        System.out.println(text);
        do {
            try {
                int res = Integer.parseInt(reader.readLine());
                return res;
            }
            catch (IOException e) {
                System.out.println("Некорректное число\n"+text);
            }
        } while (true);
    }
}
