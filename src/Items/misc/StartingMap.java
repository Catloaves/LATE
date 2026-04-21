package Items.misc;

public class StartingMap {
    private String id;
    private String name;
    private String description;

    public int mapId;

    public StartingMap(int mapId) {
        this.mapId = mapId;
    }

    public String getName() { //not typically to be used by player
        return name;
    }
}

