package i.am.whp.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wuhepeng
 * @date 2019/9/28
 */
public class ContextFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(ContextFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 拿到token
        String token = request.getHeader("token");

        try {
            // 如果token为空
            if (StringUtils.isEmpty(token)) {
                logger.warn("unauthed inner call");
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "unauthed inner request");
                return;
            }
            // 验证token

            // todo
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.info("auth filter error {}", e);
        }

        // 验证通过 继续往下走
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}
