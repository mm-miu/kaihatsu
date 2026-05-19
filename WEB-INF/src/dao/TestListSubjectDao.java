package dao;

import bean.School;
import bean.Subject;
import bean.TestListSubject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class TestListSubjectDao extends Dao {

    private final String baseSql =
        "SELECT " +
        " s.ent_year, s.NO AS student_no, s.NAME, s.class_num, " +
        " t.NO AS test_id, " +
        " t.POINT AS point " +
        "FROM student s " +
        "JOIN test t " +
        " ON s.NO = t.student_no " +
        " AND s.SCHOOL_CD = t.SCHOOL_CD " +
        "JOIN subject sub " +
        " ON t.SUBJECT_CD = sub.CD " +
        " AND t.SCHOOL_CD = sub.SCHOOL_CD " +
        "WHERE s.ent_year = ? " +
        " AND s.class_num = ? " +
        " AND sub.CD = ? " +
        " AND sub.SCHOOL_CD = ? " +
        "ORDER BY s.NO, t.NO";

    public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) {

        System.out.println("==TestListSubjectDao Start==");

        List<TestListSubject> list = new ArrayList<>();

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(baseSql)) {

            ps.setInt(1, entYear);
            ps.setString(2, classNum);
            ps.setString(3, subject.getCd());
            ps.setString(4, school.getCd());

            try (ResultSet rs = ps.executeQuery()) {
                list = postFilter(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return list;
    }

    private List<TestListSubject> postFilter(ResultSet rs) throws Exception {

        List<TestListSubject> list = new ArrayList<>();
        TestListSubject bean = null;
        String currentStudentNo = null;

        while (rs.next()) {

            System.out.println("point=" + rs.getInt("point"));

            String studentNo = rs.getString("student_no");


            // 学生が変わったら新しい Bean を作る
            if (currentStudentNo == null || !studentNo.equals(currentStudentNo)) {
                //(currentStudentNo == null || !studentNo.equals(currentStudentNo)) 
                //AIはこれが安全版だと言っていた↑
                
                bean = new TestListSubject();
                bean.setEntYear(rs.getInt("ent_year"));
                bean.setStudentNo(studentNo);
                bean.setStudentName(rs.getString("NAME"));
                bean.setClassNum(rs.getString("class_num"));
                bean.setPoints(new HashMap<>());

                list.add(bean);
                currentStudentNo = studentNo;
            }

            // テストID → 点数 を追加
            int testId = rs.getInt("test_id");
            int point = rs.getInt("point");
            bean.putPoint(testId, point);
        }

        return list;
    }
}
