package impl.logic;

import impl.GameService;
import impl.model.PixelColor;

import java.awt.Point;
import java.util.*;

public class TetrisGameLogic implements GameLogic {
    // Игровое поле (логика), 0 — пусто, >0 — заполнено цветом фигуры
    private int[][] field;
    private int width = GameService.PIXELS_COUNT_W;
    private int height = GameService.PIXELS_COUNT_H;

    // Фигуры (кубики), каждая — 4 точки
    private static final int[][][] TETROMINOS = {
            // I
            {{0,1},{1,1},{2,1},{3,1}},
            // O
            {{1,0},{2,0},{1,1},{2,1}},
            // T
            {{1,0},{0,1},{1,1},{2,1}},
            // L
            {{0,0},{0,1},{1,1},{2,1}},
            // J
            {{2,0},{0,1},{1,1},{2,1}},
            // S
            {{1,0},{2,0},{0,1},{1,1}},
            // Z
            {{0,0},{1,0},{1,1},{2,1}}
    };
    private static final PixelColor[] COLORS = {
            new PixelColor(0,0,0), // фон
            new PixelColor(200,50,200), // I
            new PixelColor(220,200,0),  // O
            new PixelColor(128,128,255),// T
            new PixelColor(255,150,0),  // L
            new PixelColor(0,200,255),  // J
            new PixelColor(0,220,110),  // S
            new PixelColor(255,60,60)   // Z
    };

    // Активная текущая фигура
    private int[][] curFigure = null;
    private int curType = 1;
    private int curX = 3, curY = 0; // позиция левого верхнего блока фигуры
    private int rot = 0;
    private boolean gameOver = false;
    private Random rnd = new Random();

    @Override
    public void init() {
        field = new int[height][width];
        for (int[] row : field) Arrays.fill(row, 0);
        nextFigure();
        gameOver = false;
    }

    @Override
    public void loop(PixelColor[][] pixels) {
        // "Гравитация" — падение фигуры автоматом
        if (!gameOver) {
            if (!moveFigure(0, 1)) {
                // Ставим фигуру
                placeFigure();
                clearLines();
                nextFigure();
                if (!canPlace(curFigure, curX, curY))
                    gameOver = true;
            }
        }

        // Отрисовка поля
        for (int y=0; y<height; ++y)
            for (int x=0; x<width; ++x)
                pixels[y][x] = COLORS[field[y][x]];

        // Отрисовать активную фигуру
        if (!gameOver && curFigure!=null) {
            for (int[] block : curFigure) {
                int bx = curX + block[0];
                int by = curY + block[1];
                if (inField(bx,by))
                    pixels[by][bx]=COLORS[curType];
            }
        }
        // Опционально — при gameOver сделать поле серым
        if (gameOver)
            for (int y=0; y<height; ++y)
                for (int x=0; x<width; ++x)
                    if (field[y][x]>0)
                        pixels[y][x]=new PixelColor(80, 80, 80);
    }

    @Override
    public void handleKeyPress(int keyCode) {
        if (gameOver) {
            if (keyCode == 10) init(); // ENTER — рестарт
            return;
        }
        switch (keyCode) {
            case 37: // Left
            case 65: moveFigure(-1,0); break;
            case 39: // Right
            case 68: moveFigure(1,0); break;
            case 40: // Down/accelerate
            case 83: moveFigure(0,1); break;
            case 38: // Up — rotate
            case 87: rotateFigure(); break;
        }
    }

    // Перемещает фигуру, если возможно
    private boolean moveFigure(int dx, int dy) {
        if (canPlace(curFigure, curX+dx, curY+dy)) {
            curX += dx;
            curY += dy;
            return true;
        }
        return false;
    }

    // Поворот фигуры на 90°
    private void rotateFigure() {
        int[][] rotated = new int[4][2];
        for (int i=0;i<4;i++) {
            rotated[i][0]=-curFigure[i][1];
            rotated[i][1]= curFigure[i][0];
        }
        if (canPlace(rotated, curX, curY))
            curFigure = rotated;
    }

    // Ставим на поле фигуру как заполненные клетки
    private void placeFigure() {
        for (int[] b : curFigure) {
            int x=curX+b[0], y=curY+b[1];
            if (inField(x,y)) field[y][x]=curType;
        }
    }

    // Появляется новая фигура сверху
    private void nextFigure() {
        curType = 1 + rnd.nextInt(TETROMINOS.length);
        curFigure = deepClone(TETROMINOS[curType-1]);
        curX = width/2 -2;
        curY = 0;
    }

    // Проверка, можно ли поместить фигуру
    private boolean canPlace(int[][] form, int x0, int y0) {
        for (int[] b : form) {
            int x=x0+b[0], y=y0+b[1];
            if (!inField(x,y) || field[y][x]>0)
                return false;
        }
        return true;
    }

    // Глубокая копия фигуры (чтобы не ломать эталоны)
    private int[][] deepClone(int[][] fig) {
        int[][] c = new int[fig.length][];
        for (int i=0;i<fig.length;++i)
            c[i]=fig[i].clone();
        return c;
    }

    // Стереть заполненные линии
    private void clearLines() {
        for (int y=height-1; y>=0; --y) {
            boolean full = true;
            for (int x=0;x<width;++x)
                if (field[y][x]==0) {full=false; break;}
            if (full) {
                // сместить всё выше вниз
                for (int ny=y; ny>0; --ny)
                    field[ny]=Arrays.copyOf(field[ny-1], width);
                Arrays.fill(field[0], 0);
                y++; // проверяем линию снова
            }
        }
    }

    private boolean inField(int x,int y) {
        return x>=0&&x<width&&y>=0&&y<height;
    }
}