package com.kpluswebup.web.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("expressFormatItemVO")
public class ExpressFormatItemVO {

	public Long id;

	private String formatID;

	private Integer item;

	private String value;

	private Double xray;

	private Double yray;

	private Double width;

	private Double height;

	private Integer wordsize;

	private Integer font;

	private Integer intervals;

	private Integer linewidth;

	private Integer bold;

	private Integer italic;

	private Integer position;

	private String creator;

	private String modifier;

	private Date createTime;

	private Date modifyTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFormatID() {
		return formatID;
	}

	public void setFormatID(String formatID) {
		this.formatID = formatID;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Double getXray() {
		return xray;
	}

	public void setXray(Double xray) {
		this.xray = xray;
	}

	public Double getYray() {
		return yray;
	}

	public void setYray(Double yray) {
		this.yray = yray;
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Integer getWordsize() {
		return wordsize;
	}

	public void setWordsize(Integer wordsize) {
		this.wordsize = wordsize;
	}

	public Integer getFont() {
		return font;
	}

	public void setFont(Integer font) {
		this.font = font;
	}

	public Integer getIntervals() {
		return intervals;
	}

	public void setIntervals(Integer intervals) {
		this.intervals = intervals;
	}

	public Integer getLinewidth() {
		return linewidth;
	}

	public void setLinewidth(Integer linewidth) {
		this.linewidth = linewidth;
	}

	public Integer getBold() {
		return bold;
	}

	public void setBold(Integer bold) {
		this.bold = bold;
	}

	public Integer getItalic() {
		return italic;
	}

	public void setItalic(Integer italic) {
		this.italic = italic;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getItem() {
		return item;
	}

	public void setItem(Integer item) {
		this.item = item;
	}
}
