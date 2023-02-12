package ContactInfo;

import java.util.List;

public interface ContactInfoParser {
    List<ContactInfoItem> parse(String s);

    ContactInfoItem extract(String s);

    String[] split(String multiContactInfo);
}
