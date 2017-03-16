package com.lewismsparlin.benchmarks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.lewismsparlin.StreamBenchmark;

public class OptionalBenchmark implements StreamBenchmark {

	private static final int ITERATIONS = 100000;

	private static final String DISCLAIMER = "Disclaimer!  java.util.Optional This is not a stream";

	@Override
	public void performImperativeBenchmark() {
		System.out.println(DISCLAIMER);

		List<String> stuffList = new ArrayList<>();
		for (int i = 0; i <= ITERATIONS; i++) {
			String stuff = "String " + i;

			if (stuff != null) {
				stuffList.add(stuff);
			}
		}
		System.out.println("Completed NULL CHECK benchmark of " + stuffList.size() + " strings");
	}

	@Override
	public void performFunctionalBenchmark() {
		System.out.println(DISCLAIMER);

		List<String> stuffList = new ArrayList<>();
		for (int i = 0; i <= ITERATIONS; i++) {
			Optional<String> optStuff = Optional.of("String " + i);

			if (optStuff.isPresent()) {
				stuffList.add(optStuff.get());
			}
		}
		System.out.println("Completed OPTIONAL CHECK benchmark of " + stuffList.size() + " strings");
	}

}
