package analysis;


import java.util.List;
import java.util.Scanner;

/**
 * Controller is responsible for prompting user for barren rectangles and driving simulation
 */
public class LandAnalysisController {

    private UserInputParser parser = new UserInputParser();

    private void runSimulation(List<CoordinatePair> coordinatePairList) {
        Farm farm = new Farm(400,600);

        for (CoordinatePair coordinatePair : coordinatePairList) {
            farm.addBarrenPlot(coordinatePair.getLowerLeft(), coordinatePair.getUpperRight());
        }

        LandAnalyzer simulator = new LandAnalyzer(farm);
        List<Integer> fertileAreas = simulator.findFertileAreas();
        displayFertileAreas(fertileAreas);
    }

    private void displayFertileAreas(List<Integer> fertileAreas) {
        for (Integer area : fertileAreas) {
            System.out.print(area + " ");
        }
        System.out.println();
    }

    private List<CoordinatePair> parseUserInput(String input) {
        return parser.parseUserInput(input);
    }

    public static void main(String[] args) {

        System.out.println("Barren Land Analysis Application");
        System.out.println("Enter coordinates of barren rectangles to perform analysis");
        System.out.println("Example: {\"0 292 399 307\"}");
        System.out.println("Type 'exit' to quit application");

        LandAnalysisController controller = new LandAnalysisController();

        while (true) {
            Scanner reader = new Scanner(System.in);
            System.out.println("Enter set of barren rectangles: ");
            String input = reader.nextLine();
            if (input.equals("exit")) {
                System.exit(0);
            } else {
                try {
                    List<CoordinatePair> coordinatePairList = controller.parseUserInput(input);
                    controller.runSimulation(coordinatePairList);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid Input: " + e.getMessage());
                }
            }

        }

    }

}
