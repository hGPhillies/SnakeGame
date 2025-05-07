import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener
{
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    static final int DELAY = 75;

    private Snake snake;
    private Food food;
    private int applesEaten = 0;
    private boolean running = false;
    private Timer timer;

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        startGame();
    }

    public void startGame()
    {
        snake = new Snake(GAME_UNITS, 6);
        food = new Food();
        food.generate(SCREEN_WIDTH, SCREEN_HEIGHT, UNIT_SIZE);
        this.addKeyListener(new MovementHandler(snake));
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g)
    {
        if (running)
        {
            // Draw grid (optional)
            for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++)
            {
                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
                g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
            }

            // Drawing Apple
            g.setColor(Color.RED);
            g.fillOval(food.getX(), food.getY(), UNIT_SIZE, UNIT_SIZE);

            // Drawing snake
            for (int i = 0; i < snake.getBodyParts(); i++)
            {
                if (i == 0)
                {
                    g.setColor(Color.GREEN);
                } else
                {
                    g.setColor(Color.YELLOW);
                }
                g.fillRect(snake.getX(i), snake.getY(i), UNIT_SIZE, UNIT_SIZE);
            }

            // Displaz Score
            g.setColor(Color.RED);
            g.setFont(new Font("TimesRoman", Font.BOLD, 30));
            FontMetrics fm = g.getFontMetrics();
            g.drawString("SCORE: " + applesEaten, (SCREEN_WIDTH - fm.stringWidth("SCORE: " + applesEaten)) / 2, g.getFont().getSize());
        }
        else
        {
            gameOver(g);
        }
    }

    public void checkApple()
    {
        if (snake.getX(0) == food.getX() && snake.getY(0) == food.getY())
        {
            snake.grow();
            applesEaten++;
            food.generate(SCREEN_WIDTH, SCREEN_HEIGHT, UNIT_SIZE);
        }
    }

    public void checkCollisions()
    {
        if (snake.checkCollision(SCREEN_WIDTH, SCREEN_HEIGHT))
        {
            running = false;
            timer.stop();
        }
    }

    public void gameOver(Graphics g)
    {
        // Game over text
        g.setColor(Color.RED);
        g.setFont(new Font("TimesRoman", Font.BOLD, 100));
        FontMetrics fm = g.getFontMetrics();
        g.drawString("GAME OVER", (SCREEN_WIDTH - fm.stringWidth("GAME OVER")) / 2, SCREEN_HEIGHT / 2);

        // Final score
        g.setFont(new Font("TimesRoman", Font.BOLD, 30));
        g.drawString("SCORE: " + applesEaten, (SCREEN_WIDTH - fm.stringWidth("SCORE: " + applesEaten)) / 2, g.getFont().getSize());
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (running)
        {
            snake.move(UNIT_SIZE);
            checkApple();
            checkCollisions();
        }
        repaint();
    }
}
