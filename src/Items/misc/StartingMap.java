package Items.misc;

import Items.Item;

public class StartingMap extends Item {
    public int mapId;

    public StartingMap(String id, String name, String description, int mapId) {
        super(id, name, description);
        this.mapId = mapId;
    }
}