package org.example;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static org.example.ShortestPathFinder.*;

/*
    * This is the main application where we need to use path finding algorithm to solve types of puzzles
    * @author Tashini Weerasooriya - 20212153/w1901991
    * @since 20th March 2024
*/
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("\nEnter the file path(Enter the Path from content root/ Absolute path): ");
        String filePath = scanner.nextLine();

        try {
            long startTime = System.nanoTime(); // Start timing

            ShortestPathFinder shortestPathFinder = new ShortestPathFinder();

            // Read the map from a file
            char[][] map = readMapFromFile(filePath);

            if (map.length == 1)
                System.out.println("No path found because there's only one node in the map.");
            else {

            Coordinate start = null;
            Coordinate finish = null;

                // Find the start and finish coordinates
                for (int i = 0; i < map.length; i++) {
                    for (int j = 0; j < map[i].length; j++) {
                        if (map[i][j] == shortestPathFinder.getSTART()) {
                            start = new Coordinate(i, j); // Keep the format (i, j) for consistency
                        } else if (map[i][j] == shortestPathFinder.getFINISH()) {
                            finish = new Coordinate(i, j); // Keep the format (i, j) for consistency
                        }
                    }
                }

                if (start != null && finish != null) {

                    System.out.println("\nThe Algorithm started to find the shortest path...");
                    // Find the shortest path
                    long startT = System.nanoTime(); // Start timing for the algorithm
                    List<Coordinate> shortestPath = findShortestPath(map, start, finish);

                    System.out.println("\nThe Algorithm completed finding the shortest path.");
                    long durationOfAlgorithm = System.nanoTime() - startT; // Calculate algorithm duration

                    // Print the shortest path with directions
                    if (!shortestPath.isEmpty()) {
                        System.out.println("\n1. Start at (" + (start.x + 1) + ", " + (start.y + 1) + ")"); // Adjusted the format

                        // Check if the second node exists
                        if (shortestPath.size() > 1) {
                            Coordinate secondNode = shortestPath.get(0);
                            String direction = getDirections(shortestPath, 1, secondNode);
                            System.out.println("2. Move " + direction + " to (" + (secondNode.x + 1) + ", " + (secondNode.y + 1) + ")");
                        }

                        // Print the remaining nodes
                        for (int i = 1; i < shortestPath.size(); i++) {
                            Coordinate current = shortestPath.get(i);
                            String direction = getDirections(shortestPath, i, current); // Adjusted the index
                            System.out.println((i + 2) + ". Move " + direction + " to (" + (current.x + 1) + ", " + (current.y + 1) + ")"); // Adjusted the format
                        }
                        System.out.println(shortestPath.size() + 2 + ". Done!\n");

                        long endTime = System.nanoTime(); //Ending timing
                        long totalDuration = endTime - startTime;
                        System.out.println("The Total time taken for the Process (in milliseconds): " + Math.round(totalDuration / 1_000_000.0));

                        System.out.println("\nAlgorithm duration (in milliseconds): " + Math.round(durationOfAlgorithm / 1_000_000.0));
                    }
                } else {
                    System.out.println("Start or finish not found in the map.");
                }
            }
        } catch (Exception e) {
            System.err.println("Error reading map file: " + e.getMessage());
        }
    }

    private static String getDirections(List<Coordinate> shortestPath, int i, Coordinate current) {
        Coordinate previous = shortestPath.get(i - 1);
        String direction = "";

        if (current.x > previous.x) direction = "down";
        else if (current.x < previous.x) direction = "up";
        else if (current.y < previous.y) direction = "left";
        else if (current.y > previous.y) direction = "right";
        return direction;
    }
}