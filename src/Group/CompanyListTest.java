package Group;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompanyListTest {
    CompanyList list;
    @BeforeEach
    public void setUp(){
        this.list = CompanyList.getInstance();
    }

    @Test
    public void testOneCompany(){
        Company company = Company.create("Residential Land");
        assertEquals(1, list.size());
    }

    @Test
    public void testTwoCompanies(){
        Company company = Company.create("Residential Land");
        Company company2 = Company.create("Example Co");
        assertEquals(2, list.size());
    }

    @Test
    public void testNoCompanies(){
        assertEquals(0, list.size());
    }

    @AfterEach
    public void tearDown(){
        list.clear();
    }
}
