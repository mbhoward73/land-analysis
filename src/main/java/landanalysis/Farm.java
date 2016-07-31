package landanalysis;


import java.util.ArrayList;
import java.util.List;

public class Farm {

    //TODO: refactor with dimensions of farm instead of coordinates

    private final Plot plot;
    private final List<BarrenRectangle> barrenPlots = new ArrayList<>();


    public Farm(int width, int height) {
        this.plot = new Plot(new Coordinate(0,0), new Coordinate(width-1, height-1));
    }

    public void addBarrenPlot(Coordinate lowerLeft, Coordinate upperRight) {
        barrenPlots.add(new BarrenRectangle(lowerLeft, upperRight));
    }

    boolean isCoordinateInFarm(int x, int y) {
        return plot.isCoordinateInPlot(x, y);
    }

    boolean isCoordinateBarren(int x, int y) {
        for (BarrenRectangle barrenPlot : barrenPlots) {
            if (barrenPlot.isCoordinateInRectangle(x, y)) {
                return true;
            }
        }
        return false;
    }

    int getLeftExtent() {
        return plot.getLeftExtent();
    }

    int getRightExtent() {
        return plot.getRightExtent();
    }

    int getLowerExtent() {
        return plot.getLowerExtent();
    }

    int getUpperExtent() {
        return plot.getUpperExtent();
    }

}
