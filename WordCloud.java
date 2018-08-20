/* 
 * Name: Jiaqi Fan 
 * Login: cs8bwang 
 * Date: 01/15/2016
 * File:  WordCloud.java 
 * Sources of Help: Roommate, Oracle 
 * 
 * This program read a text file and output the most frequent 
 * and unique words from ArrayLists. This program also
 * remove any command words from the txt file.
 */


import java.util.*;
import java.io.*;

public class WordCloud {
  
  // The ArrayList to store the words and their associated counts
  ArrayList<WordPair> list;
  
  // construct the list
  public WordCloud() {
    list = new ArrayList<WordPair>();
  }
  /* 
 * Name: getWordFromFile 
 * Purpose: This method will read all the words from the 
 * source file and properly update the list
 * Parameters: String filename (the file that will be read)
 * Return: void 
 */
  public void getWordsFromFile( String filename ) throws IOException {
    File sourceFile = new File(filename);
    Scanner input = new Scanner(sourceFile);
    String[] temp;
    while (input.hasNextLine())
    {
      temp = input.nextLine().split(" ");
      for(int i = 0; i <temp.length; i++)
      {
        boolean check = false;
        if (temp[i].length() != 0)
        {
        for (int j = 0; j < list.size(); j++)
        {
          if(list.get(j).getWord().equalsIgnoreCase(temp[i])== true)
          { 
            check = true;
            list.get(j).increment();
          }
        }
        if (check == false)
        {
          list.add(new WordPair(temp[i]));
        }
      }
      }
    }
  }
  // An accessor method for the tester file 
  public ArrayList<WordPair> getList(){
    return list;
  }
  /* 
 * Name: findWordCount 
 * Purpose: This method searches for a word 
 * in the list and returns its count
 * Parameters: String word (take a string of word to check for the same
 * word inside the txt file) 
 * Return: int (the word count will be returned) 
 */
  public int findWordCount(String word){
    int count = 0;
    for (int i = 0; i < list.size(); i++)
    {
      if (list.get(i).getWord().equalsIgnoreCase(word)== true)
      {
        count = count + 1;
      }
    }
    if (count != 0)
    {
      return count;
    }
    else
    {
      return 0;
    }
  }
  /* 
 * Name: removeCommon 
 * Purpose: this method will remove any commonly used word from the list
 * Parameters: String omitFilename (read the file that contain common words) 
 * Return: void 
 */ 
  public void removeCommon( String omitFilename ) throws IOException {
    ArrayList<String> mylist = new ArrayList<String>();
    File sourceFile = new File(omitFilename);
    Scanner input = new Scanner(sourceFile);
    String[] temp;
    while (input.hasNextLine())
    {
      temp = input.nextLine().split(" ");
      for (int i = 0; i < temp.length; i++)
      {
        mylist.add(temp[i]);
      }
    }
    for(int k = 0; k < mylist.size(); k++)
    {
      for (int h = 0; h < list.size(); h++)
      {
        if (mylist.get(k).equalsIgnoreCase(list.get(h).getWord()) || mylist.get(k).equals(" "))
          list.remove(h);
      }
    }
  }
  
  /* 
 * Name: topNWords 
 * Purpose: This method finds the top n 
 * occurring words in the list 
 * and returns it as an ArrayList. 
 * In the event of a tie, use the first 
 * occurring word with that count.
 * Parameters: int n (the number of top repeat 
 * word need to be printed) 
 * Return: ArrayLisy<Word Pair> (return a new arraylist that
 * contain the top 5 words and their count) 
 */
  public ArrayList<WordPair> topNWords(int n){
    ArrayList<WordPair> mylist = new ArrayList<WordPair>();
    int index = 0;
    int max = 0;
    for(int i = 0; i < n; i++)
    {
      max = 0;
      for (int j = 0; j < list.size(); j++)
      {
        if (list.get(j).getCount() > max)
        {
          max = list.get(j).getCount();
          index = j;
          
        }
      }
      mylist.add(list.get(index));
      list.get(index).setCount(-1 * list.get(index).getCount());
    }
    for (int i = 0; i < list.size(); i++)
    {
       if(list.get(i).getCount() <= 0)
          list.get(i).setCount(-1 * list.get(i).getCount());
    }

    return mylist;
  }

/* 
 * Name: printWords 
 * Purpose: This method takes in an ArrayList of WordPairs and a boolean 
 * printToFile. If printToFile is true, it should output the 
 * arraylist of wordpair with their counts to a file named 
 * myOutput.out . If printToFile is false, it should print the 
 * arraylist of wordpair with their counts on the console
 * Parameters: ArrayLists<WordPair> (the arraylist that need to be
 * printed) boolean printToFile (true false value for control what to do) 
 * Return: void 
 */  
  public void printWords(ArrayList<WordPair>wordList,boolean printToFile) throws IOException{
    PrintWriter writer = null;
    if (printToFile == false)
    {
      System.out.print(wordList);
    }
    else
    {
      writer = new PrintWriter("myOutput.out");
      for (int i = 0; i < wordList.size(); i++)
      {
        writer.print(wordList.get(i).getWord() + "(" + wordList.get(i).getCount() + ")" + " ");
      }
      writer.close();
    }
  }
}
