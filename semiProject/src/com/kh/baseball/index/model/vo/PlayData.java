package com.kh.baseball.index.model.vo;

public class PlayData {
	private int p_no;
	private String p_day;
	private String p_time;
	private String p_play;

	public PlayData() {

	}

	public PlayData(int p_no, String p_day, String p_time, String p_play) {
		super();
		this.p_no = p_no;
		this.p_day = p_day;
		this.p_time = p_time;
		this.p_play = p_play;
	}

	public int getP_no() {
		return p_no;
	}

	public void setP_no(int p_no) {
		this.p_no = p_no;
	}

	public String getP_day() {
		return p_day;
	}

	public void setP_day(String p_day) {
		this.p_day = p_day;
	}

	public String getP_time() {
		return p_time;
	}

	public void setP_time(String p_time) {
		this.p_time = p_time;
	}

	public String getP_play() {
		return p_play;
	}

	public void setP_play(String p_play) {
		this.p_play = p_play;
	}

	@Override
	public String toString() {
		return "PlayData [p_no=" + p_no + ", p_day=" + p_day + ", p_time=" + p_time + ", p_play=" + p_play + "]";
	}

	
}
