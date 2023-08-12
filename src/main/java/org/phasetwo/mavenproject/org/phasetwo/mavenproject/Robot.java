package org.phasetwo.mavenproject.org.phasetwo.mavenproject;

import java.util.Scanner;

public class Robot {

    static int[][] board;
    static int dimension;
    static int Xaxis;
    static int Yaxis;
    static boolean PenDown;
    static String Direction;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.print("Enter the command (or 'Q' to quit): ");
        while (!(command = scanner.nextLine()).equalsIgnoreCase("Q")) {
            try {
                executeCommand(command);
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
            System.out.print("Enter the command (or 'Q' to quit): ");
        }
        scanner.close();
    }

    public static void executeCommand(String command) throws IllegalArgumentException {
        if (command.length() < 1) {
            throw new IllegalArgumentException("Command must not be empty.");
        }

        char commandType = command.charAt(0);
        String arguments = command.length() > 1 ? command.substring(1).trim() : "";

        try {
            switch (Character.toLowerCase(commandType)) {
                case 'i':
                    initialize_System(Integer.parseInt(arguments));
                    break;
                case 'c':
                    print_CurrentPosition();
                    break;
                case 'd':
                    setPenDown(true);
                    break;
                case 'u':
                    setPenDown(false);
                    break;
                case 'r':
                    turn_Right();
                    break;
                case 'l':
                    turn_Left();
                    break;
                case 'm':
                    move(Integer.parseInt(arguments));
                    break;
                case 'p':
                    print_board();
                    break;
                default:
                    System.out.println("Invalid command.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format in command: " + command);
        }
    }

    public static void initialize_System(int n) {
        dimension = n;
        board = new int[dimension][dimension];
        Xaxis = 0;
        Yaxis = 0;
        PenDown = false;
        Direction = "north";
    }

    public static String print_CurrentPosition() {
        String output = String.format("Position: %d, %d - Pen: %s - Facing: %s%n",
                Xaxis, Yaxis, (PenDown ? "down" : "up"), Direction);
        System.out.println(output);
        return output;
    }

    public static void setPenDown(boolean down) {
        PenDown = down;
    }

    public static void turn_Right() {
        if (Direction.equals("north")) { Direction = "east"; }
        else if (Direction.equals("east")) { Direction = "south"; }
        else if (Direction.equals("south")) { Direction = "west"; }
        else { Direction = "north"; }
    }

    public static void turn_Left() {
        if (Direction.equals("north")) { Direction = "west"; }
        else if (Direction.equals("east")) { Direction = "north"; }
        else if (Direction.equals("south")) { Direction = "east"; }
        else { Direction = "south"; }
    }

    public static void move(int steps) {
        if (Direction.equals("north")) {
            for (int i = 0; i < steps; i++) {
                Yaxis = Math.min(Yaxis + 1, dimension - 1);
                if (PenDown) {
                    board[Yaxis][Xaxis] = 1;
                }
            }
        } else if (Direction.equals("east")) {
            for (int i = 0; i < steps; i++) {
                Xaxis = Math.min(Xaxis + 1, dimension - 1);
                if (PenDown) {
                    board[Yaxis][Xaxis] = 1;
                }
            }
        } else if (Direction.equals("south")) {
            for (int i = 0; i < steps; i++) {
                Yaxis = Math.max(Yaxis - 1, 0);
                if (PenDown) {
                    board[Yaxis][Xaxis] = 1;
                }
            }
        } else {
            for (int i = 0; i < steps; i++) {
                Xaxis = Math.max(Xaxis - 1, 0);
                if (PenDown) {
                    board[Yaxis][Xaxis] = 1;
                }
            }
        }
    }

    public static String print_board() {
        StringBuilder output = new StringBuilder();
        for (int i = dimension - 1; i >= 0; i--) {
            output.append(String.format("%2d ", i));
            for (int j = 0; j < dimension; j++) {
                if (board[i][j] == 1) {
                    output.append("* ");
                } else {
                    output.append("  ");
                }
            }
            output.append("\n");
        }
        output.append("   ");
        for (int i = 0; i < dimension; i++) {
            output.append(String.format("%2d", i));
        }
        output.append("\n");
        System.out.print(output);

        return output.toString();
    }
}
