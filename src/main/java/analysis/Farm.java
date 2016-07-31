package analysis;


import java.util.ArrayList;
import java.util.List;

/**
 * Rectangular farm with dimensions from 0,0 to width-1,height-1
 */
public class Farm {


    private final Rectangle rectangle;
    private final List<BarrenPlot> barrenPlots = new ArrayList<>();


    public Farm(int width, int height) {
        this.rectangle = new Rectangle(new Coordinate(0,0), new Coordinate(width-1, height-1));
    }

    void addBarrenPlot(Coordinate lowerLeft, Coordinate upperRight) {
        barrenPlots.add(new BarrenPlot(lowerLeft, upperRight));
    }

    boolean isCoordinateInFarm(int x, int y) {
        return rectangle.isCoordinateInRectangle(x, y);
    }

    boolean isCoordinateBarren(int x, int y) {
        for (BarrenPlot barrenPlot : barrenPlots) {
            if (barrenPlot.isCoordinateInPlot(x, y)) {
                return true;
            }
        }
        return false;
    }

    int getLeftExtent() {
        return rectangle.getLeftExtent();
    }

    int getRightExtent() {
        return rectangle.getRightExtent();
    }

    int getLowerExtent() {
        return rectangle.getLowerExtent();
    }

    int getUpperExtent() {
        return rectangle.getUpperExtent();
    }

}
