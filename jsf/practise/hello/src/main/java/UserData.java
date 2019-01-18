import java.io.Serializable;

public class UserData implements Serializable {

    private static final long serialVersionUID = 1L;
    private MessageService messageService;

    public MessageService getMessageService() {
        return messageService;
    }

    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    public String getGreetingMessage() {
        return messageService.getGreetingMessage();
    }
}