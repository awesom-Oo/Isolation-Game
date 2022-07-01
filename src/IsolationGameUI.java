import processing.core.PApplet;


public class IsolationGameUI extends PApplet {

    GameState currentState = GameState.STARTSCREEN;
    private GameBoard gameBoard;
    private boolean whiteTurn;
    private int currentFrame = 0;

    public static void main(String[] args) {
        PApplet.runSketch(new String[]{""}, new IsolationGameUI());
    }

    @Override
    public void settings() {
        size(540, 669);
    }

    @Override
    public void setup() {
        Resources.loadRessources(this);
        this.gameBoard = new GameBoard();
    }

    @Override
    public void draw() {
        switch (currentState) {
            case STARTSCREEN -> gameInitScreen();
            case INSTRUCTION -> gameInstructionScreen();
            case GAME -> gamePlayScreen();
            case GAMEOVER -> gameOverScreen();
        }
    }

    public void gameInitScreen() {
        frameRate(9.0f);
        currentFrame = (currentFrame+1) % Resources.numOfFrames;
        int offset = 0;
        for (int x = -100; x < width; x += Resources.menuScreenBackground[0].width) {
            background(Resources.menuScreenBackground[(currentFrame+offset) % Resources.numOfFrames]);
            offset+=2;
        }
        textFont(Resources.beteFont);
        textAlign(CENTER);
        fill(252, 241, 201);
        textSize(40);
        text("Don´t get stranded", width/2, height-550);
        fill(0, 95, 177);
        textSize(25);
        text("Click to start", width/2, height-60);
        textSize(15);
        text("Game Instructions", width/2, height-30);

    }

    public void gameInstructionScreen() {
        background(Resources.islandScreen);
        image(Resources.treasureMap, 10 , height-550, 200F*2.6F, 166*2.8F);
        textFont(Resources.pirateFont);
        textAlign(CENTER);
        fill(0);
        textSize(25);
        text("Game Instruction", width/2, height-460);
        textSize(18);
        text("The first player chooses a cell to start\n" +
                "Each player takes turns\nmoving their player to a new cell\n" +
                "A player can move to any cell with a clear path\n" +
                "up down left right or diagonal\n" +
                "as long as the path is not blocked by a previously visited cell\n" +
                "or by the other player\n" +
                "If a player is unable to make any further move\n" +
                "the opponent wins\n" +
                "Thus the goal of the game is\n" +
                "to be the last player with a remaining move available", width/2, height-420);
        textFont(Resources.beteFont);
        textAlign(CENTER);
        fill(0, 95, 177);
        textSize(25);
        text("Go Back", width/2, height-40);

    }

    public void gamePlayScreen() {
        int xOffset = 90, yOffset = 171;
        background(Resources.islandScreen);
        image(Resources.treasureMap, 10, height-550, 200F*2.6F, 166F*2.8F);
        boolean isWhite = true;
        stroke(color(194,145,78));
        for (int x = 0; x < 350; x+=45) {
            for (int y = 0; y < 350; y+=45) {
                if(isWhite)
                    fill(color(194,145,78));
                else
                    fill(color(229,189,128));
                rect(x + xOffset, y + yOffset, 40, 40, 12);
                isWhite = !isWhite;
            }
            isWhite = !isWhite;
        }

        gameBoard.draw(this);
    }

    public void gameOverScreen() {

    }

    @Override
    public void mousePressed() {
        System.out.println(mouseX);
        System.out.println(mouseY);

        switch (currentState) {
            case STARTSCREEN -> {
                if (mouseX > 152 && mouseX < 389 && mouseY > 578 && mouseY < 616) {
                    startGameScreen();
                } else if (mouseX > 189 && mouseX < 354 && mouseY > 626 && mouseY < 642) {
                    startInstructionScreen();
                }
            }
            case INSTRUCTION -> {
                if (mouseX > 205 && mouseX < 337 && mouseY > 612 && mouseY < 640) {
                    startInitScreen();
                }
            }
            case GAME -> {
                int posX = (mouseX - 90)/45;
                int posY = (mouseY - 171)/45;
                gameBoard.executeMove(whiteTurn, posX, posY);
                whiteTurn = !whiteTurn;
            }
            case GAMEOVER -> startInitScreen();
        }
    }

    private void startGameScreen() {
        currentState = GameState.GAME;
    }

    private void startInstructionScreen() {
        currentState = GameState.INSTRUCTION;
    }

    private void startInitScreen() {
        currentState = GameState.STARTSCREEN;
    }


}

