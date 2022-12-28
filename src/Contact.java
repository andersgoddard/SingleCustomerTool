import java.util.Objects;

public class Contact {
    private String title;
    private String firstName;
    private String lastName;
    private String fullName;
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
        return Objects.requireNonNullElseGet(this.fullName, () -> title + " " + firstName + " " + lastName);
    }

    public void setContactInfo(ContactInfo info) {
        this.info = info;
    }

    public boolean hasContactInfo(ContactInfoItem item) {
        return info.contains(item);
    }

    public void setName(String fullName) {
        this.fullName = fullName;
    }
}
