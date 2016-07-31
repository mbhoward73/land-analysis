package analysis

import spock.lang.Specification


class CoordinateTest extends Specification {

    def "build coordinate key"() {
        expect:
        Coordinate.buildKey(10,20) == "10,20"
    }

    def "parse coordinate"() {
        when:
        int[] coordinate = Coordinate.parseCoordinate("10,20");

        then:
        coordinate.length == 2
        coordinate[0] == 10
        coordinate[1] == 20
    }

    def "parse coordinate number format error"() {
        when:
        int[] coordinate = Coordinate.parseCoordinate("10,foo");

        then:
        def e = thrown(NumberFormatException)
    }

    def "parse coordinate syntax error"() {
        when:
        int[] coordinate = Coordinate.parseCoordinate("1020");

        then:
        def e = thrown(IllegalArgumentException)
        e.getMessage() == "coordinate string must consist of two comma separated integers"
    }

}
