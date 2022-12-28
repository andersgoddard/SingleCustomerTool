public class PlaceholderEmailAddressChecker {
    public static boolean isPlaceholder(String emailAddress) {
        return emailAddress.equals("test@example.com") || emailAddress.equals("no@email.com");
    }
}
