public class GridGenerator {
    private int[][][] easyGrids;
    private int[][][] hardGrids;  
    private boolean[] easyGridUsed; //easyGridUsed[i] is true if easyGrids[i] has already been used
    private boolean[] hardGridUsed; //hardGridUsed[i] is true if hardGrids[i] has already been used

    public GridGenerator() {
        int o = 0;
        easyGrids = new int[][][] {
            { { o, 5, 9, o, o, o, 2, o, o }
            , { 7, o, 2, o, 5, 1, o, o, o }
            , { 4, 6, 1, 3, o, o, 8, o, 7 }
            , { o, 9, 3, 4, 6, o, 7, o, o }
            , { o, o, o, o, o, o, o, o, o }
            , { o, o, 5, o, 8, 2, 3, 1, o }
            , { 9, o, 6, o, o, 4, 1, 8, 5 }
            , { o, o, o, 8, 9, o, 4, o, 2 }
            , { o, o, 4, o, o, o, 6, 7, o }
            }
            ,
            { { 2, 6, o, o, o, 8, 9, o, o }
            , { 7, o, 9, 2, 1, 5, 6, o, o }
            , { o, o, o, 6, 4, o, o, 5, o }
            , { o, 1, o, 5, o, 7, 8, o, o }
            , { o, o, o, o, 2, o, o, o, o }
            , { o, o, 4, 8, o, 1, o, 2, o }
            , { o, 8, o, o, 6, 3, o, o, o }
            , { o, o, 3, 1, 8, 2, 4, o, 7 }
            , { o, o, 6, 7, o, o, o, 9, 8 }
            }
            ,
            { { o, o, o, 8, 3, 9, 2, 6, 5 }
            , { o, 2, o, o, o, o, 1, o, o }
            , { 5, o, o, 1, o, 2, 4, 3, o }
            , { o, o, 5, o, o, 6, o, o, o }
            , { 1, 9, o, 3, o, 7, o, 8, 6 }
            , { o, o, o, 5, o, o, 7, o, o }
            , { o, 4, 6, 2, o, 1, o, o, 3 }
            , { o, o, 3, o, o, o, o, 5, o }
            , { 8, 5, 7, 4, 6, 3, o, o, o }
            }
            ,
            { { 3, o, o, 2, 8, o, o, o, 5 }
            , { o, o, 8, o, o, o, o, o, o }
            , { 6, 5, o, 3, 4, o, 9, o, o }
            , { 8, 1, o, o, o, 3, 2, 4, o }
            , { 9, o, o, 4, 2, 5, o, o, 1 }
            , { o, 4, 7, 1, o, o, o, 9, 3 }
            , { o, o, 6, o, 9, 4, o, 8, 2 }
            , { o, o, o, o, o, o, 3, o, o }
            , { 5, o, o, o, 3, 2, o, o, 9 }
            }
            ,
            { { 3, o, o, 7, 1, o, o, o, o }
            , { 2, 8, o, o, 3, o, o, o, 6 }
            , { 9, 1, o, o, o, 2, o, 3, o }
            , { 7, o, o, 6, 4, o, 3, o, o }
            , { o, 3, 4, o, o, o, 9, 6, o }
            , { o, o, 9, o, 5, 1, o, o, 7 }
            , { o, 7, o, 1, o, o, o, 5, 9 }
            , { 1, o, o, o, 6, o, o, 7, 8 }
            , { o, o, o, o, 9, 7, o, o, 3 }
            }
            ,
            { { o, o, o, 6, o, o, 9, 4, 3 }
            , { o, 9, 4, 5, 8, o, 2, o, o }
            , { o, o, 3, 2, o, o, o, 8, o }
            , { o, 2, 1, o, o, 9, o, o, 4 }
            , { o, o, o, 1, 4, 5, o, o, o }
            , { 6, o, o, 3, o, o, 5, 1, o }
            , { o, 7, o, o, o, 2, 3, o, o }
            , { o, o, 5, o, 3, 7, 6, 9, o }
            , { 2, 3, 8, o, o, 6, o, o, o }
            }
            ,
            { { 4, o, o, o, 6, 9, 7, 1, 8 }
            , { o, 3, 6, o, 8, o, 2, o, o }
            , { 8, o, o, o, 7, o, o, o, o }
            , { o, 4, o, o, 2, o, 9, o, 6 }
            , { 3, 1, o, o, o, o, o, 2, 5 }
            , { 6, o, 9, o, 4, o, o, 3, o }
            , { o, o, o, o, 3, o, o, o, 2 }
            , { o, o, 5, o, 1, o, 6, 8, o }
            , { 9, 6, 3, 2, 5, o, o, o, 7 }
            }
            ,
            { { 6, 5, o, o, 1, 2, o, 9, o }
            , { o, 8, o, 9, o, o, 2, 1, 7 }
            , { o, o, o, o, o, o, o, o, 3 }
            , { o, o, o, 6, o, o, 3, 7, o }
            , { 4, 6, o, o, 8, o, o, 2, 1 }
            , { o, 9, 1, o, o, 3, o, o, o }
            , { 5, o, o, o, o, o, o, o, o }
            , { 2, 7, 9, o, o, 8, o, 5, o }
            , { o, 3, o, 7, 5, o, o, 4, 2 }
            }
            ,
            { { 8, o, 4, 6, o, o, 9, 2, o }
            , { 6, 5, o, o, o, 2, o, o, o }
            , { o, o, o, o, 5, 9, 3, 8, o }
            , { o, o, o, o, 3, o, 5, 1, 9 }
            , { o, o, 1, 8, o, 5, 4, o, o }
            , { 5, 4, 3, o, 7, o, o, o, o }
            , { o, 1, 6, 5, 4, o, o, o, o }
            , { o, o, o, 1, o, o, o, 5, 4 }
            , { o, 9, 5, o, o, 6, 8, o, 1 }
            }
            ,
            { { o, 5, o, 8, o, 3, 6, 2, 7 }
            , { 8, o, o, 6, o, o, 1, o, o }
            , { o, 6, 2, o, o, o, o, 4, o }
            , { 6, o, o, 4, 3, o, 5, o, 1 }
            , { o, o, o, o, o, o, o, o, o }
            , { 7, o, 4, o, 6, 1, o, o, 9 }
            , { o, 7, o, o, o, o, 4, 9, o }
            , { o, o, 6, o, o, 4, o, o, 5 }
            , { 4, 1, 8, 2, o, 9, o, 7, o }
            }
            ,
            { { o, o, o, o, o, o, o, o, o }
            , { o, o, o, o, o, o, o, o, o }
            , { o, o, o, o, o, o, o, o, o }
            , { o, o, o, o, o, o, o, o, o }
            , { o, o, o, o, o, o, o, o, o }
            , { o, o, o, o, o, o, o, o, o }
            , { o, o, o, o, o, o, o, o, o }
            , { o, o, o, o, o, o, o, o, o }
            , { o, o, o, o, o, o, o, o, o }
            }
            ,
            { { 6, o, 7, o, 4, 9, o, 5, 8 }
            , { o, 3, o, o, 6, o, o, 9, o }
            , { o, 9, 4, 3, 7, o, o, o, 1 }
            , { 2, o, o, o, o, o, o, o, 5 }
            , { 4, o, o, 8, o, 5, o, o, 6 }
            , { 3, o, o, o, o, o, o, o, 9 }
            , { 7, o, o, o, 8, 3, 5, 6, o }
            , { o, 6, o, o, 5, o, o, 7, o }
            , { 9, 4, o, 7, 2, o, 8, o, 3 }
            }
        };

        hardGrids = new int[][][] {
            { { o, o, 1, o, o, o, o, o, 9 }
            , { o, o, o, 2, o, 6, o, o, 7 }
            , { o, o, o, o, o, 1, 5, o, 3 }
            , { o, 2, o, o, 3, o, 6, 7, o }
            , { o, 5, o, 1, 7, 2, o, 9, o }
            , { o, 9, 8, o, 6, o, o, 1, o }
            , { 5, o, 9, 3, o, o, o, o, 6 }
            , { 2, o, o, 6, o, 5, o, o, o }
            , { 4, o, o, o, o, o, 2, o, o }
            }
            ,
            { { o, o, 4, o, o, o, o, 3, o }
            , { 5, 7, o, o, 3, o, o, 6, 9 }
            , { 2, o, 3, 1, 6, o, o, o, o }
            , { o, o, o, 6, o, o, 7, 2, o }
            , { o, o, o, o, 8, o, o, o, o }
            , { o, 4, 5, o, o, 9, o, o, o }
            , { o, o, o, o, 4, 3, 8, o, 7 }
            , { 4, 1, o, o, 2, o, o, 9, 5 }
            , { o, 3, o, o, o, o, 2, o, o }
            }
            ,
            { { o, o, 1, o, 4, o, o, o, o }
            , { o, o, 7, o, o, o, o, 2, o }
            , { 5, o, 9, 8, 1, o, o, 6, o }
            , { o, o, o, o, o, 5, o, 3, 8 }
            , { 8, o, o, 1, o, 3, o, o, 2 }
            , { 3, 2, o, 4, o, o, o, o, o }
            , { o, 6, o, o, 3, 1, 2, o, 5 }
            , { o, 9, o, o, o, o, 1, o, o }
            , { o, o, o, o, 7, o, 4, o, o }
            }
            ,
            { { 5, o, 1, o, 4, o, o, 9, 8 }
            , { o, o, o, 3, o, 2, o, 6, 4 }
            , { o, o, o, o, o, 8, 7, o, o }
            , { o, o, o, o, 1, o, o, o, 9 }
            , { 2, 4, 3, o, o, o, 5, 1, 6 }
            , { 1, o, o, o, 6, o, o, o, o }
            , { o, o, 9, 4, o, o, o, o, o }
            , { 4, 5, o, 9, o, 1, o, o, o }
            , { 3, 1, o, o, 8, o, 9, o, 7 }
            }
            ,
            { { o, 2, o, o, o, o, 9, o, o }
            , { 7, o, 9, 4, o, o, o, 5, o }
            , { o, 4, o, o, 3, o, o, 1, o }
            , { o, 9, 8, o, o, 6, 7, o, o }
            , { 6, o, o, o, 4, o, o, o, 2 }
            , { o, o, 4, 8, o, o, 6, 3, o }
            , { o, 7, o, o, 9, o, o, 6, o }
            , { o, 5, o, o, o, 8, 4, o, 1 }
            , { o, o, 6, o, o, o, o, 2, o }
            }
            ,
            { { 4, o, o, o, o, 6, 7, o, 1 }
            , { o, o, o, o, o, o, o, o, 9 }
            , { o, 1, 8, o, 2, o, 5, o, o }
            , { o, 6, o, o, 4, o, o, o, 5 }
            , { 5, o, 3, o, 9, o, 8, o, 4 }
            , { 7, o, o, o, 5, o, o, 3, o }
            , { o, o, 5, o, 6, o, 3, 9, o }
            , { 8, o, o, o, o, o, o, o, o }
            , { 3, o, 2, 1, o, o, o, o, 7 }
            }
            ,
            { { o, 1, o, o, o, 4, o, o, 9 }
            , { o, o, o, o, o, o, 6, 5, 2 }
            , { 5, o, 9, o, o, 7, o, o, o }
            , { o, o, 6, o, 5, 2, 9, o, 4 }
            , { o, 9, 1, o, o, o, 5, 2, o }
            , { 2, o, 5, 1, 9, o, 3, o, o }
            , { o, o, o, 2, o, o, 8, o, 3 }
            , { 9, 5, 3, o, o, o, o, o, o }
            , { 7, o, o, 9, o, o, o, 6, o }
            }
            ,
            { { o, o, o, o, 2, 8, 5, 9, 1 }
            , { 8, o, 1, o, o, 3, o, o, o }
            , { o, o, 5, o, o, o, 8, o, o }
            , { 7, o, o, o, 8, 6, o, o, o }
            , { 1, o, o, 2, 7, 4, o, o, 5 }
            , { o, o, o, 9, 3, o, o, o, 7 }
            , { o, o, 2, o, o, o, 7, o, o }
            , { o, o, o, 8, o, o, 9, o, 3 }
            , { 5, 3, 6, 7, 1, o, o, o, o }
            }
            ,
            { { 6, o, o, o, 3, o, o, o, 2 }
            , { 8, o, o, o, o, 2, 5, 9, o }
            , { o, o, 5, o, o, o, o, o, 4 }
            , { o, o, 9, 7, o, o, 1, o, 5 }
            , { o, 4, o, o, 2, o, o, 7, o }
            , { 3, o, 7, o, o, 1, 2, o, o }
            , { 4, o, o, o, o, o, 7, o, o }
            , { o, 6, 2, 1, o, o, o, o, 8 }
            , { 7, o, o, o, 5, o, o, o, 9 }
            }
            ,
            { { 7, o, o, o, 2, o, 5, 8, o }
            , { o, o, o, 3, o, o, o, o, o }
            , { o, o, 6, 7, 8, o, o, 1, o }
            , { 5, o, 3, o, o, o, o, 4, 1 }
            , { o, 6, o, 4, 7, 3, o, 9, o }
            , { 4, 9, o, o, o, o, 6, o, 3 }
            , { o, 1, o, o, 3, 8, 7, o, o }
            , { o, o, o, o, o, 1, o, o, o }
            , { o, 4, 5, o, 6, o, o, o, 2 }
            }
            ,
            { { 1, 9, 8, o, o, o, 5, o, o }
            , { o, 4, o, o, o, 9, o, o, o }
            , { o, 5, 3, 6, o, 8, o, o, 7 }
            , { o, o, o, 5, o, o, 2, 4, 9 }
            , { o, o, o, o, o, o, o, o, o }
            , { 3, 7, 4, o, o, 1, o, o, o }
            , { 8, o, o, 1, o, 2, 9, 5, o }
            , { o, o, o, 8, o, o, o, 3, o }
            , { o, o, 5, o, o, o, 7, 2, 8 }
            }
            ,
            { { 6, 5, o, o, o, o, o, o, o }
            , { o, 3, o, o, 6, 5, o, o, o }
            , { o, o, 9, 7, o, 1, o, 4, o }
            , { 4, 7, o, o, 2, 8, 3, o, o }
            , { o, o, o, o, 1, o, o, o, o }
            , { o, o, 8, 3, 7, o, o, 6, 9 }
            , { o, 8, o, 6, o, 3, 1, o, o }
            , { o, o, o, 1, 4, o, o, 7, o }
            , { o, o, o, o, o, o, o, 9, 6 }
            }
        };

        //none of the grids has been used yet
        easyGridUsed = new boolean[easyGrids.length];
        for(int i = 0; i < easyGrids.length; i++) {
            easyGridUsed[i] = false;
        }
        hardGridUsed = new boolean[hardGrids.length];
        for(int i = 0; i < hardGrids.length; i++) {
            hardGridUsed[i] = false;
        }
    }

