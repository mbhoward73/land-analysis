package analysis;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * responsible for parsing user input
 */
public class UserInputParser {


    List<CoordinatePair> parseUserInput(String input) {
        List<CoordinatePair> coordinatePairList = new ArrayList<>();
        String inputTrimmed = validateAndTrimInput(input, '{', '}', "curly brace");
        StringTokenizer tokenizer = new StringTokenizer(inputTrimmed, ",");
        while (tokenizer.hasMoreTokens()) {
            CoordinatePair coordinatePair = parseBarrenRectangle(tokenizer.nextToken().trim());
            coordinatePairList.add(coordinatePair);
        }
        return coordinatePairList;
    }

    private CoordinatePair parseBarrenRectangle(String input) {
        String inputTrimmed = validateAndTrimInput(input, '"', '"', "quotation mark");
        StringTokenizer tokenizer = new StringTokenizer(inputTrimmed, " ");
        if (tokenizer.countTokens() != 4) {
            throw new IllegalArgumentException("each rectangle should have 4 integers");
        }
        Coordinate lowerLeft = getCoordinateFromInput(tokenizer.nextToken(), tokenizer.nextToken());
        Coordinate upperRight = getCoordinateFromInput(tokenizer.nextToken(), tokenizer.nextToken());
        return new CoordinatePair(lowerLeft, upperRight);
    }

    private String validateAndTrimInput(String input, char leadChar, char trailingChar, String charDescription) {
        if (input.isEmpty() || input.charAt(0) != leadChar || input.charAt(input.length() -1) != trailingChar) {
            throw new IllegalArgumentException("input must start and end with " + charDescription);
        }
        return input.trim().substring(1, input.length() - 1);
    }

    private Coordinate getCoordinateFromInput(String tokenX, String tokenY) {
        try {
            int x = Integer.parseInt(tokenX);
            int y = Integer.parseInt(tokenY);
            return new Coordinate(x, y);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("rectangle must consist of 4 integers");
        }
    }

}
