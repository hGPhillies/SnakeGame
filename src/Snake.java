public class Snake {
    private final int[] x;
    private final int[] y;
    private int bodyParts;
    private char direction;

    public Snake(int maxUnits, int initialLength)
    {
        x = new int[maxUnits];
        y = new int[maxUnits];
        bodyParts = initialLength;
        direction = 'R';
    }

    public void move(int unitSize)
    {
        for (int i = bodyParts; i > 0; i--)
        {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        switch (direction)
        {
            case 'U' -> y[0] -= unitSize;
            case 'D' -> y[0] += unitSize;
            case 'L' -> x[0] -= unitSize;
            case 'R' -> x[0] += unitSize;
        }
    }
//
    public void grow()
    {
        bodyParts++;
    }

    public boolean checkCollision(int screenWidth, int screenHeight)
    {
        //Check collision with body
        for (int i = bodyParts; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) return true;
        }
        //heck wall collisions
        return x[0] < 0 || x[0] >= screenWidth || y[0] < 0 || y[0] >= screenHeight;
    }

    public int getX(int index) { return x[index]; }
    public int getY(int index) { return y[index]; }
    public int getBodyParts() { return bodyParts; }
    public char getDirection() { return direction; }
    public void setDirection(char dir) { direction = dir; }
}
