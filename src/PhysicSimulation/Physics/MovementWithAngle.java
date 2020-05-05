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
    double velocityX = 1;
    double velocityY = 10;
    double positionX = 0;
    double positionY = 0;
    double velocity = 0;
    double accelerationY = 0;
    double oldPositionX = 10;
    double oldPositionY = 10;
    double FG = 0;
    double FA = 0;
    double FN = 0;
    double FR = 0;
    double u = 0.03;
    double F = 0;
    double FX = 0;
    double FY = 0;
    double FGH = 0;
    double FGN = 0;
    double vXOld = 0;
    double vYOld = 0;
    boolean start = false;
    int counter = 0;
    // Inits the last time for calculation
    double lastTime = System.nanoTime()*1E-9;
  public void calculateMotion(AssetData physicAsset, AssetData anglePlatform)
  {

      if (counter == 0)
      {
          intiMovement(physicAsset);
          counter = 1;
      }
      //physicAsset.getShape().setRotate(40);

      time = System.nanoTime()*1E-9;
      dt =  time - lastTime;
      lastTime = time;
      totalTime = totalTime + dt;

      // Gewichtskraft FA berechenen
      // Formel FG = m*g
      FG = 10*g;

      FGH = FG*Math.sin(10);
      FGN = FG*Math.cos(10);




      // Hangantriebskraft FA berechnen
      // Formel FA = m*g*sin(a)
      FA = 10*g*Math.sin(40);

      // Normalkraftkomponente FN berechenen
      // Formel FN = m*g*cos(a)
      FN = 10*g*Math.cos(40);

      // Haftreibung FR berechnen
      // Formel FR' = μ'*FN
      FR = u*FN;

      // Beschleunigung durch F=m*a berechenen
      // F = FA-FR
      F = FA-FR;


      accelerationX = g*Math.sin(Math.toRadians(66));
      //accelerationX = g*Math.sin(30);
      accelerationY = g*Math.cos(Math.toRadians(66));
      //accelerationY = g*Math.cos(Math.toRadians(30));

      //accelerationX = g*Math.sin(20);
      //accelerationY = FGY/10;





      //accelerationX = g*Math.sin(20);
      //accelerationX = F/physicAsset.getMass();
      //accelerationX += g*Math.sin(20);
      //accelerationX += (Math.sin(20)*FG)/physicAsset.getMass();

      //accelerationX = g*Math.sin(20);

      //accelerationY = g*Math.cos(20);

      //accelerationY = g*Math.cos(20);

      //accelerationY += (Math.cos(20)*FG)/physicAsset.getMass();
      //Geschwindigkeit berechnen durch v = a*t


      velocityX += accelerationX*dt;
      velocityY += accelerationY*dt;

      //velocityX += accelerationX*dt;
      //velocityY += accelerationY*dt;

      //physicAsset.setVelocity(velocityY);

      //Neue Position berechnen durch s = 0.5*a*t
      //positionX += (1/2)*(5/7)*g*Math.sin(0)*dt;
      //positionY += (1/2)*(5/7)*g*Math.cos(0)*dt;
      positionX = (oldPositionX+vXOld*dt)+(0.5*accelerationX*dt);
      //positionX += 0.5*velocityX*dt;
      positionY = (oldPositionY+vYOld*dt)+(0.5*accelerationY*dt);
      //positionY -= 1;
      //positionY += 0.5*velocityY*dt;
      vXOld = velocityX;
      vYOld = velocityY;
      oldPositionX = positionX;
      oldPositionY = positionY;

      physicAsset.getShape().setTranslateX(positionX);
      physicAsset.getShape().setTranslateY(positionY);
      physicAsset.getShape().setRotate(positionY);
      System.out.println("X: "+positionX+" Y: "+positionY+" vX= "+velocityX+" vY= "+velocityY+"Delta Time: "+dt+" Last Time:"+lastTime+" Vergangene Zeit: "+totalTime);





   /*

      // X
      accelerationX += velocityX/dt;
      // Y
      accelerationY -= g/dt;

      // calculate the new velocity
      velocityX += accelerationX*dt;
      velocityY -= accelerationY*dt;

      //positionX += 0.5*velocityX*dt;
      //positionY += 0.5*velocityY*dt;

      positionX += 0.5*velocityX*dt;
      positionY += 0.5*velocityY;

*
    */



  }

  public double calcAngleX()
  {
      double angle = 20;
      System.out.println(angle);
      return angle;
  }
  public double calcAngleY()
  {
      double angle = Math.atan(0);
      System.out.println(angle);
      return angle;
  }
  public void intiMovement(AssetData assetData)
  {
      oldPositionX = assetData.getShape().getTranslateX();
      oldPositionY = assetData.getShape().getTranslateY();
      vXOld = 0;
      vYOld = 0;
  }
  public void resetAngleMovement()
  {
      velocityX = 0;
      velocityY = 0;
      totalTime = 0;
      time = System.nanoTime()*1E-9;
      dt = 0;
      oldPositionX = 0;
      oldPositionY = 0;
      accelerationX = 0;
      accelerationY = 0;
      counter = 0;
      vXOld = 0;
      vYOld = 0;
  }
}
