package Tools;

public enum Constants {
    G(6.67430 * Math.pow(10, -11)),
    //G(1),
    FrameTime(20 );

    public final double value;

    private Constants(double value) {
        this.value = value;
    }
}
