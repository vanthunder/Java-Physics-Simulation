package PhysicSimulation.Physics;


import com.sun.javafx.geom.Shape;

/*
 *   @author Erwin Kling
 *   @version 0.1.
 */


public class GravitationOLD
{
/*
Benötigte Gesetzmäßigkeiten
1. Gravitationsgesetz: Gravitationskraft = -Gravitationskonstante * (masse * Masse / Weg^2)
2. Schwerkraft: Gravitationskraft = Masse * Erdbeschleunigung
3. Hangabtriebskraft = Masse * Erdbeschleunigung * sin * Winkel
4.
* */

    //gravitation Values
    double gConstant;
    double gAccelaration;
    double angle;
    double gForce;

    public GravitationOLD(Shape shape, double gConstant, double gAccelaration, double angle, double gForce){
        gAccelaration = 9.81;

        this.gConstant = gConstant;
        this.gAccelaration = gAccelaration;
        this.angle = angle;
        this.gForce = gForce;

    }




}
