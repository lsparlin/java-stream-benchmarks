package com.lewismsparlin.benchmarks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.lewismsparlin.StreamBenchmark;

public class GroupByIntoMapBenchmark implements StreamBenchmark {

	private static class IspNumbers {
		public String isp;

		public int inboxCount;

		public int spamCount;

		public IspNumbers(String isp, int inboxCount, int spamCount) {
			this.isp = isp;
			this.inboxCount = inboxCount;
			this.spamCount = spamCount;
		}
		
		public String getIsp() {
			return this.isp;
		}

	}

	private static final String[] isps = new String[] { "Gmail", "AOL", "Yahoo" };

	private static final List<IspNumbers> ispNumbers = IntStream.range(0, 1000).mapToObj(i -> {
		String isp = isps[i % 3];
		int inboxCount = 5 - (i % 3);
		int spamCount = 3 - (i % 3);
		return new IspNumbers(isp, inboxCount, spamCount);
	}).collect(Collectors.toList());

	@Override
	public void performImperativeBenchmark() {
		Map<String, List<IspNumbers>> map = new HashMap<>();
		for (IspNumbers numbers : ispNumbers) {
			if (!map.containsKey(numbers.isp)) {
				List<IspNumbers> newList = new ArrayList<>();
				map.put(numbers.isp, newList);
			}
			map.get(numbers.isp).add(numbers);
		}
		
		System.out.println("map of size " + map.size() + "created by IMPERATIVE style");
	}

	@Override
	public void performFunctionalBenchmark() {
		Map<String, List<IspNumbers>> map = ispNumbers.stream()
				.collect(Collectors.groupingBy(IspNumbers::getIsp));
				
		System.out.println("map of size " + map.size() + "created by FUNCTIONAL style");
	}

}
