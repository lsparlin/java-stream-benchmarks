package com.lewismsparlin;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;

import com.lewismsparlin.benchmarks.GroupByIntoMapBenchmark;
import com.lewismsparlin.benchmarks.MapToPropertyBenchmark;
import com.lewismsparlin.benchmarks.ObjectTransformationBenchmark;
import com.lewismsparlin.benchmarks.SummingEvenNumbersBenchmark;

public class StreamBenchmarkRunner {

	static final List<Class<? extends StreamBenchmark>> benchmarks = Arrays.asList(
			SummingEvenNumbersBenchmark.class,
			ObjectTransformationBenchmark.class,
			MapToPropertyBenchmark.class,
			GroupByIntoMapBenchmark.class);

	public static void main(String[] args) {

		benchmarks.stream().map(StreamBenchmarkRunner::newBenchmarkInstance).filter(Optional::isPresent)
				.map(Optional::get).forEach(benchmark -> {
					String name = benchmark.getClass().getSimpleName();
					System.out.println();
					System.out.println();
					System.out.println("### starting " + name + " benchmarks");
					StopWatch masterStopwatch = StopWatch.createStarted();

					StopWatch fStopwatch = StopWatch.createStarted();
					benchmark.performFunctionalBenchmark();
					System.out.println(name + " FUNCTIONAL benchmark completed in " + fStopwatch.getTime(TimeUnit.MILLISECONDS) + " ms");
					fStopwatch.stop();

					StopWatch iStopwatch = StopWatch.createStarted();
					benchmark.performImperativeBenchmark();
					System.out.println(name + " IMPERITIVE benchmark completed in " + iStopwatch.getTime(TimeUnit.MILLISECONDS) + " ms");
					iStopwatch.stop();

					System.out.println("### Completed " + name + " benchmark in " + masterStopwatch.getTime(TimeUnit.MILLISECONDS) + " ms");
					masterStopwatch.stop();
				});

		System.out.println();
	}

	private static Optional<StreamBenchmark> newBenchmarkInstance(Class<? extends StreamBenchmark> type) {
		try {
			StreamBenchmark benchmark = type.newInstance();
			return Optional.of(benchmark);
		} catch (Exception e) {
			return Optional.empty();
		}
	}
}
