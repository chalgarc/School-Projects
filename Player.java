/**
 * Represents the player in an adventure game.
 * Things that the player can use
 * 
 */
class Player {
  private Room location;
  void moveTo(Room room) {
    location = room;
  }
  
  //go(direction) tells which direction to go.  goNorth makes goes north, goSouth makes goes south, etc.
  void goNorth() {
    location = location.north();
  }
  void goSouth(){
    location = location.south();
  }
  void goEast(){
    location = location.east();
  }
  void goWest(){
    location = location.west();
  }
  Room getRoom() {//determine the current location
    return location;
  }
}