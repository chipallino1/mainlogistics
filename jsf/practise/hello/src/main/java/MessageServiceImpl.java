
public class MessageServiceImpl implements MessageService {
    private String message;

    public String getGreetingMessage() {
        return message;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}