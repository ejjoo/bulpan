package net.ejjoo.bulpan.Model;

import org.joda.time.DateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Post {
	@Id
	@GeneratedValue
	private int id;

	private String title;

	private String content;

	private DateTime createDtm;

	private DateTime modifyDtm;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public DateTime getCreateDtm() {
		return createDtm;
	}

	public void setCreateDtm(DateTime createDtm) {
		this.createDtm = createDtm;
	}

	public DateTime getModifyDtm() {
		return modifyDtm;
	}

	public void setModifyDtm(DateTime modifyDtm) {
		this.modifyDtm = modifyDtm;
	}
}