package k17.util;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import k17.bean.BookListJson;
import k17.bean.ChapterContentJson;
import k17.bean.VolumnAndChapterListJson;
import k17.keyt.Sign;
import k17.keyt.SignUtil;


public class K17Executor {

    private static final String PARTNER_ID = "1021";
    private static final String SECRET_KEY = "MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEA09lLyECaAfz64RM0J/E505rQfxbGTTT9Z/30Tuxbdqc7OwMwX1ZHe5tfxuj/VuaE5UAT3j7xZeijY/HHLijSDwIDAQABAkBRaE8WxLxpxy0hEKAaOThfeD5ml/nb8WDvdUdMjMcY8LdrQVjzNs1wIG3vLA5mRkd336TryBWGpeeyx4mfCWkBAiEA7i5izfmFTiiwzfqqsOroMG6Ds3gG557rjTt/SH+pbLsCIQDjspiI5dGeLGQ/BEDu7T8/VskYZRxgJ1HXIHxKPmfkvQIhAL9DKn6Cl4SK8meFmhoVmLyDkmjEwq6ulDLGi1CZi2DPAiEAyszwJMYknC/HnYTpXKS8d2qRs4Oi8VU0BFpvuSS6HjUCIGljzPDLvBCsOW30vx6PI0IFwuP3TpfBLfowlTlr+1v9";

    private static final String URL_PREFIX = "http://cup.17k.com/union/book/";
    private static final String URL_BOOK_LIST_JSON = "bookList.json";
    private static final String URL_VOLUMNAND_CHAPTER_LIST_JSON="volumnAndChapterList.json";
    private static final String URL_CHAPTER_CONTENT_JSON="chapterContent.json";

    //private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_FORMAT_ZERO_POINT = "yyyy-MM-dd 00:01:01";
    private static final String HAS_NEXT_PAGE_STATUS = "1";
    private static final String DATA_TEST="2011-08-17 17:50:35";

    public static final String SUCCESS_CODE="200";
    public static final String BOOK_STATUS_DONE="03";
    public static final String PART_VIP_STATUS="Y";


    private K17Executor(){}

    public static K17Executor getInstance(){
        return new K17Executor();
    }


    /**
     * 根据作品 id 获取卷和章节目录
     */
    public VolumnAndChapterListJson volumnAndChapterList(String bookId){
        Map<String, String> param = paramOfVolumeList(PARTNER_ID, bookId);
        final String url = URL_PREFIX+URL_VOLUMNAND_CHAPTER_LIST_JSON;
        String result=null;
        try {
            result = HttpHelper.doPost(url,param);
            //System.out.println("VolumnAndChapterList-->"+result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        VolumnAndChapterListJson dto = HttpParser.parseVolumnAndChapterList(result);
        return dto;
    }

    private Map<String,String> paramOfVolumeList(String partnerId,String bookId){
        Map<String, String> param = new HashMap<String, String>();
        param.put("partnerId", partnerId);
        param.put("bookId", bookId);
        String sign = createSign(param);
        param.put("sign", sign);
        return param;
    }


    /**
     * 获取 xxx 日期到现在有章节更新的图书列表
     */
    public List<BookListJson> bookList(){
        List<BookListJson> bookList = new ArrayList<BookListJson>();

        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_ZERO_POINT);
        String currentDate = sdf.format(new Date());
        int pageNo = 1;

        Map<String, String> param = paramOfBookList(PARTNER_ID, DATA_TEST, pageNo);
        String url = URL_PREFIX + URL_BOOK_LIST_JSON;
        try {
            String postResult = HttpHelper.doPost(url, param);
            BookListJson bookListJson = HttpParser.parseBookList(postResult);
            //System.out.println(bookListJson);
            bookList.add(bookListJson);

            boolean hasNextPage = HAS_NEXT_PAGE_STATUS.equals(bookListJson.getHasNext());
            while (hasNextPage) {
                pageNo++;
                param = paramOfBookList(PARTNER_ID, DATA_TEST, pageNo);
                postResult = HttpHelper.doPost(url, param);
                //System.out.println(postResult);

                bookListJson = HttpParser.parseBookList(postResult);
                bookList.add(bookListJson);

                hasNextPage = HAS_NEXT_PAGE_STATUS.equals(bookListJson.getHasNext());
                //System.out.println("bookList hasNextPage = "+hasNextPage);

                //loop sentinel
                if(pageNo>1000){
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookList;
    }


    private Map<String, String> paramOfBookList(String partnerId, String date, int pageNum) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("partnerId", partnerId);
        param.put("lastUpdateDate", date);
        param.put("page", String.valueOf(pageNum));
        String sign = createSign(param);
        param.put("sign", sign);
        return param;
    }

    private String createSign(Map<String, String> param) {
        String param_str = SignUtil.getParameterString(param);
        String sign = Sign.sign(param_str, SECRET_KEY);
        return sign;
    }

    public ChapterContentJson chapterContent(String bookId, String chapterId) {
        final  String url = URL_PREFIX+ URL_CHAPTER_CONTENT_JSON;
        Map<String, String> params = paramOfChapterContent(chapterId);
        String result = null;
        try {
            result = HttpHelper.doPost(url, params);
            //System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ChapterContentJson chapterContentJson = HttpParser.parseChapterContent(result);
        return chapterContentJson;
    }

    private Map<String, String> paramOfChapterContent(String chapterId) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("partnerId", PARTNER_ID);
        param.put("chapterId", chapterId);
        String sign = createSign(param);
        param.put("sign", sign);
        return param;

    }

}
