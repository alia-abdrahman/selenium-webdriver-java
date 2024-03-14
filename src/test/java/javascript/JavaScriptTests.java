package javascript;

import base.BaseTests;
import org.testng.annotations.Test;

public class JavaScriptTests extends BaseTests {

    @Test
    public void testScrollToTable() {
        homePage.clickLargeAndDeepDom().scrollToTable();
    }

    @Test
    public void testScrollToParagraph() {
        homePage.clickInfiniteScroll().scrollToParagraph(5);
    }


}
