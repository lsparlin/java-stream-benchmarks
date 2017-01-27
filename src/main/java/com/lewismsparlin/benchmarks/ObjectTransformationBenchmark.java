package com.lewismsparlin.benchmarks;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import com.lewismsparlin.StreamBenchmark;
import com.lewismsparlin.model.MyObj;

public class ObjectTransformationBenchmark implements StreamBenchmark {

	private static final List<Long> numbers = LongStream.range(0, 100000).boxed().collect(Collectors.toList());

	@Override
	public void performImperativeBenchmark() {
		List<MyObj> objects = new ArrayList<>();

		for (Long number : numbers) {
			MyObj obj = numberToMyObj(number);

			objects.add(obj);
		}

	}

	@Override
	public void performFunctionalBenchmark() {

		List<MyObj> list = numbers.stream()
				.map(ObjectTransformationBenchmark::numberToMyObj)
				.collect(Collectors.toList());
	}

	private static MyObj numberToMyObj(Long number) {
		MyObj obj = new MyObj();
		obj.name = "Object of index " + number;
		obj.someNumber = number;
		return obj;
	}

}
