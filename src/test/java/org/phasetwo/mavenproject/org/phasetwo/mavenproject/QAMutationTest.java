package org.phasetwo.mavenproject.org.phasetwo.mavenproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

/**
 * Select function move as mutation Test
 *
 */
public class QAMutationTest {
    @BeforeEach
    public void setUp() {
    	Robot.executeCommand("I 10");
    	Robot.executeCommand("D");
    }
    
	@Test
	public void testMove() {
		// direction: north
    	Robot.move(5);
	    assertEquals(5, Robot.Yaxis);
	    for (int i = 1; i <= 5; i++) {
	        assertEquals(1, Robot.board[i][Robot.Xaxis]); // Check that board was marked
	    }
	    Robot.move(6); // Move north 6 steps
	    assertEquals(Robot.dimension - 1, Robot.Yaxis);
	    
	    // direction: east
	    Robot.executeCommand("R");
	    Robot.move(3);
	    for (int i = 1; i <= 3; i++) {
	        assertEquals(1, Robot.board[Robot.Yaxis][i]); // Check that board was marked
	    }
	    assertEquals(0, Robot.board[Robot.Yaxis][4]);
	    Robot.move(11);
	    assertEquals(Robot.dimension - 1, Robot.Yaxis);
	    
	    // direction: south
	    Robot.executeCommand("R");
	    Robot.move(3);
	    for (int i = 6; i <= 8; i++) {
	        assertEquals(1, Robot.board[i][Robot.Xaxis]); // Check that board was marked
	    }
	    assertEquals(0, Robot.board[5][Robot.Xaxis]);
	    
	    // direction: west
	    Robot.executeCommand("R");
	    Robot.move(3);
	    for (int i = 6; i <= 8; i++) {
	        assertEquals(1, Robot.board[Robot.Yaxis][i]); // Check that board was marked
	    }
	    assertEquals(0, Robot.board[Robot.Yaxis][5]);
	}
}
