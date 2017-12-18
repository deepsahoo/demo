package io.pivotal.domain;

import java.util.Date;

public class Snippet {

	
	private String id;
	private String title;
	private String code;
	
	private Date created;
	private Date modified;
	
	
	
	
	public Snippet() {
	}
	public Snippet(String id, String title, String code, Date created, Date modified) {
		this.id = java.util.UUID.randomUUID().toString();
		this.title = title;
		this.code = code;
		this.created = created;
		this.modified = modified;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
	
	
	
	
}
