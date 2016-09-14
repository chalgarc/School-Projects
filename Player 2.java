/**
 * Represents the player in an adventure game.
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

class Player {
  private static Room location;
  static ArrayList <Item> item = new ArrayList <Item> ();
  
  static void moveTo(Room room) {
    location = room;
  }
  
  static void goNorth() {
    location = location.north();
  }
  
  static void goSouth(){
    location = location.south();
  }
  
  static void goEast(){
    location = location.east();
  }
  
  static void goWest(){
    location = location.west();
  }
  
  static Room getRoom() {
    return location;
  }
  
  static String getInventory() {
    String x = " ";
    for(int i=0; i<item.size(); i++) {
      x += "Slot "+i+": "+item.get(i).toString()+item.get(i).getItemValue()+'\n';
    }
    return x;
  }
  
  static String look() {
    if(location.getItem()!=null) {
      return location.getItem().toString();
    }
    else {
      return "No item found";
    }
  }
  
  static void pickUp() {
    if(item.size()>=4){
      System.out.println("Inventory is full");
    }
    item.add(location.getItem());
    location.removeItem();
  }
  
  static void removeItem() {
     String getT = Adventure.I.getText();
     Adventure.o.append("What slot do you want to clear?");
     Adventure.o.append(getInventory());
     //Scanner in = new Scanner(System.in);
     //int itemNum = in.nextInt();
     //in.close();
     try{
        int num = Integer.parseInt(getT);
        item.remove(num);
     }catch(NumberFormatException e){
        System.out.println("Invalid input. Must give a slot number."); 
     }
  }
   
}