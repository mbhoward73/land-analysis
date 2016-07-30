package landanalysis

import spock.lang.Specification
import spock.lang.Unroll


class BarrenRectangleTest extends Specification {


    def barrenRectangle;

    @Unroll
    def "coordinate #coordinate is inside barren rectangle"() {

        given:
        barrenRectangle = new BarrenRectangle(10,10,20,20)

        expect:
        barrenRectangle.isCoordinateInRectangle(coordinate.getX(), coordinate.getY());

        where:
        coordinate << getAllInnerCoordinates(10,20,10,20)
    }

    @Unroll
    def "coordinate #coordinate is outside barren rectangle"() {

        given:
        barrenRectangle = new BarrenRectangle(10,10,20,20)

        expect:
        !barrenRectangle.isCoordinateInRectangle(coordinate.getX(), coordinate.getY());

        where:
        coordinate << getAllOuterCoordinates(new Coordinate(0,0), new Coordinate(30,30), new Coordinate(10,10), new Coordinate(20,20))
    }

    List<Coordinate> getAllInnerCoordinates(leftExtent, rightExtent, lowerExtent, upperExtent) {
        def coordinateList = new ArrayList<Coordinate>()
        for (int i = leftExtent; i <= rightExtent; i++) {
            for (int j = lowerExtent; j <= upperExtent; j++) {
                coordinateList.add(new Coordinate(i,j))
            }
        }
        return coordinateList
    }

    List<Coordinate> getAllOuterCoordinates(Coordinate farmLowerLeft, Coordinate farmUpperRight, Coordinate barrenLowerLeft,
                                            Coordinate barrentUpperRight) {
        def coordinateList = new ArrayList<Coordinate>()
        for (int i = farmLowerLeft.getX(); i <= farmUpperRight.getX(); i++) {
            for (int j = farmLowerLeft.getY(); j <= farmUpperRight.getY(); j++) {
                if (i < barrenLowerLeft.getX() || i > barrentUpperRight.getX() || j < barrenLowerLeft.getY() || j > barrentUpperRight.getY()) {
                    coordinateList.add(new Coordinate(i,j));
                }
            }
        }
        return coordinateList
    }


}
