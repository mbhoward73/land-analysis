package analysis

/**
 * unit test utility methods
 */
class TestUtil {

    static List<Coordinate> getAllInnerCoordinates(leftExtent, rightExtent, lowerExtent, upperExtent) {
        def coordinateList = new ArrayList<Coordinate>()
        for (int i = leftExtent; i <= rightExtent; i++) {
            for (int j = lowerExtent; j <= upperExtent; j++) {
                coordinateList.add(new Coordinate(i,j))
            }
        }
        return coordinateList
    }

    static List<Coordinate> getAllOuterCoordinates(Coordinate outerLowerLeft, Coordinate outerUpperRight, Coordinate innerLowerLeft,
                                            Coordinate innerUpperRight) {
        def coordinateList = new ArrayList<Coordinate>()
        for (int i = outerLowerLeft.getX(); i <= outerUpperRight.getX(); i++) {
            for (int j = outerLowerLeft.getY(); j <= outerUpperRight.getY(); j++) {
                if (i < innerLowerLeft.getX() || i > innerUpperRight.getX() || j < innerLowerLeft.getY() || j > innerUpperRight.getY()) {
                    coordinateList.add(new Coordinate(i,j));
                }
            }
        }
        return coordinateList
    }
}
