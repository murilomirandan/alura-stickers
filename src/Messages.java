import java.text.MessageFormat;
import java.util.ResourceBundle;

public class Messages {
    private Messages() {}

    public static final String getString(String key, Object... value) {
        ResourceBundle bundle = ResourceBundle.getBundle("resources.messages");
        String pattern = bundle.getString(key);

        MessageFormat messageFormat = new MessageFormat(pattern);  
        return messageFormat.format(value);
    }
}
