package ContactInfo;

public class EmailAddress implements ContactInfoItem {
    String originalAddress;
    String address;
    String emailDomain;

    private EmailAddress(String address) {
        this.originalAddress = address;
        this.address = address;
        clean();
    }

    public static EmailAddress create(String address) {
        return new EmailAddress(address);
    }

    public void clean(){
        String[] components = address.split(" ");
        for (String component : components)
            if (component.contains("@")) {
                address = component.toLowerCase();
                emailDomain = address.split("@")[1];
            }
    }

    @Override
    public boolean isEmail() {
        return true;
    }

    public String get() {
        return address;
    }

    public boolean isValid() {
        return !PlaceholderEmailAddressChecker.isPlaceholder(address);
    }

    public String getOriginal() {
        return originalAddress;
    }

    public String getEmailDomain(){
        return emailDomain;
    }
}
