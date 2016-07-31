package analysis

import spock.lang.Specification
import spock.lang.Unroll


class BarrenPlotTest extends Specification {


    def barrenPlot;

    @Unroll
    def "coordinate #coordinate is inside barren plot"() {

        given:
        barrenPlot = new BarrenPlot(10,10,20,20)

        expect:
        barrenPlot.isCoordinateInPlot(coordinate.getX(), coordinate.getY())

        where:
        coordinate << TestUtil.getAllInnerCoordinates(10,20,10,20)
    }

    @Unroll
    def "coordinate #coordinate is outside barren plot"() {

        given:
        barrenPlot = new BarrenPlot(10,10,20,20)

        expect:
        !barrenPlot.isCoordinateInPlot(coordinate.getX(), coordinate.getY())

        where:
        coordinate << TestUtil.getAllOuterCoordinates(new Coordinate(0,0), new Coordinate(30,30), new Coordinate(10,10), new Coordinate(20,20))
    }



}
