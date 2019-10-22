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
    private Space[] homeSpace;
    private int[] numChipsInStartingPointsPerPlayer = {4,4,4,4};
    private int[] numChipsInHomePerPlayer={0,0,0,0};


    public Board()
    {
        mainLoop = new Space[68];
        // this makes four 6-element arrays of Spaces. Technically, this is a 2-d array we haven't covered.
        // Just think of it as an array of arrays.
        homeSpace = new Space[4];
        safePaths = new Space[4][6];
            safePaths[0] = new Space[6];
            safePaths[1] = new Space[6];
            safePaths[2] = new Space[6];
            safePaths[3] = new Space[6];
        // ------------------------------
        // initialize all these spaces in both the main loop and the safe paths.
        for (int i = 0; i<mainLoop.length; i++)
        {
            Space spaceI = new Space(-1,0,false);
            mainLoop[i] = spaceI;
        }

        for (int sp = 0; sp<safePaths.length; sp++)
        {
            for (int ss = 0;ss<safePaths[sp].length;ss++)
            {
                Space pathSP = new Space(-1,0, true);
                safePaths[sp][ss] = pathSP;
            }
        }

        for (int h = 0; h< homeSpace.length; h++)
        {
            Space spaceH = new Space(-1,0,true);
            spaceH.setHome(true);
            homeSpace[h] = spaceH;
        }

        //Make sure you set the appropriate squares to safe!
        for(int squareNum = 0; squareNum < mainLoop.length; squareNum++) {
            //safe spaces
            if (squareNum % 17 == 0){
                mainLoop[squareNum].setSafe(true);

                //starting spaces
                if (squareNum == 0)
                {
                    mainLoop[squareNum].setWhoIsHere(0);
                    mainLoop[squareNum].setStartingPosition(true);
                    mainLoop[squareNum].setStartingPlayerNum(0);
                }
                else if (squareNum==17)
                {
                    mainLoop[squareNum].setWhoIsHere(1);
                    mainLoop[squareNum].setStartingPosition(true);
                    mainLoop[squareNum].setStartingPlayerNum(1);
                }
                else if (squareNum == 34)
                {
                    mainLoop[squareNum].setWhoIsHere(2);
                    mainLoop[squareNum].setStartingPosition(true);
                    mainLoop[squareNum].setStartingPlayerNum(2);
                }
                else if (squareNum == 51)
                {
                    mainLoop[squareNum].setWhoIsHere(3);
                    mainLoop[squareNum].setStartingPosition(true);
                    mainLoop[squareNum].setStartingPlayerNum(3);
                }
            }

           else  if (squareNum % 17 == 7){
                mainLoop[squareNum].setSafe(true);
            }

            //this is a safePath
            else if (squareNum % 17 == 12){
                mainLoop[squareNum].setSafe(true);
                if(squareNum == 12)
                {
                    mainLoop[squareNum].setPlayerSafePath(1);
                }
                if(squareNum == 29)
                {
                    mainLoop[squareNum].setPlayerSafePath(2);
                }
                if(squareNum == 46)
                {
                    mainLoop[squareNum].setPlayerSafePath(3);
                }
                if(squareNum == 63)
                {
                    mainLoop[squareNum].setPlayerSafePath(0);
                }
            }
            //everything else
            else{
                mainLoop[squareNum].setSafe(false);
                mainLoop[squareNum].setNumPieces(0);
            }

        }

        // ------------------------------

    }

    public String toString()
    {
        String result = "";
        // -------------------------------
        //in a loop, keep appending information to "result" so that result winds up being a string that you can print to see the whole board.
        //System.out.println();
        for (int i = 0; i<mainLoop.length; i++) {
            // suggestion: start by just printing the row numbers, a tab, and the squares themselves.
            result += i + "\t";
            result += mainLoop[i];

            // then you can get fancy by printing information about the various players' starting positions.
            if (mainLoop[i].isStartingPosition())
            {
                int p =  mainLoop[i].getStartingPlayerNum();
                result += "<-- Player "+p +" start. ";
                        result+="<";
                        //for loop that loops through num chips in home and prints playerNum that many times
                        for (int s = 0; s< numChipsInStartingPointsPerPlayer[p] ; s++)
                        {
                            result+= p;
                        }
                result+=">";
            }
            // then you can get fancy by adding in the safe rows to the goal for the various players.
            if (mainLoop[i].getPlayerSafePath()!=-1)
            {
                int ps = mainLoop[i].getPlayerSafePath();
                for (int s = 0; s<safePaths[ps].length; s++)
                {
                    result += safePaths[ps][s];
                }
               result += homeSpace[ps];
                result += " Safe Path for "+ps;
            }
            if(i%17==11)
            {
                result += "  -5   -4   -3   -2   -1   0   Home";
            }

            result += "\n";
        }
        // -------------------------------
        return result;
    }

    public void moveToANewSpace (int newSpace, int playerNum){
        if (numChipsInStartingPointsPerPlayer[playerNum]==4)
        {
            numChipsInStartingPointsPerPlayer[playerNum]=numChipsInStartingPointsPerPlayer[playerNum]-1;
        }
        mainLoop[newSpace].setWhoIsHere(playerNum);
        mainLoop[newSpace].setNumPieces(1);

    }

    public void clearOldSpace (int oldSpace, int playerNum){

        mainLoop[oldSpace].setWhoIsHere(-1); //idk if this will work
        mainLoop[oldSpace].setNumPieces(0);
    }

    public boolean checkIfSafe (int space)
    {
        if(mainLoop[space].isSafe())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean checkOtherPlayer(int space)
    {
        if(mainLoop[space].getWhoIsHere()>-1 )
        {
            return true;
        }
        else {
            return false;
        }
    }
    public void kickOtherPlayer(int space)
    {
        //receive space and kick other player back to their starting point
        int player = mainLoop[space].getWhoIsHere();
        mainLoop[space].setWhoIsHere(-1);
        mainLoop[space].setNumPieces(0);
        numChipsInStartingPointsPerPlayer[player] += 1;

    }
    public int checkIfHomePath(int space, int playerNum)
    {
        if (playerNum==0 && space==63)
        {
           return space;
        }
        else if (playerNum==1 && space==12)
        {
            return space;
        }
        else if (playerNum==2 && space==29)
        {
            return space;
        }
        else if (playerNum==3 && space==46)
        {
            return space;
        }
        else{
            return -1;
        }
    }
    public boolean isGameOver (int playerNum){
        for (int i = 0; i < numChipsInHomePerPlayer.length; i ++){
            if (numChipsInHomePerPlayer[i] == 4){
                System.out.println("Player " + playerNum + " wins!");
                return true;
            }

            else {
                return false;
            }
        }
        return false;
    }

}
