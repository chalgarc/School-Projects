
public class Room {
  private Room north, south, east, west;
  private String name;
  private Item items; 
  
  public Room(String name, Item items) {
    this.name = name;
    this.items = items;
  }
  
  Item getItem() {
    return items;
  }
  
  
  public Room(String name) {
    this.name = name;
  }
  
  String getItemName() {
    return items.getItemName();
  }
  
  Room north() {
    return north;
  }
  
  Room south() {
    return south;
  }
  
  Room west() {
    return west;
  }
  
  Room east() {
    return east;
  }
  
  void connectNorth(Room room) {
    north = room;
  }
  
  void connectSouth(Room room) {
    south = room;
  }
  
  void connectEast(Room room) {
    east = room;
  }
  
  void connectWest(Room room) {
    west = room;
  }
  
  public String toString() {
    return name;
  }
  
  void removeItem() {
    items = null;
  }
  
}
