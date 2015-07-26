package JSE.cc.parser0001;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ansj.splitWord.analysis.NlpAnalysis;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Hello world!
 *
 */
public class App {
	String outputname;
	static String filepath = "C:\\Users\\Home\\workspace\\parser0001\\src\\main\\java\\JSE\\cc\\parser0001\\2011";
	static File file = new File(filepath);
	static File readSingleFile;
	static BufferedReader reader;
    static String Reg = "共计[0-9]*元";
	public static void main(String[] args) {
		getDirectory(file);
		NlpAnalysis.parse(Jsoup.parse(text).text().toString());
	}

	private static void getDirectory(File file2) {
		// TODO Auto-generated method stub
		File fList[] = file2.listFiles();
		if (fList == null || fList.length == 0) {
			System.out.println("No Such File");
		}
		for (File f : fList) {
			if (f.isDirectory()) {
				System.out.println("Dir==>" + f.getAbsolutePath());
				getDirectory(f);
			} else {
				System.out.println("file==>" + f.getAbsolutePath());
				try {
					String fName = f.getName();
					// System.out.println(fName);
					OpenFileandCheck(f.getAbsolutePath(), fName);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private static void  OpenFileandCheck(String absolutePath, String fName)
			throws IOException {
		// TODO Auto-generated method stub
		Pattern patter = Pattern.compile(Reg);
		File file = new File(absolutePath);
		reader = new BufferedReader(new FileReader(file));
		String text = null;
		while (reader.ready() && (text = reader.readLine()) != null) {
			text += reader.readLine();
			Document doc = Jsoup.parse(text);		
			System.out.println();
//			 Matcher match = patter.matcher(Jsoup.parse(text).text());
//	           while(match.find()){
//	        	  
//	           }
			Elements date = doc.select("div.annexInfo span");
			if (!date.isEmpty()) {
				System.out.println(date.first().text());
				String month = date.first().text().toString().substring(4, 6);
				System.out.println(month);
				// SWITCH(month,absolutePath,fName);
			}
          
		}

	}

	private static void SWITCH(String month, String absolutePath, String fName)
			throws IOException {
		// TODO Auto-generated method stub
		String dst = "C:\\Users\\Home\\workspace\\parser0001\\src\\main\\java\\JSE\\cc\\parser0001\\output\\1st\\";
		String dst2 = "C:\\Users\\Home\\workspace\\parser0001\\src\\main\\java\\JSE\\cc\\parser0001\\output\\2nd\\";
		String dst3 = "C:\\Users\\Home\\workspace\\parser0001\\src\\main\\java\\JSE\\cc\\parser0001\\output\\3rd\\";
		String dst4 = "C:\\Users\\Home\\workspace\\parser0001\\src\\main\\java\\JSE\\cc\\parser0001\\output\\4th\\";
		if (month.equals("01") || month.equals("02") || month.equals("03")) {
			System.out.println("1st");
			File File = new File(absolutePath);
			File Output = new File(dst + fName);
			Files.copy(File.toPath(), Output.toPath(),
					StandardCopyOption.REPLACE_EXISTING);
		} else {
			if (month.equals("04") || month.equals("05") || month.equals("06")) {

				File File = new File(absolutePath);
				File Output = new File(dst2 + fName);
				Files.copy(File.toPath(), Output.toPath(),
						StandardCopyOption.REPLACE_EXISTING);
				System.out.println("2nd");
			} else {
				if (month.equals("07") || month.equals("08")
						|| month.equals("09")) {
					File File = new File(absolutePath);
					File Output = new File(dst3 + fName);
					Files.copy(File.toPath(), Output.toPath(),
							StandardCopyOption.REPLACE_EXISTING);
					System.out.println("3rd");
				} else {
					File File = new File(absolutePath);
					File Output = new File(dst4 + fName);
					Files.copy(File.toPath(), Output.toPath(),
							StandardCopyOption.REPLACE_EXISTING);
					System.out.println("4th");
				}
			}
		}
	}

}
