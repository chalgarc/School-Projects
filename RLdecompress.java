/*
 * This program decompresses text files compressed using RLencoder which uses a form of run length encoding.
 * It reads the input (compressed) file from standard input and writes the uncompressed output to standard output.
 */
import java.util.Scanner;

class RLdecompress {
  static final Scanner in = new Scanner(System.in);
  public static void main(String[] args) {
    while (in.hasNextLine()) {
      String line = in.nextLine();
      processOneLine(line);      
    }
  }
  /**
   * Decompress one line, writing the results to standard out.
   * @param line - a complete line of input that needs processing (decompressing)
   */
  static void processOneLine(String line) {
    int index = 0;
    while(index < line.length()) {
      // TO BE COMPLETED
    
      if (line.charAt(index) == '#') {
        index = index + 1;
        processHash( line,index);
     
      }
      else if (Character.isLetter(line.charAt(index))) {
        System.out.print(line.charAt(index));
      }
  
   
        index++;
    }
     
                        
     
    
  
    System.out.println();
  }
  
  /**
   * We have seen a hash mark. It could be ### for a single #, ##! for a single !,
   * #Xddd for a run of X including ##ddd for a run of #.
   * index is the position of the symbol following the hash mark.
   * Read the encoded run (or single # or !), print the decompressed output, and return
   * the index of the next symbol that needs to be processed from line.
   * @param line - the input line containing the hash mark
   * @param index - the position of the symbol following the hash mark in line
   * @return the index of the character in line following the "run" starting with the hash mark
   */
  static int processHash(String line, int index) {
    // could be the start of a run or ### or ##!
    char c = line.charAt(index); // read symbol following the first #
    index++;
    // TO BE COMPLETED
    
    if (c != '#') {
     index = index + 2;
  readCountAndPrintRun(line, index - 2, c);
    }

    if (c == '#') {
      // must be a run of # or one of ### or ##!
   index = index + 1;
   if ( line.charAt(index - 1) == '!') {
     System.out.print('!');
   }
   else System.out.print(c);
    
    }

    
    
    return index;
  }
  
  /**
   * Expect to find a series of digits starting an position index in line.
   * Read those digits as a number (the length of the run), print the run,
   * and return the index of the symbol following the string of digits.
   * @param line - the input line being processed
   * @param index - the position of the start of the count in line
   * @param c - the symbol for the run
   * @return the index of the symbol following the compressed run
   */
  static int readCountAndPrintRun(String line, int index, char c) {
    int cnt = readCount(line, index);
    print(c, cnt);
    // skip over the digits just read and processed
    // TO BE COMPLETED
  index = index + 2;
    
    return index;
  }
  /**
   * Read the count and return it.
   * @param line - the input line being processed
   * @param index - the position of the first digit in the count to be read
   * @return the integer count read from the input line starting at position index
   */
  static int readCount(String line, int index) {
    int last = index;
    while (last < line.length() && Character.isDigit(line.charAt(last))) {
      last++;
    }
    return Integer.parseInt(line.substring(index,last));
  }
  /**
   * Print a run of characters given the character and the count.
   * @param c - the symbol to printed
   * @param count - the number of times to print symbol
   */
  static void print(char c, int count) {
    while (count > 0) {
      System.out.print(c);
      count--;
    }
  }
}