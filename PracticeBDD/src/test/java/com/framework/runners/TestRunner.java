package com.framework.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src/test/resources/Features",
		glue = "com.framework.stepdefinitions", // 🔁 must match your step definition package
//				glue = {"com.framework.stepdefinitions", "com.framework.hooks"},
		plugin = {"pretty", "html:target/cucumber-reports.html", "json:target/cucumber.json"},
		//		tags = "@scenarioOutline", // 👈 will run only scenarios with @smoke
		tags = "@login1", // 👈 will run only scenarios with @smoke
		monochrome = true,
		dryRun = false
		)
public class TestRunner extends AbstractTestNGCucumberTests {
}
//@testFrame



//  Expression	                                    Meaning
//  @Tag1	                                  Run scenarios with @Tag1
//  @Tag1 or @Tag2	                          Run scenarios with either @Tag1 or @Tag2
//  @Tag1 and @Tag2	                          Run scenarios with both tags
//  not @Tag3	                              Exclude scenarios with @Tag3
//  (@Tag1 or @Tag2) and not @Tag3	          Complex: run @Tag1 or @Tag2 but skip if @Tag3 exists
//  (@Tag1 and @Tag2) or @Tag3	              Run @Tag1 AND @Tag2, OR if only @Tag3 exists



// 🧠 Recommendation for Frameworks
//     Practice	                              Recommended?	                            Reason
//  tags = "not @WIP"	                         ⚠️ Not ideal	                   Too broad and uncontrolled
//  tags = "@SmokeTest"	                        ✅ Good	                       Focused and clear
//  tags = "@SmokeTest and not @WIP"	          ✅✅ Best	                       Controlled and excludes in-progress tests
//  Untagged Scenarios	                     ❌ Bad Practice                   Hard to filter/run specifically


//  ✅ Final Suggestion
//  In your framework:
//  Always tag your scenarios (@SmokeTest, @Regression, @Sanity, @API, etc.)
//  Avoid running tests only based on negative conditions.
//  Use combined logic


//  📌 What Does Tagging a Feature Do?
//  When you tag a feature file:
//  All scenarios inside the feature file inherit that tag.
//  You don’t need to tag each scenario individually unless you want different tags per scenario.


//   🧠 Best Practice Tips
//                          Practice	                                                                  Benefit
//   Tag the feature if all scenarios inside are of the same category	                             Cleaner and simpler
//   Tag individual scenarios for mixed cases	                                                      Better control
//   Combine feature + scenario-level tagging when needed	                                         Maximum flexibility
