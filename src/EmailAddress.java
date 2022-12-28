public class EmailAddress implements ContactInfoItem {
    String address;
    boolean valid;

    public EmailAddress(String address) {
        this.address = address;
        clean();
        this.checkValidity();
    }

    public void clean(){
        String[] components = address.split(" ");
        for (String component : components)
            if (component.contains("@"))
                address = component.toLowerCase();
    }

    private void checkValidity() {
        valid = !(this.address.equals("test@example.com"));
    }

    public String get() {
        return address;
    }

    public boolean isValid() {
        return valid;
    }
}
