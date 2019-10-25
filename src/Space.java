public class Space
{
    /**
     * Each space can have be empty, or it can have one or two of a single player's pieces. (I.e., two different players
     * cannot share a space. Some spaces are considered "safe" - these show up with an asterisk next to them in our game.
     * They correspond to the spaces with circles on them and the home rows on the original board game.
     */

    private int whoIsHere; // which player, if any has pieces on this space.
    private int numPieces; // how many pieces, {0, 1, or 2} are on this space
    private boolean isSafe; // wh
    private boolean isStartingPosition = false;
    private int startingPlayerNum = -1;
    private int playerSafePath = -1;
    private boolean isHome = false;
    private boolean isSpecialSpace = false;


    public Space(int i, int n, boolean s)
    {
        //idk if whoIsHere would reset every time we call space now, aka make a new space
        whoIsHere = i;
        numPieces = n;
        isSafe = s;
    }

    public boolean isSafe()
    {
        return isSafe;
    }

    public void setSafe(boolean safe)
    {
        isSafe = safe;
    }

    public int getWhoIsHere() {
        return whoIsHere;
    }

    public void setWhoIsHere(int whoIsHere) {
        this.whoIsHere = whoIsHere;
    }

    public int getNumPieces() {
        return numPieces;
    }

    public void setNumPieces(int numPieces) {
        this.numPieces = numPieces;
    }

    public boolean isStartingPosition() {
        return isStartingPosition;
    }

    public void setStartingPosition(boolean startingPosition) {
        isStartingPosition = startingPosition;
    }
    public int getStartingPlayerNum() {
        return startingPlayerNum;
    }

    public void setStartingPlayerNum(int startingPlayerNum) {
        this.startingPlayerNum = startingPlayerNum;
    }

    public int getPlayerSafePath() {
        return playerSafePath;
    }

    public void setPlayerSafePath(int playerSafePath) {
        this.playerSafePath = playerSafePath;
    }
    public boolean isHome() {
        return isHome;
    }

    public void setHome(boolean home) {
        isHome = home;
    }

    public boolean isSpecialSpace() {
        return isSpecialSpace;
    }

    public void setSpecialSpace(boolean specialSpace) {
        isSpecialSpace = specialSpace;
    }

    /**
     * generates a string describing this space. It will lead with an asterisk if it is safe, or a space if not. The
     * space itself is drawn as two characters, surrounded by "pipes" ("|") - spaces, if the space is considered empty,
     * or one or more of the number of the player if there are pieces on this board.
     * For example:
     * " |  |" - an empty, unsafe space.
     * "*|3 |" - a safe space with one of player 3's pieces on it.
     * " |00|" - an unsafe space with two of player 0's pieces on it.
     * @return - a string describing this space.
     */
    @Override
    public String toString()
    {
        String result;
        //if space is Home, change appearance
        if (isHome) {
            result = "<";
            for (int i = 0; i < 4; i++) {
                if (i < numPieces)
                    result += whoIsHere;
                else
                    result += "_";
            }

            result += ">";
            return result;
        }
        else {
            if (isSafe())
                result = "*|";
            else
                result = " |";
            for (int i = 0; i < 2; i++)
                if (i < numPieces)
                    result += whoIsHere;
                else
                    result += " ";
            result += "|";
            return result;
        }
    }
}
