import bridges.base.GameGrid;
import bridges.base.NamedColor;
import bridges.base.NamedSymbol;
import bridges.connect.Bridges;
import bridges.connect.SocketConnection;

/**
 * Extend this class to create a game.
 */
public abstract class Game {
    private final GameGrid grid;
    private final Bridges bridges;
    private final SocketConnection sock;
    private final Keyboard keyboard;
    private boolean firstTime;
    private boolean gameRunning;
    private long lastFrame;

    /**
     * @param assignmentNumber
     * @param username
     * @param apiKey
     * @param rows  number of rows in the game grid
     * @param cols  number of columns in the game grid
     */
    protected Game(int assignmentNumber, String username, String apiKey, int rows, int cols) {
        grid = new GameGrid(rows, cols);
        grid.setEncoding("rle");
        bridges = new Bridges(assignmentNumber, username, apiKey);
        bridges.setServer("games");

        sock = new SocketConnection(bridges);
        sock.setupConnection();
        keyboard = new Keyboard();
        sock.addListener(keyboard);

        firstTime = true;
        gameRunning = true;
        lastFrame = System.currentTimeMillis();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                Thread.sleep(200);
                System.out.println("Shutting down ...");
                sock.close();
            }
            catch(InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }));
    }

    /**
     * Child classes must implement this method.
     * It is called automatically at the start of the game.
     */
    protected abstract void initialize();

    /**
     * Child classes must implement this method.
     * It is called automatically once per frame (30 times a second).
     */
    protected abstract void gameLoop();

    /**
     * Set the project title.
     * @param title
     */
    protected void setTitle(String title) {
        bridges.setTitle(title);
    }

    /**
     * Set the project description.
     * @param desc
     */
    protected void setDescription(String desc) {
        bridges.setDescription(desc);
    }

    /**
     * Set the background color of a cell.
     * @param row
     * @param col
     * @param c  background color
     */
    protected void setBGColor(int row, int col, NamedColor c) {
        grid.setBGColor(row, col, c);
    }

    /**
     * Get the background color of a cell
     * @param row
     * @param col
     * @return background color
     */
    protected NamedColor getBGColor(int row, int col) {
        return grid.getBGColor(row, col);
    }

    /**
     * Draw a symbol on the grid.
     * @param row
     * @param col
     * @param s  symbol
     * @param c  color of symbol
     */
    protected void drawSymbol(int row, int col, NamedSymbol s, NamedColor c) {
        grid.drawSymbol(row, col, s, c);
    }

    /**
     * Get the symbol inside of a cell.
     * @param row
     * @param col
     * @return symbol (<code>NamedSymbol.none</code> if the cell is blank)
     */
    protected NamedSymbol getSymbol(int row, int col) {
        return grid.getSymbol(row, col);
    }

    /**
     * Get the color of the symbol inside of a cell.
     * @param row
     * @param col
     * @return color of symbol
     */
    protected NamedColor getSymbolColor(int row, int col) {
        return grid.getSymbolColor(row, col);
    }

    /**
     * Test if a key is pressed.
     * @param key  for example, "1", "W", "ArrowUp", or " "
     * @return whether the key is pressed
     */
    protected boolean keyDown(String key) {
        return keyboard.keyDown(key);
    }

    /**
     * Test if a key was pressed within the last frame.
     * @param key  for example, "1", "W", "ArrowUp", or " "
     * @return whether the key was just pressed
     */
    protected boolean keyPress(String key) {
        return keyboard.keyPress(key);
    }

    private void render() {
        if(firstTime) {
            firstTime = false;
            bridges.setDataStructure(grid);
            try {
                bridges.visualize();


            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }

        String gridState = grid.getDataStructureRepresentation();
        sock.sendData('{' + gridState);
    }

    private void sleepTimer(long millis) {
        try {
            Thread.sleep(millis);
        }    
        catch (InterruptedException e) {
            gameRunning = false;
        }
    }

    private void controlFrameRate() {
        double FPS = 30;
        long currentTime = System.currentTimeMillis();
        long nextFrame = lastFrame + (int) (1 / FPS * 1000);
        long waitTime = nextFrame - currentTime;

        if(waitTime > 0) {
            sleepTimer(waitTime);
        }
        lastFrame = System.currentTimeMillis();
    }

    /**
     * Start the game.
     */
    public void start() {
        sleepTimer(1000);
        render();
        initialize();

        while (gameRunning) {
            gameLoop();
            keyboard.update();
            render();
            controlFrameRate();

        }


        sock.close();
    }
}
