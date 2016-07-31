package landanalysis;


import java.util.StringTokenizer;

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
        int[] coordinateAsArray = new int[2];
        coordinateAsArray[0] = Integer.parseInt(tokenizer.nextToken());
        coordinateAsArray[1] = Integer.parseInt(tokenizer.nextToken());
        return coordinateAsArray;
    }


}
