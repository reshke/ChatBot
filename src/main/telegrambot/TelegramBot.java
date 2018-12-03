package main.telegrambot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import main.Bot;
import main.DialogManager;
import main.classLoader.LoaderGames;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class TelegramBot extends TelegramLongPollingBot {
	private final Bot bot = new Bot(new DialogManager(), new LoaderGames());
	
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
		System.out.println("Input: " + txt);
		if (txt.equals("/start")) {
			sendMsg(msg, "Hello, world! This is simple bot!");
			bot.startBot(msg.getChatId());
		}
		else {
			
			String answer = bot.executeQuery(msg.getChatId(), txt);
			
			sendMsg(msg, answer);
		}
	}

	@Override
	public String getBotToken() {
			return "667623625:AAEnR1RZwHBKDGLSrLaYfgemIX6TXAQlZX4";
	}

}
