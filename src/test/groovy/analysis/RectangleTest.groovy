package analysis

import spock.lang.Specification
import spock.lang.Unroll


class RectangleTest extends Specification {


    def rectangle;

    @Unroll
    def "coordinate #coordinate is inside rectangle"() {

        given:
        rectangle = new Rectangle(10,10,20,20)

        expect:
        rectangle.isCoordinateInRectangle(coordinate.getX(), coordinate.getY())

        where:
        coordinate << TestUtil.getAllInnerCoordinates(10,20,10,20)
    }

    @Unroll
    def "coordinate #coordinate is outside rectangle"() {

        given:
        rectangle = new Rectangle(10,10,20,20)

        expect:
        !rectangle.isCoordinateInRectangle(coordinate.getX(), coordinate.getY())

        where:
        coordinate << TestUtil.getAllOuterCoordinates(new Coordinate(0,0), new Coordinate(30,30), new Coordinate(10,10), new Coordinate(20,20))
    }

}
