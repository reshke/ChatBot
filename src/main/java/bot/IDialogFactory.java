package bot;

import userDialog.IGameFactory;

public interface IDialogFactory {
	public IDialogCommon createDialog(IGameLoaderFactory factory, Long userId);
}
