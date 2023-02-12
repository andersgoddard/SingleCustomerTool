package ContactInfo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PlaceholderEmailAddressChecker {
    public static boolean isPlaceholder(String emailAddress) {
        ContactInfoItem email = EmailAddress.create(emailAddress);
        return checkPlaceholderFileFor(email);
    }

    private static boolean checkPlaceholderFileFor(ContactInfoItem email) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("src/main/java/ContactInfo/placeholder_email_addresses.txt"));
            String line = reader.readLine();
            while (line != null){
                if (line.equals(email.get()))
                        return true;
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
