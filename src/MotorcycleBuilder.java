public class MotorcycleBuilder extends TransportBuilder<Motorcycle>{
    @Override
    public Motorcycle build() {
        return new Motorcycle(this.vin, this.length, this.width, this.height, this.maxSpeed);
    }
}
