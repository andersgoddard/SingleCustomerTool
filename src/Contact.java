public class Contact {
    private String title;
    private String firstName;
    private String lastName;
    private ContactInfo info;
    
    public void setTitle(String title) {
        this.title = title;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return title + " " + firstName + " " + lastName;
    }

    public void setContactInfo(ContactInfo info) {
        this.info = info;
    }

    public boolean hasContactInfo(ContactInfoItem item) {
        return info.contains(item);
    }
}
