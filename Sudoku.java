import java.util.ArrayList;

public class Snake {
    //direction the snake is moving in
    //either "up", "down", "left", or "right"
    private String direction;

    //row and column numbers of the snake
    private ArrayList<Integer> rows;
    private ArrayList<Integer> cols;

    //Snake constructor
    //takes three arguments: a starting direction, and a starting row and column
    public Snake(String startingDirection, int startingRow, int startingCol) {
        direction = startingDirection;
        
        //set the variables 'rows' and 'cols' to empty arraylists
        //YOUR CODE HERE:
        rows = new ArrayList<Integer>();
        cols = new ArrayList<Integer>();


        //add starting row and column to 'rows' and 'cols' respectively
        //YOUR CODE HERE:
        rows.add(startingRow);
        cols.add(startingCol);
    }

    //set the snake's direction
    public void setDirection(String newDirection) {
        //calculate the direction opposite the one the snake is moving in
        //store it in a String variable
        //YOUR CODE HERE:
        String oppDirection = "";
        if(this.direction.equals("right")){
            oppDirection = "left";
        }
        if(this.direction.equals("left")){
            oppDirection = "right";
        }
        if(this.direction.equals("up")){
            oppDirection = "down";
        }
        if(this.direction.equals("down")){
            oppDirection = "up";
        }


        //set 'direction' to 'newDirection', unless the opposite of 'direction' is equal to 'newDirection'
        //this condition keeps the snake from turning back on itself
        //YOUR CODE HERE:
        if(!oppDirection.equals(newDirection)){
            this.direction = newDirection;
        }
    }

    //create a method called getSize, which returns the size of the snake
    //you can use either 'rows' or 'cols' to calculate the size, it doesn't matter
    //YOUR CODE HERE:
    public int getSize(){
        return rows.size();
    }

    //create a method called getRow, which takes an integer 'i'
    //and returns the row number at position 'i' along the snake
    //YOUR CODE HERE:
    public int getRow(int i){
        return rows.get(i);
    }

    //create a method called getCol, which takes an integer 'i'
    //and returns the column number at position 'i' along the snake
    //YOUR CODE HERE:
    public int getCol(int i){
        return cols.get(i);
    }

    //if the snake is about to crash into the other snake or go off the game board, return 'true'
    //otherwise, move the snake forward and return 'false'
    public boolean move(Snake otherSnake, int height, int width) {
        //create two variables called 'rowChange' and 'colChange' based on the snake's direction
        //for example, if the snake is moving up, 'rowChange' should be -1 and 'colChange' should be 0
        //YOUR CODE HERE:
        int rowChange = 0;
        int colChange = 0;
        if(direction.equals("up")){
            rowChange--;
            colChange = 0;

        }

        if(direction.equals("down")){
            rowChange++;
            colChange = 0;

        }
        if(direction.equals("left")){
            rowChange = 0;
            colChange--;

        }
        if(direction.equals("right")){
            rowChange = 0;
            colChange++;

        }


        //create a variable called 'newRow' which equals the last row number plus 'rowChange'
        //create a variable called 'newCol' which equals the last column number plus 'colChange'
        //YOUR CODE HERE:
      

        int newRow = (rows.get(rows.size() - 1)) + rowChange;
        int newCol = (cols.get(cols.size() - 1)) + colChange;

        //if the point [newRow, newCol] is already in the snake, return 'true'
        //moving forward would cause the snake to crash into itself!
        //YOUR CODE HERE:
        for(int i = 0; i < rows.size(); i++){
            if(rows.get(i) == newRow && cols.get(i) == newCol){
                return true;
            }

        }

        //if the point [newRow, newCol] is in the other snake, return 'true'
        //moving forward would cause the snake to crash into its opponent!
        //YOUR CODE HERE:
        for(int i = 0; i < otherSnake.getSize(); i++){
            if(otherSnake.getRow(i) == newRow && otherSnake.getCol(i) == newCol){
                return true;
            }
        }

        //if the point [newRow, newCol] is out of bounds, return 'true'
        //moving forward would cause the snake to go off the screen!
        //YOUR CODE HERE:
        if (newRow < 0 || newRow >= height || newCol < 0 || newCol >= width){
            return true;
        }

        //add 'newRow' and 'newCol' to 'rows' and 'cols' respectively
        //YOUR CODE HERE:
        rows.add(newRow);
        cols.add(newCol);

        return false;
    }
{
    
}
}
