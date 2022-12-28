public class Contact {
    private String title;
    private String firstName;
    private String lastName;
    
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
}
