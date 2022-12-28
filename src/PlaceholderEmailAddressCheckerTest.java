import org.junit.jupiter.api.Test;

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

    }
}
