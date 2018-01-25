/*
	AJAY NADHAVAJHALA
	CS 1C - Summer 2017 
	Professor David Harden
	Assignment 6 - Quadratic Probing with a find()
 	This program contains three classes. EBookCompInt,
 	EBookCompString, and Foothill. EBookCompInt is 
 	when you want to find the book by its integer key
 	and EBookCompString is when you want to find
 	it by its title or author. Foothill will test
 	these classes. 
 */

import cs_1c.*;
import java.util.*;

//----------- wrapper classes -------------

class EBookCompInt implements Comparable<Integer> {
	EBookEntry data;

	public EBookCompInt(EBookEntry e) {
		data = e;
	}

	public String toString() {
		return data.toString();
	}

	public int compareTo(Integer key) {
		return data.getETextNum() - key;
	}

	public boolean equals(EBookCompInt rhs) {
		return data.equals(rhs.data);
	}

	public int hashCode() {
		return data.getETextNum();
	}
}

class EBookCompString implements Comparable<String> {
	EBookEntry data;

	public EBookCompString(EBookEntry e) {
		data = e;
	}

	public String toString() {
		return data.toString();
	}

	public int compareTo(String key) {
		return data.getCreator().compareTo(key);
	}

	public boolean equals(EBookCompString rhs) {
		return data.equals(rhs.data);
	}

	public int hashCode() {
		return data.getCreator().hashCode();
	}
}

// ------------------------------------------------------
public class Foothill {

	public static final int NUM_RANDOM_INDICES = 25;

	// ------- main --------------
	public static void main(String[] args) throws Exception {
		int k;
		EBookEntryReader bookCatalog = new EBookEntryReader("catalog-short4.txt");

		FHhashQPwFind<String, EBookCompString> hashTableString = new FHhashQPwFind<String, EBookCompString>();
		FHhashQPwFind<Integer, EBookCompInt> hashTableInt = new FHhashQPwFind<Integer, EBookCompInt>();

		// these books will be used to test the classes
		// one for string, and one for int
		EBookCompString book1;
		EBookCompInt book2;

		String key1[] = new String[NUM_RANDOM_INDICES];
		int key2[] = new int[NUM_RANDOM_INDICES];
		EBookEntry temp = null;

		// test if it can read the name
		if (bookCatalog.readError()) {
			System.out.println("couldn't open " + bookCatalog.getFileName() + " for input.");
			return;
		}

		// populates both hash tables
		for (k = 0; k < NUM_RANDOM_INDICES; k++) {
			temp = bookCatalog.getBook(randomNum(0, bookCatalog.getNumBooks()));
			key1[k] = temp.getCreator();
			hashTableString.insert(new EBookCompString(temp));

			temp = bookCatalog.getBook(randomNum(0, bookCatalog.getNumBooks()));
			key2[k] = temp.getETextNum();
			hashTableInt.insert(new EBookCompInt(temp));
		}

		// testing first with an string key field
		System.out.println("hash table with string key:\n");
		hashTableString.display();

		System.out.println("\n25 entries using key:\n");
		for (k = 0; k < NUM_RANDOM_INDICES; k++) {
			try {
				book1 = hashTableString.find(key1[k]);
				System.out.println(String.format("Found book with key %s", key1[k]));
			} catch (NoSuchElementException e) {
				System.out.println(String.format("Failed to find book by key %s", key1[k]));
			}
		}
		// testing with an int key field
		System.out.println("\n25 entries using integer key:\n");
		hashTableInt.display();

		System.out.println("Attempt to find on the selected integer key:\n");
		for (k = 0; k < NUM_RANDOM_INDICES; k++) {
			try {
				book2 = hashTableInt.find(key2[k]);
				System.out.println(String.format("Found book with key %d", key2[k]));
			} catch (NoSuchElementException e) {
				System.out.println(String.format("Failed to find book by key %d", key2[k]));
			}
		}

		System.out.print("\n");
		// tests two objects that are not in the data
		try {
			book2 = hashTableInt.find(-25);
		} catch (NoSuchElementException e) {
			System.out.println(String.format("Failed to find book by key %d", -25));
		}

		try {
			book1 = hashTableString.find("Mitch Albom");
		} catch (NoSuchElementException e) {
			System.out.println(String.format("Failed to find book by key %s", "Mitch Albom"));
		}
	}

