package main.telegrambot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import main.Bot;
import main.DialogManager;
import main.IDialogManager;
import main.IResult;
import main.ResultState;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class TelegramBot extends TelegramLongPollingBot {
	private final IDialogManager dialog = new DialogManager();
	
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
	
	@Override
	public void onUpdateReceived(Update e) {
		Message msg = e.getMessage();
		String txt = msg.getText();
		if (txt.equals("/start")) {
			sendMsg(msg, "Hello, world! This is simple bot!");
			dialog.startDialog(msg.getChatId());
		}
		else {
			IResult<String> result = dialog.handleQuery(msg.getChatId(), txt);
			ResultState state = result.getState();
			String answer = "";
			if (state == ResultState.SUCCESS)
				answer = result.getResult();
			if (state == ResultState.UNKNOWN)
				answer = " do not understand";
			if (state == ResultState.WRONG_ARGUMENTS)
				answer = result.getError();
			sendMsg(msg, answer);
		}
	}

	@Override
	public String getBotToken() {
			return "667623625:AAEnR1RZwHBKDGLSrLaYfgemIX6TXAQlZX4";
	}

}
