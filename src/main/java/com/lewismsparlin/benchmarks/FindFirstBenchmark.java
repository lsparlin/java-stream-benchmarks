package com.lewismsparlin.benchmarks;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import com.lewismsparlin.StreamBenchmark;
import com.lewismsparlin.model.MyObj;

public class FindFirstBenchmark implements StreamBenchmark {
	
	private static final int ITERATIONS = 1000000;
	 
	private static final int MEDIAN = ITERATIONS / 2;
	
	private static final List<Long> numbers = LongStream.range(0, ITERATIONS).boxed().collect(Collectors.toList());

	private static final List<MyObj> existingObjects = numbers.stream()
			.map(n -> {
				MyObj object = new MyObj();
				object.name = "Object " + n;
				object.someNumber = n;
				return object;
			}).collect(Collectors.toList());

	@Override
	public void performImperativeBenchmark() {
		String nameOfMiddleObject = null;
		for (MyObj  obj : existingObjects) {
			if (obj.name.equals("Object " + MEDIAN)) {
				nameOfMiddleObject = obj.name;
				break;
			}
		}

		System.out.println("Found middle: " + nameOfMiddleObject + " of " + ITERATIONS + " using IMPERATIVE");
	}

	@Override
	public void performFunctionalBenchmark() {
		String nameOfMiddleObject = existingObjects.stream()
				.filter(obj -> obj.name.equals("Object " + MEDIAN))
				.map(MyObj::getName)
				.findFirst().get();
		
		System.out.println("Found middle: " + nameOfMiddleObject + " of " + ITERATIONS + " using FUNCTIONAL");
	}

}