	// this creates a random number
	static int randomNum(int min, int max) {
		Random ran = new Random();
		int range = max - min + 1;
		int randomNum = ran.nextInt(range) + min;
		return randomNum;
	}
}

/****	OUTPUT 1

hash table with string key:

Array[ 0 ]    # 28043  ---------------
   "Ships in Harbour"
   by Morton, David, 1886-1957
   re: Poetry


Array[ 1 ]    # 26727  ---------------
   "The Political History of England - Vol XIFrom Addington's Administration to 
the close of WilliamIV.'s Reign (1801-1837)"
   by Brodrick, George C. (George Charles), 1831-1903
   re: Great Britain -- Politics and government -- 1800-1837


Array[ 3 ]    # 28538  ---------------
   "A Bookful of Girls"
   by Fuller, Anna, 1853-1916
   re: Short stories


Array[ 5 ]    # 10459  ---------------
   "The Celtic Twilight"
   by Yeats, W. B. (William Butler), 1865-1939
   re: (no data found)


Array[ 7 ]    # 28663  ---------------
   "The Rangeror The Fugitives of the Border"
   by Ellis, Edward Sylvester, 1840-1916
   re: Frontier and pioneer life -- Ohio -- Fiction


Array[ 12 ]    # 5900  ---------------
   "Umboo, the Elephant"
   by Garis, Howard Roger, 1873-1962
   re: (no data found)


Array[ 16 ]    # 29091  ---------------
   "The Complete Poetical Works of Samuel Taylor ColeridgeVol I (of II)"
   by Coleridge, Samuel Taylor, 1772-1834
   re: PR


Array[ 20 ]    # 24438  ---------------
   "From Fort Henry to Corinth"
   by Force, M. F. (Manning Ferguson), 1824-1899
   re: United States -- History -- Civil War, 1861-1865 -- Campaigns


Array[ 22 ]    # 29317  ---------------
   "There Will Be School Tomorrow"
   by Thiessen, V. E.
   re: Science fiction


Array[ 23 ]    # 17423  ---------------
   "Theme from Mozart's Piano Sonata in A major, K.331Arranged for Solo Guitar"
   by Mozart, Wolfgang Amadeus, 1756-1791
   re: (no data found)


Array[ 31 ]    # 26003  ---------------
   "Murray, Andrew, 1828-1917"
   by Murray, Andrew, 1828-1917
   re: Jesus Christ -- Significance


Array[ 34 ]    # 27530  ---------------
   "The Tailor and the CrowAn Old Rhyme with New Drawings"
   by Brooke, L. Leslie (Leonard Leslie), 1862-1940
   re: Children's poetry


Array[ 43 ]    # 3159  ---------------
   "The Hermit of Far End"
   by Pedler, Margaret, -1948
   re: England -- Fiction


Array[ 48 ]    # 26252  ---------------
   "The Prince and the Pauper"
   by Twain, Mark, 1835-1910
   re: Historical fiction


Array[ 49 ]    # 28743  ---------------
   "Miss Ashton's New PupilA School Girl's Story"
   by Robbins, Mrs. S. S.
   re: PZ


Array[ 50 ]    # 29272  ---------------
   "No Hiding Place"
   by Smith, Richard R.
   re: Science fiction


Array[ 62 ]    # 29249  ---------------
   "Harper's Young People, 1880 IndexAn Illustrated Weekly"
   by Various
   re: Children's periodicals, American


Array[ 63 ]    # 18823  ---------------
   "Modern Eloquence: Vol II, After-Dinner Speeches E-O"
   by Various
   re: (no data found)


Array[ 64 ]    # 30361  ---------------
   "The Stowaway"
   by Heiner, Alvin
   re: Science fiction


Array[ 65 ]    # 25609  ---------------
   "A Child's Garden of Verses"
   by Stevenson, Robert Louis, 1850-1894
   re: Children's poetry, English


Array[ 68 ]    # 25610  ---------------
   "A Child's Garden of Verses"
   by Stevenson, Robert Louis, 1850-1894
   re: Children's poetry, English


Array[ 69 ]    # 28531  ---------------
   "The Banner Boy Scouts SnowboundA Tour on Skates and Iceboats"
   by Warren, George A.
   re: Boy Scouts -- Juvenile fiction


Array[ 83 ]    # 2967  ---------------
   "Memoirs of Casanova — Volume 17: Return to Italy"
   by Casanova, Giacomo, 1725-1798
   re: Casanova, Giacomo, 1725-1798


Array[ 84 ]    # 26413  ---------------
   "Selections from early Middle English, 1130-1250Part I: Texts"
   by (no data found)
   re: English literature -- Middle English, 1100-1500


Array[ 93 ]    # 25847  ---------------
   "Patty's Friends"
   by Wells, Carolyn, 1862-1942
   re: Detective and mystery stories



25 entries using key:

Found book with key Twain, Mark, 1835-1910
Found book with key Mozart, Wolfgang Amadeus, 1756-1791
Found book with key Ellis, Edward Sylvester, 1840-1916
Found book with key Morton, David, 1886-1957
Found book with key Various
Found book with key Brodrick, George C. (George Charles), 1831-1903
Found book with key Thiessen, V. E.
Found book with key Robbins, Mrs. S. S.
Found book with key Pedler, Margaret, -1948
Found book with key Warren, George A.
Found book with key Heiner, Alvin
Found book with key Fuller, Anna, 1853-1916
Found book with key Various
Found book with key Stevenson, Robert Louis, 1850-1894
Found book with key Casanova, Giacomo, 1725-1798
Found book with key Murray, Andrew, 1828-1917
Found book with key Stevenson, Robert Louis, 1850-1894
Found book with key Yeats, W. B. (William Butler), 1865-1939
Found book with key Brooke, L. Leslie (Leonard Leslie), 1862-1940
Found book with key Garis, Howard Roger, 1873-1962
Found book with key Force, M. F. (Manning Ferguson), 1824-1899
Found book with key Smith, Richard R.
Found book with key Wells, Carolyn, 1862-1942
Found book with key (no data found)
Found book with key Coleridge, Samuel Taylor, 1772-1834

25 entries using integer key:

Array[ 0 ]    # 29391  ---------------
   "Blue-grass and Broadway"
   by Daviess, Maria Thompson, 1872-1924
   re: Broadway (New York, N.Y.) -- Fiction


Array[ 3 ]    # 29782  ---------------
   "Space, Time and GravitationAn Outline of the General Relativity Theory"
   by Eddington, Arthur Stanley, Sir, 1882-1944
   re: Relativity (Physics)


Array[ 4 ]    # 18822  ---------------
   "The House of Martha"
   by Stockton, Frank Richard, 1834-1902
   re: (no data found)


Array[ 7 ]    # 26779  ---------------
   "The Ghost"
   by O'Connor, William Douglas, 1832-1889
   re: Christmas stories


Array[ 8 ]    # 24937  ---------------
   "Mike MarbleHis Crotchets and Oddities."
   by Woodworth, Francis C. (Francis Channing), 1812-1859
   re: Conduct of life -- Juvenile fiction


Array[ 23 ]    # 15931  ---------------
   "A History of English Romanticism in the Nineteenth Century"
   by Beers, Henry A. (Henry Augustin), 1847-1926
   re: (no data found)


Array[ 34 ]    # 28552  ---------------
   "Twinkle and ChubbinsTheir Astonishing Adventures in Nature-Fairyland"
   by Baum, L. Frank (Lyman Frank), 1856-1919
   re: Fantasy


Array[ 37 ]    # 29234  ---------------
   "Presentation Pieces in the Museum of History and TechnologyContributions fro
m the Museum of History and Technology, Paper No. 47 [Smithsonian Institution]"
   by Klapthor, Margaret Brown, 1922-1994
   re: Silverwork


Array[ 42 ]    # 23904  ---------------
   "The Open Library"
   by Kahle, Brewster, 1960-
   re: (no data found)


Array[ 45 ]    # 25750  ---------------
   "Colonial BornA tale of the Queensland bush"
   by Scott, G. Firth
   re: Australia -- Fiction


Array[ 56 ]    # 27604  ---------------
   "A History of the Japanese PeopleFrom the Earliest Times to the End of the Me
iji Era"
   by Brinkley, F. (Frank), 1841-1912
   re: Japan -- Civilization


Array[ 66 ]    # 25189  ---------------
   "The Idler Magazine, Volume III, June 1893An Illustrated Monthly"
   by Various
   re: AP


Array[ 67 ]    # 3171  ---------------
   "In Defence of Harriet Shelley"
   by Twain, Mark, 1835-1910
   re: Shelley, Percy Bysshe, 1792-1822


Array[ 69 ]    # 29654  ---------------
   "The Wall Street Girl"
   by Bartlett, Frederick Orin, 1876-1945
   re: Wall Street (New York, N.Y.) -- Fiction


Array[ 70 ]    # 27327  ---------------
   "Agricultural Implements and Machines in the Collection of the National Museu
m of History and TechnologySmithsonian Studies in History and Technology, No. 17
"
   by Schlebecker, John T.
   re: Agricultural implements -- United States -- Exhibitions


Array[ 76 ]    # 27527  ---------------
   "Rules and Regulations of the Insane Asylum of CaliforniaPrescribed by the Re
sident Physician, August 1, 1861"
   by Stockton State Hospital (Calif.)
   re: Psychiatric hospitals -- California


Array[ 79 ]    # 25590  ---------------
   "Saru no ikigimo. English"
   by Chamberlain, Basil Hall, 1850-1935
   re: Fairy tales -- Japan


Array[ 80 ]    # 28500  ---------------
   "All About Coffee"
   by Ukers, William H. (William Harrison), 1873-1945
   re: Coffee


Array[ 83 ]    # 30056  ---------------
   "Punch, or, the London Charivari, Volume 98, March 8, 1890."
   by Various
   re: English wit and humor -- Periodicals


Array[ 85 ]    # 2025  ---------------
   "My Lady Caprice"
   by Farnol, Jeffery, 1878-1952
   re: Fiction


Array[ 89 ]    # 25018  ---------------
   "Amusement: A Force in Christian Training"
   by Vincent, Marvin Richardson, 1834-1922
   re: Christian life


Array[ 90 ]    # 30062  ---------------
   "The Plague"
   by Keller, Teddy
   re: Science fiction


Array[ 91 ]    # 25699  ---------------
   "The Battle of New Orleansincluding the Previous Engagements between the Amer
icansand the British, the Indians and the Spanish which led tothe Final Conflict
 on the 8th of January, 1815"
   by Smith, Z. F. (Zachariah Frederick), 1827-1911
   re: New Orleans, Battle of, New Orleans, La., 1815


Array[ 93 ]    # 26865  ---------------
   "The Corsair King"
   by Jókai, Mór, 1825-1904
   re: PH


Array[ 94 ]    # 27638  ---------------
   "The 2001 CIA World Factbook"
   by United States. Central Intelligence Agency
   re: World politics -- Handbooks, manuals, etc.


Attempt to find on the selected integer key:

Found book with key 27527
Found book with key 3171
Found book with key 15931
Found book with key 2025
Found book with key 29391
Found book with key 18822
Found book with key 25189
Found book with key 25018
Found book with key 28552
Found book with key 29782
Found book with key 25750
Found book with key 27327
Found book with key 30062
Found book with key 25590
Found book with key 23904
Found book with key 25699
Found book with key 29234
Found book with key 30056
Found book with key 24937
Found book with key 27604
Found book with key 26865
Found book with key 27638
Found book with key 28500
Found book with key 26779
Found book with key 29654

Failed to find book by key -25
Failed to find book by key Mitch Albom

*/