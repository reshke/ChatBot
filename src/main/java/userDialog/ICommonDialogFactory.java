package userDialog;

import bot.IDialogCommon;
import bot.IGameLoaderFactory;

public interface ICommonDialogFactory {
	public IDialogCommon createDialog(IGameLoaderFactory factory, Long userId);
}
