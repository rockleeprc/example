package com.tadu.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.LineIterator;

import com.tadu.bean.InputData;
import com.tadu.util.FileHelper;

public class T {

	public static void main(String[] args) throws IOException {
		List<InputData> list = new ArrayList<>();
		LineIterator lineIterator = FileHelper.lineIterator("E:" + File.separator + "yw_id.txt");
		while (lineIterator.hasNext()) {
			String line = lineIterator.next();
			if (line.indexOf("id") != -1) {
				continue;
			}
			String[] fields = line.split("	");
			String taduId = fields[0];
			String otherId = fields[1];
			list.add(new InputData(taduId, otherId));
		}
		LineIterator.closeQuietly(lineIterator);
		System.out.println(list.size());

	}

}
