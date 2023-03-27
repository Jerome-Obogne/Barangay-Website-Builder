package com.entity;

public class FooterSetting {

	/* Footer Setting attribute*/
	
	private int footer_id;
	private String footer_title;
	private String footer_paragraph;
	
	private String footer_title_col2;
	private String footer_serviceList1;
	private String footer_serviceList2;
	private String footer_serviceList3;
	private String footer_serviceList4;
	
	private String footer_title_col3;
	private String footer_contactList1;
	private String footer_contactList2;
	private String footer_contactList3;
	private String footer_contactList4;

	
	
	
	
		/*Attribute for Column 1 */
	
	public FooterSetting(int footer_id, String footer_title, String footer_paragraph) {
		super();
		this.footer_id = footer_id;
		this.footer_title = footer_title;
		this.footer_paragraph = footer_paragraph;
	}
	
	/*Attribute for Column 2 & Column 3  */
	public FooterSetting(int footer_id, String footer_title_col2, String footer_serviceList1,
			String footer_serviceList2, String footer_serviceList3, String footer_serviceList4) {
		super();
		this.footer_id = footer_id;
		this.footer_title_col2 = footer_title_col2;
		this.footer_serviceList1 = footer_serviceList1;
		this.footer_serviceList2 = footer_serviceList2;
		this.footer_serviceList3 = footer_serviceList3;
		this.footer_serviceList4 = footer_serviceList4;
	}
	
	
 
	/* Attribute for All to display Data*/
	
	   public FooterSetting(int footer_id, String footer_title, String footer_paragraph, String footer_title_col2,
				String footer_serviceList1, String footer_serviceList2, String footer_serviceList3,
				String footer_serviceList4, String footer_title_col3, String footer_contactList1,
				String footer_contactList2, String footer_contactList3, String footer_contactList4) {
			super();
			this.footer_id = footer_id;
			this.footer_title = footer_title;
			this.footer_paragraph = footer_paragraph;
			this.footer_title_col2 = footer_title_col2;
			this.footer_serviceList1 = footer_serviceList1;
			this.footer_serviceList2 = footer_serviceList2;
			this.footer_serviceList3 = footer_serviceList3;
			this.footer_serviceList4 = footer_serviceList4;
			this.footer_title_col3 = footer_title_col3;
			this.footer_contactList1 = footer_contactList1;
			this.footer_contactList2 = footer_contactList2;
			this.footer_contactList3 = footer_contactList3;
			this.footer_contactList4 = footer_contactList4;
		}

			
			
			

	

	public int getFooter_id() {
		return footer_id;
	}

	

	public String getFooter_title() {
		return footer_title;
	}
	public String getFooter_paragraph() {
		return footer_paragraph;
	}
	public String getFooter_title_col2() {
		return footer_title_col2;
	}
	public String getFooter_contactList1() {
		return footer_contactList1;
	}
	public String getFooter_contactList2() {
		return footer_contactList2;
	}
	public String getFooter_contactList3() {
		return footer_contactList3;
	}
	public String getFooter_contactList4() {
		return footer_contactList4;
	}
	public String getFooter_title_col3() {
		return footer_title_col3;
	}
	public String getFooter_serviceList1() {
		return footer_serviceList1;
	}
	public String getFooter_serviceList2() {
		return footer_serviceList2;
	}
	public String getFooter_serviceList3() {
		return footer_serviceList3;
	}
	public String getFooter_serviceList4() {
		return footer_serviceList4;
	}
	
	
	
	
	
		
	
}
