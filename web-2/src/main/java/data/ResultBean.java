package data;

import java.util.ArrayList;
import java.util.List;

public class ResultBean {

    private List<Result> results = new ArrayList<>();

    public void addResult(Result result) {
        results.add(result);
    }

    public List<Result> getResults() {
        return results;
    }

}
