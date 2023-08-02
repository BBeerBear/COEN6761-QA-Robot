package org.phasetwo.mavenproject.org.phasetwo.mavenproject;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QAStatCovTest {

    @BeforeEach
    public void setUp() {
    	Robot.executeCommand("I 10");
    }

    @Test
    public void testInitialize() {
        assertEquals(10, Robot.dimension);
        assertEquals(0, Robot.Xaxis);
        assertEquals(0, Robot.Yaxis);
        assertEquals(false, Robot.PenDown);
        assertEquals("north", Robot.Direction);
    }

    @Test
    public void testPenDown() {
    	Robot.executeCommand("D");
        assertEquals(true, Robot.PenDown);
    }

    @Test
    public void testPenUp() {
    	Robot.executeCommand("U");
        assertEquals(false, Robot.PenDown);
    }
    
    @Test
    public void testTurnRight() {
    	Robot.executeCommand("R");
        assertEquals("east", Robot.Direction);
        Robot.executeCommand("R");
        assertEquals("south", Robot.Direction);
        Robot.executeCommand("R");
        assertEquals("west", Robot.Direction);
        Robot.executeCommand("R");
        assertEquals("north", Robot.Direction);
    }

    @Test
    public void testTurnLeft() {
        Robot.executeCommand("L");
        assertEquals("west", Robot.Direction);
        Robot.executeCommand("L");
        assertEquals("south", Robot.Direction);
        Robot.executeCommand("L");
        assertEquals("east", Robot.Direction);
        Robot.executeCommand("L");
        assertEquals("north", Robot.Direction);
    }
    
    @Test
    public void testMove() {
    	Robot.executeCommand("D");
    	Robot.executeCommand("M 5"); // Move north 5 steps
	    assertEquals(5, Robot.Yaxis);
	    for (int i = 1; i <= 5; i++) {
	        assertEquals(1, Robot.board[i][Robot.Xaxis]); // Check that board was marked
	    }

	    Robot.executeCommand("R"); // Turn right to face east
	    Robot.executeCommand("M 5"); // Move east 5 steps
	    assertEquals(5, Robot.Xaxis);
	    for (int i = 1; i <= 5; i++) {
	        assertEquals(1, Robot.board[Robot.Yaxis][i]); // Check that board was marked
	    }

	    Robot.executeCommand("R"); // Turn right to face south
	    Robot.executeCommand("M 3"); // Move south 3 steps
	    assertEquals(2, Robot.Yaxis); // Y should decrease by 3 when moving South
	    for (int i = 2; i <= 5; i++) {
	        assertEquals(1, Robot.board[i][Robot.Xaxis]); // Check that board was marked
	    }

	    Robot.executeCommand("R"); // Turn right to face west
	    Robot.executeCommand("M 3"); // Move west 3 steps
	    assertEquals(2, Robot.Xaxis); // X should decrease by 3 when moving West
	    for (int i = 2; i <= 5; i++) {
	        assertEquals(1, Robot.board[Robot.Yaxis][i]); // Check that board was marked
	    }
    }

    @Test
    public void testPrintCurrentPosition() {
        String expected = "Position: 0, 0 - Pen: up - Facing: north\r\n";
        assertEquals(expected, Robot.print_CurrentPosition());
    }

    @Test
    public void testInvalidCommand() {
        // Store the original System.out
        PrintStream originalOut = System.out;
        // Capture the console output
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOutput));   
        // Execute the invalid command
        Robot.executeCommand("X");
        // Restore the original System.out
        System.setOut(originalOut);
        // Assert that the console output matches the expected message
        assertEquals("Invalid command.\r\n", consoleOutput.toString());
    }

    @Test
    public void testPrintBoard() {
        Robot.executeCommand("D");
        Robot.executeCommand("r");
        Robot.executeCommand("M 1");
        String expected = " 9                     \n"
                        + " 8                     \n"
                        + " 7                     \n"
                        + " 6                     \n"
                        + " 5                     \n"
                        + " 4                     \n"
                        + " 3                     \n"
                        + " 2                     \n"
                        + " 1                     \n"
                        + " 0   *                 \n"
                        + "    0 1 2 3 4 5 6 7 8 9\n";
        assertEquals(expected, Robot.print_board());
    }
}
