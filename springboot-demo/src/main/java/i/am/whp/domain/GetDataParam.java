package i.am.whp.domain;

import i.am.whp.util.PageCondition;

/**
 * @author wuhepeng
 * @date 2019/9/3
 */
public class GetDataParam extends PageCondition {
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

}
