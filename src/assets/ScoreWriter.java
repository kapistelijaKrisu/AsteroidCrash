package assets;

import java.util.Arrays;
import java.util.Collections;

public class ScoreWriter {	
	
	private static Integer[] topTen = {0,0,0,0,0,0,0,0,0,0,0};
	
	public static String readTopTen() {
		StringBuilder build = new StringBuilder("");
		for (int i = 0; i < 10; i++) {
			build.append(topTen[i]).append("\n");
		}
		return build.toString();
	}
	public static void writeTopTen(int score) {
		if (score <= topTen[10]) {
			return;
		}
		topTen[10] = score;
		Arrays.sort(topTen, Collections.reverseOrder());
	}
	
	/*public static  String readScores() {
		try {
		InputStream stream = ScoreWriter.class.getResourceAsStream("/scores.txt");
		Scanner scanner = new Scanner(stream);
		
		StringBuilder build = new StringBuilder("");
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				build.append(line).append("\n");
				}
			scanner.close();
			return build.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void writeTopTen(int score) {
		
		try{ //add 11th score
		String file = ScoreWriter.class.getResourceAsStream("/scores.txt").toString();
		FileWriter writer = new FileWriter(file, true);
		writer.write(Integer.toString(score) + "\n");		
		writer.close();
			
		//sort
		Integer[] scoreArray = sortScores();
		//return if score less than top10
		if (score < scoreArray[9]) {
			return;
		}
		
		//write only top 10
		FileWriter update = new FileWriter(file, false);
		for (int i = 0; i < 10; i++) {
			update.write(Integer.toString(scoreArray[i]) + System.lineSeparator());
			System.out.println(scoreArray[i]);
		}
		update.close();
		System.out.println(file);
		System.out.println(readScores());
		
		} catch(Exception e) {
			System.out.println("make sure you have 10lines in your file");
			e.printStackTrace();
		}
	}
	
	private static Integer[] sortScores() {
		String scores = readScores();
		String[] arrayScores = scores.split("\n");

		
		Integer[] scoresInInt = new Integer[arrayScores.length];
		for (int i = 0; i < arrayScores.length; i++) {
			try {
			scoresInInt[i] = Integer.parseInt(arrayScores[i]);
			} catch (Exception e) {
				System.out.println("at "+ i + "failed to parse int");
			}
		}
		Arrays.sort(scoresInInt, Collections.reverseOrder());

		return scoresInInt;
	}*/

	/*//test
	String curScores = readScores();
	String[] arrayScores = curScores.split("\n");
	for (int i = 0; i < 10; i++) {
		System.out.println(arrayScores[i]);
	}*/
	
}
