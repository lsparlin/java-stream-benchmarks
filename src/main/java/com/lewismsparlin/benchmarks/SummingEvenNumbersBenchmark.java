package com.lewismsparlin.benchmarks;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import com.lewismsparlin.StreamBenchmark;

public class SummingEvenNumbersBenchmark implements StreamBenchmark {

	private static final int ITERATIONS = 2000000;

	private static final List<Long> range = LongStream.rangeClosed(0, ITERATIONS).boxed().collect(Collectors.toList());

	@Override
	public void performImperativeBenchmark() {
		long sum = 0L;
		for (Long num : range) {
			if (num % 2 == 0) {
				sum += num;
			}
		}
		System.out.println("IMPERITIVE sum of " + ITERATIONS / 2 + " even numbers is " + sum);

	}

	@Override
	public void performFunctionalBenchmark() {
		long sum = range.stream()
				.filter(num -> num % 2 == 0)
				.mapToLong(Long::longValue)
				.sum();
		System.out.println("FUNCTIONAL sum of " + ITERATIONS / 2 + " even numbers is " + sum);
	}

}
