package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

public class RunnerClass {

    @RunWith(Cucumber.class)
    @CucumberOptions(features = "src/test/features",
                     glue = {"src/test/java/steps", "src/test/java/hooks"}  )
    public class RunFeatures {
    }
}
