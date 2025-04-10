package hooks;

import context.ScenarioContext;

import io.cucumber.java.After;

public class Hook {

    @After
    public void tearDown() {
        //necessity to clear the scenario context is disputable, because garbage collector should delete it after
        // scenario finishes, but I left it mostly to demonstrate the usage of annotations like @Before and @After
        ScenarioContext.clear();
    }
}
