package runner;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParallelRunner {

    private static final int WORKERS = 5;

    @Test
    void testParallel() {
        Results results = Runner.path("classpath:features")
                .tags("@regression")
                .parallel(WORKERS);
        assertEquals(0, results.getFailCount(), results.getErrorMessages());
    }
}
