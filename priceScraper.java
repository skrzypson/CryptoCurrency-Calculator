import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class priceScraper {
	
	static String templateUrl = "https://coinmarketcap.com/currencies/";
	
	// method that combines the main part of the url with the currency name string
	public static String urlBuilder(String extension){
		return templateUrl+extension;
	}
	
	// method that retrieves text from a specific web page reference in the form of a string
	public static String scrape(String t){
		
		try{
			t = urlBuilder(t);
			Document doc = Jsoup.connect(t).get();
			Elements myElement = doc.select("span[id=quote_price]");
			String text = myElement.text();
			StringBuilder sb = new StringBuilder(text);
			sb.deleteCharAt(0);
			return (text = sb.toString());
		}
		catch(IOException io){
			return "0.0";
		}
		
	}
	
}
