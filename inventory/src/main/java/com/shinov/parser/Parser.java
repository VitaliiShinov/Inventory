package com.shinov.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.shinov.Item;
import com.shinov.Item.Type;

public class Parser {

	private static String dir;
	private static int ID = 0;

	public static List<Item> getListOfItems() {
		List<Item> items = new ArrayList<>();
		List<Elements> elements = getElements("https://heroes.thelazy.net/index.php/List_of_artifacts_(HotA)");
		Item item;
		Random random = new Random();
		
		for (Elements tr : elements) {

			for (Element td : tr)

				switch (td.select("a").text()) {
				case "Torso":
					item = new Item();
					item.setName(tr.get(0).text());


					item.setAttack(random.nextInt(10));
					item.setDefense(random.nextInt(10));
					
					item.setType(Type.ARMOR);
					items.add(item);
					break;

				case "Helm":
					item = new Item();
					item.setName(tr.get(0).text());


					item.setAttack(random.nextInt(10));
					item.setDefense(random.nextInt(10));
					item.setType(Type.HELM);
					items.add(item);
					break;
					
				case "Weapon":
					item = new Item();
					item.setName(tr.get(0).text());


					item.setAttack(random.nextInt(10));
					item.setDefense(random.nextInt(10));
					item.setType(Type.WEAPON);
					items.add(item);
					break;
				case "Misc" :
				case "Feet":
				case "Necklace":
				case "Cape":	
					item = new Item();
					item.setName(tr.get(0).text());


					item.setAttack(random.nextInt(10));
					item.setDefense(random.nextInt(10));
					item.setType(Type.ARTIFACT);
					items.add(item);
					break;
				}
		}
		return items;
	}

	protected static List<Elements> getElements(String url) {
		List<Elements> result = new ArrayList<Elements>();

		String html = null;
		URL realURL;
		try {
			realURL = new URL(url);
			savePage(realURL);
			html = getFileContent();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Document doc = Jsoup.parse(html);
		Elements elements = doc.getElementsByTag("tr");
		for (Element e : elements) {
			result.add(e.select("td"));
		}
		return result;
	}

	private static void savePage(URL url) {
		URLConnection conn;

		dir = Paths.get("").toAbsolutePath().toString() + "/src/main/resources/parser.html";
		try {
			OutputStream out = new FileOutputStream(dir);
			conn = url.openConnection();
			conn.connect();
			InputStream is = conn.getInputStream();

			copy(is, out);
			is.close();
			out.close();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	private static void copy(InputStream from, OutputStream to) throws IOException {
		byte[] buffer = new byte[4096];
		while (true) {
			int numBytes = from.read(buffer);
			if (numBytes == -1) {
				break;
			}
			to.write(buffer, 0, numBytes);
		}
	}

	private static String getFileContent() throws IOException {
		FileInputStream fis = new FileInputStream(new File(dir));

		try (BufferedReader br = new BufferedReader(new InputStreamReader(fis))) {
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
				sb.append('\n');
			}
			return sb.toString();
		}
	}
}
