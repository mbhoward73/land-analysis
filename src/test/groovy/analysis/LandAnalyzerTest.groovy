package analysis

import spock.lang.Specification


class LandAnalyzerTest extends Specification {

    def farm = new Farm(400,600)
    def landAnalysisSimulator = new LandAnalyzer(farm);

    def "farm with no barren rectangles"() {
        when:
        List<Integer> fertileAreas = landAnalysisSimulator.findFertileAreas()

        then:
        fertileAreas.size() == 1
        fertileAreas.get(0) == 400 * 600
    }

    def "entire farm is barren - 1 rectangle"() {

        given:
        farm.addBarrenPlot(new Coordinate(0,0), new Coordinate(399,599))

        when:
        List<Integer> fertileAreas = landAnalysisSimulator.findFertileAreas()

        then:
        fertileAreas.size() == 0
    }

    def "entire farm is barren - 2 rectangles"() {

        given:
        farm.addBarrenPlot(new Coordinate(0,0), new Coordinate(399,299))
        farm.addBarrenPlot(new Coordinate(0,300), new Coordinate(399,599))

        when:
        List<Integer> fertileAreas = landAnalysisSimulator.findFertileAreas()

        then:
        fertileAreas.size() == 0
    }

    def "one barren rectangle which horizontally bisects farm"() {
        given:
        farm.addBarrenPlot(new Coordinate(0,292), new Coordinate(399,307))

        when:
        List<Integer> fertileAreas = landAnalysisSimulator.findFertileAreas()

        then:
        fertileAreas.size() == 2
        fertileAreas.get(0) == 116800
        fertileAreas.get(1) == 116800
    }

    def "one barren rectangle which vertically bisects farm"() {
        given:
        farm.addBarrenPlot(new Coordinate(192,0), new Coordinate(207,599))

        when:
        List<Integer> fertileAreas = landAnalysisSimulator.findFertileAreas()

        then:
        fertileAreas.size() == 2
        fertileAreas.get(0) == 115200
        fertileAreas.get(1) == 115200
    }

    def "one rectangle in middle of farm"() {
        given:
        farm.addBarrenPlot(new Coordinate(100,100), new Coordinate(199,199))

        when:
        List<Integer> fertileAreas = landAnalysisSimulator.findFertileAreas()

        then:
        fertileAreas.size() == 1
        fertileAreas.get(0) == 230000
    }

    def "two rectangles which intersect in upper right corner of farm"() {
        given:
        farm.addBarrenPlot(new Coordinate(300,500), new Coordinate(310,599))
        farm.addBarrenPlot(new Coordinate(300,500), new Coordinate(399,510))

        when:
        List<Integer> fertileAreas = landAnalysisSimulator.findFertileAreas()

        then:
        fertileAreas.size() == 2
        fertileAreas.get(0) == 7921
        fertileAreas.get(1) == 230000
    }

    def "two rectangles which intersect in upper right corner of farm and one rectangle which cuts farm vertically"() {
        given:
        farm.addBarrenPlot(new Coordinate(300,500), new Coordinate(310,599))
        farm.addBarrenPlot(new Coordinate(300,500), new Coordinate(399,510))
        farm.addBarrenPlot(new Coordinate(100,0), new Coordinate(110,599))

        when:
        List<Integer> fertileAreas = landAnalysisSimulator.findFertileAreas()

        then:
        fertileAreas.size() == 3
        fertileAreas.get(0) == 7921
        fertileAreas.get(1) == 60000
        fertileAreas.get(2) == 163400
    }

    def "hash pattern in middle of farm"() {
        given:
        farm.addBarrenPlot(new Coordinate(48,192), new Coordinate(351,207))
        farm.addBarrenPlot(new Coordinate(48,392), new Coordinate(351,407))
        farm.addBarrenPlot(new Coordinate(120,52), new Coordinate(135,547))
        farm.addBarrenPlot(new Coordinate(260,52), new Coordinate(275,547))

        when:
        List<Integer> fertileAreas = landAnalysisSimulator.findFertileAreas()

        then:
        fertileAreas.size() == 2
        fertileAreas.get(0) == 22816
        fertileAreas.get(1) == 192608
    }

    def "2 hash patterns in middle of farm"() {
        given:
        //first hash
        farm.addBarrenPlot(new Coordinate(48,92), new Coordinate(351,107))
        farm.addBarrenPlot(new Coordinate(48,192), new Coordinate(351,207))
        farm.addBarrenPlot(new Coordinate(120,52), new Coordinate(135,247))
        farm.addBarrenPlot(new Coordinate(260,52), new Coordinate(275,247))
        //second hash
        farm.addBarrenPlot(new Coordinate(48,292), new Coordinate(351,307))
        farm.addBarrenPlot(new Coordinate(48,392), new Coordinate(351,407))
        farm.addBarrenPlot(new Coordinate(120,267), new Coordinate(135,447))
        farm.addBarrenPlot(new Coordinate(260,267), new Coordinate(275,447))


        when:
        List<Integer> fertileAreas = landAnalysisSimulator.findFertileAreas()

        then:
        fertileAreas.size() == 3
        fertileAreas.get(0) == 10416
        fertileAreas.get(1) == 10416
        fertileAreas.get(2) == 189696
    }


}
