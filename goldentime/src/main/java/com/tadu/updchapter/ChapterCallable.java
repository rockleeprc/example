package com.tadu.updchapter;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;

import com.tadu.bean.ChapterList;
import com.tadu.bean.ChapterList.Result;
import com.tadu.bean.ChapterList.Result.ResultData;
import com.tadu.util.FileHelper;

public class ChapterCallable implements Runnable {

	private InputYWData data;

	public ChapterCallable() {
		super();
	}

	public ChapterCallable(InputYWData data) {
		super();
		this.data = data;
	}

	@Override
	public void run() {
		goToSleep();
		invoke();
	}

	private void invoke() {
		RequestExecutor executor = RequestExecutor.getInstance();
		ChapterList chapterlist = executor.chapterlist(1, 1, data.getTaduCBID());

		String returnCode = chapterlist.getReturnCode();
		data.setReturnCode(returnCode);
		if (StringUtils.equals("0", returnCode)) {
			Result result = chapterlist.getResult();
			List<ResultData> results = result.getResultData();
			if (!results.isEmpty()) {
				ResultData resultData = results.get(0);
				data.setcBID(resultData.getcBID());
				data.setcCID(resultData.getcCID());
				data.setChapterTitle(resultData.getChaptertitle());
				data.setUpdateDate(resultData.getUpdatetime());
			}else{
				System.out.println(data);
				try {
					FileHelper.writeLine("D:\\yw_chapter_timeout.txt",data.toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		try {
			FileHelper.writeLine("D:\\yw_chapter.txt", data.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void goToSleep() {
		Random r = new Random();
		try {
			TimeUnit.MILLISECONDS.sleep(r.nextInt(2000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
