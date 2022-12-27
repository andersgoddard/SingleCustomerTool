import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactInfoParserTest {
    @Test
    public void testParsePhoneNumber(){
        List<ContactInfoItem> items = ContactInfoParser.parse("07881266969");
        assertEquals(PhoneNumber.class, items.get(0).getClass());
    }

    @Test
    public void testParseEmailAddress(){
        List<ContactInfoItem> items = ContactInfoParser.parse("andersgoddard@gmail.com");
        assertEquals(EmailAddress.class, items.get(0).getClass());
    }

    @Test
    public void testParseMultiplePhoneNumbers(){
        String multiContactInfo = "07701319165;07903886311";
        List<ContactInfoItem> items = ContactInfoParser.parse(multiContactInfo);
        assertEquals("07701319165", items.get(0).get());
        assertEquals("07903886311", items.get(1).get());
    }

    @Test
    public void testParseMultiplePhoneNumbersWithNoise(){
        String multiContactInfo = "+61 (0)401 811 872 (australian mobile); old 07588101770";
        List<ContactInfoItem> items = ContactInfoParser.parse(multiContactInfo);
        assertEquals("0061401811872", items.get(0).get());
        assertEquals("07588101770", items.get(1).get());
    }
}
