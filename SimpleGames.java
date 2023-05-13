import java.util.*;

import javax.swing.event.AncestorListener;

public class CreateProjectTask {
    private static void getBoard(String[][] board) {
        // loop through board to print every element
        System.out.println("Board:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
            }
            // formatting purposes
            System.out.println();
        }
    }

    private static boolean endOddOrEven(int user, int computer) {
        if (user == 0 || computer == 0) {
            return true;
        }
        return false;
    }

    private static boolean wagerBooleanCheck(int stonesHand, int wager) {
        if (wager > stonesHand || wager < 1) {
            return false;
        }
        return true;
    }

    private static boolean winnerOrLoser(int wager, int computerStonesWager, boolean guess) {
        int sum = wager + computerStonesWager;
        boolean answer = false;
        if (sum % 2 == 1) {
            answer = true;
        }

        if (guess == answer) {
            return true;
        } else {
            return false;
        }
    }

    private static String checkWinner(String[][] bd) {
        // check rows and columns
        for (int i = 0; i < 3; i++) {
            if (bd[i][0].equals(bd[i][1]) && bd[i][1].equals(bd[i][2]) && !bd[i][0].equals("_")) {
                return bd[i][0];
            }
            if (bd[0][i].equals(bd[1][i]) && bd[1][i].equals(bd[2][i]) && !bd[0][i].equals("_")) {
                return bd[0][i];
            }
        }
        // check diagonals
        if (bd[0][0].equals(bd[1][1]) && bd[1][1].equals(bd[2][2]) && !bd[0][0].equals("_")) {
            return bd[0][0];
        }
        if (bd[2][0].equals(bd[1][1]) && bd[1][1].equals(bd[0][2]) && !bd[2][0].equals("_")) {
            return bd[2][0];
        }
        return "";
    }

    private static String full(String[][] bd2) {
        // check if full by looping through whole 2D array
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (bd2[i][j].equals("_")) {
                    return "";
                }
            }
        }
        return "full";
    }

    private static String valid(String[][] bd, int row, int col) {
        // input validation
        if (row < 0 || col < 0 || row > 2 || col > 2) {
            return "Not valid. This is out of bounds.";
        } else if (!bd[row][col].equals("_")) {
            return "This position is taken. Try again.";
        }
        return "That's valid.";
    }

    public static void main(String[] args) {
        // import scanner and random
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int game = 0;
        System.out.println("Tic Tac Toe and Rock Paper Scissors");
        System.out.println("===================================");
        // while loop forcing user to enter either 1 or 2
        while (game != 1 || game != 2) {
            System.out.print(
                    "What game would you like to play? \n1)Tic Tac Toe \n2)Rock Paper Scissors \nEnter the number next to the name of the game: ");
            game = scanner.nextInt();
            if (game > 2 || game < 1) {
                System.out.println("This is out of bounds. Try again.");
            } else {
                break;
            }
        }
        // tic tac toe choice
        if (game == 1) {
            // initialize matrix to make board
            String[][] mat = new String[3][3];
            // initalize board
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    mat[i][j] = "_";
                }
            }

            // Introduction, ask player names
            System.out.println("Two Player Tic Tac Toe");
            System.out.println("======================");
            System.out.print("Player 1, enter your name: ");
            String p1 = scanner.next();
            System.out.print("Player 2, enter your name: ");
            String p2 = scanner.next();
            // same name case
            if (p1.equals(p2)) {
                System.out.println("Same name I see... good luck diffrentiating between you two.");
            }

            // variables
            // turn and game validation using boolean declarations
            boolean p1t = true;
            boolean end = false;
            // row and columns
            int row = 0;
            int col = 0;
            int row2 = 0;
            int col2 = 0;
            // to ensure whole game goes through
            while (end == false) {
                // getBoard
                getBoard(mat);

                // turn
                if (p1t == true) {
                    System.out.println("It is " + p1 + "'s turn.");
                } else if (p1t == false) {
                    System.out.println("It is " + p2 + "'s turn.");
                }

                // String to fill board with x and o
                String str = "_";
                if (p1t == true) {
                    str = "x";
                } else if (p1t == false) {
                    str = "o";
                }

                // run while loop with true condition to ensure that this happens every time
                while (true) {
                    // Ask the user for what position they want to place their x or o
                    System.out.print("Enter a row number (1, 2, or 3): ");
                    row = scanner.nextInt();
                    row2 = row - 1;
                    System.out.print("Enter a column number (1, 2, or 3): ");
                    col = scanner.nextInt();
                    col2 = col - 1;

                    // Input validation
                    if (valid(mat, row2, col2).equals("That's valid.")) {
                        mat[row2][col2] = str;
                        break;
                    } else if (valid(mat, row2, col2).equals("You not valid. This is out of bounds.")) {
                        System.out.println("This is out of bounds. Try again.");
                    } else if (valid(mat, row2, col2).equals("This position is taken. Try again.")) {
                        System.out.println("This position is taken. Try again.");
                    }
                }

                // check winner based on return string
                if (checkWinner(mat).equals("x")) {
                    getBoard(mat);
                    System.out
                            .println(p1 + " won the game. Congratulations! You earned yourself some bragging rights.");
                    end = true;
                } else if (checkWinner(mat).equals("o")) {
                    getBoard(mat);
                    System.out
                            .println(p2 + " won the game. Congratulations! You earned yourself some bragging rights.");
                    end = true;
                } else {
                    // in case for ties, makes sure to check winner before tie to prevent any last
                    // second wins that are considered ties
                    if (full(mat).equals("full")) {
                        getBoard(mat);
                        System.out.println("The game resulted in a tie. Imagine not winning nor losing...");
                        end = true;
                    } else {
                        // turn switch
                        if (p1t == true) {
                            p1t = false;
                        } else {
                            p1t = true;
                        }
                    }
                }
            }
        }
        // rock paper scissor choice
        else if (game == 2) {
            // consume remaining newline
            scanner.nextLine();
            // introduction, declaration of important variables
            System.out.println("Single Player Rock Paper Scissors");
            System.out.println("=================================");
            System.out.print("Enter your name here: ");
            String name = scanner.nextLine();
            String rps = "";
            String cpuchoice = "";
            String yn = "";
            int userscore = 0;
            int cpuscore = 0;
            boolean end = false;
            // makes sure game can be repeated since it's very short
            while (end == false) {
                // ensures the right input can be entered
                while (true) {
                    System.out.print(name + ", enter rock, paper, or scissors here: ");
                    rps = scanner.nextLine();
                    rps = rps.toLowerCase();
                    if (!rps.equals("rock") && !rps.equals("paper") && !rps.equals("scissors")) {
                        System.out.println("This is invalid. Try again.");
                    } else {
                        break;
                    }
                }
                // cpurand converted into cpuchoice string by using switch cases
                int cpurand = random.nextInt(3);
                switch (cpurand) {
                    case 0:
                        cpuchoice = "rock";
                        break;
                    case 1:
                        cpuchoice = "paper";
                        break;
                    case 2:
                        cpuchoice = "scissors";
                        break;
                    default:
                        break;
                }
                System.out.println("Cpu chooses: " + cpuchoice);
                // userwin if statement
                if ((rps.equals("rock") && cpuchoice.equals("scissors"))
                        || (rps.equals("paper") && cpuchoice.equals("rock"))
                        || (rps.equals("scissors") && cpuchoice.equals("paper"))) {
                    System.out.println(name + " wins!");
                    userscore += 1;
                    System.out.println("Your score: " + userscore);
                    System.out.println("Cpu score: " + cpuscore);
                }
                // cpu win if statement
                else if ((rps.equals("scissors") && cpuchoice.equals("rock"))
                        || (rps.equals("rock") && cpuchoice.equals("paper"))
                        || (rps.equals("paper") && cpuchoice.equals("scissors"))) {
                    System.out.println("Cpu wins!");
                    cpuscore += 1;
                    System.out.println("Your score: " + userscore);
                    System.out.println("Cpu score: " + cpuscore);
                }
                // tie if statement
                else if ((rps.equals("rock") && cpuchoice.equals("rock"))
                        || (rps.equals("paper") && cpuchoice.equals("paper"))
                        || (rps.equals("scissors") && cpuchoice.equals("scissors"))) {
                    System.out.println("It's a tie!");
                    System.out.println("Your score: " + userscore);
                    System.out.println("Cpu score: " + cpuscore);
                }
                // forces user to enter y or n
                while (true) {
                    System.out.print("Would you like to play again? Y/N: ");
                    yn = scanner.nextLine();
                    yn = yn.toLowerCase();
                    // input validation
                    if (!yn.equals("y") && !yn.equals("n")) {
                        System.out.println("Invalid answer. Please try again.");
                    } else {
                        break;
                    }
                }
                // end or repeat loop
                if (yn.equals("y")) {
                    end = false;
                } else if (yn.equals("n")) {
                    end = true;
                }
            }
        }
    }
}