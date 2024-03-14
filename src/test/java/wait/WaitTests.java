package wait;

import base.BaseTests;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test
public class WaitTests extends BaseTests {
    public void testWaitUntilHidden(){
        var loadingPage = homePage.clickDynamicLoading().clickExample1();
        loadingPage.clickStart();
        assertEquals(loadingPage.getLoadedText(), "Hello World!", "Loaded text is incorrect");
    }


}
