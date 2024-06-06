package org.example;

/*
 * This is the Node class to store coordinates and distance from start
 * @author Tashini Weerasooriya - 20212153/w1901991
 * @since 20th March 2024
 */

public class Node implements Comparable<Node> {
    Coordinate coordinate;
    int distance;

    public Node(Coordinate coordinate, int distance) {
        this.coordinate = coordinate;
        this.distance = distance;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.distance, other.distance);
    }
}