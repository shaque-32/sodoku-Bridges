import bridges.base.GameGrid;
import bridges.base.Grid;
import bridges.base.NamedColor;
import bridges.base.NamedSymbol;

public class Sudoku extends Game {
    public static void main(String[] args) {
        //fill in your own assignment number, username, and API key
        Sudoku game = new Sudoku(0, "shaque", "260127091696");
        game.start();
    }

    private Sudoku(int assignmentNumber, String username, String apiKey) {
        super(assignmentNumber, username, apiKey, 9, 9);
        setTitle("Sudoku");
        setDescription("Solve the sudoku puzzles");
    }

    private int selectedRow, selectedCol; //coordinates of selected cell
    private GridGenerator gridGen;

    //current grid state
    //numberGrid[r][c] is 0 if the cell at (r, c) is empty, 1 if the cell contains a 1, etc.
    private int[][] numberGrid;
    //mutable[r][c] is true if the player is allowed to edit the cell at (r, c)
    private boolean[][] mutable;

    Scene currentScene;
    enum Scene { START, GAME, SUCCESS, FAILURE };

    //automatically called at the start of the first game
    public void initialize() {
        for(int r = 0; r < 9; r++) {
            for(int c = 0; c < 9; c++) {
                drawBackground(r, c);
            }
        }

        //the selected cell starts in the middle of the canvas
        selectedRow = 4;
        selectedCol = 4;

        gridGen = new GridGenerator();
        mutable = new boolean[9][9];

        //draw start scene message
        String[] message = new String[9];
        message[2] = " SUDOKU  ";
        message[4] = " Q  easy ";
        message[6] = " A  hard ";
        drawMessage(message);

        currentScene = Scene.START;
    }

    //called at the start of each game
    private void restart(boolean isEasy) {
        numberGrid = gridGen.newStartingGrid(isEasy);
        numberGrid = gridGen.testGrid(); /* uncomment to test game */

        for(int r = 0; r < 9; r++) {
            for(int c = 0; c < 9; c++) {
                //mutable[r][c] should only be true if the cell at (r, c) is empty
                //this prevents the player from editing the clue numbers
                //YOUR CODE HERE:
                if(numberGrid[r][c] >= 1 && numberGrid[r][c] <= 9){
                    mutable[r][c] = false;

                }else{
                    mutable[r][c] = true;
                }
            }
        }

        resume();
    }

    //called at the start of each game, and when the player returns to the game
        //after submitting an incorrect solution
    private void resume() {
        for(int r = 0; r < 9; r++) {
            for(int c = 0; c < 9; c++) {
                drawNumber(r, c);
            }
        }

        currentScene = Scene.GAME;
    }

    //called when the player solves a puzzle
    private void success() {
        String[] message = new String[9];
        message[2] = "you won  ";
        message[4] = "Q  easy  ";
        message[6] = "A  hard  ";
        drawMessage(message);

        currentScene = Scene.SUCCESS;
    }

    //called when the player submits an incorrect solution
    private void failure() {
        String[] message = new String[9];
        message[2] = "incorrect";
        message[4] = "solution ";
        message[6] = "Q  resume";
        drawMessage(message);

        currentScene = Scene.FAILURE;
    }

    //automatically called once per frame
    public void gameLoop() {
        if(currentScene == Scene.START || currentScene == Scene.SUCCESS) {
            //start the game when the player presses 'q' (easy) or 'a' (hard)
            if(keyPress("q")) restart(true);
            if(keyPress("a")) restart(false);
            return;
        }

        if(currentScene == Scene.FAILURE) {
            //resume the game wwhen the player presses 'q'
            if(keyPress("q")) resume();
            return;
        }

        drawBackground(selectedRow, selectedCol);

        if(keyPress("ArrowUp")) selectedRow--;
        //write corresponding 'if' statements for the other three arrow keys
        //YOUR CODE HERE:
        if(keyPress("ArrowDown")) selectedRow--;
        if(keyPress("ArrowRight")) selectedCol++;
        if(keyPress("ArrowLeft")) selectedCol--;


        //prevent the selected cell from going out of bounds
        //YOUR CODE HERE:
        if(selectedRow == 9){
            selectedRow = 0;
        }
        if(selectedCol == 9){
            selectedCol = 0;
        }
        if(selectedRow == -1){
            selectedRow = 8;
        }
        if(selectedCol == -1){
            selectedCol = 8;
        }

        //set the color of the selected cell
        //it should be different from the two background colors so that the player can
        setBGColor(selectedRow, selectedCol, NamedColor.black);
            //tell which cell is selected
        //YOUR CODE HERE:

        //edit the selected cell
        if(keyPress("Backspace")) editCell(0);
        if(keyPress("1")) editCell(1);
        if(keyPress("2")) editCell(2);
        if(keyPress("3")) editCell(3);
        if(keyPress("4")) editCell(4);
        if(keyPress("5")) editCell(5);
        if(keyPress("6")) editCell(6);
        if(keyPress("7")) editCell(7);
        if(keyPress("8")) editCell(8);
        if(keyPress("9")) editCell(9);

        if(keyPress("Enter")) {
            //check if the grid is a correct solution
            if(gridIsSolution()) success();
            else failure();
        }
    }

