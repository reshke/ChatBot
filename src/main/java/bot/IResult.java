package bot;

public interface IResult<T> {
	public T getResult();
	public String getError();
	public ResultState getState();
	public T getInfo();
}
