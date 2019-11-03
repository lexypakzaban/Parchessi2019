import java.util.InputMismatchException;
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
                        System.out.println("Which space is that piece currently on in the main board?");
                        //if they enter a string or not an int, redo the question.

                        boolean isInteger = false;
                        int currentSpace = 0;

                        while (isInteger == false) {
                            Scanner scanner = new Scanner(System.in);

                            try {
                                currentSpace = scanner.nextInt();
                                isInteger = true;

                            } catch (InputMismatchException e) {
                                System.out.println("Enter an integer");
                            }
                        }


                        //checks if the player is actually on currentSpace
                        while (!myBoard.checkIfOnCurrentSpace(currentSpace,playerNum))
                        {
                            System.out.println("Your piece is not on that space! Try again.");
                            System.out.println("Which space is that piece currently on in the main board?");
                            currentSpace = keyboardReader.nextInt();
                        }
                        int newSpace = currentSpace + dieA;

                        if (newSpace >67)
                        {
                            newSpace = newSpace-67;
                        }
                        //check to see if that piece is on a safe space
                        if (!myBoard.checkIfSafe(newSpace))
                        {
                            int otherPlayer = myBoard.checkOtherPlayer(newSpace);
                            //if otherPlayer is one of the player's pieces,
                            // just move current piece 1 more space (it's a bonus)
                            while (otherPlayer==playerNum)
                            {
                                newSpace++;
                            }
                            if(otherPlayer!= -1)
                            {
                                myBoard.kickOtherPlayer(newSpace);
                                System.out.println(otherPlayer+" was kicked back to home!");
                            }
                        }

                        //this might conflict if player is on start space (for example: player 0 is on space 63 exactly)
                        //because checkIfStartSafePath also includes space 63, so this will catch them all first

                        //if player's currentSpace is on a safePath
                        if (myBoard.isOnStartOfSafePath(playerNum, currentSpace))
                        {
                            keyboardReader.nextLine();
                            System.out.println("Which space is the piece on in the safe path?");
                            int safePathSpace = keyboardReader.nextInt();
                            //player is already on the safePath
                            if (myBoard.checkIfSpecialSpace(safePathSpace+6,playerNum))
                            {
                                myBoard.checkIfHome(currentSpace,dieA,playerNum);
                            }
                        }

                        else //player's currentSpace is not on a safePath
                        {
                            int homePath = myBoard.checkIfMainTOSafePath(newSpace,playerNum);
                            if (homePath!=-1)
                            {
                                //moves FROM mainPath TO safePath
                                myBoard.moveToSafePath(homePath,playerNum);

                            }
                            else
                            {
                                //moves that piece
                                myBoard.moveToANewSpace(currentSpace,newSpace, playerNum);
                            }
                        }



                        //clears old space (make sure that piece isn't on its old space)
                        myBoard.clearOldSpace(currentSpace, playerNum);


                        System.out.println("Ok. The piece on " + currentSpace + " has moved to " + newSpace);

                        //checks to see if all the pieces per player are in Home
                        if (myBoard.isGameOver(playerNum) == true){
                            System.out.println("Congratulations, "+playerNum+", you win!");
                            gameIsPlaying = false;
                        }

                        rolling = true;
                    }

                    else //move = "B"
                    {
                        System.out.println("Which space is that piece currently on in the main board?");
                        int currentSpace = keyboardReader.nextInt();
                        while (!myBoard.checkIfOnCurrentSpace(currentSpace,playerNum))
                        {
                            System.out.println("Your piece is not on that space! Try again.");
                            System.out.println("Which space is that piece currently on in the main board?");
                            currentSpace = keyboardReader.nextInt();
                        }
                        int newSpace = currentSpace + dieB;

                        //loops the board spaces
                        if (newSpace >67)
                        {
                            newSpace = newSpace-67;
                        }

                            //check to see if that piece is in a safe path
                            if (!myBoard.checkIfSafe(newSpace))
                            {
                                int otherPlayer = myBoard.checkOtherPlayer(newSpace);

                                //if otherPlayer is one of the player's pieces,
                                // just move current piece 1 more space (it's a bonus)
                                while (otherPlayer==playerNum)
                                {
                                    newSpace++;
                                }
                                if(otherPlayer!= -1)
                                {
                                    myBoard.kickOtherPlayer(newSpace);
                                    System.out.println(otherPlayer+" was kicked back to home!");
                                }
                            }


                        //if player's currentSpace is on a safePath
                        if (myBoard.isOnStartOfSafePath(playerNum, currentSpace))
                        {
                            keyboardReader.nextLine();
                            System.out.println("Which space is the piece on in the safe path?");
                            int safePathSpace = keyboardReader.nextInt();
                            //player is already on the safePath
                            if (myBoard.checkIfSpecialSpace(safePathSpace+6,playerNum))
                            {
                                myBoard.checkIfHome(currentSpace,dieB,playerNum);
                            }
                        }

                        else //player's currentSpace is not on a safePath
                        {
                            int homePath = myBoard.checkIfMainTOSafePath(newSpace,playerNum);
                            if (homePath!=-1)
                            {
                                //moves FROM mainPath TO safePath
                                myBoard.moveToSafePath(homePath,playerNum);

                            }
                            else
                            {
                                //moves that piece
                                myBoard.moveToANewSpace(currentSpace,newSpace, playerNum);
                            }
                        }


                        //clears old space (make sure that piece isn't on its old space)
                        myBoard.clearOldSpace(currentSpace, playerNum);

                        System.out.println("Ok. The piece on " + currentSpace + " has moved to " + newSpace);

                        //checks to see if all the pieces per player are in Home
                        if (myBoard.isGameOver(playerNum) == true){
                            System.out.println("Congratulations, "+playerNum+", you win!");
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
