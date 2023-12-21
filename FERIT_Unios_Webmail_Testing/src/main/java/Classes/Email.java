package Classes;

public class Email {
    private final String receiver;
    private final String subject;
    private final String message;

    public Email(String receiver, String subject, String message){
        this.receiver = receiver;
        this.subject = subject;
        this.message = message;
    }

    public String GetReceiver(){
        return this.receiver;
    }

    public String GetSubject(){
        return this.subject;
    }

    public String GetMessage(){
        return this.message;
    }
}
