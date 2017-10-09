package com.tadu.main;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.LineIterator;

import com.tadu.util.FileHelper;

public class CheckMain {

	private final static String INPUT_FILE_PATH = "E:" + File.separator + "yw3" + File.separator + "yw_time.txt";
	private final static String INPUT_FILE_PATH_TEST = "E:" + File.separator + "test.txt";

	public static void main(String[] args) throws IOException {
		Map<String, Integer> map = new HashMap<>();
		LineIterator lineIterator = FileHelper.lineIterator(INPUT_FILE_PATH);
		while (lineIterator.hasNext()) {
			String line = (String) lineIterator.next();
			String[] fields = line.split(",");
			String taduId = fields[0];
			Integer count = map.get(taduId);
			if (count != null) {
				map.put(taduId, count + 1);
			} else {
				map.put(taduId, 1);
			}
		}
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			if (entry.getValue() > 1) {
				System.out.println(entry.getKey()+","+entry.getValue());
			}
		}

	}
}
