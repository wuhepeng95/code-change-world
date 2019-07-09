package i.am.whp.model.enums;

import com.alibaba.fastjson.JSONObject;

public enum Status {
    /**
     * <code>unknown_answer_teacher_open_status = -1;</code>
     */
    unknown_answer_teacher_open_status(-1, "未知"),
    /**
     * <code>closed_answer_teacher_open_status = 0;</code>
     *
     * <pre>
     * 关闭
     * </pre>
     */
    closed_answer_teacher_open_status(0, "关闭"),
    /**
     * <code>complete_open_answer_teacher_open_status = 1;</code>
     *
     * <pre>
     * 开通
     * </pre>
     */
    complete_open_answer_teacher_open_status(1, "开通"),
    /**
     * <code>interim_open_answer_teacher_open_status = 2;</code>
     *
     * <pre>
     * 暂时开通
     * </pre>
     */
    interim_open_answer_teacher_open_status(2, "暂时开通"),
    ;

    private Integer value;
    private String desc;


    Status(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static Status valueOfDesc(String desc) {
        for (Status status : Status.values()) {
            if (status.desc.equals(desc)) {
                return status;
            }
        }
        return null;
//        throw new ConvertException("unknown BelongSystem value:" + value);
    }



    public static Status valueOf(int value) {
        for (Status status : Status.values()) {
            if (status.value.equals(value)) {
                return status;
            }
        }
        return null;
//        throw new ConvertException("unknown BelongSystem value:" + value);
    }

    public static void main(String[] args) {
        System.out.println(Status.valueOfDesc("开通").getValue());
        System.out.println(Status.interim_open_answer_teacher_open_status);
        System.out.println(JSONObject.toJSONString(Status.interim_open_answer_teacher_open_status));
    }

    @Override
    public String toString() {
        return this.desc;
    }
}
