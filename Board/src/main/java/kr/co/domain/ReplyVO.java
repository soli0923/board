package kr.co.domain;

import java.io.Serializable;

public class ReplyVO implements Serializable{
	private static final long serialVersionUID = 1L;
	private int rno;
	private int bno;
	private String replytext;
	private String replyer;
	private String regidate;
	private String updatedate;
	
	public ReplyVO() {}

	
	public ReplyVO(int bno, String replytext, String replyer) {
		super();
		this.bno = bno;
		this.replytext = replytext;
		this.replyer = replyer;
	}


	public ReplyVO(int rno, int bno, String replytext, String replyer, 
			String regidate, String updatedate) {
		super();
		this.rno = rno;
		this.bno = bno;
		this.replytext = replytext;
		this.replyer = replyer;
		this.regidate = regidate;
		this.updatedate = updatedate;
	}


	public int getRno() {
		return rno;
	}


	public void setRno(int rno) {
		this.rno = rno;
	}


	public int getBno() {
		return bno;
	}


	public void setBno(int bno) {
		this.bno = bno;
	}


	public String getReplytext() {
		return replytext;
	}


	public void setReplytext(String replytext) {
		this.replytext = replytext;
	}


	public String getReplyer() {
		return replyer;
	}


	public void setReplyer(String replyer) {
		this.replyer = replyer;
	}


	public String getRegidate() {
		return regidate;
	}


	public void setRegidate(String regidate) {
		this.regidate = regidate;
	}


	public String getUpdatedate() {
		return updatedate;
	}


	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "ReplyVO [rno=" + rno + ", bno=" + bno + ", replyer=" + replyer + ", updatedate=" + updatedate + "]";
	}
	
	

}
