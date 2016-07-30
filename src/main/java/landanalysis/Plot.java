package landanalysis;

/**
 * Rectangular plot of land which could be entire farm or a barren rectangle
 */
public class Plot {

    private final Coordinate lowerLeft;
    private final Coordinate upperRight;

    public Plot(int lowerLeftX, int lowerLeftY, int upperRightX, int upperRightY) {
        this.lowerLeft = new Coordinate(lowerLeftX, lowerLeftY);
        this.upperRight = new Coordinate(upperRightX, upperRightY);
    }

    public Plot(Coordinate lowerLeft, Coordinate upperRight) {
        this.lowerLeft = lowerLeft;
        this.upperRight = upperRight;
    }

    public boolean isCoordinateInPlot(int x, int y) {
        return x >= lowerLeft.getX() && x <= upperRight.getX() && y >= lowerLeft.getY() && y <= upperRight.getY();
    }

    public int getLeftExtent() {
        return lowerLeft.getX();
    }

    public int getRightExtent() {
        return upperRight.getX();
    }

    public int getLowerExtent() {
        return lowerLeft.getY();
    }

    public int getUpperExtent() {
        return upperRight.getY();
    }

}
