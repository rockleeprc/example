package com.tadu.constant;

import java.io.File;

public class FilePathConsts {
	
	private static final String REAL_PATH = System.getProperty("user.dir");
	private static final String SEPARATOR = File.separator;
	private static final String RESOURCES = "src"+SEPARATOR+"main"+SEPARATOR+"resources";
	
	public static final String YW_QUERY_BOOK_INFO=REAL_PATH+SEPARATOR+RESOURCES+SEPARATOR+"com"+SEPARATOR+"tadu"+SEPARATOR+"updchapter"+SEPARATOR+"data"+SEPARATOR+"query_book_info_test.txt";
	
	public static final String YW_INPUT_FILE_PATH = "E:" + File.separator + "workfile"+File.separator+"yw_book_id.txt";
	public static final String QQ_INPUT_FILE_PATH = "E:" + File.separator + "yw_book.txt";
	public static final String QQ_OUTPUT_FILE_PATH = "E:" + File.separator + "yw_time.txt";
	public static final String QQ_OUTPUT_FAIL_PATH = "E:" + File.separator + "yw_time_fail.txt";
	
	
	public static final String G3_INPUT_FILE_PATH = "E:" + File.separator + "3g_book.txt";
	public static final String G3_OUTPUT_FILE_PATH = "E:" + File.separator + "3g_time.txt";
	public static final String G3_OUTPUT_FAIL_PATH = "E:" + File.separator + "3g_time_fail.txt";
}
