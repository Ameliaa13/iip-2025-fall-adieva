package impl.logic;

import java.util.Arrays;
import java.util.Random;

import impl.GameService;
import impl.model.PixelColor;

public class RaceLogic implements GameLogic {


    private int width = GameService.PIXELS_COUNT_W;
    private int height = GameService.PIXELS_COUNT_H;
    private int[][] field;
    private int space;
    private int speedCoefficient;
    private int totalPlatforms;

    private int curPlatformY = 0;
    private int platformSize = width / GameService.PIXEL_SIZE;
    private int holeInPlatformStarts;

    private int curX = width / 2;
    private int curY = height / 2;

    private boolean gameOver;

    private int[][] me = null;
    private PixelColor meColor = new PixelColor(26,142,219);
    private PixelColor enemyColor = new PixelColor(219,26,165);
    private PixelColor bgColor = new PixelColor(35,44,51);
    private long platformsPassed = 1;
    private long loopCounter = 0;
    private int[] platform = new int[platformSize];
    private boolean collision = false;
    private boolean pobeda = false;


    private Random rnd = new Random();


    @Override
    public void init() {
        gameOver = false;
        pobeda = false;

        field = new int[height][width];
        for (int i = 0; i < platformSize; i++) {
            platform[i] = 1;
        }
        for (int[] row : field) Arrays.fill(row, 0);

        curX = width / 2;
        curY = height / 2;
        curPlatformY = 0;
        holeInPlatformStarts = 10;
        space = 10;
        speedCoefficient = 2;
        platformsPassed = 1;
        totalPlatforms = 0;

    }

    @Override
    public void loop(PixelColor[][] pixels) {

        for (int y=0; y<height; ++y) {
            for (int x=0; x<width; ++x) {
                pixels[y][x] = bgColor;
            }
        }
        System.out.println(totalPlatforms);
        if (!gameOver && !pobeda) {
            if (loopCounter % speedCoefficient == 0) {
                movePlatform();
            }
        }

        if(platformsPassed % 10 == 0) {
            changeDifficulty();
            platformsPassed++;
        }

        if (totalPlatforms == 40) {
            pobeda = true;
        }


        
        pixels[curY][curX] = meColor;
        field[curY][curX] = 2;


        for (int i = 0; i < width; i++) {
            field[curPlatformY][i] = 1;
            pixels[curPlatformY][i] = enemyColor;
        }

        for (int i = holeInPlatformStarts; i < holeInPlatformStarts+space; i++) {
            field[curPlatformY][i] = 0;
            pixels[curPlatformY][i] = bgColor;
        }

        if (field[curY][curX] == 1) {
            collision = true;
        }

        if (collision) {
            gameOver = true;
            collision = false;
        }

        if (gameOver)
            for (int y=0; y<height; ++y)
                for (int x=0; x<width; ++x)
                    if (pixels[y][x].getGreenComponent() == 26 || pixels[y][x].getGreenComponent() == 142)
                        pixels[y][x]=new PixelColor(80, 80, 80);

        if (pobeda) {
            for (int y=0; y<height; ++y)
                for (int x=0; x<width; ++x)
                    if (pixels[y][x].getGreenComponent() == 26 || pixels[y][x].getGreenComponent() == 142)
                        pixels[y][x]=new PixelColor(0, 255, 0);
        }

        
        loopCounter++;

    }

    @Override
    public void handleKeyPress(int keyCode) {
        if (gameOver || pobeda) {
            if (keyCode == 10) init();
            return;
        }
        switch (keyCode) {
            case 37: // Лево
            case 65: moveMe(-1,0); break;
            case 39: // Право
            case 68: moveMe(1,0); break;
            case 40: // Вниз
            case 83: moveMe(0,1); break;
            case 38: // Вверх
            case 87: moveMe(0, -1); break;
        }
    }

    private void moveMe(int x, int y) {
        if (inField(curX+x, curY+y)) {
            field[curY][curX] = 0;
            curX+=x;
            curY+=y;

        }
        
    }

    private boolean inField(int x, int y) {
        return x>=0&&x<width&&y>=0&&y<height;
    }

    private void changeDifficulty() {
        space--;
        speedCoefficient--;
        if (speedCoefficient == 0) speedCoefficient = 1;
        if (space == 0) space = 1;
    }

    private void movePlatform() {
        if (inField(0, curPlatformY+1))
            curPlatformY++;
        if (curPlatformY+1 == height) {
            curPlatformY = 0;
            platformsPassed++;
            totalPlatforms++;
            holeInPlatformStarts = rnd.nextInt(0, 23-1-space);
        }
    }
}
    

