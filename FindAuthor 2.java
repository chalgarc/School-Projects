import java.util.regex.*;
import java.util.*;
import java.io.*;

/**
 * This program uses 5 simple linguistic features to try and identify the authorship of an "unknown" text.
 * This is based on an assignment created by Michelle Craig from the University of Toronto.
 * http://nifty.stanford.edu/2013/craig-authorship-detection/
 * 
 * In the comments below a "word" is a String without embedded white space characters and no
 * leading or trailing non-word characters (the word characthers are _,a-z,A-Z, and 0-9).
 * A "token" is a String without embedded white space characters (i.e. what you get when you call next() from the
 * class Scanner).
 */
class FindAuthor {
  static final int NUM_FEATURES = 5;

  /**
   * This program expects to be passed the name of the unknown file as the first command line argument,
   * and the name of a directory containing nothing but signature files for known authors as the second
   * command line argument.
   */
  public static void main(String[] args) throws IOException {
    if (args.length != 2) {
      System.out.println("Usage: java FindAuthor unknownAuthorFileName signaturesDirectory");
      System.exit(1);
    }
    
    // read the new text file from the unknown author
    Scanner textIn = new Scanner(new File(args[0])); 
    ArrayList<String> text = new ArrayList<String>();
    while (textIn.hasNext()) {
      text.add(textIn.next());
    }
    
    // Compute the signature of the unknown author
    double[] features = new double[NUM_FEATURES];
    features[0] = averageWordLength(text);
    features[1] = typeTokenRatio(text);
    features[2] = hapaxLegomanaRatio(text);
    features[3] = averageSentenceLength(text);
    features[4] = avgSentenceComplexity(text);
    Signature unknown = new Signature(args[0], features);
    System.out.println(unknown);
    
    double[] weights = {11, 33, 50, 0.4, 4};
    
    // compare the computed signature with those of known authors using the given weights
    File[] files = new File(args[1]).listFiles();
    Signature sig = readSignature(files[0]);
    // guess that the first author is the best match
    double bestScore = compareSignatures(unknown.feature, sig.feature, weights);
    String bestAuthor = sig.author;
    // try other authors for a better match
    for (int i = 1; i < files.length ; i++) {
      sig = readSignature(files[i]);
      double score = compareSignatures(unknown.feature, sig.feature, weights);
      if (score < bestScore) {
        bestScore = score;
        bestAuthor = sig.author;
      } 
    }
    System.out.println("Best author is " + bestAuthor + " with score " + bestScore);      
  }
  
  /**
   * Return a version of String str in which all letters have been
   * converted to lowercase and non-word characters (anything but _,A-Z,a-z,0-9) 
   * have been stripped from both ends. Inner non-word characters are left untouched. 
   * If str does not contain any white space characters, then cleanUp() will convert
   * a "token" into a "word" (see definitions in the opening comment).
   */
  static String cleanUp(String str) {
    Pattern p = Pattern.compile("(\\W*)(.*?)(\\W*)");
    Matcher m = p.matcher(str);
    m.matches();
    return str.substring(m.end(1),m.end(2)).toLowerCase();    
  }
  
  /**
   * Return the average length of all words (as defined above) in text. 
   * text is a non-empty list of strings.
   * At least one line in text contains a word.
   */
  static double averageWordLength(ArrayList<String> text) {
    double charCount= 0;
    double averageWordLength = 0;
    for(int n=0;n<text.size();n++) { //WORDS IN ARAY
      String word = cleanUp(text.get(n));
      if(word.length()>0){
      for(int i=0;i<word.length();i++) { //CHARACTERS IN WORD use word.length instead error in for loop dont devide by text.size
        charCount++; 
      }
      averageWordLength = charCount/text.size();
    }
         }    
        
//        public static int countOccurrences(String haystack, char needle)
//{
//    int count = 0;
 //   for (int i=0; i < haystack.length(); i++)
 //   {
 //       if (haystack.charAt(i) == needle)
 //       {
 //            count++;
 //       }
  //  }
    return averageWordLength;
  }
  
  /**
   * Return the type token ratio (TTR) for this text.
   * TTR is the number of different words divided by the total number of words.
   * text is a non-empty list of strings.
   * At least one line in text contains a word
   */
  static double typeTokenRatio(ArrayList<String> text){
    // To be completed
    return 1;
  }
  
  /**
   * Return the hapaxLegomana ratio for this text.
   * This ratio is the number of words that occur exactly once divided
   * by the total number of words.
   * text is a list of strings.
   * At least one line in text contains a word.
   */
  static double hapaxLegomanaRatio(ArrayList<String> text) {
    // To be completed
    return 1;
  }
  
  /**
   * Return the average number of words per sentence in text.
   * text is guaranteed to have at least one sentence.
   * Terminating punctuation is defined as !?.
   * A sentence is defined as a non-empty sequence of words
   * terminated by a token ending in terminating punctuation 
   * or end of file.
   */
  static double averageSentenceLength(ArrayList<String> text) {
    // To be completed
    return 1;
  }
  
  /**
   * Return the average number of phrases per sentence.
   * Terminating punctuation defined as !?.
   * A sentence is defined as a non-empty sequence of words
   * terminated by a token ending in terminating punctuation 
   * or end of file.
   * Phrases are subsequences of a sentences terminated by a token
   * ending with ,;: or by the end of the sentence.
   */
  static double avgSentenceComplexity(ArrayList<String> text) {
    // To be completed
    return 1;
  }
  
  /**
   * Return a non-negative real number indicating the similarity of two
   * linguistic signatures. The smaller the number the more similar the
   * signatures. Zero indicates identical signatures.
   * sig1 and sig2 are NUM_FEATURES element double arrays with the following elements
   *
   * 0 : average word length
   * 1 : TTR
   * 2 : Hapax Legomana Ratio
   * 3 : average sentence length
   * 4 : average sentence complexity
   * weight is a list of multiplicative weights to apply to each
   * linguistic feature.
   */
  static double compareSignatures(double[] sig1, double[] sig2, double[] weight) {
    // To be completed
    return 1;
  }
  
  /**
   * Read a linguistic signature from file and return it as
   * a Signature object.
   */
  static Signature readSignature(File file) throws IOException {
    // To be completed
    double[] features = {0, 0, 0, 0, 0};
    return new Signature("needs to be completed", features);
  } 
}

