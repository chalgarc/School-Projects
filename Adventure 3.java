/**
 * The starter for an adventure game.
 */
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;


class Adventure {
  static final Scanner input = new Scanner(System.in);
  static JTextArea o = new JTextArea(6,2);
  static JTextArea I = new JTextArea();
  static JScrollPane s = new JScrollPane(o);
  public static void main(String[] args) {
    Player player = new Player();

        
     JFrame frame = new JFrame ("Adventure Game");  
    
     Container d = frame.getContentPane();
     
      //Layout
      d.setLayout(new GridLayout(5,5)); 
      
      //Instructions
      d.add(new JLabel("<html> Instructions: <br> Move around, pick up, and drop items using the JButtons <br> When trying to drop items, type in the slot number. <br> To drop an item, you must find out what you are holding, then type the <br> corresponding number into the text field on the right, then click Drop.<html>")); 
      
      //J Buttons
      JButton north = new JButton("North");
      d.add(north);
      north.addActionListener(new North());
      
      JButton south = new JButton("South");
      d.add(south);
      south.addActionListener(new South());
      
      JButton west = new JButton("West");
      d.add(west);
      west.addActionListener(new West());
      
      JButton east = new JButton("East");
      d.add(east);
      east.addActionListener(new East());
      
      JButton pickUp = new JButton("Pick Up");
      d.add(pickUp);
      pickUp.addActionListener(new PickUp());
      
      JButton holding = new JButton("Holding");
      d.add(holding);
      holding.addActionListener(new Holding());
      
      JButton drop = new JButton("Drop");
      d.add(drop);
      drop.addActionListener(new Drop());
      
      
      //Include Text Area and Scroll Pane
      d.add(s);
      d.add(I);

      frame.pack();
      frame.setVisible(true);
   
      
        
    Room entryWay = buildWorld();
    player.moveTo(entryWay);
    printInstructions();
    play(player);   
  }
  
  /**
   * Main play loop of the game. 
   */
  public static void play(Player player) {
    while(input.hasNextLine()) {
      String cmd = input.nextLine();
      if (cmd.equals("quit")) {
        return;
      }
     
      if(cmd.equals("North")){
        player.goNorth();
      }
      
      if(cmd.equals("South")){
        player.goSouth();
      }
      
      if(cmd.equals("West")){
        player.goWest();
      }
      
      if(cmd.equals("East")){
        player.goEast();
      }
      
      if(cmd.equals("PickUp")){
        player.pickUp();
      }
      
      if(cmd.equals("Holding")){
        System.out.println(player.getInventory());
      }
      
      if(cmd.equals("Drop")){
        player.removeItem();
      }
      
          
      System.out.println("You are in " + player.getRoom());
      System.out.println("Item in room:" + player.look());
    } 
        
          
  }
  /**
   * Construct a world.
   * @return a reference to the room where the player enters the world.
   */
  public static Room buildWorld() {
    
    Item [] mItem = {new Item("Gold", 300), null, new Item("Sword", 200), new Item("Potion", 50), new Item("Shield", 150)};
    
    Room entryWay = new Room("Entry Chamber");
    Room room1 = new Room("Room 1", mItem[(int)(Math.random()*5)]);
    Room room2 = new Room("Room 2", mItem[(int)(Math.random()*5)]);   
    Room room3 = new Room("Room 3", mItem[(int)(Math.random()*5)]);
    Room room4 = new Room("Room 4", mItem[(int)(Math.random()*5)]);
    Room room5 = new Room("Room 5", mItem[(int)(Math.random()*5)]);
    
    entryWay.connectNorth(room1);
    entryWay.connectSouth(room3);
    entryWay.connectWest(room2);
    entryWay.connectEast(room4);
    
    room1.connectNorth(room5);
    room1.connectSouth(room3);
    room1.connectWest(room2);
    room1.connectEast(room4);
    
    room2.connectNorth(room4);
    room2.connectSouth(room3);
    room2.connectWest(room1);
    room2.connectEast(room5);
    
    room3.connectNorth(room5);
    room3.connectSouth(room4);
    room3.connectWest(room2);
    room3.connectEast(room1);
    
    room4.connectNorth(room1);
    room4.connectSouth(room5);
    room4.connectWest(room2);
    room4.connectEast(room3);
    
    room5.connectNorth(room4);
    room5.connectSouth(room3);
    room5.connectWest(room2);
    room5.connectEast(room1);
      
    return entryWay;
  }
  
  static void printInstructions() {
    System.out.println("You wanna play a game?");
  }
  

}