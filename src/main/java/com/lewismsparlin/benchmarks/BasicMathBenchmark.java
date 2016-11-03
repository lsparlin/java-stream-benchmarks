package com.lewismsparlin.benchmarks;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import com.lewismsparlin.StreamBenchmark;

public class BasicMathBenchmark implements StreamBenchmark {
	
	private static final List<Long> range = LongStream.rangeClosed(0, 1000000).boxed().collect(Collectors.toList());

	@Override
	public void performImperativeBenchmark() {
		long sum = 0L;
		for (Long num : range) {
			if (num % 2 == 0) {
				sum += num;
			}
		}
		System.out.println("IMPERITIVE sum is " + sum);
		
	}

	@Override
	public void performFunctionalBenchmark() {
		long sum = range.stream()
				.filter(num -> num % 2 == 0)
				.reduce(0L, Long::sum);
		System.out.println("FUNCTIONAL sum is " + sum);
	}

}
