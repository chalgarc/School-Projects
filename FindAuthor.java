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
    double wordCount = 0;
    for(int n=0;n<text.size();n++) { //WORDS IN ARRAY
    String word = cleanUp(text.get(n));
      if(word.isEmpty()){
      }
      else{
      charCount=charCount+word.length();
      wordCount++;
      }
    }
    averageWordLength = charCount/wordCount;   
    return averageWordLength;
  }
 
  /**
   * Return the type token ratio (TTR) for this text.
   * TTR is the number of different words divided by the total number of words.
   * text is a non-empty list of strings.
   * At least one line in text contains a word
   */
  static double typeTokenRatio(ArrayList<String> text){
    double uniqueWordCount=0;
    double wordCount = 0;
    ArrayList<String> uniqueWords= new ArrayList<String>();
    for(int n=0;n<text.size();n++) { //WORDS IN ARRAY
      String word = cleanUp(text.get(n));
      if(word.isEmpty()){
      }
      else {
        if(!uniqueWords.contains(word)){
          uniqueWords.add(word);
          uniqueWordCount=uniqueWords.size();
        }
        wordCount++;
      }
    }
    double typeTokenRatio= uniqueWordCount/wordCount;
    return typeTokenRatio;
  }

 
  /**
   * Return the hapaxLegomana ratio for this text.
   * This ratio is the number of words that occur exactly once divided
   * by the total number of words.
   * text is a list of strings.
   * At least one line in text contains a word.
   */
  static double hapaxLegomanaRatio(ArrayList<String> text) {
    ArrayList<String> totalWords= new ArrayList<String>();
    ArrayList<String> reoccuringWords= new ArrayList<String>();
    for(int n=0;n<text.size();n++) { //WORDS IN ARRAY
      String word = cleanUp(text.get(n));
      if(word.isEmpty()){
      }
      else {
        totalWords.add(word);
       
      }
    }
    for(int i=0;i<totalWords.size();i++){
      String temp = totalWords.get(i);
      totalWords.remove(temp);
      if(totalWords.contains(temp)){
        if(!reoccuringWords.contains(temp)){
          reoccuringWords.add(temp);
          reoccuringWords.add(temp);
        }
         else{
        reoccuringWords.add(temp);
      }
      }
      totalWords.add(temp);
         }
    double hepaxLegomanaRatio = (((double)totalWords.size()-(reoccuringWords.size()))/totalWords.size());
    return hepaxLegomanaRatio;
    }
// To be completed
        
 
  /**
   * Return the average number of words per sentence in text.
   * text is guaranteed to have at least one sentence.
   * Terminating punctuation is defined as !?.
   * A sentence is defined as a non-empty sequence of words
   * terminated by a token ending in terminating punctuation
   * or end of file.
   */
  static double averageSentenceLength(ArrayList<String> text) {
    double sentenceCount=0;
    double wordCount=0;
    for(int n=0;n<text.size();n++) {
      String uncleanedWord=text.get(n);
      String word = cleanUp(text.get(n));
      if(!word.isEmpty()){ //When token is a word
        wordCount++;
        if(uncleanedWord.endsWith(".") || uncleanedWord.endsWith("!") || uncleanedWord.endsWith("?")){
          sentenceCount++;
        }
        else if(n==text.size()-1){
          sentenceCount++;
        }
      }
      //when token is not a word and ends with punctuation
      else if(uncleanedWord.endsWith(".") || uncleanedWord.endsWith("!") || uncleanedWord.endsWith("?")){
        if(text.get(n-1).endsWith(".") || text.get(n-1).endsWith("!") 
             || text.get(n-1).endsWith("?")){//if previous token ends with punctuation
          if(!cleanUp(text.get(n-1)).isEmpty()){//if previous token is a word do nothing
          }
        }
        else if(!cleanUp(text.get(n-1)).isEmpty()){ //if previous token is a word w/out punctuation
          sentenceCount++;                          //increment sentenceCount
        }
      }
    }
    return wordCount/sentenceCount;
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
    double phraseCount=0;
    double sentenceCount=0;
    for(int n=0;n<text.size();n++) {
      String uncleanedWord=text.get(n);
      if(uncleanedWord.endsWith(".") || uncleanedWord.endsWith("!") || uncleanedWord.endsWith("?")){
        sentenceCount++;
        phraseCount++;
      }
      if(uncleanedWord.endsWith(",") || uncleanedWord.endsWith(":") || uncleanedWord.endsWith(";")){
        phraseCount++;
      }
    }
    return phraseCount/sentenceCount;
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
    double sum=0;
    double subtractionResult= 0;
    double multiplier= 0;
    double totalSum = 0;
    for(int n=0;n<sig1.length;n++) {
          subtractionResult=sig1[n]-sig2[n];
          multiplier=weight[n];
          sum=Math.abs(subtractionResult)*multiplier;
          totalSum = totalSum+sum;
        
     }
    return totalSum;
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