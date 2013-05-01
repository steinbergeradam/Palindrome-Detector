import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Palidrome Detector reads strings from file line by line and detects whether
 * or not the strings are palindromes.
 * 
 * @author Adam Steinberger
 * 
 */
public class PalindromeDetector {

	/**
	 * The name of the input file to parse through
	 */
	private static String fileName;

	/**
	 * Read in strings from file line by line and detect whether or not the
	 * strings are palindromes
	 * 
	 * @param file
	 *            the name of the input file to parse through
	 */
	public PalindromeDetector(String file) {

		fileName = file;

		// parse through the input file, or exit the program if an error occurs
		try {
			parseInputFile();
		} catch (Exception e) {
			System.err
					.println("Error:  There was a problem reading the input file!");
		}

	}

	/**
	 * Read through the input file line by line and parse it
	 * 
	 * @throws Exception
	 *             the input file must exist for this program to run correctly
	 */
	private static void parseInputFile() throws Exception {

		// get ready to read the input file line by line
		FileReader fileReader = new FileReader(fileName);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		// this is where each line is stored during file reading
		String line;

		// read the input file line by line
		while ((line = bufferedReader.readLine()) != null) {

			// remove the "non-commit" character automatically added to the
			// beginning of the input file by the source control manager
			line = line.trim().replace("\ufeff", "");

			// skip the current line if it is empty
			if (line.length() == 0)
				continue;

			// remove all non alphanumeric characters from the current line in
			// the input file, and then convert the string to all lower case
			// characters
			String optimized = line.replaceAll("\\W", "").toLowerCase();

			// convert the optimized line into a string buffer
			StringBuffer buffer = new StringBuffer(optimized);

			// now reverse the optimized string buffer
			StringBuffer reverseBuffer = buffer.reverse();

			// convert the reverse of the optimized string buffer back into a
			// string
			String reverse = reverseBuffer.toString();

			// compare the optimized string to its reverse, and if they are the
			// same then the current line is a palindrome
			String isPalindrome = "";
			if (!optimized.equals(reverse))
				isPalindrome = "not ";

			// print out the result for the current line
			System.out.println("\"" + line + "\" is " + isPalindrome
					+ "a palindrome.");

		}

		// close the input file reader
		bufferedReader.close();

	}

	/**
	 * Run the Palindrome Detector program from here.
	 * 
	 * @param args
	 *            inputFileName
	 */
	public static void main(String[] args) {

		// get the number of command line arguments provided from the console
		int numArgs = args.length;

		// exit the program if the input file name is not provided as a command
		// line argument
		if (numArgs != 1) {
			System.err
					.println("Error:  No input file was provided as a command line argument for this program!");
			return;
		}

		// get input file name from the command line argument provided
		String inputFileName = args[0];

		// exit the program if there's a problem reading the input file
		try {

			// run a new instance of the palindrome detector for the input file
			new PalindromeDetector(inputFileName);

		} catch (Exception e) {
			System.err
					.println("Error:  There was a problem reading the input file!");
			return;
		}

	}

}
