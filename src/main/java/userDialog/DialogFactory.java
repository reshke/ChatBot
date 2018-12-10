package userDialog;

import Commands.CommandHelp;
import IO.GameSaver;
import bot.IDialogCommon;
import bot.IDialogFactory;
import bot.IGameLoaderFactory;

public class DialogFactory implements IDialogFactory {

	@Override
	public IDialogCommon createDialog(IGameLoaderFactory factory, Long userId) {
		return new CommonUserDialog(factory.Load(),
				new CommandContainer(new ICommand[] { new CommandHelp<String>("help", "help")}),
				new GameSaver(System.getProperty("user.dir") + "\\out\\production\\main\\data\\"), userId);
	}
}
