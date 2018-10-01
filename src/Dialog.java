import java.util.ArrayList;
import java.util.List;

public class Dialog {
	
	private final List<Message> history = new ArrayList<Message>();
	
	public Dialog() {
		
	}
	
	public void PostMessage(Message currentMessage){
		history.add(currentMessage);
	}
	
	private Message GetLastMessage() {
		return history.get(history.size() - 1);
	}
	
	public Message SendMessage(){
		Message currentMessage = new Message();
		return currentMessage;
	}
	
	public Message StartDialog() {
		return new Message(false, "Hi! I`m chat bot, lets play!");
	}
}
