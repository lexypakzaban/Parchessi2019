public class Board
{

    // "clever" hint about the Parcheesi board:
    // If you divide the number of a square by 17 and look at the remainder, you can get important
    // information about that square. For example...
    //      if the remainder is zero, this is a safe space.
    //      if the remainder is seven, this is a safe space.
    //      if the remainder is twelve, this is a safe space.
    //      if the remainder is zero, this is the starting position for one of the players (int)(squareNum/17)...
    //      if the remainder is twelve, this is the start of a home row for one of the players (I'll let you figure out
    //             which one.)

    private Space[] mainLoop;
    private Space[][] safePaths; // see note, below.
    private int[] numChipsInStartingPointsPerPlayer = {4,4,4,4};
    private int[] numChipsInHomePerPlayer={0,0,0,0};

    public Board()
    {
        mainLoop = new Space[68];
        safePaths = new Space[4][6];  // this makes four 6-element arrays of Spaces. Technically, this is a 2-d array we haven't covered. Just think of it as an array of arrays.
            safePaths[0] = new Space[6];
            safePaths[1] = new Space[6];
            safePaths[2] = new Space[6];
            safePaths[3] = new Space[6];
        // ------------------------------
        // TODO: initialize all these spaces in both the main loop and the safe paths. Make sure you set the appropriate squares to safe!

        // This is a test.

        for(int squareNum = 0; squareNum < mainLoop.length; squareNum++) {
            //safe spaces
            if (squareNum % 17 == 0){
                mainLoop[squareNum].setSafe(true);
                //starting spaces
                if (squareNum == 0)
                {
                    mainLoop[squareNum].setWhoIsHere(0);
                }
                else if (squareNum==17)
                {
                    mainLoop[squareNum].setWhoIsHere(1);
                }
                else if (squareNum == 34)
                {
                    mainLoop[squareNum].setWhoIsHere(2);
                }
                else if (squareNum == 51)
                {
                    mainLoop[squareNum].setWhoIsHere(3);
                }
            }

           else  if (squareNum % 17 == 7){
                mainLoop[squareNum].setSafe(true);
            }

            else if (squareNum % 17 == 12){
                mainLoop[squareNum].setSafe(true);
            }

            //everything else
            else{
                mainLoop[squareNum].setSafe(false);
            }


        }
        // ------------------------------

    }

    public String toString()
    {
        String result = "";
        // -------------------------------
        // TODO: in a loop, keep appending information to "result" so that result winds up being a string that you can print to see the whole board.
        System.out.println();

        // suggestion: start by just printing the row numbers, a tab, and the squares themselves.
        // then you can get fancy by printing information about the various players' starting positions.
        // then you can get fancy by adding in the safe rows to the goal for the various players.

        // -------------------------------

        return result;
    }

}
