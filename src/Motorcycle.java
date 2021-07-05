public class Motorcycle extends Transport{
    @Override
    public TypeTransport getType() {
        return TypeTransport.Motorcycle;
    }
    public Motorcycle(String vin, Integer length, Integer width, Integer height, Integer maxSpeed) {
        super(vin, length, width, height, maxSpeed);
    }

    @Override
    public String toString() {
        return TypeTransport.Motorcycle + "\n"+super.toString();
    }
}
