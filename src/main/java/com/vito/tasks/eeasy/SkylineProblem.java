package com.vito.eeasy;

import java.util.*;

public class SkylineProblem {

    private static class Point {
        final Building building;
        final int value;
        final boolean start;

        private Point(Building building, int value, boolean start) {
            this.building = building;
            this.value = value;
            this.start = start;
        }

        public int getValue() {
            return value;
        }
    }

    private static class Building {
        private final int height;

        private Building(int height) {
            this.height = height;
        }

        public int getHeight() {
            return height;
        }
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> result = new ArrayList<>();

        List<Point> points = new ArrayList<>();
        for (var buildingArray : buildings) {
            var building = new Building(buildingArray[2]);
            points.add(new Point(building, buildingArray[0], true));
            points.add(new Point(building, buildingArray[1], false));
        }

        Collections.sort(points, Comparator.comparingInt(Point::getValue));
        PriorityQueue<Building> currentBuildings = new PriorityQueue<>(
                Comparator.comparingInt(Building::getHeight).reversed()
        );

        for (int i = 0; i < points.size(); ) {

            int currentHeight = currentBuildings.isEmpty() ? 0 : currentBuildings.peek().getHeight();

            List<Point> currentPoints = new ArrayList<>();
            currentPoints.add(points.get(i++));
            int currentX = currentPoints.get(0).getValue();
            while (i < points.size() && (points.get(i - 1).getValue() == points.get(i).getValue())) {
                currentPoints.add(points.get(i));
                i++;
            }

            for (Point p : currentPoints) {
                if (p.start) {
                    currentBuildings.add(p.building);
                }
                if (!p.start) {
                    currentBuildings.remove(p.building);
                }
            }

            int newHeight = currentBuildings.isEmpty() ? 0 : currentBuildings.peek().getHeight();

            if (newHeight != currentHeight) {
                result.add(Arrays.asList(currentX, newHeight));
            }
        }

        return result;
    }
}
