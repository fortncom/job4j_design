package ru.job4j.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Warmup(iterations = 50)
@Measurement(iterations = 500)
@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
@Threads(1)
@Fork(1)
@Timeout(time = 2, timeUnit = TimeUnit.MINUTES)
public class JMHTest {

    @Benchmark
    public void test() {
        Integer x = new Random().nextInt();
        Integer y = new Random().nextInt();
        System.out.println("Результат: " + x * y);
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder().include(JMHTest.class.getSimpleName()).threads(1).forks(1)
                .timeout(TimeValue.minutes(2)).build();
        new Runner(options).run();
    }
}
