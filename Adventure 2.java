/**
 * The starter for an adventure game.
 */
import java.util.Scanner;

class Adventure {
  static final Scanner input = new Scanner(System.in);
  public static void main(String[] args) {
    Player player = new Player();
    
    Room entryWay = buildWorld();//
    player.moveTo(entryWay);
    printInstructions();
    play(player);   
  }
  
  /**
   * Main play loop of the game. 
   */
  static void play(Player player) {
    while(input.hasNextLine()) {
      String cmd = input.nextLine();
      if (cmd.equals("quit")) {
        System.out.print("You have died.");
        return;
      }
      // always move north for starters
      player.goNorth();
      System.out.println("You are in " + player.getRoom());
    }      
  }
  /**
   * Construct a world.
   * @return a reference to the room where the player enters the world.
   */
  static Room buildWorld() {
    Room entryWay = new Room("Entry Chamber");
    Room room1 = new Room("Room 1");//give a name to room1
    Room room2 = new Room("Room 2");//give a name to room2
    /*Room room3 = new Room("Room 3");//give a name to room3
    Room room4 = new Room("Room 4");//give a name to room4
    Room room5 = new Room("Room 5");//give a name to room5
    Room room6 = new Room("Room 6");//give a name to room6
    Room room7 = new Room("Room 7");//give a name to room7
    Room room8 = new Room("Room 8");//give a name to room8
    Room room9 = new Room("Room 9");//give a name to room9*/
    
    entryWay.connectNorth(room1);//in the entryway, going north will end up in room 1
    entryWay.connectWest(room2);//go west in the entry way to end up in room 2
    
    room1.connectNorth(entryWay);//in room 1, going north will end up in the entry way.  Rooms are not logically connected.
    
    room2.connectNorth(entryWay);//go north from room 2 to end up in the entryway
    
    

    
    return entryWay;//start of the game, the main function calls on buildworld to put the player in the entryway
  }
  
  static void printInstructions() {
    System.out.println("Welcome to Hell.");
  }
}