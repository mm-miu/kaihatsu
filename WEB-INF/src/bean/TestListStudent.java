package bean;

public class TestListStudent implements java.io.Serializable{
    
    private String subjectName;
    private String subjectCd;
    private int num;
    private int point;
    private String studentNo;
    private String schoolCd;

    //public TestListStudent() {} AIが必須と言っていた真偽不明

    public String getSubjectName() {
        return subjectName;
    }

    public String getSubjectCd() {
        return subjectCd;
    }

    public int getNum() {
        return num;
    }

    public int getPoint() {
        return point;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public String getSchoolCd() {
        return schoolCd;
    }

    public void setSubjectName(String subjectName){
        this.subjectName=subjectName;
    }

    public void setSubjectCd(String subjectCd){
        this.subjectCd=subjectCd;
    }

    public void setNum(int num){
        this.num=num;
    }

    public void setPoint(int point){
        this.point=point;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public void setSchoolCd(String schoolCd) {
        this.schoolCd = schoolCd;
    }
}
