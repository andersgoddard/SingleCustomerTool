package ContactInfo;

public class EmailAddress implements ContactInfoItem {
    String address;

    public EmailAddress(String address) {
        this.address = address;
        clean();
    }

    public void clean(){
        String[] components = address.split(" ");
        for (String component : components)
            if (component.contains("@"))
                address = component.toLowerCase();
    }

    public String get() {
        return address;
    }

    public boolean isValid() {
        return !PlaceholderEmailAddressChecker.isPlaceholder(address);
    }
}
