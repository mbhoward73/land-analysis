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

    def "entire farm is barren - 2 rectangles"() {

        given:
        farm.addBarrenPlot(new Coordinate(0,0), new Coordinate(399,299));
        farm.addBarrenPlot(new Coordinate(0,300), new Coordinate(399,599));

        when:
        List<Integer> fertileAreas = landAnalysisSimulator.findFertileAreas();

        then:
        fertileAreas.size() == 0;
    }

    def "one barren rectangle which horizontally bisects farm"() {
        given:
        farm.addBarrenPlot(new Coordinate(0,292), new Coordinate(399,307));

        when:
        List<Integer> fertileAreas = landAnalysisSimulator.findFertileAreas();

        then:
        fertileAreas.size() == 2;
        fertileAreas.get(0) == 116800;
        fertileAreas.get(1) == 116800;
    }

    def "one barren rectangle which vertically bisects farm"() {
        given:
        farm.addBarrenPlot(new Coordinate(192,0), new Coordinate(207,599));

        when:
        List<Integer> fertileAreas = landAnalysisSimulator.findFertileAreas();

        then:
        fertileAreas.size() == 2;
        fertileAreas.get(0) == 115200;
        fertileAreas.get(1) == 115200;
    }

    def "hash pattern in middle of farm"() {
        given:
        farm.addBarrenPlot(new Coordinate(48,192), new Coordinate(351,207));
        farm.addBarrenPlot(new Coordinate(48,392), new Coordinate(351,407));
        farm.addBarrenPlot(new Coordinate(120,52), new Coordinate(135,547));
        farm.addBarrenPlot(new Coordinate(260,52), new Coordinate(275,547));

        when:
        List<Integer> fertileAreas = landAnalysisSimulator.findFertileAreas();

        then:
        fertileAreas.size() == 2;
        fertileAreas.get(0) == 22816;
        fertileAreas.get(1) == 192608;
    }

}
