package Group;

import ContactInfo.PhoneNumber;
import java.util.List;

public interface Group {
    String getUniqueIdentifier();

    void setSharedPhoneNumbers(String number);
    void setSharedPhoneNumbers(List<PhoneNumber> numbers);

    List<PhoneNumber> getSharedPhoneNumbers();
}
