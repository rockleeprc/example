package com.tadu.updchapter;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import com.tadu.bean.ChapterList;
import com.tadu.bean.ChapterList.Result;
import com.tadu.bean.ChapterList.Result.ResultData;
import com.tadu.constant.FilePathConsts;
import com.tadu.io.FileParser;

public class Main {
	public static void main(String[] args) throws IOException {
		List<InputYWData> parseData = FileParser.newInstance(new File(FilePathConsts.YW_QUERY_BOOK_INFO)).parseYW();
		
		RequestExecutor executor = RequestExecutor.getInstance();
		for(int i=0,len=parseData.size();i<len;i++){
			InputYWData data = parseData.get(i);
			ChapterList chapterlist = executor.chapterlist(1,1,data.getcBID());
			String returnCode = chapterlist.getReturnCode();
			data.setReturnCode(returnCode);
			if(StringUtils.equals("0", returnCode)){
				Result result = chapterlist.getResult();
				List<ResultData> results = result.getResultData();
				ResultData resultData = results.get(0);
				data.setcBID(resultData.getcBID());
				data.setcCID(resultData.getcCID());
				data.setChapterTitle(resultData.getChaptertitle());
				data.setUpdateDate(resultData.getUpdatetime());
			}
			System.out.println(data);
		}
		
		
	}
}
