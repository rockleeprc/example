package com.tadu.bean;

/**
 * 阅文bookinfo接口映射
 * 
 * @author Administrator
 *
 */
public class BookInfo extends ReturnCode {
	private Result result;

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public static class Result {
		private Book book;

		public Book getBook() {
			return book;
		}

		public void setBook(Book book) {
			this.book = book;
		}

		public static class Book {
			private int allwords;// 总字数
			private String authorid;// 作者id
			String authorname;// 作者名
			private String cBID;// 书号
			/*
			 * 1：创世 2：云起 3：起点女生 5: 起点男生 9：起点文学网 24：言情小说吧 66960: 红袖添香 56029：小说阅读网
			 * 47626：潇湘书院
			 */
			private String cPID;

			private int categoryid;// 阅文二级分类
			// 计费方式 1:按章 2:按本
			private int chargetype;
			private String coverurl;// 封面url
			private String createtime;// 入库时间
			private int form;// -1:原创 1:出版
			private String intro;// 简介
			private String keyword;// 关键字
			private String monthlyallowed;// 是否允许包月 -1: 否 1:是
			private String monthlytime;// 允许包月时间
			private String canclemonthlytime;// 取消包月时间
			private int signtype;
			/*
			 * 5: 起点男生 1：创世 2：云起 3：起点女生 6：阅文集团 9：起点文学网 24：言情小说吧
			 */
			private int site;// 作品在阅文的首发站点，可以体现授权来源
			/*
			 * 进度状态 30:连载中 40:暂停中 45:完结申请 50:已完结 (101: 封 笔 102:undefined 103: 精
			 * 彩 纷 呈 104: 接 近 尾 声 105: 新 书 上 传 106: 情 节 展 开 107:出版中)
			 */
			private int status;// 一本作品的状态
			private int subcategoryid;// 三级分类
			private String tag;// 标签
			private String title;// 小说名
			private Integer totalprice;// 按本计费价格
			private int unitprice;// 千字价格 单位:分
			private String updatetime;// 最近一个章节的更新时间
			/*
			 * Vip 标志 -1: 公众 1:VIP
			 */
			private int vipstatus;
			private String viptime;// 被设置成为收费作品的时间

			

			public int getAllwords() {
				return allwords;
			}



			public void setAllwords(int allwords) {
				this.allwords = allwords;
			}



			public String getAuthorid() {
				return authorid;
			}



			public void setAuthorid(String authorid) {
				this.authorid = authorid;
			}



			public String getAuthorname() {
				return authorname;
			}



			public void setAuthorname(String authorname) {
				this.authorname = authorname;
			}



			public String getcBID() {
				return cBID;
			}



			public void setcBID(String cBID) {
				this.cBID = cBID;
			}



			public String getcPID() {
				return cPID;
			}



			public void setcPID(String cPID) {
				this.cPID = cPID;
			}



			public int getCategoryid() {
				return categoryid;
			}



			public void setCategoryid(int categoryid) {
				this.categoryid = categoryid;
			}



			public int getChargetype() {
				return chargetype;
			}



			public void setChargetype(int chargetype) {
				this.chargetype = chargetype;
			}



			public String getCoverurl() {
				return coverurl;
			}



			public void setCoverurl(String coverurl) {
				this.coverurl = coverurl;
			}



			public String getCreatetime() {
				return createtime;
			}



			public void setCreatetime(String createtime) {
				this.createtime = createtime;
			}



			public int getForm() {
				return form;
			}



			public void setForm(int form) {
				this.form = form;
			}



			public String getIntro() {
				return intro;
			}



			public void setIntro(String intro) {
				this.intro = intro;
			}



			public String getKeyword() {
				return keyword;
			}



			public void setKeyword(String keyword) {
				this.keyword = keyword;
			}



			public String getMonthlyallowed() {
				return monthlyallowed;
			}



			public void setMonthlyallowed(String monthlyallowed) {
				this.monthlyallowed = monthlyallowed;
			}



			public String getMonthlytime() {
				return monthlytime;
			}



			public void setMonthlytime(String monthlytime) {
				this.monthlytime = monthlytime;
			}



			public String getCanclemonthlytime() {
				return canclemonthlytime;
			}



			public void setCanclemonthlytime(String canclemonthlytime) {
				this.canclemonthlytime = canclemonthlytime;
			}



			public int getSigntype() {
				return signtype;
			}



			public void setSigntype(int signtype) {
				this.signtype = signtype;
			}



			public int getSite() {
				return site;
			}



			public void setSite(int site) {
				this.site = site;
			}



			public int getStatus() {
				return status;
			}



			public void setStatus(int status) {
				this.status = status;
			}



			public int getSubcategoryid() {
				return subcategoryid;
			}



			public void setSubcategoryid(int subcategoryid) {
				this.subcategoryid = subcategoryid;
			}



			public String getTag() {
				return tag;
			}



			public void setTag(String tag) {
				this.tag = tag;
			}



			public String getTitle() {
				return title;
			}



			public void setTitle(String title) {
				this.title = title;
			}



			public Integer getTotalprice() {
				return totalprice;
			}



			public void setTotalprice(Integer totalprice) {
				this.totalprice = totalprice;
			}



			public int getUnitprice() {
				return unitprice;
			}



			public void setUnitprice(int unitprice) {
				this.unitprice = unitprice;
			}



			public String getUpdatetime() {
				return updatetime;
			}



			public void setUpdatetime(String updatetime) {
				this.updatetime = updatetime;
			}



			public int getVipstatus() {
				return vipstatus;
			}



			public void setVipstatus(int vipstatus) {
				this.vipstatus = vipstatus;
			}



			public String getViptime() {
				return viptime;
			}



			public void setViptime(String viptime) {
				this.viptime = viptime;
			}



			@Override
			public String toString() {
				return "Book [allwords=" + allwords + ", authorid=" + authorid + ", authorname=" + authorname
						+ ", cBID=" + cBID + ", cPID=" + cPID + ", categoryid=" + categoryid + ", chargetype="
						+ chargetype + ", coverurl=" + coverurl + ", createtime=" + createtime + ", form=" + form
						+ ", intro=" + intro + ", keyword=" + keyword + ", monthlyallowed=" + monthlyallowed
						+ ", monthlytime=" + monthlytime + ", canclemonthlytime=" + canclemonthlytime + ", signtype="
						+ signtype + ", site=" + site + ", status=" + status + ", subcategoryid=" + subcategoryid
						+ ", tag=" + tag + ", title=" + title + ", totalprice=" + totalprice + ", unitprice="
						+ unitprice + ", updatetime=" + updatetime + ", vipstatus=" + vipstatus + ", viptime=" + viptime
						+ "]";
			}
		}
	}

	@Override
	public String toString() {
		return result.getBook().toString();
	}

}
