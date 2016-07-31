package analysis;


import java.util.StringTokenizer;

/**
 * X,Y Coordinate used for farm, barren plot, and fertile areas
 */
public class Coordinate {

    private final int x;
    private final int y;

    public Coordinate (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    @Override
    public String toString() {
        return x + "," + y;
    }

    static String buildKey(int x, int y) {
        return x + "," + y;
    }

    static int[] parseCoordinate(String coordinate) {
        StringTokenizer tokenizer = new StringTokenizer(coordinate, ",");
        if (tokenizer.countTokens() != 2) {
            throw new IllegalArgumentException("coordinate string must consist of two comma separated integers");
        }
        int[] coordinateAsArray = new int[2];
        coordinateAsArray[0] = Integer.parseInt(tokenizer.nextToken());
        coordinateAsArray[1] = Integer.parseInt(tokenizer.nextToken());
        return coordinateAsArray;
    }


}
