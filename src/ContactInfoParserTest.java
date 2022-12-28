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

    @Test
    public void testParseTwoEmailAddresses(){
        String multiContactInfo = "1dakidm@gmail.com;y.sofidiya@yahoo.co.uk";
        List<ContactInfoItem> items = ContactInfoParser.parse(multiContactInfo);
        assertEquals("1dakidm@gmail.com", items.get(0).get());
        assertEquals("y.sofidiya@yahoo.co.uk", items.get(1).get());
    }

    @Test
    public void testParseThreeEmailAddresses(){
        String multiContactInfo = "a.fox@wellcome.org; hamid.irshad55@gmail.com; michael.holder@btinternet.com";
        List<ContactInfoItem> items = ContactInfoParser.parse(multiContactInfo);
        assertEquals("a.fox@wellcome.org", items.get(0).get());
        assertEquals("hamid.irshad55@gmail.com", items.get(1).get());
        assertEquals("michael.holder@btinternet.com", items.get(2).get());
    }

    @Test
    public void testParseNoisyPhoneAndEmail(){
        String multiContactInfo = "0787 55 99 214 - Mia; annabel_tino@hotmail.com (Business)";
        List<ContactInfoItem> items = ContactInfoParser.parse(multiContactInfo);
        assertEquals("07875599214", items.get(0).get());
        assertEquals("annabel_tino@hotmail.com", items.get(1).get());
    }

    @Test
    public void testParseWithCommas(){
        String multiContactInfo = "Annepher@163.com; 18861820224@163.com; yangmaolin97@outlook.com, 352492942@qq.com";
        List<ContactInfoItem> items = ContactInfoParser.parse(multiContactInfo);
        assertEquals("Annepher@163.com", items.get(0).get());
        assertEquals("18861820224@163.com", items.get(1).get());
        assertEquals("yangmaolin97@outlook.com", items.get(2).get());
        assertEquals("352492942@qq.com", items.get(3).get());
    }
}
