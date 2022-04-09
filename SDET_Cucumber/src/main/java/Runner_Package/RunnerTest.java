package Runner_Package;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="src\\test\\java\\Feature\\Login.feature", glue={"sdet.test.stepDefinition"},
monochrome=true,plugin={"pretty","html:target/HTML_Reporting/report"})

public class RunnerTest {

}
