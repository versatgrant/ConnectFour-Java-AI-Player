import java.util.Random;
import java.util.ArrayList;
public class MyAgent extends Agent
{
    Random r;
    

    /**
     * Constructs a new agent, giving it the game and telling it whether it is Red or Yellow.
     * 
     * @param game The game the agent will be playing.
     * @param iAmRed True if the agent is Red, False if the agent is Yellow.
     */
    public MyAgent(Connect4Game game, boolean iAmRed)
    {
        super(game, iAmRed);
        r = new Random();
    }

    /**
     * The move method is run every time it is this agent's turn in the game. You may assume that
     * when move() is called, the game has at least one open slot for a token, and the game has not
     * already been won.
     * 
     * By the end of the move method, the agent should have placed one token into the game at some
     * point.
     * 
     * After the move() method is called, the game engine will check to make sure the move was
     * valid. A move might be invalid if:
     * - No token was place into the game.
     * - More than one token was placed into the game.
     * - A previous token was removed from the game.
     * - The color of a previous token was changed.
     * - There are empty spaces below where the token was placed.
     * 
     * If an invalid move is made, the game engine will announce it and the game will be ended.
     * 
     */
    public void move()
    {
      int move = 0;
        if(iCanWin() >= 0){
            move=iCanWin();
        }else if(theyCanWin() >= 0){
            move=theyCanWin();
        }else{
            move=randomMove();
            System.out.println("Making a random move at: column - " + move + ", row - "+getLowestEmptyIndex(myGame.getColumn(move)) + ". Moving...");
        }
        
        moveOnColumn(move);
    }

    /**
     * Drops a token into a particular column so that it will fall to the bottom of the column.
     * If the column is already full, nothing will change.
     * 
     * @param columnNumber The column into which to drop the token.
     */
    public void moveOnColumn(int columnNumber)
    {
        int lowestEmptySlotIndex = getLowestEmptyIndex(myGame.getColumn(columnNumber));   // Find the top empty slot in the column
                                                                                                  // If the column is full, lowestEmptySlot will be -1
        if (lowestEmptySlotIndex > -1)  // if the column is not full
        {
            Connect4Slot lowestEmptySlot = myGame.getColumn(columnNumber).getSlot(lowestEmptySlotIndex);  // get the slot in this column at this index
            if (iAmRed) // If the current agent is the Red player...
            {
                lowestEmptySlot.addRed(); // Place a red token into the empty slot
            } 
            else // If the current agent is the Yellow player (not the Red player)...
            {
                lowestEmptySlot.addYellow(); // Place a yellow token into the empty slot
            }
        }
    }

    /**
     * Returns the index of the top empty slot in a particular column.
     * 
     * @param column The column to check.
     * @return the index of the top empty slot in a particular column; -1 if the column is already full.
     */
    public int getLowestEmptyIndex(Connect4Column column) {
        int lowestEmptySlot = -1;
        for  (int i = 0; i < column.getRowCount(); i++)
        {
            if (!column.getSlot(i).getIsFilled())
            {
                lowestEmptySlot = i;
            }
        }
        return lowestEmptySlot;
    }

    /**
     * Returns a random valid move. If your agent doesn't know what to do, making a random move
     * can allow the game to go on anyway.
     * 
     * @return a random valid move.
     */
    public int randomMove()
    {
        int i = r.nextInt(myGame.getColumnCount());
        while (getLowestEmptyIndex(myGame.getColumn(i)) == -1)
        {
            i = r.nextInt(myGame.getColumnCount());
        }
        return i;
    }

