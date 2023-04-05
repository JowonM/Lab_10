import java.util.Scanner;
public class TicTacToe {
    //Static ints to use in methods//
    private static final int ROW = 3;
    private static final int COL = 3;
    //Static String array that acts as the board//
    private static String board[][] = new String[COL][ROW];
    //void = no return in method//
    private static void turn(Scanner in, String player)
    {
        int rRow = 0;
        int cCol = 0;
        //boolean to control the do-while loop//
        boolean validMove = true;
        do
        {
            validMove = false;

            System.out.print(player + "'s turn");
            cCol = SafeInput.getRangedInt(in, "Enter the column you would like to occupy(1-3): ", 1, 3) - 1;
            rRow = SafeInput.getRangedInt(in, "Enter the row you would like to occupy(1-3): ", 1, 3) - 1;
            if (isValidMove(cCol, rRow))
            {
                board[rRow][cCol] = player;
                display();
                System.out.println();

            }
            else
            {
                validMove = true;
                System.out.println("Invalid move, this space is occupied.");

            }
        }    while (validMove);
    }
            //method to clear(add spaces to) the board //
    private static void clearBoard() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                board[i][j] = " ";
            }
        }
    }
            //method to display the current board//
    private static void display() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                System.out.print("[" + board[i][j] + "]");
            }
            //to make sure the next thing printed is on a newline//
            System.out.println();
        }
    }
            //method to check if a there was a valid input(1-3)//
    private static boolean isValidMove(int row, int col) {
        if (board[col][row].equals(" ")) {
            return true;
        } else {
            return false;
        }
    }
    //method to check each column input for a win//

    private static boolean isColWin(String player) {

        for (int i = 0; i < board[0].length; i++)
        {
            boolean completeCol = true;

            if (!(board[0][i].equals(player)))
            {
                completeCol = false;
            } else {
                for (int j = 1; j < board.length; j++)
                {
                    if (!(board[j][j].equals(player)))
                    {
                        completeCol = false;
                        break;
                    }
                }
            }
            if (completeCol)
            {
                return true;
            }
        }
        return false;
    }
    //method to check each row input for a win//

    private static boolean isRowWin(String player)
    {
        for (int i = 0; i < board.length; i++) {
            boolean completeRow = true;

            if (!(board[i][0].equals(player))) {
                completeRow = false;
            } else {
                for (int j = 1; j < board[i].length; j++) {
                    if (!(board[i][j].equals(player))) {
                        completeRow = false;
                        break;
                    }
                }
            }
            if (completeRow)
            {
                return true;
            }
        }
        return false;
    }
            //method to check each diagonal input for a win//

    private static boolean isDiagonalWin(String player)
    {
        boolean completeDiagonal = true;

        if(!(board[0][0].equals(player)))
        {
            completeDiagonal = false;
        }
        else
        {
            for(int i = 1; i < board.length; i++)
            {
                if (!(board[i][i].equals(player)))
                {
                    completeDiagonal = false;
                    break;
                }
            }
            if (completeDiagonal)
            {
                return true;
            }
        }
        completeDiagonal = true;
        if(!(board[0][board.length -1].equals(player)))
        {
            completeDiagonal = false;
        }
        else
        {
            for(int i = 1; i < board.length; i++)
            {
                if (!(board[i][board.length-1-i].equals(player)))
                {
                    completeDiagonal = false;
                    break;
                }
            }
            if (completeDiagonal)
            {
                return true;
            }
        }

        return false;
    }

            //method to show someone won a column, row, or diagonal//
    private static boolean isWin(String player)
    {
        if (isColWin(player) || isRowWin(player) || isDiagonalWin(player))
        {
            return true;
        }
        return false;


    }
                //method to show if there has been a tie//
    private static boolean isTie ()
    {
        boolean fullBoard = true;
        for (int i = 0; i < ROW; i++)
        {
            for (int j = 0; j < COL; j++) {
                if (board[i][j].equals(" "))
                {
                    fullBoard = false;
                }
            }
        }
        if (fullBoard)
        {
            return true;
        }
        return false;
    }
                //tester method//
    public static void main(String[] args) {
        //creating a scanner object to take inputs//
        Scanner in = new Scanner(System.in);
        boolean gameOver = false;
        //clearing the board//
        clearBoard();
        do {
                //inner workings of the game//
            do
            {
                gameOver = false;
                //telling player X to go//
                turn(in, "X");
                if (isWin("X")) {
                    System.out.println("Xs Win!");
                    gameOver = true;
                }
                if (isTie())
                {
                    System.out.println("It's a Tie!");
                    gameOver = true;
                }
                if (!gameOver)
                {
                    //telling player O to go//
                    turn(in, "O");
                    if (isWin("O")) {
                        System.out.println("Os Win!");
                        gameOver = true;
                    }
                }
                //making sure the game plays while gameOver is not true//
            }while(!gameOver);
                //clearing the board before playing again or ending the program//
            clearBoard();
            //prompt to play again//
        } while (SafeInput.getYNConfirm(in, "Would you like to play again"));
    }


}