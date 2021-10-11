package com.shinov.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Paths;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Parser {

	private static String dir;

	public static Elements getElementsByTag(String url, String tag) {
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

		return doc.getElementsByTag(tag);
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
