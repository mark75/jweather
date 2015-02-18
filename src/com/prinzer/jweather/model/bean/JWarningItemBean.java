package com.prinzer.jweather.model.bean;

public class JWarningItemBean {

	private String title; // 山东省气象台发布大风黄色预警
	private String link; // http://www.weather.com.cn/alarm/newalarmcontent.shtml?file=10112-20150207162658-0502.html
	private String atype; // 大风
	private String alevel; // 黄色
	private String astatus; // 预警中
	private String img; // http://www.weather.com.cn/m2/i/alarm_s/0502.gif
	private String description; // 继续发布海上大风黄色预警信号：渤海今天夜间北风6～7级阵风8级逐渐减弱到5～6级，明天白天转西到西南风5～6级；渤海海峡、黄海北部和中部今天夜间北风7～8级阵风9～10级，明天白天逐渐减弱到5～6级。7日16时
	private String pubDate; // 2015-02-07 16:26:58


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getAtype() {
		return atype;
	}

	public void setAtype(String atype) {
		this.atype = atype;
	}

	public String getAlevel() {
		return alevel;
	}

	public void setAlevel(String alevel) {
		this.alevel = alevel;
	}

	public String getAstatus() {
		return astatus;
	}

	public void setAstatus(String astatus) {
		this.astatus = astatus;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

}
