package ContactInfo;

public class EmailAddress implements ContactInfoItem {
    String address;

    private EmailAddress(String address) {
        this.address = address;
        clean();
    }

    public static EmailAddress create(String address) {
        return new EmailAddress(address);
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
