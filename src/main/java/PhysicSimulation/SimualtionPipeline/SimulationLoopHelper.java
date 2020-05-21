package PhysicSimulation.SimualtionPipeline;

public class SimulationLoopHelper
{
    long firstFrame;
    int frames;
    long currentFrame;
    int fps = 0;
    long amountOfFrames = 0;

    public int getFps()
    {
        return fps;
    }

    public long getAmountOfFrames()
    {
        return amountOfFrames;
    }

    // This Method calculates the frames per second
    public void calculateFPS()
    {
        frames++;
        currentFrame = System.currentTimeMillis();
        if(currentFrame > firstFrame + 1000)
        {
            firstFrame = currentFrame;
            fps = frames;
            frames = 0;
        }
    }
    // This Method counts the frames
    public void calculateAmountOfFrames()
    {
        amountOfFrames++;
    }
}
