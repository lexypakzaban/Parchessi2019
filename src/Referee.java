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
        //TODO: if player is already on a safe path, ask them which space on the safe path they are on
        //TODO: (con't), then use moveToSafePath(?) to move piece within safe path
        //TODO: it would be even better if we could check if the player actually has a piece at CurrentSpace
        boolean rolling = true;
        boolean gameIsPlaying = true;
        int dieA = 0;
        int dieB = 0;
        Scanner keyboardReader = new Scanner(System.in);
        boolean isOnSafePath = false;

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
                        int newSpace = currentSpace + dieA;
                        if (newSpace >67)
                        {
                            newSpace = newSpace-67;
                        }
                        else
                        {
                            //check to see if that piece is in a safe path
                            if (!myBoard.checkIfSafe(newSpace))
                            {
                                if(myBoard.checkOtherPlayer(newSpace))
                                {
                                    myBoard.kickOtherPlayer(newSpace);
                                }
                            }
                        }

                        if (isOnSafePath)
                        {
                            myBoard.checkIfHome(currentSpace,dieA,playerNum);
                        }

                        int homePath = myBoard.checkIfSafePath(newSpace,playerNum);
                        if (homePath!=-1)
                        {
                            myBoard.moveToSafePath(homePath,playerNum);
                            isOnSafePath = true;

                        }
                        else
                        {
                            //moves that piece
                            myBoard.moveToANewSpace(newSpace, playerNum);
                        }


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
                        int newSpace = currentSpace + dieB;
                        if (newSpace >67)
                        {
                            newSpace = newSpace-67;
                        }
                        else
                        {
                            //check to see if that piece is in a safe path
                            if (!myBoard.checkIfSafe(newSpace))
                            {
                                if(myBoard.checkOtherPlayer(newSpace))
                                {
                                    myBoard.kickOtherPlayer(newSpace);
                                }
                            }
                        }
                        if (isOnSafePath)
                        {
                            myBoard.checkIfHome(currentSpace,dieA,playerNum);
                        }

                        int homePath = myBoard.checkIfSafePath(newSpace,playerNum);
                        if (homePath!=-1)
                        {
                            myBoard.moveToSafePath(homePath,playerNum);
                            isOnSafePath = true;

                        }
                        else
                        {
                            //moves that piece
                            myBoard.moveToANewSpace(newSpace, playerNum);
                        }



                        //clears old space (make sure that piece isn't on its old space)
                        myBoard.clearOldSpace(currentSpace, playerNum);

                        System.out.println("Ok. The piece on " + currentSpace + " has moved to " + newSpace);

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
