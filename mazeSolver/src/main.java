/**
 * Created by MSD on 6.03.2017.
 */
public class main {
    public static void main(String [] args){
        Maze m = new Maze();

        m.printBoard();

        m.exitOfMaze(0,0);

        System.out.println("___________________");

        m.printBoard();

        System.out.print(m.toString());

        return;
    }
}

