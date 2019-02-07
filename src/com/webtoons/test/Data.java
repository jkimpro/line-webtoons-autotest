package com.webtoons.test;

public class Data {

	private String name;
	private String genre;
	private String color;
	
	public Data(){
		name = "";
		genre = "";
		color = "";
	}
	public String getName()
	{
		return name;
	}
	
	public String getGenre()
	{
		return genre;
	}
	public String getColor()
	{
		return color;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	public void setGenre(String genre)
	{
		this.genre = genre;
	}
	public void setColor(String color)
	{
		this.color = color;
	}
}
