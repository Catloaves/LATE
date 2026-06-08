package Items;

import Mobs.HostileMob;

public class Tool extends Item {
    public HostileMob target; 

    public Tool(String id, String name, String description) {
        super(id, name, description);
    }

    public void setTarget(HostileMob target){
        this.target = target;
    }
}