    private void editCell(int num) {
        if(mutable[selectedRow][selectedCol]){
            numberGrid[selectedRow][selectedCol] = num;
            drawNumber(selectedRow, selectedCol);
        }

        //if the player is allowed to edit the selected cell, set its value to 'num'
        //then call the 'drawNumber' method
        //YOUR CODE HERE:
    }

    private void drawBackground(int row, int col) {
        //set the background color of the cell at (row, col)
        //use two different colors depending on the coordinates
        //YOUR CODE HERE:
        if((row <= 2 && col <=2)|| (row >= 6 && col<=2) || (row <=2 && col >= 6) || (row>=6 && col>=6)){
            setBGColor(row, col, NamedColor.blueviolet);
        } else {
            setBGColor(row, col, NamedColor.burlywood);
        }
    }

    private void drawNumber(int row, int col) {
       // protected void drawSymbol(int row, int col, NamedSymbol s, NamedColor c) {
         //   grid.drawSymbol(row, col, s, c);
        
        int val = numberGrid[row][col];
        NamedSymbol newEntry = numberToSymbol(val);

        if(mutable[row][col] == true){
            drawSymbol(row,col,newEntry,NamedColor.black);
        } else {
            drawSymbol(row,col,newEntry,NamedColor.red);

        }

        }

        
        //use the 'numberToSymbol' method to convert numberGrid[row][col] into a symbol
        //then draw that symbol on the grid
        //use two different colors depending on whether the cell is mutable
        //YOUR CODE HERE:
    

    private NamedSymbol numberToSymbol(int num) {
        return switch(num) {
            case 0 -> NamedSymbol.none;
            case 1 -> NamedSymbol.one;
            
            //complete the rest of the cases (2 through 9)
            //YOUR CODE HERE:
            case 2 -> NamedSymbol.two;
            case 3 -> NamedSymbol.three;
            case 4 -> NamedSymbol.four;
            case 5 -> NamedSymbol.five;
            case 6 -> NamedSymbol.six;
            case 7 -> NamedSymbol.seven;
            case 8 -> NamedSymbol.eight;
            case 9 -> NamedSymbol.nine;


            default -> NamedSymbol.none;
        };
    }

    private void drawMessage(String[] message) {
        //draw an array of strings on the canvas
        for(int r = 0; r < 9; r++) {
            for(int c = 0; c < 9; c++) {
                NamedSymbol symbol =
                    (message[r] == null)
                    ? NamedSymbol.none
                    : charToSymbol(message[r].charAt(c));
                drawSymbol(r, c, symbol, NamedColor.black);
            }
        }
    }

    private NamedSymbol charToSymbol(char ch) {
        return switch(ch) {
            case ' ' -> NamedSymbol.none;
            case 'a' -> NamedSymbol.a;
            case 'c' -> NamedSymbol.c;
            case 'd' -> NamedSymbol.d;
            case 'e' -> NamedSymbol.e;
            case 'h' -> NamedSymbol.h;
            case 'i' -> NamedSymbol.i;
            case 'l' -> NamedSymbol.l;
            case 'm' -> NamedSymbol.m;
            case 'n' -> NamedSymbol.n;
            case 'o' -> NamedSymbol.o;
            case 'r' -> NamedSymbol.r;
            case 's' -> NamedSymbol.s;
            case 't' -> NamedSymbol.t;
            case 'u' -> NamedSymbol.u;
            case 'w' -> NamedSymbol.w;
            case 'y' -> NamedSymbol.y;
            case 'A' -> NamedSymbol.A;
            case 'D' -> NamedSymbol.D;
            case 'K' -> NamedSymbol.K;
            case 'O' -> NamedSymbol.O;
            case 'Q' -> NamedSymbol.Q;
            case 'S' -> NamedSymbol.S;
            case 'U' -> NamedSymbol.U;
            default -> NamedSymbol.none;
        };
    }

    private boolean gridIsSolution() {
        int total = 0;
        boolean checker = false;
        for(int r = 0; r < 9 ; r++){
            for(int c = 0; c<9; c++){
                total += numberGrid[r][c];
                if(total == 45){
                    checker = true;
                } else {
                    break;
                }
            }
            total = 0;
        }
        return checker;
        //test if 'numberGrid' contains a valid sudoku solution
        //each row, column, and 3 * 3 square should contain every digit from 1 to 9
        //YOUR CODE HERE:
    }
}