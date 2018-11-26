package main.Games;

import main.Game;
import main.ICommand;
import main.IResult;
import main.Result;
import main.ResultState;
import main.Commands.Command;

public class CHGK_Game extends Game {
	private final IQuestionContainer questionBase;
	
	public CHGK_Game(IQuestionContainer questionBase) {
		this.questionBase = questionBase;
	}

	public CHGK_Game() {
		this.questionBase = new PseudoBase();
	}

	public String getHint() {
		IResult<Question> result = questionBase.getCurrentQuestion();
		return result.getState() == ResultState.SUCCESS ? result.getResult().getQuestionAnswer() : result.getError();
	}
	
	public IResult<String> getHint(String[] args) {
		if (args.length != 1)
			return new Result("Incorrect count of arguments", ResultState.UNSUPPORTED_OPERATION);
		return new Result(getHint());
	}


	public Boolean postQuery(String query) {
		IResult<Question> result = questionBase.getCurrentQuestion();
		if (result.getResult().getQuestionAnswer().equals(query)) {
			this.questionBase.switchQuestion();
			return true;
		}
		else {
			return false;
		}
	}

	public IResult<String> postQuery(String[] args) {
		if (args.length != 1)
			return new Result("count of argument is not correct", ResultState.WRONG_ARGUMENTS);
		if (this.postQuery(args[0]))
		{
			this.questionBase.switchQuestion();

			return new Result("You are right!");
		}
		return new Result("You are wrong!", ResultState.WRONG_ARGUMENTS);
	}


	public IResult<String> getQuestionWording(String[] args) {
		return new Result(this.questionBase.getCurrentQuestion().getResult().getQuestionWording());
	}

	@Override
	public ICommand<String>[] get_commands() {
		return new Command[] {new Command("ask", (x) -> this.getQuestionWording(x)),
				new Command("result", (x) -> this.postQuery(x)), new Command("hint", (x) -> this.getHint(x))};
	}
	
	@Override
	public IResult<String> gameName() { return new Result("chgk"); }
	
	@Override
	public IResult<String> getGameDescriptor() { return new Result("Chgk Game(send 'switch chgk' to start): \r\n" + 
			"	no info for now"); }
}
