package impl.logic;

import impl.GameService;
import impl.model.PixelColor;

import java.util.*;
import java.awt.Point;

public class SnakeGameLogic implements GameLogic {

    // Направления движения змейки
    private enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    // Текущее направление
    private Direction direction = Direction.RIGHT;
    // Очередь для тела змейки (голова — в конце списка)
    private LinkedList<Point> snake = new LinkedList<>();
    // Текущая позиция еды
    private Point food = null;
    // Флаг состояния игры
    private boolean gameOver = false;

    // Цвета
    private final PixelColor COLOR_BG = new PixelColor(0, 0, 0);
    private final PixelColor COLOR_SNAKE = new PixelColor(0, 200, 0);
    private final PixelColor COLOR_FOOD = new PixelColor(255, 50, 50);
    private final PixelColor COLOR_OVER = new PixelColor(100, 100, 100);

    private int width = GameService.PIXELS_COUNT_W;
    private int height = GameService.PIXELS_COUNT_H;

    private Random rand = new Random();

    @Override
    public void init() {
        // Начальное положение змейки — по центру и 3 клетки длинной
        snake.clear();
        int centerY = height / 2;
        int centerX = width / 2;
        for (int i = 2; i >= 0; i--) {
            snake.add(new Point(centerX - i, centerY));
        }
        direction = Direction.RIGHT;
        gameOver = false;
        spawnFood();
    }

    // Основной игровой шаг — логика + отрисовка
    @Override
    public void loop(PixelColor[][] pixels) {
        // Очистить поле
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                pixels[y][x] = COLOR_BG;

        // Не двигаем змейку после смерти, отрисовываем "тело"
        if (gameOver) {
            for (Point p : snake)
                if (onField(p)) pixels[p.y][p.x] = COLOR_OVER;
            if (food != null && onField(food))
                pixels[food.y][food.x] = COLOR_FOOD; // еду оставляем видимой
            return;
        }

        // Движение
        Point head = snake.getLast();
        Point newHead = new Point(head);
        switch (direction) {
            case UP:    newHead.y -= 1; break;
            case DOWN:  newHead.y += 1; break;
            case LEFT:  newHead.x -= 1; break;
            case RIGHT: newHead.x += 1; break;
        }
        // Проверка на проигрыш (стенки)
        if (newHead.x < 0 || newHead.x >= width || newHead.y < 0 || newHead.y >= height ||
                snake.contains(newHead)) {
            gameOver = true;
            GameService.finishGame("Игра завершена", "Вы набрали " + snake.size() + " очков", true);
        } else {
            snake.addLast(newHead); // добавили новую голову

            // Съела еду?
            if (food != null && newHead.equals(food)) {
                spawnFood();
            } else {
                snake.removeFirst(); // обычное движение без роста
            }
        }

        // Отрисовка
        for (Point p : snake)
            if (onField(p)) pixels[p.y][p.x] = COLOR_SNAKE;
        if (food != null && onField(food))
            pixels[food.y][food.x] = COLOR_FOOD;
    }

    // Смена направления по клавишам — стрелки: 37-40, WASD: 65/68/87/83, не даём развернуться в себя
    @Override
    public void handleKeyPress(int keyCode) {
        Direction old = direction;
        switch (keyCode) {
            case 37: case 65: // left arrow, 'A'
                if (old != Direction.RIGHT) direction = Direction.LEFT;
                break;
            case 38: case 87: // up arrow, 'W'
                if (old != Direction.DOWN) direction = Direction.UP;
                break;
            case 39: case 68: // right arrow, 'D'
                if (old != Direction.LEFT) direction = Direction.RIGHT;
                break;
            case 40: case 83: // down arrow, 'S'
                if (old != Direction.UP) direction = Direction.DOWN;
                break;
            case 10: // Enter — рестарт после смерти
                if (gameOver) init();
                break;
        }
    }

    // Генерация еды
    private void spawnFood() {
        Set<Point> occupied = new HashSet<>(snake);
        List<Point> empty = new ArrayList<>();
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                if (!occupied.contains(new Point(x, y)))
                    empty.add(new Point(x, y));
        if (empty.isEmpty()) {
            food = null; // выиграл!
            gameOver = true;
            return;
        }
        food = empty.get(rand.nextInt(empty.size()));
    }

    // Проверить попадание в поле
    private boolean onField(Point p) {
        return p.x >= 0 && p.x < width && p.y >= 0 && p.y < height;
    }
}