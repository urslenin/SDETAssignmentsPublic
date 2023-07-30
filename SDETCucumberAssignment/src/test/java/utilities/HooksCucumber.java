package utilities;


import io.cucumber.java.After;
import io.cucumber.java.Before;

public class HooksCucumber {

    @Before
    public void beforeScenario(){
        System.out.println(" Search Flight Scenario started");

    }
    @After
    public void afterScenario(){
        System.out.println(" Search Flight Scenario completed");
    }
}
