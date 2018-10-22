package main.telegrambot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;


public class TelegramBot extends TelegramLongPollingBot {
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
		return "USER";
    }

	@Override
	public void onUpdateReceived(Update e) {
	}

		@Override
		public String getBotToken() {
				return "667623625:AAEnR1RZwHBKDGLSrLaYfgemIX6TXAQlZX4";
	}

}
