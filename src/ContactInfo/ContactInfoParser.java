package ContactInfo;

import java.util.ArrayList;

public class ContactInfoParser {
    public static ArrayList<ContactInfoItem> parse(String s) {
        ArrayList<ContactInfoItem> items = new ArrayList<>();
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

    public static ContactInfoItem extract(String s){
        if (s.contains("@"))
            return EmailAddress.create(s);
        else
            return PhoneNumber.create(s);
    }

    public static String[] split(String multiContactInfo) {
        return multiContactInfo.split("[;,]");
    }
}
