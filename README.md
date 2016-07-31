# Barren Land Analysis

This application performs land analysis on a farm by finding the area of all contiguous fertile plots
given an input set of rectangles representing plots of barren land inside the farm

## Run program

Coming soon

## Run tests

Coming soon

## Algorithm

The algorithm works as follows:

1. add all fertile coordinates in farm to unmarked coordinates map
2. beginning with the lowest leftmost unmarked coordinate, find contiguous area of land associated with this
coordinate by moving right and up (marking coordinates with areaId as we go). do not cross barren land boundaries.
once top of farm or uncrossable barren land is reached, repeat with new lowest leftmost unmarked coordindate.
continue until all fertile coordinates are marked with areaId
3. merge contiguous areas by picking an area at random and looking at the border coordinates for all coordinates in this area
if selected area borders one or more other areas, merge other areas into selected area by remarking with selected areaId
if selected area does not border any other area, move these coordinates to merged coordinates list
continue until all coordinates have been merged and/or moved
4. calculate fertile areas by counting all coordinates in each remaining area. return sorted areas
