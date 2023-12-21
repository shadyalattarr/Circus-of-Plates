package backend.object;

public class ObjectFactory {
    public FallingObject createObject(String objectName, int posX, int posY)
    {
        switch (objectName) {
            case "Plate":
                return new Plate(posX, posY);
            case "Bomb":
                return new Bomb(posX, posY, "FinalProjectt\\bombresized.png");
            case "Heart":
                return new Heart(posX,posY,"FinalProjectt\\clown-removebg-preview_3_53.png");
            default:
                System.out.println("Invalid falling object");
                return null;
        }
    }

}
