package PhysicSimulation.Physics;

import PhysicSimulation.Objects.Manager.AssetData;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

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
    double lastTime = System.nanoTime()*1E-9;
    // Acceleration
    double accelerationX = 0;
    double velocityX = 1;
    double velocityY = 10;
    double positionX = 0;
    double positionY = 0;
    double velocity = 0;
    double accelerationY = 0;
    double oldPositionX = 50;
    double oldPositionY = 198;
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
    double pi = 3.14159265;
    double cosAngle = 0;
    double sinAngle = 0;
    double weg = 0;
    double centerX = 0;
    double centerY = 0;



    ArrayList <Circle> mPoints = new ArrayList<>();

    // Debug

    // Test with parameters
    double friction = 0.04;
    double weight = 10;
    double angle = 30;

    double a = 0;
    double norm_Force = 0;
    double fric_Force = 0;
    double b = 0;
    double weight_inc = 0;
    double theata = 0;
    double mag_ForceX = 0;
    double mag_ForceY = 0;

    double counter1 = 0;

    double t = 0;
    double t0 = System.nanoTime()*1E-9;
    // Inits the last time for calculation
    //double lastTime = System.nanoTime()*1E-9;
  public void calculateMotion(AssetData physicAsset, AssetData anglePlatform)
  {

      if (counter == 0)
      {
          if(physicAsset.isMoved())
          {
              //vXOld = physicAsset.getVelocityX();
              //vYOld = physicAsset.getVelocityY();
              Bounds shape = physicAsset.getShape().getBoundsInLocal();
//              Circle circle = (Circle) physicAsset.getShape();
              oldPositionX = 10;
              //oldPositionY = physicAsset.getStartPositionY()+physicAsset.getCurrentPositionY();
              oldPositionY = 40;
              vXOld = 0;
              vYOld = 0;
              //oldPositionX = 0;
              //oldPositionY = 0;
          }
          else
              if(!physicAsset.isMoved())
              {
                  //intiMovement(physicAsset);
              }
              counter = 1;
      }
      //physicAsset.getShape().setRotate(40);
      time = System.nanoTime()*1E-9;
      dt =  time - lastTime;
      lastTime = time;
      totalTime = totalTime + dt;


      // Debug section

      /*
      // Compute Normal force
      a = Math.toRadians(angle);
      norm_Force = weight*Math.cos(a);

      // Compute frictional Force
      fric_Force = norm_Force*friction;

      // Compute Weight in direction of incline
      b = Math.toRadians(90-angle);
      weight_inc = weight*Math.cos(b);

      System.out.println("The frictional force is: "+fric_Force);
      System.out.println("The weight component down the incline is "+ weight_inc);

      if(fric_Force >= weight_inc)
      {
          System.out.println("The block will not move!");
      }
      else
          System.out.println("The block will slide down!");

      theata = angle*(pi/180);
      anglePlatform.getShape().setRotate(theata);
      physicAsset.getShape().setRotate(theata);
      mag_ForceY = -(20*g*Math.cos(theata));
      mag_ForceX = -(20*g*Math.sin(theata));
      if(vYOld>0)
      {
          mag_ForceX += (friction*weight*g*Math.sin(theata));
          mag_ForceY += (friction*weight*g*Math.cos(theata));
      }
      else
      {
          mag_ForceY -= (friction*weight*g*Math.cos(theata));
          mag_ForceX -= (friction*weight*g*Math.sin(theata));
      }

       */


      //velocityX = vXOld+(mag_ForceX/weight*dt);
      //velocityY = vYOld+(mag_ForceY/weight*dt);

      //positionX = oldPositionX+velocityX*dt;
      //positionY = oldPositionY+velocityY*dt;


      // End Debug section

      // Gewichtskraft FA berechenen
      // Formel FG = m*g
      System.out.println("Angle Move: "+"Xold: "+oldPositionX+" Yold: "+oldPositionY+" velX: "+vXOld+" velY: "+vYOld);
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

      sinAngle = Math.sin(30*pi/180);
      cosAngle = Math.cos(30*pi/180);

      accelerationX = g*a;
      //accelerationX = g*Math.sin(30);
      accelerationY = g*b;
      //accelerationY = g*Math.cos(Math.toRadians(30));

      //accelerationX = g*Math.sin(20);
      //accelerationY = FGY/10;

      //μs = sin [gt] / cos [gt] = tan [gt]

      //friction = Math.sin(Math.toRadians(30))/Math.cos(Math.toRadians(30));
      //accelerationY = Math.cos(Math.toRadians(30))*g*(Math.sin(Math.toRadians(30))-friction*Math.cos(Math.toRadians(30)));
      //accelerationX = Math.sin(Math.toRadians(30))*g*(Math.sin(Math.toRadians(30))-friction*Math.cos(Math.toRadians(30)));

      //velocityX = vXOld+(accelerationX*dt);
      //vXOld = velocityX;
      //velocityY = vYOld+(accelerationY*dt);
      //vYOld = velocityY;

      //positionX = oldPositionX + (velocityX*dt);
      //positionY = oldPositionY + (velocityY*dt);




      //accelerationX = g*Math.sin(20);
      //accelerationX = F/physicAsset.getMass();
      //accelerationX += g*Math.sin(20);
      //accelerationX += (Math.sin(20)*FG)/physicAsset.getMass();

      accelerationY = g*Math.sin(Math.toRadians(30));

      accelerationX = g*Math.cos(Math.toRadians(30));

      //accelerationY = g*Math.cos(20);

      //accelerationY += (Math.cos(20)*FG)/physicAsset.getMass();
      //Geschwindigkeit berechnen durch v = a*t


      //!!!!
      //velocityX = accelerationX*dt;
      //velocityY = accelerationY*dt;
      //!!!


      velocityX += accelerationX*dt;
      velocityY += accelerationY*dt;

      //physicAsset.setVelocity(velocityY);

      //Neue Position berechnen durch s = 0.5*a*t
      //positionX += (1/2)*(5/7)*g*Math.sin(0)*dt;
      //positionY += (1/2)*(5/7)*g*Math.cos(0)*dt;

      positionX = (oldPositionX+vXOld*dt)+(0.5*accelerationX*dt);

      //positionX += 0.5*velocityX*dt;

      positionY = (oldPositionY+vYOld*dt)+(0.5*accelerationY*dt);

      //positionY -= 1;
      //positionY += 0.5*velocityY*dt;
      physicAsset.setVelocityX(velocityX);
      physicAsset.setVelocityY(velocityY);
      //vXOld = velocityX;
      //vYOld = velocityY;
      oldPositionX = positionX;
      oldPositionY = positionY;
      vXOld = velocityX;
      vYOld = velocityY;



      physicAsset.getShape().setTranslateX(positionX);
      physicAsset.getShape().setTranslateY(positionY);
      //physicAsset.getShape().setRotate(30);
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
      Bounds shape = assetData.getShape().getBoundsInLocal();
      oldPositionX = shape.getMaxX();
      oldPositionY = shape.getMaxY();
      vXOld = 0;
      vYOld = 0;
  }
  public void resetAngleMovement()
  {
      velocityX = 0;
      velocityY = 0;
      totalTime = 0;
      lastTime = System.nanoTime()*1E-9;
      time = System.nanoTime()*1E-9;
      positionX = 0;
      positionY = 0;
      oldPositionX = 10;
      oldPositionY = 10;
      accelerationX = 0;
      accelerationY = 0;
      counter = 0;
      vXOld = 0;
      vYOld = 0;
  }

  public void debugMovement(AssetData physicAsset)
  {
      t =  ((System.nanoTime() - t0) / 1E9);
      // Calculate the delta time form the time and the last saved time
      dt = 0.031126199998147786;
      // Saves the current time as last time
      lastTime = time;
      totalTime = totalTime + dt;
      System.out.println("time: "+time+" dt: "+dt+" lastTime: "+lastTime+"totalTime:"+totalTime);
      //m.out.println("Angle Move: "+"Xold: "+oldPositionX+" Yold: "+oldPositionY+" velX: "+vXOld+" velY: "+vYOld);
      //accelerationX = g*Math.sin(30);

      //accelerationY = g*Math.cos(30);


      // Überprüft Beschleunigung bei 30 Grad = 4,90 m/s
      //accelerationX = g*Math.sin(Math.toRadians(30));
      accelerationX = g*Math.sin(Math.toRadians(30))*Math.cos(Math.toRadians(30));
      accelerationY = g*Math.cos(30);
      accelerationY = accelerationY*accelerationY;
      double gesamt = accelerationX+accelerationY;



      //accelerationX = g*Math.cos(Math.toRadians(30)/10);

      //System.out.println("BeschleunigungX: "+accelerationX+" m/s"+" BeschleunigungY: "+accelerationY+" m/s"+" Gesamt:"+gesamt);
      double acx = accelerationX*friction;
      //accelerationX = Math.sin(Math.toRadians(30))*g*(Math.sin(Math.toRadians(30)));
      //accelerationY = Math.cos(Math.toRadians(30))*g*(Math.cos(Math.toRadians(30)));


      velocityX += accelerationX;
      velocityY += accelerationY;
      vXOld = velocityX;
      vYOld = velocityY;
      //positionX = (oldPositionX+vXOld*dt)+(0.5*accelerationX*dt);

      //positionY = (oldPositionY+vYOld*dt)+(0.5*accelerationY*dt);
      //positionX = physicAsset.getShape().getLayoutX();
      //positionY = physicAsset.getShape().getLayoutY();
      System.out.println(positionX+" "+positionY);
      if(counter1 == 0)
      {
          positionX = 50;
          positionY = physicAsset.getShape().getLayoutY();
          counter1 = 1;
      }
      physicAsset.setVelocityX(velocityX);
      physicAsset.setVelocityY(velocityY);
      positionX += 0.5*velocityX*dt;
      positionY += 0.5*velocityY*dt;
      createPoints();

      oldPositionX = positionX;
      oldPositionY = positionY;


      physicAsset.getShape().setLayoutX(positionX);
      physicAsset.getShape().setLayoutY(positionY);
      System.out.println(physicAsset.getShape().getLayoutY()+"t: "+totalTime+" L:"+calcL(50, 190, positionX, positionY)+" Px: "+positionX+" PY: "+positionX+" LayoutX: "+physicAsset.getShape().getLayoutX()+" LayoutY: "+physicAsset.getShape().getLayoutY());
  }

  double calcL (double StartX, double StartY, double EndX, double EndY)
  {
      double vectorX = EndX - StartX;
      double vectorY = EndY - StartY;
      double calc1 = vectorX*vectorX;
      double calc2 = vectorY*vectorY;
      weg = Math.sqrt(calc1+calc2);
     // System.out.println("VX: "+vectorX+" VY: "+vectorY+" Betrag: "+weg);
      return weg;
  }

  public void createPoints()
  {
      Circle point = new Circle(positionX,positionY,1);
      point.setFill(Color.BLUEVIOLET);
      mPoints.add(point);
  }

    public ArrayList<Circle> getmPoints()
    {
        return mPoints;
    }
    public void startTime()
    {
        if(counter == 0)
        {

            totalTime = 0;
            dt = 0;
            lastTime = System.nanoTime()*1E-9;
            time = System.nanoTime()*1E-9;
        }
    }
}
