package analysis

import spock.lang.Specification
import spock.lang.Unroll


class FarmTest extends Specification {

    def farm;

    @Unroll
    def "coordinate #coordinate is inside farm"() {
        given:
        farm = new Farm(10,10)

        expect:
        farm.isCoordinateInFarm(coordinate.getX(), coordinate.getY())

        where:
        coordinate << TestUtil.getAllInnerCoordinates(0,9,0,9)
    }

    @Unroll
    def "coordinate #coordinate is outside farm"() {
        given:
        farm = new Farm(10,10)

        expect:
        !farm.isCoordinateInFarm(coordinate.getX(), coordinate.getY())

        where:
        coordinate << TestUtil.getAllOuterCoordinates(new Coordinate(-1,-1), new Coordinate(10,10), new Coordinate(0,0), new Coordinate(10,10))
    }

    @Unroll
    def "coordinate #coordinate is inside barren plot"() {
        given:
        farm = new Farm(20,20)
        farm.addBarrenPlot(new Coordinate(5,5), new Coordinate(10,10))

        expect:
        farm.isCoordinateBarren(coordinate.getX(), coordinate.getY())

        where:
        coordinate << TestUtil.getAllInnerCoordinates(5, 10, 5, 10)
    }

    @Unroll
    def "coordinate #coordinate is outside barren plot"() {
        given:
        farm = new Farm(20,20)
        farm.addBarrenPlot(new Coordinate(5,5), new Coordinate(10,10))

        expect:
        !farm.isCoordinateBarren(coordinate.getX(), coordinate.getY())

        where:
        coordinate << TestUtil.getAllOuterCoordinates(new Coordinate(0,0), new Coordinate(19,19), new Coordinate(5,5), new Coordinate(10,10))
    }




}
