package com.lewismsparlin.benchmarks;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import com.lewismsparlin.StreamBenchmark;

public class ObjectTransformationBenchmark implements StreamBenchmark{
	
	private static final List<Long> numbers = LongStream.range(0, 10000).boxed().collect(Collectors.toList());
	
	private static class MyObj {
		public String name;
		
		public long someNumber;
	}

	@Override
	public void performImperativeBenchmark() {
		List<MyObj> objects = new ArrayList<>();
		
		for (Long number : numbers) {
			MyObj obj = new MyObj();
			obj.name = "Object of index " + number;
			obj.someNumber = number;
			
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
