package ContactInfo;

public interface ContactInfoItem {
    String get();

    String getOriginal();

    void clean();

    boolean isEmailAddress();

    String getEmailDomain();
}
