package ContactInfo;

import java.util.ArrayList;

public class ContactInfo implements Info {
    ArrayList<ContactInfoItem> items = new ArrayList<>();
    public void add(ContactInfoItem item) {
        items.add(item);
    }

    public void addAll(Info info) {
        items.addAll(info.getItems());
    }

    public boolean contains(ContactInfoItem item) {
        for (ContactInfoItem existingItem : items){
            if (existingItem.get().equals(item.get()))
                return true;
        }
        return false;
    }

    public ArrayList<ContactInfoItem> getItems() {
        return items;
    }
}
