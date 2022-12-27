public class EmailAddress implements ContactInfoItem {
    String address;
    boolean valid;

    public EmailAddress(String address) {
        this.address = address;
        this.checkValidity();
    }

    public void clean(){

    }

    private void checkValidity() {
        valid = !(this.address == "test@example.com");
    }

    public String get() {
        return address;
    }

    public boolean isValid() {
        return valid;
    }
}
