package ContactInfo;

import java.util.List;
import java.util.ArrayList;

public class ContactInfoImpl implements ContactInfo {
    List<ContactInfoItem> items = new ArrayList<>();
    public void add(ContactInfoItem item) {
        items.add(item);
    }

    public void addAll(ContactInfo info) {
        items.addAll(info.getItems());
    }

    public boolean contains(ContactInfoItem item) {
        for (ContactInfoItem existingItem : items){
            if (existingItem.get().equals(item.get()))
                return true;
        }
        return false;
    }

    public List<ContactInfoItem> getItems() {
        return items;
    }
}
