package com.jjt.project.model.enums;

public enum BookStatusEnum {
	AVAILABLE(0),
	BORROWED(1),
	RECOMMENDED(2);
	
	private int value;
	
	BookStatusEnum(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
}
