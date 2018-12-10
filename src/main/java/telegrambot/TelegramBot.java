package telegrambot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import bot.Bot;
import bot.DialogManager;
import bot.IBot;
import classLoader.LoaderGames;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class TelegramBot extends TelegramLongPollingBot {
	private final IBot bot = new Bot(new DialogManager(), new LoaderGames());
	
	public static void main(String[] args) {
		ApiContextInitializer.init();
		TelegramBotsApi botapi = new TelegramBotsApi();
		try {
			botapi.registerBot(new TelegramBot());
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String getBotUsername() {
		return "karp_dima_bot";
    }

	private void sendMsg(Message msg, String text) {
		SendMessage s = new SendMessage();
		s.setChatId(msg.getChatId());
		s.setText(text);

		try { 
			execute(s);
		} catch (TelegramApiException e){
			e.printStackTrace();
		}
	}
	
	private ReplyKeyboardMarkup getKeyboardButtons(String[] commands){
		ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        
        List<KeyboardRow> keyboard = new ArrayList<>();
        
        for (String command : commands)
        {
        	KeyboardRow keyboardRow = new KeyboardRow();
            keyboardRow.add(new KeyboardButton(command));
            keyboard.add(keyboardRow);
        }
        
        replyKeyboardMarkup.setKeyboard(keyboard);
        return replyKeyboardMarkup;
	}
	
	@Override
	public void onUpdateReceived(Update element) {
		Message msg = element.getMessage();
		String txt = msg.getText();
		System.out.println("Input: " + txt);
		Long userId = msg.getChatId();
		if (txt.equals("/start")) {
			sendMsg(msg, "Hello, world! This is simple bot!");
			bot.startBot(userId);
		}
		else if (this.bot.hasDialogWith(userId)){
			String answer = bot.executeQuery(userId, txt);
			SendMessage s = new SendMessage();
			s.setChatId(userId);
			s.setReplyMarkup(
					getKeyboardButtons(bot.getExecutableCommands(userId)));
			
			s.setText(answer);
			
			try { 
				execute(s);
			} catch (TelegramApiException ex){
				ex.printStackTrace();
			}
		}
	}
	

	@Override
	public String getBotToken() {
		return "667623625:AAEnR1RZwHBKDGLSrLaYfgemIX6TXAQlZX4";
	}

}