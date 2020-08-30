package net.ejjoo.bulpan.post;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Proxy
public class Post {
	@Id
	@GeneratedValue
	private int id;

	@Column(nullable = false)
	private String title;

	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition = "TEXT")
	private String content;

	@CreationTimestamp
	private LocalDateTime createDtm;

	@UpdateTimestamp
	private LocalDateTime modifyDtm;

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

	public LocalDateTime getCreateDtm() {
		return createDtm;
	}

	public void setCreateDtm(LocalDateTime createDtm) {
		this.createDtm = createDtm;
	}

	public LocalDateTime getModifyDtm() {
		return modifyDtm;
	}

	public void setModifyDtm(LocalDateTime modifyDtm) {
		this.modifyDtm = modifyDtm;
	}
}