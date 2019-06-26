package i.am.whp.model;

public enum TeacherSource {
    public_school_teacher_source(1, "公校在职"),
    private_school_teacher_source(2, "私校在职"),
    free_lancer_teacher_source(3, "自由职业"),
    Institutional_teacher_source(4, "机构在职"),
    college_students_source(5, "在校学生"),
    ;

    public int value;
    public String desc;

    TeacherSource(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static TeacherSource valueOf(int value) {
        for (TeacherSource teacherSource : TeacherSource.values()) {
            if (value == teacherSource.getValue()) {
                return teacherSource;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

//D:\>javap TeacherSource.class
//Compiled from "TeacherSource.java"

//public final class TeacherSource extends java.lang.Enum<TeacherSource> {
//  public static final TeacherSource public_school_teacher_source;
//  public static final TeacherSource private_school_teacher_source;
//  public static final TeacherSource free_lancer_teacher_source;
//  public static final TeacherSource Institutional_teacher_source;
//  public static final TeacherSource college_students_source;

//  public int value;
//  public java.lang.String desc;

//  public static TeacherSource[] values();
//  public static TeacherSource valueOf(java.lang.String);
//  public static TeacherSource valueOf(int);
//  public int getValue();
//  public void setValue(int);
//  public java.lang.String getDesc();
//  public void setDesc(java.lang.String);
//  static {};
//}
}
