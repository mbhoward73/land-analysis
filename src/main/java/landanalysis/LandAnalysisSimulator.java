package landanalysis;


import java.util.*;

/**
 *
 */
public class LandAnalysisSimulator {


    private final Farm farm;
    //string coordinate, integer areaId
    private final Map<String, Integer> unmarkedCoordinates = new HashMap<>();
    //string coordinate, integer areaId
    private final Map<String, Integer> markedCoordinates = new HashMap<>();

    public LandAnalysisSimulator(Farm farm) {
        this.farm = farm;
    }

    //TODO: refactor iterators

    public List<Integer> findFertileAreas() {
        addAllFertileCoordinates();
        int areaId = 1;
        while (!unmarkedCoordinates.isEmpty()) {
            Coordinate seed = getLowestUnmarkedCoordinate();
            markCoordinates(seed.getX(), seed.getY(), areaId);
            areaId++;
        }
        return calculateFertileAreas();
    }

    private List<Integer> calculateFertileAreas() {
        //areaId, totalArea
        Map<Integer, Integer>  aggregatedAreas = new HashMap<>();
        for (int areaId : markedCoordinates.values()) {
            if (aggregatedAreas.containsKey(areaId)) {
                int currentArea = aggregatedAreas.get(areaId);
                aggregatedAreas.put(areaId, currentArea + 1);
            } else {
                aggregatedAreas.put(areaId, 1);
            }
        }
        return buildAndSortTotalAreaList(aggregatedAreas);
    }

    //map of total areas by areaId
    private List<Integer> buildAndSortTotalAreaList(Map<Integer, Integer> aggregatedAreas) {
        List<Integer> sortedAreas = new ArrayList<>();
        for (int totalArea : aggregatedAreas.values()) {
            sortedAreas.add(totalArea);
        }
        Collections.sort(sortedAreas);
        return sortedAreas;
    }

    //starting from the lowermost seed and moving up and to the right, mark all contiguous coordinates and remove them from unmarked map
    //don't cross farm or barren land boundaries
    private void markCoordinates(int seedX, int seedY, int areaId) {
//        System.out.println("processing key " + key);
//        System.out.println("unmarkedCoordinatesSize " + unmarkedCoordinates.size());
//        System.out.println("markedCoordinatesSize " + markedCoordinates.size());
        int currentX = seedX;
        int currentY = seedY;
        while (isCoordinateValid(currentX, currentY)) {
            String key = Coordinate.buildKey(currentX, currentY);
            unmarkedCoordinates.remove(key);
            markedCoordinates.put(key, areaId);
            Coordinate nextCoordinate = getNextCoordinate(currentX, currentY, seedX);
            currentX = nextCoordinate.getX();
            currentY = nextCoordinate.getY();
        }
    }

    private Coordinate getNextCoordinate(int currentX, int currentY, int seedX) {
        if (isCoordinateValid(currentX + 1, currentY)) {
            return new Coordinate(currentX + 1, currentY);
        } else {
            return new Coordinate(seedX, currentY + 1);
        }
    }

    private boolean isCoordinateValid(int x, int y) {
        boolean isCoordinateMarked = markedCoordinates.containsKey(Coordinate.buildKey(x, y));
        return farm.isCoordinateInFarm(x, y) && !farm.isCoordinateBarren(x, y) && !isCoordinateMarked;
    }


    //add all fertile coordinates to unmarked list
    private void addAllFertileCoordinates() {
        for (int i = farm.getLeftExtent(); i <= farm.getRightExtent(); i++) {
            for (int j = farm.getLowerExtent(); j <= farm.getUpperExtent(); j++) {
                if (!farm.isCoordinateBarren(i, j)) {
                    unmarkedCoordinates.put(Coordinate.buildKey(i, j), -1);
                }
            }
        }
    }

    private Coordinate getLowestUnmarkedCoordinate() {
        if (unmarkedCoordinates.isEmpty()) {
            throw new AssertionError("couldn't find any unmarked coordinates");
        }

        int lowestX = Integer.MAX_VALUE;
        int lowestY = Integer.MAX_VALUE;
        for (String coordinateKey : unmarkedCoordinates.keySet()) {
            int[] coordinate = Coordinate.parseCoordinate(coordinateKey);
            if (coordinate[0] <= lowestX && coordinate[1] <= lowestY) {
                lowestX = coordinate[0];
                lowestY = coordinate[1];
            }
        }
        return new Coordinate(lowestX, lowestY);
    }




}
