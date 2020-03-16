package i.am.whp.filter;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class WebSiteMeshFilter extends ConfigurableSiteMeshFilter {
    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {

        //设置模板装饰的请求，参数（拦截的请求（可以采用通配符方式），装饰该请求的装饰模板）
        //可设置不同请求用不同模板装饰
        builder.addDecoratorPath("/expend", "/decorator/decorator");
        //白名单，不进行过滤的请求
        builder.addExcludedPath("/");
        //用于添加自定义标签
//       builder.addTagRuleBundles(new DivExtractingTagRuleBundle());
    }
}
