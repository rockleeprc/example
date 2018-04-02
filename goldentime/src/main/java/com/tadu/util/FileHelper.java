package com.tadu.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;

public class FileHelper {
	public static void writeLine(String path,String date) throws IOException{
		writeLine(new File(path), date);
	}
	
	public static void writeLine(File file,String date) throws IOException{
		FileUtils.write(file, date+"\r\n","UTF-8",true);
	}

	public static LineIterator lineIterator(String path) throws IOException {
		return lineIterator(new File(path));
	}

	public static LineIterator lineIterator(File file) throws IOException {
		LineIterator it = FileUtils.lineIterator(file, "UTF-8");
		return it;
	}
	
	public static String readContent(String path) throws IOException{
		File file= new File(path);
		InputStream in = new FileInputStream(file);
		String content;
		try {
			  content= IOUtils.toString(in, "UTF-8");
		} finally {
			IOUtils.closeQuietly(in);
		}
		return content;
	}
}
