// Main file for EBookEntry project.  See Read Me file for details
// CS 1C, Foothill College, Michael Loceff, creator

import cs_1c.*;
import java.util.*;
import java.text.*;

//------------------------------------------------------
public class Foothill
{
   // -------  main --------------
   public static void main(String[] args) throws Exception
   {
      // how we read the data from files
      EBookEntryReader bookInput = new EBookEntryReader("catalog-short4.txt");
      int arraySize;

      // how we test the success of the read:
      if (bookInput.readError())
      {
         System.out.println("couldn't open " + bookInput.getFileName()
            + " for input.");
         return;
      }

      System.out.println(bookInput.getFileName());
      System.out.println(bookInput.getNumBooks());

      // create an array of objects for our own use:
      arraySize = bookInput.getNumBooks();
      EBookEntry[] bookArray = new EBookEntry[arraySize];
      for (int k = 0; k < arraySize; k++)
         bookArray[k] = bookInput.getBook(k);

      // how we time our algorithms -------------------------
      Date startTime, stopTime;
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);
      
      // display first 5 books (only) unsorted
      for (int k = 0; k < 5; k++)
         System.out.print(bookArray[k]);
      
      //get start time
      startTime = new Date();

      // sort
      EBookEntry.setSortType(EBookEntry.SORT_BY_TITLE);
      System.out.println( " Sorting ...\n" );
      FoothillSort.arraySort(bookArray);
      System.out.println( " Sorted!\n" );
      // how we determine the time elapsed -------------------
      stopTime = new Date();

      // display first 40 books (only) sorted
      for (int k = 0; k < 40; k++)
         System.out.print(bookArray[k]);

      // report algorithm time
      System.out.println("Algorithm Elapsed Time: "
         + tidy.format((stopTime.getTime() - startTime.getTime()) / 1000.)
         + " seconds.");
   }
}

/* -------------- First few lines and last few lines of run ----------------
catalog-short4.txt
4863
   # 30170  ---------------
   "Lonesome Hearts"
   by Winterbotham, R. R. (Russell Robert), 1904-1971
   re: Science fiction

   # 30169  ---------------
   "The Story of the White Mouse"
   by Unknown
   re: Conduct of life -- Juvenile fiction

   # 28546  ---------------
   "A History of England Principally in the Seventeenth Century, Volume I (of 6)
"
   by Ranke, Leopold von, 1795-1886
   re: Great Britain -- History -- Stuarts, 1603-1714

   # 28711  ---------------
   "Operas Every Child Should KnowDescriptions of the Text and Music of Some of 
the Most Famous Masterpieces"
   by Bacon, Mary Schell Hoke, 1870-1934
   re: Operas -- Stories, plots, etc.

   # 28805  ---------------
   "Dorothy's House Party"
   by Raymond, Evelyn, 1843-1910
   re: Orphans -- Juvenile fiction

 Sorting ...

 Sorted!

   # 28147  ---------------
   ""
   by Gillkrest, J. (James), -1853
   re: Gastroenteritis

   # 29388  ---------------
   ""
   by Velasquez, Pedro
   re: Central America -- Antiquities


   ... several entries skipped, then:

   # 28564  ---------------
   "A Bunch of CherriesA Story of Cherry Court School"
   by Meade, L. T., 1854-1914
   re: Schools -- Juvenile fiction

   # 28528  ---------------
   "A California Girl"
   by Eldridge, Edward
   re: California -- Fiction

   # 28466  ---------------
   "A Catalogue of Play Equipment"
   by Hunt, Jean Lee
   re: Play

   # 25493  ---------------
   "A Cathedral Courtship"
   by Wiggin, Kate Douglas Smith, 1856-1923
   re: Courtship -- Fiction

   # 18555  ---------------
   "A Chance Acquaintance"
   by Howells, William Dean, 1837-1920
   re: (no data found)

   # 28190  ---------------
   "A Chapter of Adventures"
   by Henty, G. A. (George Alfred), 1832-1902
   re: Alexandria (Egypt) -- History -- Bombardment, 1882 -- Juvenile fiction

Algorithm Elapsed Time: 0.546 seconds.

------------------------------------------------------- */