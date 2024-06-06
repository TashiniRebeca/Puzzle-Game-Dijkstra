package org.example;

import java.io.*;
import java.util.*;

/*
 * This is where we need use path finding algorithm - Dijkstra's algorithm to solve types of puzzles
 * @author Tashini Weerasooriya - 20212153/w1901991
 * @since 20th March 2024
*/
public class ShortestPathFinder {
    // Define constants for map symbols
    //private final char EMPTY = '.';
    private static final char ROCK = '0';
    private static final char START = 'S';
    private static final char FINISH = 'F';

    // Define directions: Left, Right, Down, Up
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public char getSTART() {
        return START;
    }

    public char getFINISH() {
        return FINISH;
    }

    //
    private static List<Node> generateNeighbors(Node node, char[][] map, int m, int n) {
        List<Node> neighbors = new ArrayList<>();

        for (int[] dir : DIRECTIONS) {
            int nx = node.coordinate.x;
            int ny = node.coordinate.y;

            // Keep sliding in the current direction until hitting a wall or a rock
            while (true) {
                int nextX = nx + dir[0];
                int nextY = ny + dir[1];

                // Stop sliding if the new position is out of bounds or a rock
                if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n || map[nextX][nextY] == ROCK) {
                    break;
                }

                nx = nextX;
                ny = nextY;

                // Stop sliding if the new position is the start or the finish
                if (map[nx][ny] == START || map[nx][ny] == FINISH) {
                    break;
                }
            }

            // Add the stopping position as a neighbor
            if (nx != node.coordinate.x || ny != node.coordinate.y) {
                neighbors.add(new Node(new Coordinate(nx, ny), 0));
            } else {
                // Add the node right after the start node as a neighbor if it's accessible
                int nextX = nx + dir[0];
                int nextY = ny + dir[1];
                if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && map[nextX][nextY] != ROCK) {
                    neighbors.add(new Node(new Coordinate(nextX, nextY), 0));
                }
            }
        }

        return neighbors;
    }

    // Dijkstra's algorithm to find the shortest path
    public static List<Coordinate> findShortestPath(char[][] map, Coordinate start, Coordinate finish) {
        int m = map.length;
        int n = map[0].length;

        //Priority Queue to store the nodes
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.distance));
        Map<Coordinate, Integer> distances = new HashMap<>();
        Map<Coordinate, Coordinate> parent = new HashMap<>();

        pq.offer(new Node(start, 0));
        distances.put(start, 0);

        while (!pq.isEmpty()) {
            Node currNode = pq.poll(); //retrieve and remove the head of the queue
            Coordinate curr = currNode.coordinate;
            int distance = currNode.distance;

            //If the current node is the finish node, return the path
            if (curr.equals(finish)) {
                return reconstructPath(parent, curr);
            }

            int dx = 0, dy = 0; // Initialize direction
            boolean slide = true; // Flag to check if sliding

            // Generate neighbors of current node
            List<Node> neighbors = generateNeighbors(currNode, map, m, n);

            for (Node neighbor : neighbors) {
                int newDistance = distance + 1; // Cost is 1 for each move

                if (!distances.containsKey(neighbor.coordinate) || newDistance < distances.get(neighbor.coordinate)) {
                    distances.put(neighbor.coordinate, newDistance); // Update the distance
                    pq.add(new Node(neighbor.coordinate, newDistance));
                    parent.put(neighbor.coordinate, curr);
                }
            }
        }

        // If there's No Path
        System.out.println("No path found from start to finish.");
        return new ArrayList<>();
    }

    // Reconstruct the path from start to finish
    private static List<Coordinate> reconstructPath(Map<Coordinate, Coordinate> parent, Coordinate curr) {
        List<Coordinate> path = new ArrayList<>();
        while (parent.containsKey(curr)) {
            path.add(curr);
            curr = parent.get(curr);
        }
        Collections.reverse(path);
        return path;
    }

    // Read the map from a text file
    public static char[][] readMapFromFile(String filename) throws IOException {
        List<String> lines = new ArrayList<>();
        // Read the map from a file
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        // Get the number of rows and columns
        int numRows = lines.size();
        int numCols = lines.get(0).length();
        char[][] map = new char[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            String line = lines.get(i);
            for (int j = 0; j < numCols; j++) {
                map[i][j] = line.charAt(j);
            }
        }
        return map;
    }
}
