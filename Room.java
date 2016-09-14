
public class Room {
  private Room north, south, east, west;
  private String name;
  
  Room(String name) {//assigning room name to name
    this.name = name;
  }
  
  Room north() {//north door
    return north;
  }
  
  Room south() {//sourth door
    return south;
  }
  
  Room west() {//west door
    return west;
  }
  
  Room east() {//east door
    return east;
  }
  
  void connectNorth(Room room) {//a room input is now the north of that room
    north = room;
  }
  
  void connectSouth(Room room) {//a room input is now the south of that room
    south = room;
  }
  
  void connectEast(Room room) {// a room input is now the east of that room
    east = room;
  }
  
  void connectWest(Room room) {// a room input is now the west of that room
    west = room;
  }
  
  public String toString() {//print out the name of the room.  Equivalent to System.out.print(name);
    return name;
  }
  
}
