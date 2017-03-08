import java.util.Random;

/**
 * Created by MSD on 6.03.2017.
 */
public class Maze {

    private char [][] board;
    private final char space = '-';
    private final char blackStamp = 'x';
    private final char whiteStamp = 4; //ascii code of  symbol
    private final int boardWidthSize = 8;
    private final int boardHeightSize = 8;
    private Random rand;
    private int numberOfBlackStamp;
    private String blackStampsLoactions = "";
    private String whiteStampLocations = "";

    Maze()// no parameter constructor
    {
        board = new char[boardHeightSize][boardWidthSize];

        for (int i = 0; i < boardHeightSize; ++i) //reset the board with spaces (-)
            for (int j = 0; j < boardWidthSize; ++j)
                board[i][j] = space;

        board[0][0] = whiteStamp; //white stamp positioning to start coordinate
        rand = new Random();
        numberOfBlackStamp = rand.nextInt(6) + 3; //determine number of black stamp

        //positioning of black stamps on board
        int tempNumberBlackStamp = numberOfBlackStamp;
        int tempNumber = 1;  //At least one black stamp on the cross to encounter with white stamp
        while(tempNumberBlackStamp > 0)
        {
            int tempRow = rand.nextInt(8);
            int tempColumn = rand.nextInt(8);

            if(board[tempRow][tempColumn] ==  space && tempRow !=7 && tempColumn!=7)
            {
                if(checkLine(tempColumn) == true)
                {
                    if(tempRow == tempColumn)
                    {
                        board[tempRow][tempColumn] = blackStamp;
                        --tempNumberBlackStamp;
                        --tempNumber;
                    }
                    else if(tempNumber<=0)
                    {
                        board[tempRow][tempColumn] = blackStamp;
                        --tempNumberBlackStamp;
                    }
                }
            }
        }

    }
    //method check that can be at most six blackStamp at one row to prevent deadlock.
    public boolean checkLine(int column)
    {
        int tempNumber = 0;
        for (int j = 0; j < boardWidthSize; ++j)
            if(board[j][column] == blackStamp)
                ++tempNumber;
        if( tempNumber == 6 && column > 0 && column < 7 )
            return false;

        return true;
    }

    public void printBoard()
    {
        System.out.print("  A B C D E F G H");
        for (int i = 0; i < boardHeightSize; ++i){
            System.out.print("\n"+(i+1) +" ");
            for (int j = 0; j < boardWidthSize; ++j)
                System.out.print(board[i][j] + " ");
        }
        System.out.println("");

    }
    //check board and go on the closest path on board, if encounter a black stamp, change direction.
    public boolean exitOfMaze(int row, int column)
    {
        /* base cases */
        if(row == boardHeightSize || column == boardWidthSize)
            return false;

        if(row < 0 || column < 0)
            return  false;

        if(board[row][column] == blackStamp) {
            blackStampsLoactions = blackStampsLoactions + (row+1) + "," + (char) (65 + column) + "  " ;
            return false;
        }
        //saving the white stamp movements
        whiteStampLocations = whiteStampLocations + (row+1)  + "," + (char) (65 + column) + "  ";

        //finish base case
        if(row == boardHeightSize-1 && column == boardWidthSize-1)
        {
            board[boardHeightSize-1][boardWidthSize-1] = whiteStamp;
            return true;
        }
        else //recursiv calls
        {
            board[row][column] = whiteStamp;

            if( exitOfMaze(row + 1, column + 1) == true) //South East
                return true;
            else if(exitOfMaze(row, column + 1) == true) //East
                return true;
            else if(exitOfMaze(row + 1, column) == true) //South
                return true;
            else if(exitOfMaze( row - 1, column ) == true ) //North
                return true;
            else if(exitOfMaze( row, column - 1) == true ) //West
                return true;
            else if(exitOfMaze( row - 1, column + 1) == true) //North East
                return true;
            else if(exitOfMaze( row + 1, column - 1) == true) //South West
                return true;
            else if(exitOfMaze(row - 1, column - 1) == true) //North West
                return true;

            return false;
        }
    }

    public String toString()
    {
        String tempString = "";
        tempString = tempString + "Encountered locations on board with black stamp: " + blackStampsLoactions + "\n";
        tempString = tempString + "Steps of white stamp: " + whiteStampLocations  + "\n";
        return tempString;
    }
}
