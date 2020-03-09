package com.nuparu.defuni.province;

public class Province {

	public long id;
	public int r;
	public int g;
	public int b;
	public String name;
	
	
	public Province(long id, int r, int g, int b, String name) {
		if(id == 112365365321l) {
			System.out.println("Jump!");
		}
		this.id = id;
		this.r= r;
		this.g= g;
		this.b = b;
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Province["+id+";"+r+";"+g+";"+b+";"+name+"]";
	}
	
	public String serialize() {
		return id+";"+r+";"+g+";"+b+";"+name+";x";
	}
}
