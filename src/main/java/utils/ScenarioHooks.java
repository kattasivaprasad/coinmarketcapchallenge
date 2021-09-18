package utils;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.io.IOException;

public class ScenarioHooks {

    private static Scenario scenario;

    @Before(order = 1)
    public void KeepScenario(Scenario scenario) {
        ScenarioHooks.scenario = scenario;
        this.setScenario(scenario);
    }

    public void setScenario(Scenario scenario) {
        ScenarioHooks.scenario = scenario;
    }

    public static Scenario getScenario() {
        return scenario;
    }


    @AfterStep
    public void as(Scenario scenario) throws IOException, InterruptedException {
        scenario.attach(DriverManager.getScreenshot(), "image/png", "screenshot name");
    }

}
