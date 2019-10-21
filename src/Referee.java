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
        int dieA = 0;
        int dieB = 0;
        Scanner keyboardReader = new Scanner(System.in);

        while (gameIsPlaying) {

            for (int playerNum = 0; playerNum < 4; playerNum++) {
                //loops through every player
                System.out.println("Player " + playerNum + " 's turn!");

                while (rolling) {
                    //rolls die and asks user which they want to move

                    dieA = (int) (Math.random() * 6) + 1;
                    dieB = (int) (Math.random() * 6) + 1;

                    System.out.println("You've rolled:");
                    System.out.println("Die A: " + dieA);
                    System.out.println("Die B: " + dieB);
                    System.out.println("Which do you want to move? Type A or B");

                    rolling = false;

                }

                while (rolling == false) {
                    String move = keyboardReader.next();

                    if (move.equals("A")) {
                        System.out.println("Which space is that piece currently on?");
                        int currentSpace = keyboardReader.nextInt();
                        //TODO: check to see if that piece is in a safe path

                        //moves that piece
                        int newSpace = currentSpace + dieA;
                        myBoard.moveToANewSpace(newSpace, playerNum);

                        //clears old space (make sure that piece isn't on its old space)
                        myBoard.clearOldSpace(currentSpace, playerNum);

                        System.out.println("Ok. The piece on " + currentSpace + " has moved to " + newSpace);

                        //checks to see if all the pieces per player are in Home
                        if (myBoard.isGameOver(playerNum) == true){
                            gameIsPlaying = false;
                        }

                        rolling = true;
                    }

                    else {
                        System.out.println("Which space is that piece currently on?");
                        int currentSpace = keyboardReader.nextInt();
                        //TODO: check to see if that piece is in a safe path

                        //moves that piece
                        int newSpace = currentSpace + dieB;
                        myBoard.moveToANewSpace(newSpace, playerNum);

                        //clears old space (make sure that piece isn't on its old space)
                        myBoard.clearOldSpace(currentSpace, playerNum);

                        System.out.println("Ok. The piece on " + currentSpace + " has moved to ");

                        //checks to see if all the pieces per player are in Home
                        if (myBoard.isGameOver(playerNum) == true){
                            gameIsPlaying = false;
                        }

                        rolling = true;

                    }

                }
                System.out.println(myBoard);
            }
        }
    }
}
