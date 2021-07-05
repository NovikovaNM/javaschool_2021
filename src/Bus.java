
public class Bus extends Transport {

    @Override
    public TypeTransport getType() {
        return TypeTransport.Bus;
    }

    public Bus(String vin, Integer length, Integer width, Integer height, Integer maxSpeed) {
        super(vin, length, width, height, maxSpeed);
    }

    @Override
    public String toString() {
        return TypeTransport.Bus + "\n"+super.toString();
    }
}
