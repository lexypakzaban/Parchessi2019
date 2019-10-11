import java.util.Scanner;

public class Referee
{
    private Board myBoard;

    public Referee()
    {
        myBoard = new Board();
        System.out.println(myBoard);
    }

    public void play()
    {
        boolean rolling = true;
        Scanner keyboardReader = new Scanner(System.in);

        while(rolling){

        int dieA = (int) Math.random() * 7;
        int dieB = (int) Math.random() * 7;

        System.out.println("You've rolled:");
        System.out.println("Die A: " + dieA);
        System.out.println("Die B: " + dieB);
        System.out.println("Which do you want to move? Type A or B");

        rolling = false;
        }

        String move = keyboardReader.next();

        if (keyboardReader.next().equals("A")){
            System.out.println("Which square is that piece currently on?");
            int currentSquare = keyboardReader.nextInt();

            //TODO: move that piece

            System.out.println("Ok. The piece on " + currentSquare + " has moved to " );

            rolling = true;



        }

        if (keyboardReader.next().equals("B")){

        }

        else {
            System.out.println("That is not a valid response");
        }




    }
}
