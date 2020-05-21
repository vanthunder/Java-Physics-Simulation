package PhysicSimulation.Objects.ObjectContainer.StaticObjects;

public class InclinedPlane extends Plane{
    public InclinedPlane(){
        super();
        this.setId("inclinedPlane");
        this.setRotate(45);
        this.setWidth(200);
        this.setHeight(10);
        this.setLayoutX(0);
        this.setLayoutY(260);
        this.getStyleClass().add("plane");
    }
}