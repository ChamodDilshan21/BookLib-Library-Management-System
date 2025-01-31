package Additional;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {
    public static boolean isValidContact(String contact) {
        Pattern pattern = Pattern.compile("^07\\d{8}$");
        Matcher match = pattern.matcher(contact);
        return (match.find() && match.group().equals(contact));
    }

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        Matcher match = pattern.matcher(email);
        return (match.find() && match.group().equals(email));
    }

    public static boolean isValidDate(String date) {
        Pattern pattern = Pattern.compile("^(19|20)\\d\\d-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$");
        Matcher match = pattern.matcher(date);
        return (match.find() && match.group().equals(date));
    }
}
