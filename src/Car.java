public class Car extends Transport{
    @Override
    public TypeTransport getType() {
        return TypeTransport.Car;
    }
    public Car(String vin, Integer length, Integer width, Integer height, Integer maxSpeed) {
        super(vin, length, width, height, maxSpeed);
    }
    @Override
    public String toString() {
        return TypeTransport.Car + "\n"+super.toString();
    }
}
