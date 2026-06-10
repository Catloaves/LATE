package Game;

import java.io.Serializable;
import java.util.List;

public class SaveCurrGame implements Serializable {
    public String roomId;
    public int hp;
    public List<String> items;
}
