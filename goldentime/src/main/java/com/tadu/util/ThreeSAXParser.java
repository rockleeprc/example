package com.tadu.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class ThreeSAXParser {
	public static class Parser3G extends DefaultHandler {

		private String qName;
		private String otherId;
		private String uptime;

		@Override
		public void startDocument() throws SAXException {
			super.startDocument();
		}

		@Override
		public void endDocument() throws SAXException {
			super.endDocument();
		}

		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes)
				throws SAXException {
			this.qName = qName;
		}

		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			// TODO Auto-generated method stub
			super.endElement(uri, localName, qName);
		}

		@Override
		public void characters(char[] ch, int start, int length) throws SAXException {
			// TODO Auto-generated method stub
			super.characters(ch, start, length);
			String data = new String(ch, start, length);
			if ("uptime".equals(qName)) {
				this.uptime = data;
			}
			if ("id".equals(qName)) {
				this.otherId = data;
			}
		}

		public String getOtherId() {
			return otherId;
		}

		public void setOtherId(String otherId) {
			this.otherId = otherId;
		}

		public String getUptime() {
			return uptime;
		}

		public void setUptime(String uptime) {
			this.uptime = uptime;
		}


	}

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		String otherId = "244876";
		String key = KeyUtils.threeGKeyHex(otherId);
		String url = "http://book.interface.3gsc.com.cn/index.php";
		System.out.println(url);
		Map<String, String> param = new HashMap<>();
		param.put("m", "AccessBook");
		param.put("a", "accessbook");
		param.put("h", key);
		param.put("un", "tadu");
		param.put("bid", otherId);
		String result = HttpUtils.httpGet(url, param);
		System.out.println(result);
		// XMLReader reader = XMLReaderFactory.createXMLReader();
		// Parser3G parser = new Parser3G();
		// reader.setContentHandler(parser);
		// reader.parse(new InputSource(new
		// ByteArrayInputStream(result.getBytes())));
		// System.out.println(parser.getId()+","+parser.getOtherId());

		// SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
		// Parser3G parser = new Parser3G();
		// saxParser.parse(new InputSource(new
		// ByteArrayInputStream(result.getBytes())),parser);
		// System.out.println(parser.getId()+","+parser.getOtherId());
	}
}
