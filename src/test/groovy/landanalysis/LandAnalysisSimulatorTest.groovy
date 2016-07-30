package landanalysis

import spock.lang.Specification


class LandAnalysisSimulatorTest extends Specification {

    def farm = new Farm(400,600)
    def landAnalysisSimulator = new LandAnalysisSimulator(farm);

    def "farm with no barren rectangles"() {
        when:
        List<Integer> fertileAreas = landAnalysisSimulator.findFertileAreas();

        then:
        fertileAreas.size() == 1;
        fertileAreas.get(0) == 400 * 600;
    }

    def "entire farm is barren - 1 rectangle"() {

        given:
        farm.addBarrenPlot(new Coordinate(0,0), new Coordinate(399,599));

        when:
        List<Integer> fertileAreas = landAnalysisSimulator.findFertileAreas();

        then:
        fertileAreas.size() == 0;
    }

}
