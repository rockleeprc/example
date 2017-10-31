package com.tadu.io;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;

import com.tadu.bean.InputData;
import com.tadu.updchapter.InputYWData;
import com.tadu.util.FileHelper;

public class FileParser {
	private File file;

	private FileParser(File file) {
		this.file = file;
	}

	public static FileParser newInstance(File file) {
		return new FileParser(file);
	}

	public List<InputData> prepareQQ(String split) throws IOException {
		List<InputData> list = new ArrayList<>();
		LineIterator lineIter = FileHelper.lineIterator(file);
		try {
			while (lineIter.hasNext()) {
				String line = lineIter.next();
				String[] fields = line.split(split);
				String taduId = fields[0];
				String otherId = fields[1];
				list.add(new InputData(taduId, otherId));
			}
		} finally {
			LineIterator.closeQuietly(lineIter);
		}
		return list;
	}

	public List<InputData> prepare3G() throws IOException {
		List<InputData> list = new ArrayList<>();
		LineIterator lineIter = FileHelper.lineIterator(file);
		try {
			while (lineIter.hasNext()) {
				String line = lineIter.next();
				list.add(new InputData(null, line));
			}
		} finally {
			LineIterator.closeQuietly(lineIter);
		}
		return list;
	}

	public List<String> prepareYW() throws IOException {
		List<String> list = new ArrayList<>();
		LineIterator lineIter = FileHelper.lineIterator(file);
		try {
			while (lineIter.hasNext()) {
				String line = lineIter.next();
				list.add(line);
			}
		} finally {
			LineIterator.closeQuietly(lineIter);
		}
		return list;
	}

	public List<InputYWData> parseYW() {
		LineIterator lineIter = null;
		List<InputYWData> list = null;
		try {
			lineIter = FileHelper.lineIterator(file);
			list = new ArrayList<InputYWData>();
			while (lineIter.hasNext()) {
				String line = lineIter.next();
				if(StringUtils.isBlank(line)|| line.length()<50){
					continue;
				}
				InputYWData bean = createParseYWBean(line);
				list.add(bean);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			LineIterator.closeQuietly(lineIter);
		}
		return list;
	}
	
	private InputYWData createParseYWBean(String line){
		String[] arr = line.split("	");
		InputYWData bean = new InputYWData();
		bean.setTaduId(arr[0]);
		bean.setBookName(arr[1]);
		bean.setTaduPartId(arr[2]);
		bean.setChapterName(arr[3]);
		bean.setTaduCreateTime(arr[4]);
		bean.setTaduCBID(arr[5]);
		bean.setTaduCCID(arr[6]);
		//System.out.println(Arrays.toString(arr));
		return bean;
	}

}
