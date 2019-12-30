package i.am.whp.handler;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

import java.util.HashMap;
import java.util.Map;

// 重写返回mybatis返回map

public class SelectMapKeyAndValueHandler implements ResultHandler {

    private final Map mappedResults = new HashMap();

    @Override
    public void handleResult(ResultContext context) {
        Map map = (Map) context.getResultObject();
        // xml 配置里面的property的值，对应的列
        mappedResults.put(map.get("key"), map.get("value"));
    }

    public Map getMappedResults() {
        return mappedResults;
    }
}
