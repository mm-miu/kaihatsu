package bean;

public class Test implements java.io.Serializable {

    private Student student;     // 学生
    private Subject subject;     // 科目
    private School school;       // 学校
    private ClassNum classNum;   // クラス
    private int no;             // 回数（NO）
    private int point;           // 得点（POINT）

    //ゲッター
    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }

    public ClassNum getClassNum() {
        return classNum;
    }
    public void setClassNum(ClassNum classNum) {
        this.classNum = classNum;
    }

    public Subject getSubject() {
        return subject;
    }
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public School getSchool() {
        return school;
    }
    public void setSchool(School school) {
        this.school = school;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getPoint() {
        return point;
    }
    public void setPoint(int point) {
        this.point = point;
    }
}
