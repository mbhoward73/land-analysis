package landanalysis;


public class BarrenRectangle {

    private final Plot plot;

    public BarrenRectangle(int lowerLeftX, int lowerLeftY, int upperRightX, int upperRightY) {
        this.plot = new Plot(lowerLeftX, lowerLeftY, upperRightX, upperRightY);
    }

    public BarrenRectangle(Coordinate lowerLeft, Coordinate upperRight) {
        this.plot = new Plot(lowerLeft, upperRight);
    }

    public boolean isCoordinateInRectangle(int x, int y) {
        return plot.isCoordinateInPlot(x, y);
    }

    public int getLeftExtent() {
        return plot.getLeftExtent();
    }

    public int getRightExtent() {
        return plot.getRightExtent();
    }

    public int getLowerExtent() {
        return plot.getLowerExtent();
    }

    public int getUpperExtent() {
        return plot.getUpperExtent();
    }

}
