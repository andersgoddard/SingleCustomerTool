package ContactInfo;

public class PhoneNumber implements ContactInfoItem {
    String number;
    String originalNumber;
    private PhoneNumber(String number) {
        this.originalNumber = number;
        this.number = number;
        clean();
    }

    public static PhoneNumber create(String number) {
        return new PhoneNumber(number);
    }

    public String get() {
        return number;
    }

    public void clean(){
        dealWithPlusPrefix();
        removeZeroInBrackets();
        extractPhoneNumber();
    }

    @Override
    public boolean isEmail() {
        return false;
    }

    @Override
    public String getEmailDomain() {
        return null;
    }

    private void removeZeroInBrackets() {
        String zeroInBrackets = "\\(0\\)";
        number = number.replaceAll(zeroInBrackets, "");
    }

    private void extractPhoneNumber() {
        StringBuilder buffer = new StringBuilder();

        for (int i = 0; i < number.length(); i++){
            char character = number.charAt(i);
            if (charIsNumeric(character))
                buffer.append(character);
        }

        number = buffer.toString();
    }

    private void dealWithPlusPrefix() {
        number = number.replaceAll("^\\+", "00");
    }

    private boolean charIsNumeric(char character){
        String integers = "0123456789";
        return integers.contains(String.valueOf(character));
    }

    public String getOriginal(){
        return originalNumber;
    }
}
