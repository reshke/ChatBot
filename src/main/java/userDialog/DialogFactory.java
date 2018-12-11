package userDialog;

import Commands.CommandHelp;
import IO.GameSaver;
import bot.IDialogCommon;
import bot.IGameLoaderFactory;

public class DialogFactory implements ICommonDialogFactory {

	@Override
	public IDialogCommon createDialog(IGameLoaderFactory factory, Long userId) {
		return new CommonUserDialog(factory.Load(),
				new CommandContainer(new ICommand[] { new CommandHelp<String>("help", "help")}),
				new GameSaver(System.getProperty("user.dir") + "\\out\\production\\main\\data\\"), userId);
	}
}
