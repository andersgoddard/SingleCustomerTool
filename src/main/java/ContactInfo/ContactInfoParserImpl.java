package ContactInfo;

import java.util.ArrayList;
import java.util.List;

public class ContactInfoParserImpl implements ContactInfoParser {
    public List<ContactInfoItem> parse(String s) {
        List<ContactInfoItem> items = new ArrayList<>();
        if (s.contains(";") || s.contains(",")) {
            String[] itemArray = split(s);
            for (String item : itemArray) {
                items.add(extract(item));
            }
        } else {
            items.add(extract(s));
        }
        return items;
    }

    public ContactInfoItem extract(String s){
        if (s.contains("@"))
            return EmailAddress.create(s);
        else
            return PhoneNumber.create(s);
    }

    public String[] split(String multiContactInfo) {
        return multiContactInfo.split("[;,]");
    }
}
