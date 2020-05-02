package PhysicSimulation.Physics;

import PhysicSimulation.Objects.Manager.AssetData;

public class MovementWithAngle
{
    double g = 9.81;
    // The velocity
    double Nvelocity = 0;
    // The new position of the object
    double position = 0;
    // The Time
    double time = 0;
    // The total count time
    double totalTime = 0;
    // The delta time
    double dt = 0;
    // Acceleration
    double accelerationX = 0;
    double velocityX = 10;
    double velocityY = 0;
    double positionX = 0;
    double positionY = 0;
    double velocity = 0;
    double accelerationY = 0;
    double oldPositionX = 0;
    double oldPositionY = 0;
    boolean start = false;
    // Inits the last time for calculation
    double lastTime = System.nanoTime()*1E-9;
  public void calculateMotion(AssetData physicAsset, AssetData anglePlatform)
  {
      oldPositionX = physicAsset.getShape().getTranslateX();
      oldPositionY = physicAsset.getShape().getTranslateY();


      time = System.nanoTime()*1E-9;
      dt =  time - lastTime;
      lastTime = time;

      totalTime = totalTime + dt;

      // X
      accelerationX += velocityX/dt;
      // Y
      accelerationY += g/dt;

      // calculate the new velocity
      velocityX += accelerationX*dt;
      velocityY += accelerationY*dt;

      //positionX += 0.5*velocityX*dt;
      //positionY += 0.5*velocityY*dt;

      positionX += 0.5*velocityX*dt;
      positionY += 0.5*velocityY*dt;

      physicAsset.getShape().setTranslateX(positionX);
      physicAsset.getShape().setTranslateY(positionY);
      System.out.println(positionX+" Y: "+positionY+" vX= "+velocityX+" vY= "+velocityY);


  }
}
