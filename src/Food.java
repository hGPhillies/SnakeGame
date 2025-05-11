import java.util.Random;
//
public class Food
{
    private int x;
    private int y;
    private final Random random;

    public Food()
    {
        random = new Random();
    }

    public void generate(int screenWidth, int screenHeight, int unitSize)
    {
        x = random.nextInt(screenWidth / unitSize) * unitSize;
        y = random.nextInt(screenHeight / unitSize) * unitSize;
    }

    public int getX() { return x; }
    public int getY() { return y; }
}
