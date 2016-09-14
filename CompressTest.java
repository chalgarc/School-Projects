import junit.framework.TestCase;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */
public class CompressTest extends TestCase {
  ByteArrayOutputStream testOutput; 
   void setup() { 
     testOutput  = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(testOutput);
    System.setOut(ps);
  }
  
  /**
   * A test method.
   * (Replace "X" with a name describing the test.  You may write as
   * many "testSomething" methods in this class as you wish, and each
   * one will be called when running JUnit over this class.)
   */
  public void testGetNext1() {
    Run run = new Run();
    int index = RLcompress.getNext("abccccc123",0,run);
    assertTrue("Expect 1 got "+run.count, run.count == 1);
    assertTrue("Expect a got "+run.symbol, run.symbol == 'a');
    assertTrue("Expect 1 got "+index, index == 1);
  }
  public void testGetNext2() {
    Run run = new Run();
    int index = RLcompress.getNext("abccccc123",2,run);
    assertTrue("Expect 5 got "+run.count, run.count == 5);
    assertTrue("Expect c got "+run.symbol, run.symbol == 'c');
    assertTrue("Expect 7 got "+index, index == 7);
  }
  public void testPrint1() {
    setup();
    Run run = new Run();
    run.count = 5;
    run.symbol = 'a';
    RLcompress.print(run);
    assertTrue("Expect #a5 got " + testOutput.toString(), testOutput.toString().equals("#a5"));
  }
  // test run followed by 1 digit
  public void testPrint2() {
     setup();
    Run first = new Run(), second = new Run();
    first.count = 5;
    first.symbol = 'a';
    second.count = 1;
    second.symbol = '9';
    RLcompress.print(first,second);
    assertTrue("Expect #a5! got " + testOutput.toString(), testOutput.toString().equals("#a5!"));
  }
  // test run followed by 1 !
  public void testPrint2b() {
     setup();
    Run first = new Run(), second = new Run();
    first.count = 5;
    first.symbol = 'a';
    second.count = 1;
    second.symbol = '!';
    RLcompress.print(first,second);
    assertTrue("Expect #a5## got " + testOutput.toString(), testOutput.toString().equals("#a5##"));
  }
   // test single #
  public void testPrint2c() {
     setup();
    Run first = new Run(), second = new Run();
    first.count = 1;
    first.symbol = '#';
    second.count = 1;
    second.symbol = '!';
    RLcompress.print(first,second);
    assertTrue("Expect ### got " + testOutput.toString(), testOutput.toString().equals("###"));
  }
    
}
