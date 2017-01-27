package com.lewismsparlin.benchmarks;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import com.lewismsparlin.StreamBenchmark;
import com.lewismsparlin.model.MyObj;

public class MapToPropertyBenchmark implements StreamBenchmark {

	private static final int ITERATIONS = 100000;

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
		List<String> objectNames = new ArrayList<>();
		for (MyObj obj : existingObjects) {
			objectNames.add(obj.name);
		}

		System.out.println("Completed IMPERATIVE list of name property on " + ITERATIONS + " objects");
	}

	@Override
	public void performFunctionalBenchmark() {
		List<String> objectNames = existingObjects.stream()
				.map(MyObj::getName)
				.collect(Collectors.toList());

		System.out.println("Completed FUNCTIONAL list of name property on " + ITERATIONS + " objects");
	}

}
