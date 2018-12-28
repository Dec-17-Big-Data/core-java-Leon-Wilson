package com.revature.eval.java.core;

import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EvaluationService {

	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	public String reverse(String string) {
		char[] reversed = new char[string.length()];
		for (int i = reversed.length - 1, j=0; i >= 0; i--, j++) {
			reversed[j] = string.charAt(i);
		}
		return new String(reversed);
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	public String acronym(String phrase) {
		// DONE
		String splitter = ",+\\s|\\s|-|/";
		String[] results = phrase.split(splitter);
		StringBuilder acro = new StringBuilder();
		for (String s : results) {
			acro.append(s.charAt(0));
		}
		return acro.toString().toUpperCase();
	}

	/**
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */
	static class Triangle {
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle() {
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree) {
			this();
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double sideOne) {
			this.sideOne = sideOne;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double sideTwo) {
			this.sideTwo = sideTwo;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double sideThree) {
			this.sideThree = sideThree;
		}

		public boolean isEquilateral() {
			//DONE
			if(this.sideOne == this.sideTwo) {
				if(this.sideOne == this.sideThree) {
					return true;
				}
			}
			return false;
		}

		public boolean isIsosceles() {
			//DONE
			if(this.sideOne == this.sideTwo || this.sideOne == this.sideThree || this.sideTwo == this.sideThree) {
				return true;
			}
			return false;
		}

		public boolean isScalene() {
			//DONE
			if(this.sideOne != this.sideTwo && this.sideOne != this.sideTwo && this.sideTwo != this.sideThree) {
				return true;
			}
			return false;
		}

	}

	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	public int getScrabbleScore(String string) {
		//DONE
		String onePoints = "AEIOULNRST";
		String twoPoints = "DG";
		String threePoints ="BCMP";
		String fourPoints = "FHVWY";
		String fivePoints = "K";
		String eightPoints = "JX";
		String tenPoints = "QZ";
		
		int score = 0;
		for(char c : string.toUpperCase().toCharArray()) {
			if(onePoints.indexOf(c) != -1) {
				score += 1;
			} else if(twoPoints.indexOf(c) != -1) {
				score += 2;
			} else if(threePoints.indexOf(c) != -1) {
				score += 3;
			} else if(fourPoints.indexOf(c) != -1) {
				score += 4;
			} else if(fivePoints.indexOf(c) != -1) {
				score += 5;
			} else if(eightPoints.indexOf(c) != -1) {
				score += 8;
			} else if(tenPoints.indexOf(c) != -1) {
				score += 10;
			}
		}
			
		return score;
	}

	/**
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	public String cleanPhoneNumber(String string) throws IllegalArgumentException {
		//DONE
		
		String regex = "\\+|\\(|\\)|\\s|-|\\.|[a-zA-Z]|@|:|!";
		
		String result = string.replaceAll(regex, "");
		
		if(result.length() < 10 || result.length() > 11) {
			throw new IllegalArgumentException("Number provided is not valid. Please check the number provided.");
		}
		return result;
	}

	/**
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Integer> wordCount(String string) {
		//DONE
		String regex = ",\\s?|\\s";
		String[] result = string.split(regex);
		Map<String, Integer> resultWordCount = new HashMap<>();
		for(String s : result) {
			if(resultWordCount.containsKey(s)) {
				resultWordCount.replace(s, Integer.valueOf(resultWordCount.get(s) + 1));
			} else {
				resultWordCount.put(s, 1);
			}
		}
		
		return resultWordCount;
	}

	/**
	 * 7. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	static class BinarySearch<T>{
		private List<T> sortedList;

		public int indexOf(T t) {
			//DONE
			int lowIndex = 0;
			int highIndex = (sortedList.size());
			int middle; 
			boolean found = false;
			while(!found) {
				middle = (int) ((lowIndex + highIndex) * 0.5);
				if(sortedList.get(middle).equals(t))
				{
					found = true;
					return middle;
				} else if(Integer.valueOf(t.toString()) > Integer.valueOf(sortedList.get(middle).toString())) {
					lowIndex += 1;
				} else {
					highIndex -= 1;
				}
			}
			return -1;
		}

		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}

	}

	/**
	 * 8. Implement a program that translates from English to Pig Latin.
	 * 
	 * Pig Latin is a made-up children's language that's intended to be confusing.
	 * It obeys a few simple rules (below), but when it's spoken quickly it's really
	 * difficult for non-children (and non-native speakers) to understand.
	 * 
	 * Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of
	 * the word. Rule 2: If a word begins with a consonant sound, move it to the end
	 * of the word, and then add an "ay" sound to the end of the word. There are a
	 * few more rules for edge cases, and there are regional variants too.
	 * 
	 * See http://en.wikipedia.org/wiki/Pig_latin for more details.
	 * 
	 * @param string
	 * @return
	 */
	
	
	public String toPigLatin(String string) {
		//DONE
		
		String vowels ="^[A|E|I|O|U].*";
		String regex = "\\s|-";
		String specials = "^[sch|th|qu].*";
		
		Pattern vowelP = Pattern.compile(vowels,Pattern.CASE_INSENSITIVE);
		Pattern specialsP = Pattern.compile(specials,Pattern.CASE_INSENSITIVE);
		
		String[] split = string.split(regex);
		int word_count = split.length;
		int word_num = 0;
		StringBuilder phrase = new StringBuilder();
		for (String s : split) {
			char[] word = new char[s.length() + 2];
			if(vowelP.matcher(s).matches()) {
				for(int i = 0; i < s.length();i++) {
					word[i] = s.charAt(i);
				}
				
				word[s.length()] = 'a';
				word[s.length() + 1] = 'y';
			} else{
				if(specialsP.matcher(s).matches()) {
					if(s.charAt(0) == 't' || s.charAt(0)== 'q') {
						for(int i = 2; i < s.length();i++) {
							word[i - 2] = s.charAt(i);
						}
						
						word[s.length() - 2] = s.charAt(0);
						word[s.length() - 1] = s.charAt(1);
						word[s.length()] = 'a';
						word[s.length() + 1] = 'y';
					} else{
						for(int i = 3; i < s.length();i++) {
							word[i - 3] = s.charAt(i);
						}
						word[s.length() - 3] = s.charAt(0);
						word[s.length() - 2] = s.charAt(1);
						word[s.length() - 1] = s.charAt(2);
						word[s.length()] = 'a';
						word[s.length() + 1] = 'y';
					}
				} else {
					for(int i = 1; i < s.length();i++) {
						word[i-1] = s.charAt(i);
					}
					
					word[s.length() - 1] = s.charAt(0);
					word[s.length()] = 'a';
					word[s.length() + 1] = 'y';
				}
			}
			phrase.append(word);
			word_num++;
			if(word_num < word_count) {
				phrase.append(" ");
			}
		}
		return phrase.toString();
	}

	/**
	 * 9. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isArmstrongNumber(int input) {
		//DONE
		String s = String.valueOf(input);
		int power = s.length();
		
		int sum = 0;
		for(char c : s.toCharArray()) {
			sum += Math.pow(Character.getNumericValue(c), power);
		}
		if(sum == input) {
			return true;
		}
		return false;
	}

	/**
	 * 10. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	
	public List<Long> calculatePrimeFactorsOf(long l) {
		//DONE
		List<Long> primeFactors = new ArrayList<Long>();
		int parts = 0;
		int p1 = 0;
		int p2 = 0;
		int n = 2;
		if(isPrime((int) l)) {
			primeFactors.add(l);
			return primeFactors;
		}
		
		while(parts == 0) {
			if(isFactor((int) l,n)) {
				p1 = n;
				p2 = Integer.valueOf((int)l/n);
				if(isPrime(p2)) {
					primeFactors.add((long)p1);
					primeFactors.add((long)p2);
					parts = 1;
				} else {
					primeFactors.add((long)p1);
					List<Long> fac = calculatePrimeFactorsOf((long)p2);
					for (Long long1 : fac) {
						primeFactors.add(long1);
					}
					return primeFactors;
				}
			} else {
				n += 1;
			}
		}
		return primeFactors;
	}
	
	public boolean isFactor(int num,int  n) {
		if(num % n == 0)
		{
			return true;
		}
		return false;
	}
	
	public boolean isPrime(int num) {
		if(num % 2 == 0 && num != 2) {return false;}
		if(num % 3 == 0 && num != 3) {return false;}
		
		int start = 5;
		int step = 4;
		
		int threshold = Integer.valueOf((int) (Math.sqrt(num) + 1));
		while(start < threshold) {
			if(num % start == 0) {
				return false;
			}
			step = 6-step;
			start = start + step;
		}
		return true;
	}

	/**
	 * 11. Create an implementation of the rotational cipher, also sometimes called
	 * the Caesar cipher.
	 * 
	 * The Caesar cipher is a simple shift cipher that relies on transposing all the
	 * letters in the alphabet using an integer key between 0 and 26. Using a key of
	 * 0 or 26 will always yield the same output due to modular arithmetic. The
	 * letter is shifted for as many values as the value of the key.
	 * 
	 * The general notation for rotational ciphers is ROT + <key>. The most commonly
	 * used rotational cipher is ROT13.
	 * 
	 * A ROT13 on the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
	 * stronger than the Atbash cipher because it has 27 possible keys, and 25
	 * usable keys.
	 * 
	 * Ciphertext is written out in the same formatting as the input including
	 * spaces and punctuation.
	 * 
	 * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
	 * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
	 * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
	 * quick brown fox jumps over the lazy dog.
	 */
	static class RotationalCipher {
		private int key;
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		public RotationalCipher(int key) {
			super();
			this.key = key;
		}

		StringBuilder sb = new StringBuilder();
		public String rotate(String string) {
			// DONE
			for(char c : string.toCharArray()) {
				if(alphabet.indexOf(Character.toLowerCase(c)) != -1) {
					boolean upper = (c == Character.toUpperCase(c))? true : false; 
					int x = alphabet.indexOf(Character.toLowerCase(c)) + 1;
					int encrypt = x + this.key;
					if (encrypt > 26) {
						encrypt -= 26;
					}
					
					if(upper) {
						sb.append(Character.toUpperCase(alphabet.charAt(encrypt - 1)));
					}else {
						sb.append(alphabet.charAt(encrypt - 1));
					}
				} else {
					sb.append(c);
				}
			}
			return sb.toString();
		}

	}

	/**
	 * 12. Given a number n, determine what the nth prime is.
	 * 
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
	 * that the 6th prime is 13.
	 * 
	 * If your language provides methods in the standard library to deal with prime
	 * numbers, pretend they don't exist and implement them yourself.
	 * 
	 * @param i
	 * @return
	 */
	
	
	public int calculateNthPrime(int i) throws IllegalArgumentException{
		//DONE		
		if(i <= 0) {
			throw new IllegalArgumentException("Argument given is not valid");
		}
		int count = 0;
		int n = 2;
		
		while(count != i) {
			if(isPrime(n)) {
				count += 1;
				if(count == i) {
					break;
				}
			}
			n += 1;
		}
		return n;
	}

	/**
	 * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {

		/**
		 * Question 13
		 * 
		 * @param string
		 * @return
		 */
		public static String encode(String string) {
			// DONE

			String noPunct = string.replaceAll("[^a-zA-Z0-9]", "");
			StringBuilder sb = new StringBuilder();
			String alphabet = "abcdefghijklmnopqrstuvwxyz";
			int count = 0;
			int size = 0;
			for(char c : noPunct.toCharArray()) {
				if(alphabet.indexOf(Character.toLowerCase(c)) != -1){
					int encrypt = 25 - alphabet.indexOf(Character.toLowerCase(c));
					
					sb.append(alphabet.charAt(encrypt));
				} else {
					sb.append(c);
				}
				
				count += 1;
				size += 1;
				if(count == 5 && size != noPunct.length()) {
					sb.append(" ");
					count = 0;
				}
			}
			return sb.toString();
		}

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {
			// DONE
			String alphabet = "abcdefghijklmnopqrstuvwxyz";
			StringBuilder sb = new StringBuilder();
			
			String noSpaces = string.replace(" ", "");
			for(char c : noSpaces.toCharArray()) {
				if(alphabet.indexOf(c) != -1) {
					int decrypt = 25 - alphabet.indexOf(c);
					
					sb.append(alphabet.charAt(decrypt));
				} else {
					sb.append(c);
				}
			}
			return sb.toString();
		}
	}

	/**
	 * 15. The ISBN-10 verification process is used to validate book identification
	 * numbers. These normally contain dashes and look like: 3-598-21508-8
	 * 
	 * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
	 * a digit or an X only). In the case the check character is an X, this
	 * represents the value '10'. These may be communicated with or without hyphens,
	 * and can be checked for their validity by the following formula:
	 * 
	 * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
	 * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
	 * otherwise it is invalid.
	 * 
	 * Example Let's take the ISBN-10 . We plug it in to the formula,
	 * and get:
	 * 
	 * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
	 * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isValidIsbn(String string) {
		// TODO Write an implementation for this method declaration
		
		String parsed = string.replaceAll("[^0-9|X]", "");
		if(parsed.length() != 10) {
			return false;
		}
		
		int sum = 0;
		for(int i = 10; i > 1; i--) {
			sum += Character.getNumericValue(parsed.charAt(10 - i)) * i;
		}
		
		if(parsed.charAt(9)== 'X') {
			sum += 10;
		} else {
			sum += Character.getNumericValue(parsed.charAt(9));
		}
		
		if (sum % 11 == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 16. Determine if a sentence is a pangram. A pangram (Greek: παν γράμμα, pan
	 * gramma, "every letter") is a sentence using every letter of the alphabet at
	 * least once. The best known English pangram is:
	 * 
	 * The quick brown fox jumps over the lazy dog.
	 * 
	 * The alphabet used consists of ASCII letters a to z, inclusive, and is case
	 * insensitive. Input will not contain non-ASCII symbols.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isPangram(String string) {
		//DONE
		String regex = "[a-zA-z]";
		Map<String,Integer> container = new HashMap<>();
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(string);
		
		while(m.find()) {
			System.out.println(m.group(0));
			if(!container.containsKey(m.group(0))){
				container.put(m.group(0), 0);
			}
		}
		
		if(container.size() == 26) {
			return true;
		}
		return false;
	}

	/** SKIP
	 * 17. Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 109 (1,000,000,000) seconds.
	 * 
	 * @param given
	 * @return
	 */
	public Temporal getGigasecondDate(Temporal given) {
		// SKIP Write an implementation for this method declaration
		return null;
	}

	/**
	 * 18. Given a number, find the sum of all the unique multiples of particular
	 * numbers up to but not including that number.
	 * 
	 * If we list all the natural numbers below 20 that are multiples of 3 or 5, we
	 * get 3, 5, 6, 9, 10, 12, 15, and 18.
	 * 
	 * The sum of these multiples is 78.
	 * 
	 * @param i
	 * @param set
	 * @return
	 */
	public int getSumOfMultiples(int i, int[] set) {
		//DONE
		int multSum = 0;
		for(int j = 0; j < i; j++) {
			for(int k = 0; k < set.length; k++) {
				if(j % set[k] == 0) {
					multSum += j;
					break;
				}
			}
		}
		return multSum;
	}

	/**
	 * 19. Given a number determine whether or not it is valid per the Luhn formula.
	 * 
	 * The Luhn algorithm is a simple checksum formula used to validate a variety of
	 * identification numbers, such as credit card numbers and Canadian Social
	 * Insurance Numbers.
	 * 
	 * The task is to check if a given string is valid.
	 * 
	 * Validating a Number Strings of length 1 or less are not valid. Spaces are
	 * allowed in the input, but they should be stripped before checking. All other
	 * non-digit characters are disallowed.
	 * 
	 * Example 1: valid credit card number 1 4539 1488 0343 6467 The first step of
	 * the Luhn algorithm is to double every second digit, starting from the right.
	 * We will be doubling
	 * 
	 * 4_3_ 1_8_ 0_4_ 6_6_ If doubling the number results in a number greater than 9
	 * then subtract 9 from the product. The results of our doubling:
	 * 
	 * 8569 2478 0383 3437 Then sum all of the digits:
	 * 
	 * 8+5+6+9+2+4+7+8+0+3+8+3+3+4+3+7 = 80 If the sum is evenly divisible by 10,
	 * then the number is valid. This number is valid!
	 * 
	 * Example 2: invalid credit card number 1 8273 1232 7352 0569 Double the second
	 * digits, starting from the right
	 * 
	 * 7253 2262 5312 0539 Sum the digits
	 * 
	 * 7+2+5+3+2+2+6+2+5+3+1+2+0+5+3+9 = 57 57 is not evenly divisible by 10, so
	 * this number is not valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isLuhnValid(String string) {
		// DONE
		String illegalRegex = "[a-z]|-";
		Pattern p = Pattern.compile(illegalRegex);
		Matcher m = p.matcher(string);
		if(m.matches()) {
			return false;
		}
		
		int sum = 0;
		int digit = 1;
		String onlyDigits = string.replace(" ", "");
		for(char c : onlyDigits.toCharArray()) {
			if(digit % 2 == 0) {
				int dub = Character.getNumericValue(c) * 2;
				if(dub > 9) {
					dub -= 9;
				}
				sum += dub;
			} else {
				sum += Character.getNumericValue(c);
			}
			digit += 1;
		}
		
		if(sum % 10 == 0) {
			return true;
		}
		
		return false;
	}

	/**
	 * 20. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) {
		// DONE
		List<String> expression = new ArrayList<>();
		String regex = "-?[0-9]*[0-9]\\b|(plus|minus|(multiplied|divided)\\sby)";
		Pattern p = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(string);
		
		while(m.find()) {
			expression.add(m.group().toLowerCase());
		}
		int i = 0;
		
		
		while(expression.size() != 1) {
			while(expression.indexOf("multiplied by") != -1 || expression.indexOf("divided by") != -1){
				int mIndex = expression.indexOf("multiplied by");
				int dIndex = expression.indexOf("divided by");
				
				int lowIndex = (mIndex != -1 && dIndex != -1)?
						(mIndex < dIndex)?
								mIndex
								:dIndex
						:(mIndex == -1)?
								dIndex
								:mIndex;
				int a = (lowIndex == mIndex)? 
						Integer.valueOf(expression.get(lowIndex-1)) * Integer.valueOf(expression.get(lowIndex+1))
						:Integer.valueOf(expression.get(lowIndex-1)) / Integer.valueOf(expression.get(lowIndex+1));
				squish(expression,lowIndex,a);
			}
			if (expression.get(i).contains("plus")||expression.get(i).contains("minus")) {
				int a = (expression.get(i).contains("plus"))? 
						Integer.valueOf(expression.get(i-1)) + Integer.valueOf(expression.get(i+1))
						:Integer.valueOf(expression.get(i-1)) - Integer.valueOf(expression.get(i+1));
				squish(expression,i,a);
			} else {
				i += 1;
			}
		}
		return Integer.valueOf(expression.get(0));
	}
	
	public void squish(List<String> expression, int index,int value) {
		expression.remove(index + 1);
		expression.remove(index);
		expression.remove(index - 1);
		expression.add(index-1,String.valueOf(value));
	}

}
