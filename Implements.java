//Classes for the actionListeners
import java.awt.event.*;


class North implements ActionListener {
  public void actionPerformed (ActionEvent e){
    Player.goNorth();
    Adventure.o.append("You are in " + Player.getRoom()+'\n');
    Adventure.o.append("Item in room:" + Player.look()+'\n');
  }
}

class South implements ActionListener {
  public void actionPerformed (ActionEvent e) {
    Player.goSouth();
    Adventure.o.append("You are in " + Player.getRoom()+'\n');
    Adventure.o.append("Item in room:" + Player.look()+'\n');
  }
}

class West implements ActionListener {
  public void actionPerformed (ActionEvent e) {
    Player.goWest();
    Adventure.o.append("You are in " + Player.getRoom()+'\n');
    Adventure.o.append("Item in room:" + Player.look()+'\n');
  }
}

class East implements ActionListener {
  public void actionPerformed (ActionEvent e) {
    Player.goEast();
    Adventure.o.append("You are in " + Player.getRoom()+'\n');
    Adventure.o.append("Item in room:" + Player.look()+'\n');
  }
}

class PickUp implements ActionListener {
  public void actionPerformed (ActionEvent e) {
    Player.pickUp();
    Adventure.o.append("You are in " + Player.getRoom()+'\n');
    Adventure.o.append("Item in room:" + Player.look()+'\n');
  }
}

class Holding implements ActionListener {
  public void actionPerformed (ActionEvent e) {
    Adventure.o.append(Player.getInventory()+'\n');
  }
}
  
  
class Drop implements ActionListener {
  public void actionPerformed (ActionEvent e) {   
    Player.removeItem();
    //Adventure.o.append(Player.removeItem()+'\n');
    Adventure.o.append("You are in " + Player.getRoom()+'\n');
    Adventure.o.append("Item in room:" + Player.look()+'\n');
  }
}
  
  