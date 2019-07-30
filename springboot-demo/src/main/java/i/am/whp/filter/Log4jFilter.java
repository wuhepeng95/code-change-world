package i.am.whp.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.UUID;

public class Log4jFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(Log4jFilter.class);
    private static ThreadLocal<String> requestInfoOfCurrentThread = new ThreadLocal<>();

    public final static String GUID = "guid";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("init Log4jFilter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        // 每个请求过来设置guid的值
        MDC.put(GUID, getGuid());
        try {
            chain.doFilter(request, response);
        } finally {
            logger.info("response end");
            System.out.println(System.identityHashCode(MDC.getCopyOfContextMap()));
            //请求结束移除guid
            MDC.remove(GUID);
            logger.info("remove guid");
        }
    }

    public static String getGuid() {
        String guid = null;
        if (requestInfoOfCurrentThread.get() != null) {
            guid = requestInfoOfCurrentThread.get();
        }
        if (guid == null) {
            return UUID.randomUUID().toString();
        } else {
            return guid;
        }
    }

    @Override
    public void destroy() {

    }
}
