package plts;

import java.util.Map;
import java.util.concurrent.Callable;

import plts.util.Constants;

public class TestCallable implements Callable<Map<String, Object>> {

	private Main main;
	private String[] args;

	public TestCallable(Main main, String[] args) {
		this.main = main;
		this.args = args;
	}

	@Override
	public Map<String, Object> call() throws Exception {
		Map<String, Object> map = main.execute(args);

		System.out.println(map.get(Constants.ENGINE.value()));

		return map;
	}

}
