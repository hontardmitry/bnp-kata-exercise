package hooks;

import context.ScenarioContext;

import io.cucumber.java.After;

public class Hook {

    @After
    public void tearDown() {
        ScenarioContext.clear();
    }
}
