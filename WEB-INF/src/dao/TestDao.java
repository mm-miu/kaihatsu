package dao;

import bean.Test;
import bean.Student;
import bean.Subject;
import bean.School;
import bean.ClassNum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TestDao extends Dao {

    // ---------------------------------------------------------
    // 1. get（1件取得）
    // ---------------------------------------------------------
    public Test get(Student student, Subject subject, School school, int no) throws Exception {

        String sql =
            "SELECT STUDENT_NO, SUBJECT_CD, SCHOOL_CD, CLASS_NUM, NO, POINT " +
            "FROM TEST WHERE STUDENT_NO=? AND SUBJECT_CD=? AND SCHOOL_CD=? AND NO=?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, student.getNo());
            ps.setString(2, subject.getCd());
            ps.setString(3, school.getCd());
            ps.setInt(4, no);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {

                    Test t = new Test();

                    // Student
                    Student stu = new Student();
                    stu.setNo(rs.getString("STUDENT_NO"));
                    t.setStudent(stu);

                    // Subject
                    Subject sub = new Subject();
                    sub.setCd(rs.getString("SUBJECT_CD"));
                    t.setSubject(sub);

                    // School
                    School sch = new School();
                    sch.setCd(rs.getString("SCHOOL_CD"));
                    t.setSchool(sch);

                    // ClassNum（← 修正済み）
                    ClassNum cn = new ClassNum();
                    cn.setClass_num(rs.getString("CLASS_NUM"));
                    t.setClassNum(cn);

                    // Test info
                    t.setNo(rs.getInt("NO"));
                    t.setPoint(rs.getInt("POINT"));

                    return t;
                }
            }
        }
        return null;
    }

    // ---------------------------------------------------------
    // 2. postFilter（ResultSet → List<Test>）
    // ---------------------------------------------------------
    public List<Test> postFilter(ResultSet rSet, School school) throws Exception {

        List<Test> list = new ArrayList<>();

        while (rSet.next()) {

            Test t = new Test();

            // Student
            Student stu = new Student();
            stu.setNo(rSet.getString("STUDENT_NO"));
            t.setStudent(stu);

            // Subject
            Subject sub = new Subject();
            sub.setCd(rSet.getString("SUBJECT_CD"));
            t.setSubject(sub);

            // School
            t.setSchool(school);

            // ClassNum（← 修正済み）
            ClassNum cn = new ClassNum();
            cn.setClass_num(rSet.getString("CLASS_NUM"));
            t.setClassNum(cn);

            // Test info
            t.setNo(rSet.getInt("NO"));
            t.setPoint(rSet.getInt("POINT"));

            list.add(t);
        }

        return list;
    }

    // ---------------------------------------------------------
    // 3. filter（検索条件付き一覧）
    // ---------------------------------------------------------
    public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {

        List<Test> list = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append(
            "SELECT T.STUDENT_NO, T.SUBJECT_CD, T.SCHOOL_CD, T.CLASS_NUM, T.NO, T.POINT " +
            "FROM TEST T " +
            "JOIN STUDENT S ON T.STUDENT_NO = S.NO " +
            "WHERE T.SCHOOL_CD = ? "
        );

        List<Object> params = new ArrayList<>();
        params.add(school.getCd());

        if (entYear > 0) {
            sql.append("AND S.ENT_YEAR = ? ");
            params.add(entYear);
        }

        if (classNum != null && !classNum.isEmpty()) {
            sql.append("AND S.CLASS_NUM = ? ");
            params.add(classNum);
        }

        if (subject != null) {
            sql.append("AND T.SUBJECT_CD = ? ");
            params.add(subject.getCd());
        }

        if (num > 0) {
            sql.append("AND T.NO = ? ");
            params.add(num);
        }

        sql.append("ORDER BY T.STUDENT_NO, T.NO");

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                list = postFilter(rs, school);
            }
        }

        return list;
    }

    // ---------------------------------------------------------
    // 4. save（List<Test> を保存）
    // ---------------------------------------------------------
    public boolean save(List<Test> list) throws Exception {

        try (Connection con = getConnection()) {
            con.setAutoCommit(false);

            for (Test t : list) {
                if (!save(t, con)) {
                    con.rollback();
                    return false;
                }
            }

            con.commit();
            return true;
        }
    }

    // ---------------------------------------------------------
    // 5. save（1件保存）
    // ---------------------------------------------------------
    public boolean save(Test t, Connection con) throws Exception {

        String sql =
            "INSERT INTO TEST(STUDENT_NO, SUBJECT_CD, SCHOOL_CD, CLASS_NUM, NO, POINT) " +
            "VALUES(?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, t.getStudent().getNo());
            ps.setString(2, t.getSubject().getCd());
            ps.setString(3, t.getSchool().getCd());
            ps.setString(4, t.getClassNum().getClass_num());  // ← 修正済み
            ps.setInt(5, t.getNo());
            ps.setInt(6, t.getPoint());

            return ps.executeUpdate() == 1;
        }
    }
}
