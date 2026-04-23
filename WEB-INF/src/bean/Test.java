package bean;

public class Test implements java.io.Serializable {

    private Student student;     // 学生
    private Subject subject;     // 科目
    private School school;       // 学校
    private ClassNum classNum;   // クラス
    private int num;             // 回数（NO）
    private int point;           // 得点（POINT）

    //ゲッター
    public Student getStudent() {
        return student;
    }

    public Subject getSubject() {
        return subject;
    }

    public School getSchool() {
        return school;
    }

    public ClassNum getClassNum() {
        return classNum;
    }

    public int getNum() {
        return num;
    }

    public int getPoint() {
        return point;
    }

    //セッター
    public void setStudent(Student student) {
        this.student = student;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public void setClassNum(ClassNum classNum) {
        this.classNum = classNum;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
