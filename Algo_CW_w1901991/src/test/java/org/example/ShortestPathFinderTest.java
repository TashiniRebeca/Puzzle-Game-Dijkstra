package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.example.ShortestPathFinder.findShortestPath;
import static org.junit.jupiter.api.Assertions.assertEquals;

// Test cases for findShortestPath method
public class ShortestPathFinderTest {
    // Test case for a simple map
    @Test
    public void testFindShortestPath_SimpleMap() {
        char[][] map = {
                {'S', '.', '.', '.', '.', '.', '.', '.', '.', 'F'}
        };
        Coordinate start = new Coordinate(0, 0);
        Coordinate finish = new Coordinate(0, 9);
        assertEquals(1, findShortestPath(map, start, finish).size());
    }

    // Test case for a complex map
    @Test
    public void testFindShortestPath_ComplexMap() {
        char[][] map = {
                {'S', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '0'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '0'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '0'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '0'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '0'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '0'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '0'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', 'F'}
        };
        Coordinate start = new Coordinate(0, 0);
        Coordinate finish = new Coordinate(9, 9);
        assertEquals(2, findShortestPath(map, start, finish).size());
    }

    // Test case for a map with no path
    @Test
    public void testFindShortestPath_NoPath() {
        char[][] map = {
                {'S', '0', '0', '0'},
                {'0', '0', '.', '0'},
                {'0', '.', '.', '0'},
                {'0', '0', '0', 'F'}
        };
        Coordinate start = new Coordinate(0, 0);
        Coordinate finish = new Coordinate(3, 3);
        Assertions.assertTrue(findShortestPath(map, start, finish).isEmpty());
    }

    // Test case for a map with a single path
    @Test
    public void testFindShortestPath_StartAndFinishAdjacent() {
        char[][] map = {
                {'S', '.', 'F'}
        };
        Coordinate start = new Coordinate(0, 0);
        Coordinate finish = new Coordinate(0, 2);
        assertEquals(1, findShortestPath(map, start, finish).size());
    }

    // Test case for a map with a single cell
    @Test
    public void testFindShortestPath_SingleCellMap() {
        char[][] map = {
                {'S'}
        };
        Coordinate start = new Coordinate(0, 0);
        Coordinate finish = new Coordinate(0, 0);
        assertEquals(0, findShortestPath(map, start, finish).size());
    }

    // Test case for a map with a single row
    @Test
    public void testFindShortestPath_SingleRowMap() {
        char[][] map = {
                {'S', '.', '.', '.', '.', '.', '.', '.', '.', 'F'}
        };
        Coordinate start = new Coordinate(0, 0);
        Coordinate finish = new Coordinate(0, 9);
        assertEquals(1, findShortestPath(map, start, finish).size());
    }

    // Test case for a map with a single column
    @Test
    public void testFindShortestPath_SingleColumnMap() {
        char[][] map = {
                {'S'},
                {'.', '0', '0', '0', '0', '0', '0', '0', '0', 'F'}
        };
        Coordinate start = new Coordinate(0, 0);
        Coordinate finish = new Coordinate(1, 9);
        assertEquals(0, findShortestPath(map, start, finish).size());
    }

    // Test case for a large map
    @Test
    public void testFindShortestPath_LargeMap() {
        char[][] map = new char[100][100];
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                map[i][j] = '.';
            }
        }
        map[0][0] = 'S';
        map[99][99] = 'F';
        Coordinate start = new Coordinate(0, 0);
        Coordinate finish = new Coordinate(99, 99);
        assertEquals(198, findShortestPath(map, start, finish).size());
    }
}
