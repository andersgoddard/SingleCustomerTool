package ContactInfo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PhoneNumberTest {
    @Test
    public void testGetPhoneNumber(){
        PhoneNumber number = PhoneNumber.create("07881266969");
        assertEquals("07881266969", number.get());
    }

    @Test
    public void testExtractsPhoneNumberFromString(){
        PhoneNumber number = PhoneNumber.create("07881 266 969 - Andrew");
        assertEquals("07881266969", number.get());
    }

    @Test
    public void testDealsWithPluses(){
        PhoneNumber number = PhoneNumber.create("+447881266969");
        assertEquals("00447881266969", number.get());
    }

    @Test
    public void testDealsWithDigitInBrackets(){
        PhoneNumber number = PhoneNumber.create("+61 (0)401 811 872 (australian mobile)");
        assertEquals("0061401811872", number.get());
    }
}
