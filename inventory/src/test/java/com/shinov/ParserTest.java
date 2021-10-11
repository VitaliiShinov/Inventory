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
		for(Item i : Parser.getListOfItems()){
			System.out.println(i.getName() + " " + i.getType()+ " " + i.getAttack()+ " " + i.getDefense());
		}

	}

}