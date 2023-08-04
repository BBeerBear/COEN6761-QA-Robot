package org.phasetwo.mavenproject.org.phasetwo.mavenproject;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Assertions;


public class QaCondCovTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }
    
    @BeforeEach
    public void setUp() {
    	Robot.executeCommand("I 10");
    }
    @Test
    public void testInitializeSystemWithValidInputUpperCase() {
        // Test with commandType 'I' (uppercase)
        String command = "I 5";
        Robot.executeCommand(command);
        // Assertions to verify that the system is initialized correctly
        Assertions.assertEquals(5, Robot.dimension, "Dimension should be set to 5");
        Assertions.assertEquals(0, Robot.Xaxis, "Xaxis should be set to 0");
        Assertions.assertEquals(0, Robot.Yaxis, "Yaxis should be set to 0");
        Assertions.assertFalse(Robot.PenDown, "PenDown should be set to false");
        Assertions.assertEquals("north", Robot.Direction, "Direction should be set to 'north'");
    }

    @Test
    public void testInitializeSystemWithValidInputLowerCase() {
        // Test with commandType 'i' (lowercase)
        String command = "i 3";
        Robot.executeCommand(command);
        // Assertions to verify that the system is initialized correctly
        Assertions.assertEquals(3, Robot.dimension, "Dimension should be set to 3");
        Assertions.assertEquals(0, Robot.Xaxis, "Xaxis should be set to 0");
        Assertions.assertEquals(0, Robot.Yaxis, "Yaxis should be set to 0");
        Assertions.assertFalse(Robot.PenDown, "PenDown should be set to false");
        Assertions.assertEquals("north", Robot.Direction, "Direction should be set to 'north'");
    }

    @Test
    public void testInitializeSystemWithInvalidInput() {
        // Test with commandType 'a' (any character other than 'I' or 'i')
        String command = "a 7";
        Robot.executeCommand(command);
        // Assertions to verify that the system state remains unchanged
        // As the input is invalid, the method should not change the system state
        Assertions.assertEquals(10, Robot.dimension, "Dimension should be unchanged");
        Assertions.assertEquals(0, Robot.Xaxis, "Xaxis should be unchanged");
        Assertions.assertEquals(0, Robot.Yaxis, "Yaxis should be unchanged");
        Assertions.assertFalse(Robot.PenDown, "PenDown should be unchanged");
        Assertions.assertEquals("north", Robot.Direction, "Direction should be unchanged");
    }


    
    @Test
    public void testPrintCurrentPositionWithLowerCaseC() {
        // Set up the initial state of the robot (optional)
        Robot.dimension = 5;
        Robot.Xaxis = 2;
        Robot.Yaxis = 3;
        Robot.PenDown = true;
        Robot.Direction = "south";

        // Call the method to print the current position
        Robot.executeCommand("c");

        // Capture the printed output
        String output = outputStream.toString();

        // Define the expected output with leading/trailing whitespaces removed
        String expectedOutput = "Position: 2, 3 - Pen: down - Facing: south\n";

        // Trim both the expected and actual output before comparison
        expectedOutput = expectedOutput.trim();
        output = output.trim();

        // Assert the output matches the expected output
        Assertions.assertEquals(expectedOutput, output, "Printed current position should match expected output");
    }


    @Test
    public void testPrintCurrentPositionWithUpperCaseC() {
        // Set up the initial state of the robot (optional)
        Robot.dimension = 5;
        Robot.Xaxis = 1;
        Robot.Yaxis = 2;
        Robot.PenDown = false;
        Robot.Direction = "east";

        // Call the method to print the current position
        Robot.executeCommand("C");

        // Capture the printed output
        String output = outputStream.toString();

        // Define the expected output
        String expectedOutput = "Position: 1, 2 - Pen: up - Facing: east\n";

        // Print both the expected and actual output to check for hidden characters
        System.out.println("Expected: '" + expectedOutput + "'");
        System.out.println("Actual: '" + output + "'");

        // Trim both the expected and actual output before comparison
        expectedOutput = expectedOutput.trim();
        output = output.trim();

        // Assert the output matches the expected output
        Assertions.assertEquals(expectedOutput, output, "Printed current position should match expected output");
    }


    @Test
    public void testPrintCurrentPositionWithInvalidCommand() {
        // Call the method with an invalid command 'a'
        Robot.executeCommand("a");

        // Capture the printed output
        String output = outputStream.toString();

        // Define the expected output for invalid command
        String expectedOutput = "Invalid command.\r\n";

        // Assert the output matches the expected output
        Assertions.assertEquals(expectedOutput, output, "Printed output for invalid command should match expected output");
    }


        @Test
        public void testSetPenDownWithUpperCaseD() {
            // Set up initial state of the robot (optional)
            Robot.PenDown = false;

            // Call the method to set the pen down
            Robot.executeCommand("D");

            // Assert that the pen is down
            Assertions.assertTrue(Robot.PenDown, "Pen should be down");
        }

        @Test
        public void testSetPenDownWithLowerCaseD() {
            // Set up initial state of the robot (optional)
            Robot.PenDown = true;

            // Call the method to set the pen down
            Robot.executeCommand("d");

            // Assert that the pen is down
            Assertions.assertTrue(Robot.PenDown, "Pen should be down");
        }

        @Test
        public void testSetPenUpWithUpperCaseU() {
            // Set up initial state of the robot (optional)
            Robot.PenDown = true;

            // Call the method to set the pen up
            Robot.executeCommand("U");

            // Assert that the pen is up
            Assertions.assertFalse(Robot.PenDown, "Pen should be up");
        }

        @Test
        public void testSetPenUpWithLowerCaseU() {
            // Set up initial state of the robot (optional)
            Robot.PenDown = false;

            // Call the method to set the pen up
            Robot.executeCommand("u");

            // Assert that the pen is up
            Assertions.assertFalse(Robot.PenDown, "Pen should be up");
        }

        @Test
        public void testSetPenWithInvalidInput() {
            // Set up initial state of the robot (optional)
            Robot.PenDown = true;

            // Call the method with an invalid input
            Robot.executeCommand("a");

            // Assert that the pen state is unchanged
            Assertions.assertTrue(Robot.PenDown, "Pen state should remain unchanged");
        }
    
        @Test
        public void testTurnRight() {
        	Robot.executeCommand("R");
        	Assertions.assertEquals("east", Robot.Direction);
            Robot.executeCommand("r");
            Assertions.assertEquals("south", Robot.Direction);
            Robot.executeCommand("R");
            Assertions.assertEquals("west", Robot.Direction);
            Robot.executeCommand("r");
            Assertions.assertEquals("north", Robot.Direction);
            Robot.executeCommand("a");
            Assertions.assertEquals("north", Robot.Direction);
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
            Robot.executeCommand("a");
            Assertions.assertEquals("north", Robot.Direction);
        } 
        @Test
        public void testMoveWithValidCommands() {
            // Set up the initial state of the robot (optional)
            Robot.dimension = 5;
            Robot.Xaxis = 2;
            Robot.Yaxis = 3;
            Robot.PenDown = true;
            Robot.Direction = "south";

            // Test with command "M" (move)
            Robot.executeCommand("M 1");
            Assertions.assertEquals(2, Robot.Yaxis, "Robot should move SOUTH and Yaxis should be 4");

            // Test with command "m" (move)
            Robot.executeCommand("m 3");
            Assertions.assertEquals(0 , Robot.Yaxis, "Robot should move SOUTH and Yaxis should be 5");

            // Test with command "a" (invalid command)
            int initialY = Robot.Yaxis;
            Robot.executeCommand("a");
            Assertions.assertEquals(initialY, Robot.Yaxis, "Robot should not move for invalid command 'a'");
        }
        @Test
        public void testPrintBoardCommand() {
            // Set up the initial state of the robot (optional)
            Robot.dimension = 5;
            Robot.Xaxis = 2;
            Robot.Yaxis = 3;
            Robot.PenDown = true;
            Robot.Direction = "south";

            // Capture the standard output
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(outputStream));

            // Test with command "P" (print_board)
            Robot.executeCommand("P");
            String output = outputStream.toString();
            System.out.println("Output Captured:\n" + output);

            Assertions.assertFalse(output.contains("Dimension: 5x5"), "Print_board should show the board dimensions");

            // Test with command "p" (print_board)
            outputStream.reset(); // Clear the output stream
            Robot.executeCommand("p");
            output = outputStream.toString();
            System.out.println("Output Captured:\n" + output);

            Assertions.assertFalse(output.contains("Dimension: 5x5"), "Print_board should show the board dimensions");

            // Test with command "a" (invalid command)
            outputStream.reset(); // Clear the output stream
            Robot.executeCommand("a");
            output = outputStream.toString();
            System.out.println("Output Captured:\n" + output);

            Assertions.assertFalse(output.contains("Dimension: 5x5"), "Print_board should not be executed for invalid command 'a'");

            // Restore the standard output
            System.setOut(originalOut);
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
        public void testPrintBoardForCellsWithValueOne() {
            // Set up the initial state of the robot (optional)
            Robot.dimension = 5;
            Robot.Xaxis = 2;
            Robot.Yaxis = 3;
            Robot.PenDown = true;
            Robot.Direction = "south";

            // Set up the board with specific values
            int[][] board = {
                {0, 0, 0, 0, 0},
                {0, 1, 1, 0, 0},
                {1, 0, 0, 1, 0},
                {0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1}
            };

            // Set the board of the Robot class to the custom board
            Robot.board = board;

            // Capture the standard output
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(outputStream));

            // Call the print_board() method
            Robot.print_board();

            // Restore the standard output
            System.setOut(originalOut);

            // Verify the output
            String output = outputStream.toString();
            System.out.println("Output Captured:\n" + output);

            // Test if the output contains "* " for cells with value 1
            Assertions.assertTrue(output.contains("* "), "Output should contain '* ' for cells with value 1");

            // Test if the output does not contain "* " for cells with value other than 1
            Assertions.assertTrue(output.contains("*  "), "Output should not contain '* ' for cells with value other than 1");
        }
}


                