package backend.object;

public class ObjectFactory {
    public FallingObject createObject(String objectName, int posX, int posY,String filepath)
    {
        switch (objectName) {
            case "Plate":
                return new Plate(posX, posY);
            case "Bomb":
                return new Bomb(posX, posY, filepath);
            case "Heart":
                return new Heart(posX,posY,filepath);
            default:
                System.out.println("Invalid falling object");
                return null;
        }
    }

}
