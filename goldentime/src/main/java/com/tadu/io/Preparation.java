package com.tadu.io;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.LineIterator;

import com.tadu.bean.InputData;
import com.tadu.util.FileHelper;

public class Preparation {
	private File file;

	private Preparation(File file) {
		this.file = file;
	}

	public static Preparation newInstance(File file) {
		return new Preparation(file);
	}
	
	public  List<InputData> prepareQQ(String split) throws IOException{
		List<InputData> list = new ArrayList<>();
		LineIterator lineIter = FileHelper.lineIterator(file);
		try {
			while(lineIter.hasNext()){
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
	
	public  List<InputData> prepare3G() throws IOException{
		List<InputData> list = new ArrayList<>();
		LineIterator lineIter = FileHelper.lineIterator(file);
		try {
			while(lineIter.hasNext()){
				String line = lineIter.next();
				list.add(new InputData(null, line));
			}
		} finally {
			LineIterator.closeQuietly(lineIter);
		}
		return list;
	}
	
	public  List<String> prepareYW() throws IOException{
		List<String> list = new ArrayList<>();
		LineIterator lineIter = FileHelper.lineIterator(file);
		try {
			while(lineIter.hasNext()){
				String line = lineIter.next();
				list.add(line);
			}
		} finally {
			LineIterator.closeQuietly(lineIter);
		}
		return list;
	}
	
	
}
