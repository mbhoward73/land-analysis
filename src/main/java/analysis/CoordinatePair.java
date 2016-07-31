package analysis;

/**
 * pair of coordinates representing a rectangle
 */
public class CoordinatePair {

    private final Coordinate lowerLeft;
    private final Coordinate upperRight;

    public CoordinatePair(Coordinate lowerLeft, Coordinate upperRight) {
        this.lowerLeft = lowerLeft;
        this.upperRight = upperRight;
    }

    public Coordinate getLowerLeft() {
        return lowerLeft;
    }

    public Coordinate getUpperRight() {
        return upperRight;
    }
}
