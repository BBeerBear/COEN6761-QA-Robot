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

public class QADataFlowTest {
    @BeforeEach
    public void setUp() {
    	Robot.executeCommand("I 10");

    }
    
    @Test
    public void testPath1() {
    	Robot.turn_Right();
    	assertEquals("east", Robot.Direction);
    }
    @Test
    public void testPath2() {
    	for(int i = 0; i < 2; i++) {
    		Robot.turn_Right();
    	}
    	assertEquals("south", Robot.Direction);
    }
    @Test
    public void testPath3() {
    	for(int i = 0; i < 3; i++) {
    		Robot.turn_Right();
    	}
    	assertEquals("west", Robot.Direction);
    }
    @Test
    public void testPath4() {
    	for(int i = 0; i < 4; i++) {
    		Robot.turn_Right();
    	}
    	assertEquals("north", Robot.Direction);
    }
}
