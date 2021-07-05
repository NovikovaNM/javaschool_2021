public class CarBuilder extends TransportBuilder<Car>{

    @Override
    public Car build() {
        return new Car(this.vin, this.length, this.width, this.height, this.maxSpeed);
    }
}
