
// This is our item class

public class Item {
  String itemName;
  int itemValue;
  
  public Item(String in, int val) {
    itemName = in;
    itemValue = val;
  }
  
  String getItemName() {
    return itemName;
  }

  public String toString(){
    return itemName;
  }
  
  int getItemValue() {
    return itemValue;
  }
  
}
  

