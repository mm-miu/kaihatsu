package bean;

public class Test {
    private String studentNo; //STUDENT_NO（学生番号)
    private String subjectCd; //SUBJECT_CD(科目番号)
    private String schoolCd; //SCHOOL_CD(学校コード)
    private int no; //NO（回数)
    private int point; //POINT(得点)
    private String classNum; //CLASS_NUM(クラス番号)

    //ゲッター
    public String getStudentNo() {
        return studentNo;
    }
    public String getSubjectCd() {
        return subjectCd;
    }
    public String getSchhoolCd() {
        return schoolCd;
    }
    public int getNo() {
        return no;
    }
    public int getPoint() {
        return point;
    }
    public String getClassNum() {
        return classNum;
    }

    //セッター
    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }
    public void setSubjectCd(String subjectCd) {
        this.subjectCd = subjectCd;
    }
    public void setSchoolCd(String schoolCd) {
        this.schoolCd = schoolCd;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public void setPoint(int point) {
        this.point = point;
    }
    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }
}
