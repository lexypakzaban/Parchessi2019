import java.util.Scanner;

public class Referee
{
    private Board myBoard;

    public Referee()
    {
        myBoard = new Board();
        System.out.println(myBoard);
    }

    public void play() {
        boolean rolling = true;
        boolean gameIsPlaying = true;
        Scanner keyboardReader = new Scanner(System.in);

        while (gameIsPlaying) {
            while (rolling) {
                //rolls die and asks user which they want to move

                int dieA = (int) Math.random() * 7;
                int dieB = (int) Math.random() * 7;

                System.out.println("You've rolled:");
                System.out.println("Die A: " + dieA);
                System.out.println("Die B: " + dieB);
                System.out.println("Which do you want to move? Type A or B");

                rolling = false;

            }

            while (rolling = false) {
                String move = keyboardReader.next();

                if (move.equals("A")) {
                    System.out.println("Which square is that piece currently on?");
                    int currentSquare = keyboardReader.nextInt();
                    //TODO: check to see if that piece is in a safe path

                    //TODO: move that piece

                    System.out.println("Ok. The piece on " + currentSquare + " has moved to ");

                    rolling = true;
                }

                else {
                    System.out.println("Which square is that piece currently on?");
                    int currentSquare = keyboardReader.nextInt();
                    //TODO: check to see if that piece is in a safe path

                    //TODO: move that piece

                    System.out.println("Ok. The piece on " + currentSquare + " has moved to ");
                    rolling = true;

                }

            }

        }
    }
}
