package org.phasetwo.mavenproject.org.phasetwo.mavenproject;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QAStatMultCondCovTest {
	
	
	@BeforeEach
    public void setUp() {
    	Robot.executeCommand("I 10");
    }
	
	@Test
    public void testInitializeCommand() {
	   // Execute the "I 10" command
	   Robot.executeCommand("I 6");   
	   // Assert that the dimension is set to 10 after executing the command
	   assertTrue(Robot.dimension == 6, "Expected dimension to be 6, but was: " + Robot.dimension);
	   Robot.executeCommand("i 6");   
	   // Assert that the dimension is set to 10 after executing the command
	   assertTrue(Robot.dimension == 6, "Expected dimension to be 6, but was: " + Robot.dimension);
		
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
        Robot.executeCommand("d");
        assertEquals(true, Robot.PenDown);
    }

    @Test
    public void testPenUp() {
    	Robot.executeCommand("U");
        assertEquals(false, Robot.PenDown);
        Robot.executeCommand("u");
        assertEquals(false, Robot.PenDown);
    }
    
    @Test
    public void testTurnRight() {
    	Robot.executeCommand("R");
        assertEquals("east", Robot.Direction);
        Robot.executeCommand("r");
        assertEquals("south", Robot.Direction);
        Robot.executeCommand("R");
        assertEquals("west", Robot.Direction);
        Robot.executeCommand("r");
        assertEquals("north", Robot.Direction);
    }

    @Test
    public void testTurnLeft() {
        Robot.executeCommand("L");
        assertEquals("west", Robot.Direction);
        Robot.executeCommand("l");
        assertEquals("south", Robot.Direction);
        Robot.executeCommand("L");
        assertEquals("east", Robot.Direction);
        Robot.executeCommand("l");
        assertEquals("north", Robot.Direction);
    }
    
    @Test
    public void testMove() {
    	//north movements
    	Robot.executeCommand("u");
    	Robot.executeCommand("M 5"); // Move north 5 steps
	    assertEquals(5, Robot.Yaxis);
	    for (int i = 1; i <= 5; i++) {
	        assertEquals(0, Robot.board[i][Robot.Xaxis]); // Check that board was marked
	    }
	    Robot.executeCommand("d");
	    Robot.executeCommand("M 2"); // Move north 5 steps
	    assertEquals(7, Robot.Yaxis);
	    for (int k = 5+1; k <= 5+2; k++) {
	        assertEquals(1, Robot.board[k][Robot.Xaxis]); // Check that board was marked
	    }
	    
	    //east movements
	    Robot.executeCommand("u");
	    Robot.executeCommand("R"); // Turn right to face east
	    Robot.executeCommand("m 1"); // Move east 5 steps
	   
	    assertEquals(1, Robot.Xaxis);
	    for (int i = 1; i <= 1; i++) {
	        assertEquals(0, Robot.board[Robot.Yaxis][i]); // Check that board was marked
	    }
	    Robot.executeCommand("d");
	    Robot.executeCommand("m 4"); // Move east 5 steps
	   
	    assertEquals(1+4, Robot.Xaxis);
	    for (int i = 1+1; i <= 1+4; i++) {
	        assertEquals(1, Robot.board[Robot.Yaxis][i]); // Check that board was marked
	    }
	    
//south movements
	    Robot.executeCommand("u");
	    Robot.executeCommand("R"); // Turn right to face south
	    Robot.executeCommand("M 3"); // Move south 3 steps
	    assertEquals(7-3, Robot.Yaxis); // Y should decrease by 3 when moving South
	    for (int i = 7-3; i <= 7-1; i++) {
	        assertEquals(0, Robot.board[i][Robot.Xaxis]); // Check that board was marked
	    }
	    Robot.executeCommand("d");
	    Robot.executeCommand("M 3"); // Move south 3 steps
	    assertEquals(1, Robot.Yaxis); // Y should decrease by 3 when moving South
	    for (int i = 1; i <= 4-1; i++) {
	        assertEquals(1, Robot.board[i][Robot.Xaxis]); // Check that board was marked
	    }
	    
	    //west movements
	    Robot.executeCommand("d");
	    Robot.executeCommand("R"); // Turn right to face west
	    Robot.executeCommand("M 3"); // Move west 3 steps
	    assertEquals(5-3, Robot.Xaxis); // X should decrease by 3 when moving West
	    for (int i = 5-3; i <= 5-1; i++) {
	        assertEquals(1, Robot.board[Robot.Yaxis][i]); // Check that board was marked
	    }
	    Robot.executeCommand("u");
	    Robot.executeCommand("M 1"); // Move west 3 steps
	    assertEquals(1, Robot.Xaxis); // X should decrease by 3 when moving West
	    for (int i = 1; i <= 2-1; i++) {
	        assertEquals(0, Robot.board[Robot.Yaxis][i]); // Check that board was marked
	    }
	    }
	    

    @Test
    public void testPrintCurrentPosition() {
        // Store the original System.out
        PrintStream originalOut = System.out;
        // Capture the console output
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOutput));   
        // Execute the C command
        Robot.executeCommand("C");
        // Restore the original System.out
        System.setOut(originalOut);
        String expectedOutput = String.format("Position: %d, %d - Pen: %s - Facing: %s%n",
                Robot.Xaxis, Robot.Yaxis, (Robot.PenDown ? "down" : "up"), Robot.Direction);
        // Assert that the console output matches the expected message
        expectedOutput += "\r\n";
        assertEquals(expectedOutput, consoleOutput.toString());
        Robot.executeCommand("c");
        // Restore the original System.out
        System.setOut(originalOut);
        String expOutput = String.format("Position: %d, %d - Pen: %s - Facing: %s%n",
                Robot.Xaxis, Robot.Yaxis, (Robot.PenDown ? "down" : "up"), Robot.Direction);
        // Assert that the console output matches the expected message
        expOutput += "\r\n";
        assertEquals(expOutput, consoleOutput.toString());
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
    	// Store the original System.out
        PrintStream originalOut = System.out;
        // Capture the console output
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOutput));  
        Robot.executeCommand("D");
        Robot.executeCommand("r");
        Robot.executeCommand("M 1");
        Robot.executeCommand("P");
        
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
        // Restore the original System.out
        System.setOut(originalOut);
        // Assert that the console output matches the expected message
        assertEquals(expected, consoleOutput.toString());
//        assertEquals(expected, Robot.print_board());
Robot.executeCommand("p");
        
        String exp = " 9                     \n"
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
        // Restore the original System.out
        System.setOut(originalOut);
        // Assert that the console output matches the expected message
        assertEquals(exp, consoleOutput.toString());
//        assertEquals(expected, Robot.print_board());
    }
}
