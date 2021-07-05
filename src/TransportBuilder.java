import java.time.ZonedDateTime;

public abstract class TransportBuilder <T extends Transport>{
    protected String vin;
    protected ZonedDateTime releaseDate;
    protected Integer length, width, height, maxSpeed;

    public TransportBuilder Vin(String vin) {
        this.vin = vin;
        return this;
    }

    public TransportBuilder ReleaseDate(ZonedDateTime releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public TransportBuilder Length(Integer length) {
        this.length = length;
        return this;
    }

    public TransportBuilder Width(Integer width) {
        this.width = width;
        return this;
    }

    public TransportBuilder Height(Integer height) {
        this.height = height;
        return this;
    }

    public abstract T build();
}
