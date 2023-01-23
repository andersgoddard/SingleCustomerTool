package ContactInfo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlaceholderEmailAddressCheckerTest {
    @Test
    public void testPlaceholderEmail1(){
        assertTrue(PlaceholderEmailAddressChecker.isPlaceholder("test@example.com"));
    }

    @Test
    public void testPlaceholderEmail2(){
        assertTrue(PlaceholderEmailAddressChecker.isPlaceholder("no@email.com"));
    }

    @Test
    public void testPlaceholderEmail3(){
        assertTrue(PlaceholderEmailAddressChecker.isPlaceholder("xxx@xx.com"));
    }

    @Test
    public void testNotAPlaceholderEmail(){
        assertFalse(PlaceholderEmailAddressChecker.isPlaceholder("andersgoddard@gmail.com"));
    }
}
