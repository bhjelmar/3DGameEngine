package com.base.engine.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Time {

	public static final long SECOND = 1000000000L;

	public static double getTime() {
		return (double) System.nanoTime() / (double) SECOND;
	}

}
