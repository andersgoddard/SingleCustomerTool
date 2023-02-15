package Directory;

import ContactInfo.PhoneNumber;
import Group.CompanyImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CompanyDirectoryTest {
    CompanyDirectoryImpl list;
    @BeforeEach
    public void setUp(){
        this.list = CompanyDirectoryImpl.getInstance();
    }

    @Test
    public void testOneCompany(){
        CompanyImpl company = CompanyImpl.create("Residential Land");
        assertEquals(1, list.size());
    }

    @Test
    public void testTwoCompanies(){
        CompanyImpl company = CompanyImpl.create("Residential Land");
        CompanyImpl company2 = CompanyImpl.create("Example Co");
        assertEquals(2, list.size());
    }

    @Test
    public void testNoCompanies(){
        assertEquals(0, list.size());
    }

    @Test
    public void testDoesNotContainSharedPhoneNumber(){
        CompanyImpl company = CompanyImpl.create("Example Co");
        company.setSharedContactInfo("02078002000");
        assertFalse(list.doesNotContain(PhoneNumber.create("02078002000")));
    }

    @AfterEach
    public void tearDown(){
        list.clear();
    }
}
