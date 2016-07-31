package analysis

import spock.lang.Specification


class UserInputParserTest extends Specification {

    def userInputParser = new UserInputParser()

    def "empty string"() {
        when:
        userInputParser.parseUserInput("")

        then:
        def e = thrown(IllegalArgumentException)
        e.getMessage() == "input must start and end with curly brace"
    }

    def "doesn't start and end with curly brace"() {
        when:
        userInputParser.parseUserInput("foo")

        then:
        def e = thrown(IllegalArgumentException)
        e.getMessage() == "input must start and end with curly brace"
    }

    def "no barren rectangles"() {
        when:
        List<CoordinatePair> coordinatePairs = userInputParser.parseUserInput("{}")

        then:
        coordinatePairs.size() == 0
    }

    def "rectangle doesn't start and end with quotes"() {
        when:
        userInputParser.parseUserInput("{foo}")

        then:
        def e = thrown(IllegalArgumentException)
        e.getMessage() == "input must start and end with quotation mark"
    }

    def "rectangle doesn't have right number of coordinates"() {
        when:
        userInputParser.parseUserInput("{\"32 45\"}")

        then:
        def e = thrown(IllegalArgumentException)
        e.getMessage() == "each rectangle should have 4 integers"
    }

    def "rectangle has non-integer coordinate"() {
        when:
        userInputParser.parseUserInput("{\"32 45 foo 33\"}")

        then:
        def e = thrown(IllegalArgumentException)
        e.getMessage() == "rectangle must consist of 4 integers"
    }

    def "single barren rectangle"() {
        when:
        List<CoordinatePair> coordinatePairs = userInputParser.parseUserInput("{\"0 92 399 307\"}")

        then:
        coordinatePairs.size() == 1
        coordinatePairs.get(0).lowerLeft.getX() == 0
        coordinatePairs.get(0).lowerLeft.getY() == 92
        coordinatePairs.get(0).upperRight.getX() == 399
        coordinatePairs.get(0).upperRight.getY() == 307
    }

    def "two barren rectangles"() {
        when:
        List<CoordinatePair> coordinatePairs = userInputParser.parseUserInput("{\"0 92 399 307\", \"10 20 30 40\"}")

        then:
        coordinatePairs.size() == 2

        coordinatePairs.get(0).lowerLeft.getX() == 0
        coordinatePairs.get(0).lowerLeft.getY() == 92
        coordinatePairs.get(0).upperRight.getX() == 399
        coordinatePairs.get(0).upperRight.getY() == 307

        coordinatePairs.get(1).lowerLeft.getX() == 10
        coordinatePairs.get(1).lowerLeft.getY() == 20
        coordinatePairs.get(1).upperRight.getX() == 30
        coordinatePairs.get(1).upperRight.getY() == 40
    }

}