    public int[][] newStartingGrid(boolean isEasy) {
        int[][][] grids = isEasy ? easyGrids : hardGrids;
        boolean[] gridUsed = isEasy ? easyGridUsed : hardGridUsed;

        //if all of the grids have been used ('gridUsed' contains only 'true'),
            //update 'gridUsed' so that it contains only 'false'
        //YOUR CODE HERE:

        int n;
        //set 'n' to a random integer between 0 and grids.length - 1
        //keep generating new numbers until gridUsed[n] is false
        //YOUR CODE HERE:

        gridUsed[n] = true;

        //return a copy of grids[n]
        int[][] grid = new int[9][9];
        for(int r = 0; r < 9; r++) {
            for(int c = 0; c < 9; c++) {
                grid[r][c] = grids[n][r][c];
            }
        }
        return grid;
    }

    public int[][] testGrid() {
        //return a nearly-solved puzzle for testing the game
        int o = 0;
        return new int[][] {
        { 8, 2, 7, 1, 5, 4, 3, 9, 6 },
        { 9, 6, 5, 3, 2, 7, 1, 4, 8 },
        { 3, 4, o, 6, 8, 9, o, 5, 2 },
        { 5, 9, 3, 4, 6, 8, 2, 7, 1 },
        { 4, 7, 2, 5, o, 3, 6, 8, 9 },
        { 6, 1, 8, 9, 7, 2, 4, 3, 5 },
        { 7, 8, o, 2, 3, 5, o, 1, 4 },
        { 1, 5, 4, 7, 9, 6, 8, 2, 3 },
        { 2, 3, 9, 8, 4, 1, 5, 6, 7 }
        };
    }
}