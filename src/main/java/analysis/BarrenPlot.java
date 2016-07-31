package analysis;


/**
 * Rectangular barren plot of land inside farm
 */
public class BarrenPlot {

    private final Rectangle rectangle;

    public BarrenPlot(int lowerLeftX, int lowerLeftY, int upperRightX, int upperRightY) {
        this.rectangle = new Rectangle(lowerLeftX, lowerLeftY, upperRightX, upperRightY);
    }

    public BarrenPlot(Coordinate lowerLeft, Coordinate upperRight) {
        this.rectangle = new Rectangle(lowerLeft, upperRight);
    }

    public boolean isCoordinateInPlot(int x, int y) {
        return rectangle.isCoordinateInRectangle(x, y);
    }

    public int getLeftExtent() {
        return rectangle.getLeftExtent();
    }

    public int getRightExtent() {
        return rectangle.getRightExtent();
    }

    public int getLowerExtent() {
        return rectangle.getLowerExtent();
    }

    public int getUpperExtent() {
        return rectangle.getUpperExtent();
    }

}
