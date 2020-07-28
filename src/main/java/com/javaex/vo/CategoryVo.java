package com.javaex.vo;

public class CategoryVo {
	// 필드
	private int cateNo,postCount;
	private String id, cateName, description, regDate;

	// 생성자
	public CategoryVo() {

	}

	public CategoryVo(String id,int cateNo) {
		this.id = id;
		this.cateNo = cateNo;
	}

	public CategoryVo(int cateNo, String id, String cateName, String description, String regDate) {
		this.cateNo = cateNo;
		this.id = id;
		this.cateName = cateName;
		this.description = description;
		this.regDate = regDate;
	}

	// g.s
	public int getCateNo() {
		return cateNo;
	}

	public void setCateNo(int cateNo) {
		this.cateNo = cateNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getPostCount() {
		return postCount;
	}

	public void setPostCount(int postCount) {
		this.postCount = postCount;
	}
	//일반 메소드

	@Override
	public String toString() {
		return "CategoryVo [cateNo=" + cateNo + ", postCount=" + postCount + ", id=" + id + ", cateName=" + cateName
				+ ", description=" + description + ", regDate=" + regDate + "]";
	}
	
	
}
