package analysis;


import java.util.*;

/**
 * LandAnalyzer does the actual land analysis as follows:
 * - TODO: describe algorithm
 */
public class LandAnalyzer {


    private final Farm farm;
    //string coordinate, integer areaId
    private final Map<String, Integer> unmarkedCoordinates = new HashMap<>();
    //string coordinate, integer areaId
    private final Map<String, Integer> markedCoordinates = new HashMap<>();
    //string coordinate, integer areaId
    private final Map<String, Integer> mergedCoordinates = new HashMap<>();
    private final List<Integer> areaIds = new ArrayList<>();


    public LandAnalyzer(Farm farm) {
        this.farm = farm;
    }

    //TODO: refactor iterators

    List<Integer> findFertileAreas() {
        addAllFertileCoordinates();
        int areaId = 1;
        while (!unmarkedCoordinates.isEmpty()) {
            Coordinate seed = getLowestUnmarkedCoordinate();
            markCoordinates(seed.getX(), seed.getY(), areaId);
            areaIds.add(areaId);
            areaId++;
        }
        mergeAreas();
        return calculateFertileAreas();
    }

    //starting with a random coordinate area, look at the neighbors of all coordinates in this area
    //if at least one coordinate is adjacent to a coordinate in another area, merge the areas into a single area
    //if no coordinates in the area are adjacent to a coordinate in another area, move that area into merged coordinates
    //continue until all coordinates are moved/merged
    private void mergeAreas() {
        while (!markedCoordinates.isEmpty()) {
            int currentAreaId = areaIds.get(0);
            Set<Integer> neighborAreas = findAllNeighborAreas(currentAreaId);
            if (neighborAreas.isEmpty()) {
                moveAreaToMergedCoordinates(currentAreaId);
                removeAreaId(currentAreaId);
            } else {
                mergeAllNeighboringAreas(neighborAreas, currentAreaId);
                removeStaleAreaIds(neighborAreas);
            }
        }
    }


    private void removeStaleAreaIds(Set<Integer> neighborAreas) {
        for (int areaId : neighborAreas) {
            removeAreaId(areaId);
        }
    }

    private void removeAreaId(int areaIdToRemove) {
        for (int i = 0; i < areaIds.size(); i++) {
            int areaId = areaIds.get(i);
            if (areaId == areaIdToRemove) {
                areaIds.remove(i);
                return;
            }
        }
    }

    //merge all neighboring areas into specified areaId
    private void mergeAllNeighboringAreas(Set<Integer> neighborAreas, int areaId) {
        for (Map.Entry<String, Integer> entry : markedCoordinates.entrySet()) {
            if (neighborAreas.contains(entry.getValue())) {
               markedCoordinates.put(entry.getKey(), areaId);
            }
        }
    }

    private void moveAreaToMergedCoordinates(int areaId) {
        List<String> coordinatesToRemove = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : markedCoordinates.entrySet()) {
            if (entry.getValue() == areaId) {
                mergedCoordinates.put(entry.getKey(), entry.getValue());
                coordinatesToRemove.add(entry.getKey());
            }
        }
        for (String coordinateToRemove : coordinatesToRemove) {
            markedCoordinates.remove(coordinateToRemove);
        }
    }

    private Set<Integer> findAllNeighborAreas(int areaId) {
        Set<Integer> neighborAreas = new HashSet<>();
        for (Map.Entry<String, Integer> entry : markedCoordinates.entrySet()) {
            if (entry.getValue() == areaId) {
                int[] coordinate = Coordinate.parseCoordinate(entry.getKey());
                neighborAreas.addAll(getNeighborAreasForCoordinate(coordinate[0], coordinate[1], areaId));
            }
        }
        return neighborAreas;
    }

    private Set<Integer> getNeighborAreasForCoordinate(int x, int y, int areaId) {
        Set<Integer> neighborAreas = new HashSet<>();
        neighborAreas.add(getNeighborAreaForCoordinate(x+1,y,areaId));
        neighborAreas.add(getNeighborAreaForCoordinate(x-1,y,areaId));
        neighborAreas.add(getNeighborAreaForCoordinate(x,y+1,areaId));
        neighborAreas.add(getNeighborAreaForCoordinate(x,y-1,areaId));
        neighborAreas.remove(-1);
        return neighborAreas;
    }

    private int getNeighborAreaForCoordinate(int x, int y, int areaId) {
        String coordinateKey = Coordinate.buildKey(x, y);
        if (markedCoordinates.containsKey(coordinateKey)) {
            int existingAreaId = markedCoordinates.get(coordinateKey);
            return (existingAreaId != areaId) ? existingAreaId : -1;
        } else {
            return -1;
        }
    }

    private List<Integer> calculateFertileAreas() {
        //areaId, totalArea
        Map<Integer, Integer>  aggregatedAreas = new HashMap<>();
        for (int areaId : mergedCoordinates.values()) {
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

    //starting from the lowermost seed and moving to the right and up, mark all contiguous coordinates and remove them from unmarked map
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
