import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MovementHandler extends KeyAdapter
{
    private final Snake snake;

    public MovementHandler(Snake snake)
    {
        this.snake = snake;
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        char currentDir = snake.getDirection();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> {
                if (currentDir != 'R') snake.setDirection('L');
            }
            case KeyEvent.VK_RIGHT -> {
                if (currentDir != 'L') snake.setDirection('R');
            }
            case KeyEvent.VK_UP -> {
                if (currentDir != 'D') snake.setDirection('U');
            }
            case KeyEvent.VK_DOWN -> {
                if (currentDir != 'U') snake.setDirection('D');
            }
        }
    }
}
