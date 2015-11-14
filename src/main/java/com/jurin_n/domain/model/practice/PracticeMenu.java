package com.jurin_n.domain.model.practice;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="t_PracticeMenu")
public class PracticeMenu {
	
	@EmbeddedId
	private PracticeMenuId practiceMenuId;
	@Column(length = 4000)
	private String menu;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;
	
	public PracticeMenu(){
		super();
	}
	
	public PracticeMenu(PracticeMenuId aPracticeMenuId,String aMenu){
		Date date = new Date();
		this.practiceMenuId = aPracticeMenuId;
		this.menu = aMenu;
		this.createDate = date;
		this.updateDate = date;
	}

	public PracticeMenuId getPracticeMenuId() {
		return practiceMenuId;
	}
}
