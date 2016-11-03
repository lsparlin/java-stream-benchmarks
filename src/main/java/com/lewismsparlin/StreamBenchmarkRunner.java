package com.lewismsparlin;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;

import com.lewismsparlin.benchmarks.BasicMathBenchmark;
import com.lewismsparlin.benchmarks.ObjectTransformationBenchmark;

public class StreamBenchmarkRunner {

	static final List<Class<? extends StreamBenchmark>> benchmarks = Arrays.asList(
			BasicMathBenchmark.class,
			ObjectTransformationBenchmark.class);

	public static void main(String[] args) {
		

		benchmarks.stream().map(StreamBenchmarkRunner::newBenchmarkInstance).filter(Optional::isPresent)
				.map(Optional::get).forEach(benchmark -> {
					String name = benchmark.getClass().getSimpleName();
					System.out.println();
					System.out.println();
					System.out.println("### starting " + name + " benchmarks");
					StopWatch stopwatch = StopWatch.createStarted();
					
					benchmark.performImperativeBenchmark();
					System.out.println(name + " IMPERITIVE benchmark completed in " + stopwatch.getTime(TimeUnit.MILLISECONDS));
					
					benchmark.performFunctionalBenchmark();
					System.out.println(name + " FUNCTIONAL benchmark completd in " + stopwatch.getTime(TimeUnit.MILLISECONDS));

					System.out.println("### Completed " + name + " benchmark in " + stopwatch.getTime(TimeUnit.MILLISECONDS));
					stopwatch.stop();
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
