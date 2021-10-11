package com.shinov;

import static org.junit.jupiter.api.Assertions.*;

import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import com.shinov.parser.Parser;


class ParserTest {

	@Test
	void test() {
		Elements el = Parser.getElementsByTag("https://www.google.com/", "a");
		System.out.println(el.size());
		for(Element e:el) {
			System.out.println(e);
		}
	}

}
