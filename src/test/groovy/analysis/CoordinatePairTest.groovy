package analysis

import spock.lang.Specification



class CoordinatePairTest extends Specification {

    def "build coordinate pair"() {
        given:
        Coordinate lowerLeft = new Coordinate(10,10)
        Coordinate upperRight = new Coordinate(20,20)

        when:
        CoordinatePair coordinatePair = new CoordinatePair(lowerLeft, upperRight)

        then:
        coordinatePair.getLowerLeft().getX() == lowerLeft.getX()
        coordinatePair.getLowerLeft().getY() == lowerLeft.getY()
        coordinatePair.getUpperRight().getX() == upperRight.getX()
        coordinatePair.getUpperRight().getY() == upperRight.getY()
    }

}