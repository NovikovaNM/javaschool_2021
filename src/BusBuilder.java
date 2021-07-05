public class BusBuilder extends TransportBuilder<Bus>{
    @Override
    public Bus build() {
        return new Bus(this.vin, this.length, this.width, this.height, this.maxSpeed);
    }
}
