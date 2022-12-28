import java.util.ArrayList;

public class ContactInfo {
    ArrayList<ContactInfoItem> items = new ArrayList<>();
    public void add(ContactInfoItem item) {
        items.add(item);
    }

    public boolean contains(ContactInfoItem item) {
        for (ContactInfoItem existingItem : items){
            if (existingItem.get().equals(item.get()))
                return true;
        }
        return false;
    }
}