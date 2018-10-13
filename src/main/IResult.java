package main;

public interface IResult {
	public String getResult();
	public String getError();
	public ResultState getState();
}
