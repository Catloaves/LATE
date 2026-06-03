import java.util.Map;

//importing Currency classes
import Currency.GoldCoins;

//importing other classes
import Mobs.PetAlligator;
import Mobs.HostileMob;
import Mobs.PassiveMob;

//importing Item classes
    //importing bag classes
import Items.bags.Backpack;
import Items.bags.Pouch;
    //importing misc classes
import Items.misc.Key;
import Items.misc.PuzzleScroll;
import Items.misc.QuillAndScroll;
import Items.misc.Scroll;
import Items.misc.StartingMap;
import Items.misc.Torch;
    //importing potion classes
import Items.potions.HealingPot;
import Items.potions.InvisPot;
import Items.potions.StrengthPot;
    //importing tool classes
import Items.tools.Axe;
import Items.tools.Bow;
import Items.tools.Mace;
import Items.tools.Shield;
import Items.tools.Sword;
import Items.tools.Wand;

// start of class

public class CommandParser {
    public static String parse(String input, Player player, Map<String, Room> rooms) {
        String[] tokens = input.trim().split(" ");
        if (tokens.length == 0) return "Enter a command.";

        String cmd = tokens[0].toLowerCase();
        Room room = rooms.get(player.getCurrentRoomId());
        // if usepot is successful then run this.
       // Player.getStats().incrementStrength(10);

        switch (cmd) {
            case "go":
                if (tokens.length < 2) return "Go where?";
                String dir = tokens[1];
                if (room.getExits().containsKey(dir)) {
                    player.setCurrentRoomId(room.getExits().get(dir));
                    return rooms.get(player.getCurrentRoomId()).getLongDescription();
                } else {
                    return "You can't go that way.";
                }
            case "look":
                return room.getLongDescription();
            default:
                return "Unknown command.";

        }
    }



//KEY COMMANDS

    public String useKey() { //can be used by the player
        Key.useKey();
    }

//POTION COMMANDS

    //HEALING
    public String usePot() { //insta heal 30% of maximum hp
        int newHp = 0;
        int oldHp = Player.getStats().getHp();
        int maxHp = Player.getStats().getMaxHp();
        String potMessage = "";
        if (oldHp < maxHp){
            newHp += maxHp*0.3; //30% increase in Hp
                if (newHp > maxHp)
                    newHp = maxHp;
        potMessage = "You have regained " + newHp + " Hp!";
            }
        return potMessage;
    }

    //INVISIBILITY
    public String useInvisPot(){
        InvisPot.useInvisPot();
    }

    //STRENGTH
    public String useStrengthPot(int turnsTillEffectGone){
        
    }

    //TOOLS

    public String useAxe(HostileMob target){
        Axe.useAxe(target);
    }

    public String useBow(HostileMob target){

    }
    
    public String useMace(HostileMob target){
    }

    public String useSword(HostileMob target){
    }

    


// When you try and use the pot and itis successful, then the player will use its stats object to call stats.updadeStrength(10) to add 10 to the strength */
       // if usepot is successful then run this.
       // Player.getStats().incrementStrength(10);
//