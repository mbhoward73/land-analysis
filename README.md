# Barren Land Analysis

This application performs land analysis on a farm by finding the area of all contiguous fertile plots
given an input set of rectangles representing plots of barren land inside the farm

## Run program

./gradlew clean build
./run_analyzer.sh

# Sample run

```
mark@mark-XPS-15-9550:~/dev/land-analysis$ ./run_analyzer.sh
Barren Land Analysis Application
Enter coordinates of barren rectangles to perform analysis
Example: {"0 292 399 307"}
Type 'exit' to quit application
Enter set of barren rectangles:
{"0 292 399 307"}
116800 116800
Enter set of barren rectangles:
{"48 192 351 207", "48 392 351 407", "120 52 135 547", "260 52 275 547"}
22816 192608
Enter set of barren rectangles:
exit
mark@mark-XPS-15-9550:~/dev/land-analysis$
```

## Run unit tests

./gradlew clean build test


## Algorithm

The algorithm works as follows:

1. Add all fertile coordinates in farm to unmarked coordinates map.
2. Beginning with the lowest leftmost unmarked coordinate, find contiguous area of land associated with this
coordinate by moving right and up (marking coordinates with areaId as we go).  Do not cross barren land boundaries.
Once top of farm or uncrossable barren land is reached, repeat with new lowest leftmost unmarked coordindate.
Continue until all fertile coordinates are marked with areaId
3. Merge contiguous areas by picking an area at random and looking at the border coordinates for all coordinates in this area.
If selected area borders one or more other areas, merge other areas into selected area by remarking with selected areaId.
If selected area does not border any other area, move these coordinates to merged coordinates list.
Continue until all coordinates have been merged and/or moved
4. Calculate fertile areas by counting all coordinates in each remaining area. Return sorted areas.
