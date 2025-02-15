package Entity;

public class Vector2D
{
    float xDelta, yDelta;

    public Vector2D(float xDelta, float yDelta){
        this.xDelta = xDelta;
        this.yDelta = yDelta;
    }

    public void Set(float xDelta, float yDelta)
    {
        this.xDelta = xDelta;
        this.yDelta = yDelta;
    }

    public float getX(){
        return xDelta;
    }

    public float getY(){
        return yDelta;
    }

    public void MoveXDelta(float value)
    {
        this.xDelta += value;
    }

    public void MoveYDelta(float value)
    {
        this.yDelta += value;
    }
}