    /**
     * Returns the column that would allow the agent to win.
     * 
     * You might want your agent to check to see if it has a winning move available to it so that
     * it can go ahead and make that move. Implement this method to return what column would
     * allow the agent to win.
     *
     * @return the column that would allow the agent to win.
     */
    public int iCanWin()
    {
        int priority = -1;
        int inLineWithBlank = -1;
        char[][] board = myGame.getBoardMatrix();
        boolean amIRed = iAmRed;
        for(int i = 0; i < myGame.getColumnCount(); i++){
            inLineWithBlank = getLowestEmptyIndex(myGame.getColumn(i));
            if(inLineWithBlank > -1){//Check if the column is not full
                if(inLineWithBlank < myGame.getRowCount()-3){//make sure at least three spaces are filled vertically in a column
                    if(amIRed){
                        if(board[inLineWithBlank+1][i] == 'R' && board[inLineWithBlank+2][i] == 'R' && board[inLineWithBlank+3][i] == 'R'){//check if three colours in a row match vertically and are equal to red
                            System.out.println("I can win vertically at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }else{
                        if(board[inLineWithBlank+1][i] == 'Y' && board[inLineWithBlank+2][i] == 'Y' && board[inLineWithBlank+3][i] == 'Y'){//check if three colours in a row match vertically and are equal to yellow
                            System.out.println("I can win vertically at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;  
                            return priority;
                        }
                    }
                }
                if(i < myGame.getColumnCount() - 3){//check three places to the right
                    if(amIRed){
                        if(board[inLineWithBlank][i+2] == 'R' && board[inLineWithBlank][i+3] == 'R' && board[inLineWithBlank][i+1] == 'R'){//Check if all columns are equal to red
                            System.out.println("I can win horizontally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }else{
                        if(board[inLineWithBlank][i+2] == 'Y' && board[inLineWithBlank][i+3] == 'Y' && board[inLineWithBlank][i+1] == 'Y'){//Check if all columns are equal to yellow
                            System.out.println("I can win horizontally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }
                }
                if(i > 2){//check three places to the left
                    if(amIRed){
                        if(board[inLineWithBlank][i-2]== 'R' && board[inLineWithBlank][i-3] == 'R' && board[inLineWithBlank][i-1] == 'R'){//Check if all columns are equal to red
                            System.out.println("I can win horizontally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }else{
                        if(board[inLineWithBlank][i-2] == 'Y' && board[inLineWithBlank][i-3] == 'Y' && board[inLineWithBlank][i-1] == 'Y'){//Check if all columns are equal to yellow
                            System.out.println("I can win horizontally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }
                }
                if(i < myGame.getColumnCount() - 2 && i > 0) { //check two to the right and one to the left
                    if(amIRed){
                        if(board[inLineWithBlank][i+2] == 'R' && board[inLineWithBlank][i-1] == 'R' && board[inLineWithBlank][i+1] == 'R'){//Check if all columns are equal to red
                            System.out.println("I can win horizontally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }else{
                        if(board[inLineWithBlank][i+2] == 'Y' && board[inLineWithBlank][i-1] == 'Y' && board[inLineWithBlank][i+1] == 'Y'){//Check if all columns are equal to yellow
                            System.out.println("I can win horizontally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }
                }
                if(i < myGame.getColumnCount() - 1 && i > 1) { //check two to the left and one to the right
                    if(amIRed){
                        if(board[inLineWithBlank][i-2] == 'R' && board[inLineWithBlank][i+1] == 'R' && board[inLineWithBlank][i-1] == 'R'){//Check if all columns are equal to red
                            System.out.println("I can win horizontally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }else{
                        if(board[inLineWithBlank][i-2] == 'Y' && board[inLineWithBlank][i+1] == 'Y' && board[inLineWithBlank][i-1] == 'Y'){//Check if all columns are equal to yellow
                            System.out.println("I can win horizontally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }
                }
                if(i < myGame.getColumnCount() - 3 && inLineWithBlank > 2 ){//check three diagonal places up and to the right
                    if(amIRed){
                        if(board[inLineWithBlank-2][i+2] == 'R' && board[inLineWithBlank-3][i+3] == 'R' && board[inLineWithBlank-1][i+1] == 'R'){//Check if all columns are equal to red
                            System.out.println("I can win diagonally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }else{
                        if(board[inLineWithBlank-2][i+2] == 'Y' && board[inLineWithBlank-3][i+3] == 'Y' && board[inLineWithBlank-1][i+1] == 'Y'){//Check if all columns are equal to yellow
                            System.out.println("I can win diagonally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }
                }
                if(i > 2 && inLineWithBlank > 2 ){//check three diagonal places up and to the left
                    if(amIRed){
                        if(board[inLineWithBlank-2][i-2] == 'R' && board[inLineWithBlank-3][i-3] == 'R' && board[inLineWithBlank-1][i-1] == 'R'){//Check if all columns are equal to red
                            System.out.println("I can win diagonally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }else{
                        if(board[inLineWithBlank-2][i-2] == 'Y' && board[inLineWithBlank-3][i-3] == 'Y' && board[inLineWithBlank-1][i-1] == 'Y'){//Check if all columns are equal to yellow
                            System.out.println("I can win diagonally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }
                }
                if(i < myGame.getColumnCount() - 3 && inLineWithBlank < myGame.getRowCount() - 3){//check three diagonal places down and to the right
                    if(amIRed){
                        if(board[inLineWithBlank+2][i+2] == 'R' && board[inLineWithBlank+3][i+3] == 'R' && board[inLineWithBlank+1][i+1] == 'R'){//Check if all columns are equal to red
                            System.out.println("I can win diagonally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }else{
                        if(board[inLineWithBlank+2][i+2] == 'Y' && board[inLineWithBlank+3][i+3] == 'Y' && board[inLineWithBlank+1][i+1] == 'Y'){//Check if all columns are equal to yellow
                            System.out.println("I can win diagonally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }
                }
                if(i > 2 && inLineWithBlank < myGame.getRowCount() - 3){//check three diagonal places down and to the left
                    if(amIRed){
                        if(board[inLineWithBlank+2][i-2] == 'R' && board[inLineWithBlank+3][i-3] == 'R' && board[inLineWithBlank+1][i-1] == 'R'){//Check if all columns are equal to red
                            System.out.println("I can win diagonally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }else{
                        if(board[inLineWithBlank+2][i-2] == 'Y' && board[inLineWithBlank+3][i-3] == 'Y' && board[inLineWithBlank+1][i-1] == 'Y'){//Check if all columns are equal to yellow
                            System.out.println("I can win diagonally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }
                }
                if(i < myGame.getColumnCount() - 2 && i > 0 && inLineWithBlank > 1 && inLineWithBlank < myGame.getRowCount() - 1){//check two diagonal places up and to the right and one down to the left
                    if(amIRed){
                        if(board[inLineWithBlank-1][i+1] == 'R' && board[inLineWithBlank-2][i+2] == 'R' && board[inLineWithBlank+1][i-1] == 'R'){//Check if all columns are equal to red
                            System.out.println("I can win diagonally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }else{
                        if(board[inLineWithBlank-1][i+1] == 'Y' && board[inLineWithBlank-2][i+2] == 'Y' && board[inLineWithBlank+1][i-1] == 'Y'){//Check if all columns are equal to yellow
                            System.out.println("I can win diagonally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }
                }
                if(i < myGame.getColumnCount() - 1 && i > 1 && inLineWithBlank > 1 && inLineWithBlank < myGame.getRowCount() - 1){//check two diagonal places up and to the left and one down to the right
                    if(amIRed){
                        if(board[inLineWithBlank-1][i-1] == 'R' && board[inLineWithBlank-2][i-2] == 'R' && board[inLineWithBlank+1][i+1] == 'R'){//Check if all columns are equal to red
                            System.out.println("I can win diagonally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }else{
                        if(board[inLineWithBlank-1][i-1] == 'Y' && board[inLineWithBlank-2][i-2] == 'Y' && board[inLineWithBlank+1][i+1] == 'Y'){//Check if all columns are equal to yellow
                            System.out.println("I can win diagonally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }
                }
                if(i > 0 && i < myGame.getColumnCount() - 2 && inLineWithBlank > 0&& inLineWithBlank < myGame.getRowCount() - 2){//check one diagonal place up and to the left and two down to the right
                    if(amIRed){
                        if(board[inLineWithBlank+1][i+1] == 'R' && board[inLineWithBlank+2][i+2] == 'R' && board[inLineWithBlank-1][i-1] == 'R'){//Check if all columns are equal to red
                            System.out.println("I can win diagonally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }else{
                        if(board[inLineWithBlank+1][i+1] == 'Y' && board[inLineWithBlank+2][i+2] == 'Y' && board[inLineWithBlank-1][i-1] == 'Y'){//Check if all columns are equal to yellow
                            System.out.println("I can win diagonally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }
                }
                if(i > 1 && i < myGame.getColumnCount() - 1 && inLineWithBlank > 0&& inLineWithBlank < myGame.getRowCount() - 2){//check one diagonal place up and to the right and two down to the left
                    if(amIRed){
                        if(board[inLineWithBlank+1][i-1] == 'R' && board[inLineWithBlank+2][i-2] == 'R' && board[inLineWithBlank-1][i+1] == 'R'){//Check if all columns are equal to red
                            System.out.println("I can win diagonally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }else{
                        if(board[inLineWithBlank+1][i-1] == 'Y' && board[inLineWithBlank+2][i-2] == 'Y' && board[inLineWithBlank-1][i+1] == 'Y'){//Check if all columns are equal to yellow
                            System.out.println("I can win diagonally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }
                }
            }
        }
        return priority;
    }

    /**
     * Returns the column that would allow the opponent to win.
     * 
     * You might want your agent to check to see if the opponent would have any winning moves
     * available so your agent can block them. Implement this method to return what column should
     * be blocked to prevent the opponent from winning.
     *
     * @return the column that would allow the opponent to win.
     */
    public int theyCanWin()
    {
        int priority = -1;
        int inLineWithBlank = -1;
        char[][] board = myGame.getBoardMatrix();
        boolean amIRed = !iAmRed;
        for(int i = 0; i < board.length; i++){
            inLineWithBlank = getLowestEmptyIndex(myGame.getColumn(i));
            if(inLineWithBlank > -1){//Check if the column is not full
                if(inLineWithBlank < myGame.getRowCount()-3){//make sure at least three spaces are filled vertically in a column
                    if(amIRed){
                        if(board[inLineWithBlank+1][i] == 'R' && board[inLineWithBlank+2][i] == 'R' && board[inLineWithBlank+3][i] == 'R'){//check if three colours in a row match vertically and are equal to red
                            System.out.println("They can win vertically at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }else{
                        if(board[inLineWithBlank+1][i] == 'Y' && board[inLineWithBlank+2][i] == 'Y' && board[inLineWithBlank+3][i] == 'Y'){//check if three colours in a row match vertically and are equal to yellow
                            System.out.println("They can win vertically at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;  
                            return priority;
                        }
                    }
                }
                if(i < myGame.getColumnCount() - 3){//check three places to the right
                    if(amIRed){
                        if(board[inLineWithBlank][i+2] == 'R' && board[inLineWithBlank][i+3] == 'R' && board[inLineWithBlank][i+1] == 'R'){//Check if all columns are equal to red
                            System.out.println("They can win horizontally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }else{
                        if(board[inLineWithBlank][i+2] == 'Y' && board[inLineWithBlank][i+3] == 'Y' && board[inLineWithBlank][i+1] == 'Y'){//Check if all columns are equal to yellow
                            System.out.println("They can win horizontally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }
                }
                if(i > 2){//check three places to the left
                    if(amIRed){
                        if(board[inLineWithBlank][i-2] == 'R' && board[inLineWithBlank][i-3] == 'R' && board[inLineWithBlank][i-1] == 'R'){//Check if all columns are equal to red
                            System.out.println("They can win horizontally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }else{
                        if(board[inLineWithBlank][i-2] == 'Y' && board[inLineWithBlank][i-3] == 'Y' && board[inLineWithBlank][i-1] == 'Y'){//Check if all columns are equal to yellow
                            System.out.println("They can win horizontally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }
                }
                if(i < myGame.getColumnCount() - 2 && i > 0) { //check two to the right and one to the left
                    if(amIRed){
                        if(board[inLineWithBlank][i+2] == 'R' && board[inLineWithBlank][i-1] == 'R' && board[inLineWithBlank][i+1] == 'R'){//Check if all columns are equal to red
                            System.out.println("They can win horizontally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }else{
                        if(board[inLineWithBlank][i+2] == 'Y' && board[inLineWithBlank][i-1] == 'Y' && board[inLineWithBlank][i+1] == 'Y'){//Check if all columns are equal to yellow
                            System.out.println("They can win horizontally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }
                }
                if(i < myGame.getColumnCount() - 1 && i > 1) { //check two to the left and one to the right
                    if(amIRed){
                        if(board[inLineWithBlank][i-2] == 'R' && board[inLineWithBlank][i+1] == 'R' && board[inLineWithBlank][i-1] == 'R'){//Check if all columns are equal to red
                            System.out.println("They can win horizontally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }else{
                        if(board[inLineWithBlank][i-2] == 'Y' && board[inLineWithBlank][i+1] == 'Y' && board[inLineWithBlank][i-1] == 'Y'){//Check if all columns are equal to yellow
                            System.out.println("They can win horizontally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }
                }
                if(i < myGame.getColumnCount() - 3 && inLineWithBlank > 2 ){//check three diagonal places up and to the right
                    if(amIRed){
                        if(board[inLineWithBlank-2][i+2] == 'R' && board[inLineWithBlank-3][i+3] == 'R' && board[inLineWithBlank-1][i+1] == 'R'){//Check if all columns are equal to red
                            System.out.println("They can win diagonally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }else{
                        if(board[inLineWithBlank-2][i+2] == 'Y' && board[inLineWithBlank-3][i+3] == 'Y' && board[inLineWithBlank-1][i+1] == 'Y'){//Check if all columns are equal to yellow
                            System.out.println("They can win diagonally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }
                }
                if(i > 2 && inLineWithBlank > 2 ){//check three diagonal places up and to the left
                    if(amIRed){
                        if(board[inLineWithBlank-2][i-2] == 'R' && board[inLineWithBlank-3][i-3] == 'R' && board[inLineWithBlank-1][i-1] == 'R'){//Check if all columns are equal to red
                            System.out.println("They can win diagonally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }else{
                        if(board[inLineWithBlank-2][i-2] == 'Y' && board[inLineWithBlank-3][i-3] == 'Y' && board[inLineWithBlank-1][i-1] == 'Y'){//Check if all columns are equal to yellow
                            System.out.println("They can win diagonally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }
                }
                if(i < myGame.getColumnCount() - 3 && inLineWithBlank < myGame.getRowCount() - 3){//check three diagonal places down and to the right
                    if(amIRed){
                        if(board[inLineWithBlank+2][i+2] == 'R' && board[inLineWithBlank+3][i+3] == 'R' && board[inLineWithBlank+1][i+1] == 'R'){//Check if all columns are equal to red
                            System.out.println("They can win diagonally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }else{
                        if(board[inLineWithBlank+2][i+2] == 'Y' && board[inLineWithBlank+3][i+3] == 'Y' && board[inLineWithBlank+1][i+1] == 'Y'){//Check if all columns are equal to yellow
                            System.out.println("They can win diagonally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }
                }
                if(i > 2 && inLineWithBlank < myGame.getRowCount() - 3){//check three diagonal places down and to the left
                    if(amIRed){
                        if(board[inLineWithBlank+2][i-2] == 'R' && board[inLineWithBlank+3][i-3] == 'R' && board[inLineWithBlank+1] [i-1] == 'R'){//Check if all columns are equal to red
                            System.out.println("They can win diagonally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }else{
                        if(board[inLineWithBlank+2][i-2] == 'Y' && board[inLineWithBlank+3][i-3] == 'Y' && board[inLineWithBlank+1][i-1] == 'Y'){//Check if all columns are equal to yellow
                            System.out.println("They can win diagonally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }
                }
                if(i < myGame.getColumnCount() - 2 && i > 0 && inLineWithBlank > 1 && inLineWithBlank < myGame.getRowCount() - 1){//check two diagonal places up and to the right and one down to the left
                    if(amIRed){
                        if(board[inLineWithBlank-1][i+1] == 'R' && board[inLineWithBlank-2][i+2] == 'R' && board[inLineWithBlank+1][i-1] == 'R'){//Check if all columns are equal to red
                            System.out.println("They can win diagonally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }else{
                        if(board[inLineWithBlank-1][i+1] == 'Y' && board[inLineWithBlank-2][i+2] == 'Y' && board[inLineWithBlank+1][i-1] == 'Y'){//Check if all columns are equal to yellow
                            System.out.println("They can win diagonally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }
                }
                if(i < myGame.getColumnCount() - 1 && i > 1 && inLineWithBlank > 1 && inLineWithBlank < myGame.getRowCount() - 1){//check two diagonal places up and to the left and one down to the right
                    if(amIRed){
                        if(board[inLineWithBlank-1][i-1] == 'R' && board[inLineWithBlank-2][i-2] == 'R' && board[inLineWithBlank+1] [i+1] == 'R'){//Check if all columns are equal to red
                            System.out.println("They can win diagonally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }else{
                        if(board[inLineWithBlank-1][i-1] == 'Y' && board[inLineWithBlank-2][i-2] == 'Y' && board[inLineWithBlank+1][i+1] == 'Y'){//Check if all columns are equal to yellow
                            System.out.println("They can win diagonally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }
                }
                if(i > 0 && i < myGame.getColumnCount() - 2 && inLineWithBlank > 0&& inLineWithBlank < myGame.getRowCount() - 2){//check one diagonal place up and to the left and two down to the right
                    if(amIRed){
                        if(board[inLineWithBlank+1][i+1] == 'R' && board[inLineWithBlank+2][i+2] == 'R' && board[inLineWithBlank-1][i-1] == 'R'){//Check if all columns are equal to red
                            System.out.println("They can win diagonally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }else{
                        if(board[inLineWithBlank+1][i+1] == 'Y' && board[inLineWithBlank+2][i+2] == 'Y' && board[inLineWithBlank-1] [i-1] == 'Y'){//Check if all columns are equal to yellow
                            System.out.println("They can win diagonally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }
                }
                if(i > 1 && i < myGame.getColumnCount() - 1 && inLineWithBlank > 0&& inLineWithBlank < myGame.getRowCount() - 2){//check one diagonal place up and to the right and two down to the left
                    if(amIRed){
                        if(board[inLineWithBlank+1][i-1] == 'R' && board[inLineWithBlank+2][i-2] == 'R' && board[inLineWithBlank-1][i+1] == 'R'){//Check if all columns are equal to red
                            System.out.println("They can win diagonally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }else{
                        if(board[inLineWithBlank+1][i-1] == 'Y' && board[inLineWithBlank+2][i-2] == 'Y' && board[inLineWithBlank-1][i+1] == 'Y'){//Check if all columns are equal to yellow
                            System.out.println("They can win diagonally at: column - "+i+ ", row - "+inLineWithBlank+". Moving...");
                            priority = i;
                            return priority;
                        }
                    }
                }
            }
        }
        return priority;
    }

    /**
     * Returns the name of this agent.
     *
     * @return the agent's name
     */
    public String getName()
    {
        return "My Agent";
    }
}
