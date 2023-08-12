//package org.phasetwo.mavenproject.org.phasetwo.mavenproject;
//
//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.*;
//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;
//import java.util.Arrays;
//
//public class QADecisionCoverageTest {
//	
//	private final PrintStream originalStdOut = System.out;
//    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
//
//    
//    @Test
//    public void testExecuteCommand_Initialize_System() {
//        Robot.executeCommand("I 5");
//        assertEquals(5, Robot.dimension);
//        Robot.executeCommand("i 5");
//        assertEquals(5, Robot.dimension);
//    }
//    
//
//    @Test
//    public void testExecuteCommand_Print_CurrentPosition() {
//        Robot.initialize_System(5);
//        Robot.executeCommand("C");
//        String expectedOutput = "Position: 0, 0 - Pen: up - Facing: north";
//        assertEquals(expectedOutput, Robot.print_CurrentPosition().trim());
//        
//        Robot.initialize_System(5);
//        Robot.executeCommand("c");
//        Robot.PenDown = true;
//        String expectedOutput2 = "Position: 0, 0 - Pen: down - Facing: north";
//        assertEquals(expectedOutput2, Robot.print_CurrentPosition().trim());
//    }
//
//
//    @Test
//    public void testExecuteCommand_SetPenDown() {
//        Robot.initialize_System(5);
//        Robot.executeCommand("D");
//        assertTrue(Robot.PenDown);
//
//        Robot.executeCommand("U");
//        assertFalse(Robot.PenDown);
//        
//        Robot.executeCommand("d");
//        assertTrue(Robot.PenDown);
//
//        Robot.executeCommand("u");
//        assertFalse(Robot.PenDown);
//    }
//    
//
//    @Test
//    public void testExecuteCommand_Turn_Right() {
//        Robot.initialize_System(5);
//        Robot.executeCommand("R");
//        assertEquals("east", Robot.Direction);
//
//        Robot.executeCommand("R");
//        assertEquals("south", Robot.Direction);
//
//        Robot.executeCommand("R");
//        assertEquals("west", Robot.Direction);
//
//        Robot.executeCommand("R");
//        assertEquals("north", Robot.Direction);
//        
//        Robot.executeCommand("r");
//        assertEquals("east", Robot.Direction);
//
//        Robot.executeCommand("r");
//        assertEquals("south", Robot.Direction);
//
//        Robot.executeCommand("r");
//        assertEquals("west", Robot.Direction);
//
//        Robot.executeCommand("r");
//        assertEquals("north", Robot.Direction);
//    }
//    
//
//    @Test
//    public void testExecuteCommand_Turn_Left() {
//        Robot.initialize_System(5);
//        Robot.executeCommand("L");
//        assertEquals("west", Robot.Direction);
//
//        Robot.executeCommand("L");
//        assertEquals("south", Robot.Direction);
//
//        Robot.executeCommand("L");
//        assertEquals("east", Robot.Direction);
//
//        Robot.executeCommand("L");
//        assertEquals("north", Robot.Direction);
//        
//        Robot.executeCommand("l");
//        assertEquals("west", Robot.Direction);
//
//        Robot.executeCommand("l");
//        assertEquals("south", Robot.Direction);
//
//        Robot.executeCommand("l");
//        assertEquals("east", Robot.Direction);
//
//        Robot.executeCommand("l");
//        assertEquals("north", Robot.Direction);
//    }
//   
//
//    @Test
//    public void testExecuteCommand_Move() {
//        Robot.initialize_System(5);
//
//        // Test moving north
//        Robot.executeCommand("M 3");
//        assertEquals(3, Robot.Yaxis);
//        assertEquals(0, Robot.Xaxis);
//
//        // Test moving east
//        Robot.executeCommand("R");
//        Robot.executeCommand("M 3");
//        assertEquals(3, Robot.Yaxis);
//        assertEquals(3, Robot.Xaxis);
//
//        // Test moving south
//        Robot.executeCommand("R");
//        Robot.executeCommand("M 2");
//        assertEquals(1, Robot.Yaxis);
//        assertEquals(3, Robot.Xaxis);
//
//        // Test moving west
//        Robot.executeCommand("R");
//        Robot.executeCommand("M 1");
//        assertEquals(1, Robot.Yaxis);
//        assertEquals(2, Robot.Xaxis);
//        
//        Robot.initialize_System(5);
//        // Test moving north
//        Robot.executeCommand("m 3");
//        assertEquals(3, Robot.Yaxis);
//        assertEquals(0, Robot.Xaxis);
//
//        // Test moving east
//        Robot.executeCommand("r");
//        Robot.executeCommand("m 3");
//        assertEquals(3, Robot.Yaxis);
//        assertEquals(3, Robot.Xaxis);
//
//        // Test moving south
//        Robot.executeCommand("r");
//        Robot.executeCommand("m 2");
//        assertEquals(1, Robot.Yaxis);
//        assertEquals(3, Robot.Xaxis);
//
//        // Test moving west
//        Robot.executeCommand("r");
//        Robot.executeCommand("m 1");
//        assertEquals(1, Robot.Yaxis);
//        assertEquals(2, Robot.Xaxis);
//    }
//    
//
//    @Test
//    public void testExecuteCommand_Print_Board() {
//        Robot.initialize_System(5);
//        Robot.executeCommand("P");
//        Robot.setPenDown(true);
//        Robot.move(3);
//        String expectedOutput = " 4           \n 3 *         \n 2 *         \n 1 *         \n 0           \n    0 1 2 3 4\n";
//        assertEquals(expectedOutput, Robot.print_board());
//        
//        Robot.initialize_System(5);
//        Robot.executeCommand("p");
//        Robot.setPenDown(true);
//        Robot.move(3);
//        assertEquals(expectedOutput, Robot.print_board());
//    }
//    
//    @Test
//    public void testExecuteCommand_Invalid_Command() {
//    	System.setOut(new PrintStream(outputStreamCaptor));
//        Robot.initialize_System(5);
//        Robot.executeCommand("A");
//        
//        // Get the captured output as a string
//        String capturedText = outputStreamCaptor.toString().trim();
//        System.setOut(originalStdOut);
//        String expectedOutput = "Invalid command.";
//        assertEquals(expectedOutput, capturedText);
//    }
//
//    @Test
//    public void testMove_North_With_PenDown() {
//        Robot.initialize_System(5);
//        Robot.setPenDown(true);
//        Robot.move(3);
//        int[][] expectedBoard = {
//                {0, 0, 0, 0, 0},
//                {1, 0, 0, 0, 0},
//                {1, 0, 0, 0, 0},
//                {1, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0}
//        };
//        assertArrayEquals(expectedBoard, Robot.board);
//    }
//
//    @Test
//    public void testMove_East_With_PenDown() {
//        Robot.initialize_System(5);
//        Robot.setPenDown(true);
//        Robot.turn_Right();
//        Robot.move(3);
//        int[][] expectedBoard = {
//                {0, 1, 1, 1, 0},
//                {0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0}
//        };
//        assertArrayEquals(expectedBoard, Robot.board);
//    }
//
//    @Test
//    public void testMove_South_With_PenDown() {
//        Robot.initialize_System(5);
//        Robot.setPenDown(true);
//        Robot.turn_Right();
//        Robot.turn_Right();
//        Robot.move(3);
//        int[][] expectedBoard = {
//                {1, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0}
//        };
//        assertArrayEquals(expectedBoard, Robot.board);
//    }
//
//    @Test
//    public void testMove_West_With_PenDown() {
//        Robot.initialize_System(5);
//        Robot.setPenDown(true);
//        Robot.turn_Left();
//        Robot.move(3);
//        int[][] expectedBoard = {
//                {1, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0}
//        };
//        assertArrayEquals(expectedBoard, Robot.board);
//    }
//    
//    @Test
//    public void testMove_North_With_PenUp() {
//        Robot.initialize_System(5);
//        Robot.setPenDown(false);
//        Robot.move(3);
//        int[][] expectedBoard = {
//                {0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0}
//        };
//        assertArrayEquals(expectedBoard, Robot.board);
//    }
//    
//    @Test
//    public void testMove_East_With_PenUp() {
//        Robot.initialize_System(5);
//        Robot.setPenDown(false);
//        Robot.turn_Right();
//        Robot.move(3);
//        int[][] expectedBoard = {
//                {0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0}
//        };
//        assertArrayEquals(expectedBoard, Robot.board);
//    }
//
//    @Test
//    public void testMove_South_With_PenUp() {
//        Robot.initialize_System(5);
//        Robot.setPenDown(false);
//        Robot.turn_Right();
//        Robot.turn_Right();
//        Robot.move(3);
//        int[][] expectedBoard = {
//                {0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0}
//        };
//        assertArrayEquals(expectedBoard, Robot.board);
//    }
//
//    @Test
//    public void testMove_West_With_PenUp() {
//        Robot.initialize_System(5);
//        Robot.setPenDown(false);
//        Robot.turn_Left();
//        Robot.move(3);
//        int[][] expectedBoard = {
//                {0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0}
//        };
//        assertArrayEquals(expectedBoard, Robot.board);
//    }
//}
//
