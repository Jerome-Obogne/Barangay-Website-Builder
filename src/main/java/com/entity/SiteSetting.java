package com.entity;

public class SiteSetting {

	private int site_id;
	private String site_title;
	private String site_logo;
	private String site_banner;
	private String site_tagline;
	private String site_mainImage;
	private String site_mainTitle;
	private String site_buttonTitle;
	private String site_buttonLink;
	
	
	
	
	
	

	
	public SiteSetting(int site_id, String site_title, String site_logo, String site_banner, String site_tagline,
			String site_mainImage, String site_mainTitle, String site_buttonTitle, String site_buttonLink) {
		super();
		this.site_id = site_id;
		this.site_title = site_title;
		this.site_logo = site_logo;
		this.site_banner = site_banner;
		this.site_tagline = site_tagline;
		this.site_mainImage = site_mainImage;
		this.site_mainTitle = site_mainTitle;
		this.site_buttonTitle = site_buttonTitle;
		this.site_buttonLink = site_buttonLink;
	}
	
	
	
	
	
	
	
	
	
	public SiteSetting(int site_id, String site_title, String site_tagline,  String site_mainTitle ,String site_buttonTitle,
			String site_buttonLink) {
		super();
		this.site_id = site_id;
		this.site_title = site_title;
		this.site_tagline = site_tagline;
		this.site_mainTitle = site_mainTitle;
		this.site_buttonTitle = site_buttonTitle;
		this.site_buttonLink = site_buttonLink;
	}









	public int getSite_id() {
		return site_id;
	}
	public String getSite_title() {
		return site_title;
	}
	public String getSite_logo() {
		return site_logo;
	}
	public String getSite_banner() {
		return site_banner;
	}
	public String getSite_tagline() {
		return site_tagline;
	}
	public String getSite_mainImage() {
		return site_mainImage;
	}
	public String getSite_mainTitle() {
		return site_mainTitle;
	}
	public String getSite_buttonTitle() {
		return site_buttonTitle;
	}
	public String getSite_buttonLink() {
		return site_buttonLink;
	}
	
	
	
	
	
	
	
	
	
	
	
}
