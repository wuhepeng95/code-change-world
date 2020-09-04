package i.am.whp.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wuhepeng
 * @date 2019/9/2
 */
@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler({Exception.class})
	@ResponseBody
	public Map<String, Object> handleException(Exception e) {
		HashMap<String, Object> result = new HashMap<>();
		result.put("result", false);
        result.put("msg","后台服务异常");
//		result.put("msg", e.toString());
		return result;
	}
}
