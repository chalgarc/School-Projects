/**
 * This program using Run Length Encoding to compress text files.
 * It reads the input from standard input and writes the compressed file to standard output.
 */
import java.util.Scanner;

class RLcompress {
  static final Scanner in = new Scanner(System.in);
  public static void main(String[] args) {
    // Create two Run objects to keep track of current and next run.
    // As shown in the assignment spec, the output of the current run depends upon what follows it.
    Run current = new Run(), next = new Run();
    
    // process one line of input at a time
    while (in.hasNextLine()) {
      String line = in.nextLine();
      int index = 0;
      // read the first run of the line
      index = getNext(line,index,current);
      while(index < line.length()) {
        index = getNext(line,index, next);
        print(current,next);
        current.count = next.count;
        current.symbol = next.symbol;
      }
      print(current); 
      // handle special case of last "run" being just a single "#"
      if (current.count == 1 && current.symbol == '#') {
        System.out.print("##"); // two more for a total of 3 #s
      }
      System.out.println();    
    }  
  }
  /**
   * Output the basic part of a run (#Sddd) or single symbol.
   * @param run - the Run containing the symbol and the count
   */
  static void print(Run run) {
    System.out.println(current,next);
  }
  /**
   * Print a run and possibly add separator sequence depending upon what follows.
   * Each run is specified by a count and a symobl. The count can be 1 for either or both.
   * @param first - the Run to be output
   * @param second - the Run that follows the one being output
   */
  static void print(Run first, Run second) {
    print(first);
    // add any required extra symbols for special cases
    // TO BE COMPLETED
  }
  /**
   * Read in the next run (or single character) and return the index of the symbol following the run (or symbol read).
   * Store the count and sybmol for the run in the run parameter.
   * @param line - the input line being processed
   * @param index - the index of the first character in the next "run" (which might be just a single character)
   * @param run - where to store the symbol and count for the Run
   * @return the index of the symbol following the run just read
   */
  static int getNext(String line, int index, Run run) {
    // TO BE COMPLETED
    return index+1;// just to make it advance to starter program doesn't loop infinitely
  }
}
/*
 * A class used to "return" a count and sybmol comprising a run.
 */
class Run {
  int count;
  char symbol;
}
