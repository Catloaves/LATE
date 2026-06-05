package Items;

import Mobs.HostileMob;

public class Tool extends Item{

    public Tool(String id, String name, String description) {
        super(id, name, description);
    }

    protected HostileMob target; //only accessible to children

    public void setTarget(HostileMob target){
        this.target = target;
    }
}
