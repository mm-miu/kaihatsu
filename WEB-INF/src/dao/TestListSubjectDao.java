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
        " s.ent_year, s.NO, s.NAME, s.class_num, " +
        " sc.No AS test_id, " +
        " sc.POINT " +
        "FROM student s " +
        "JOIN test sc ON s.NO = sc.student_no " +
        "AND s.class_num = sc.class_num " +
        "WHERE s.ent_year = ? " +
        " AND s.class_num = ? " +
        " AND sc.SUBJECT_CD = ? " +
        " AND sc.SCHOOL_CD = ? " +//AIにはいらないといわれたが設計書通りならいるはず
        "ORDER BY s.NO, sc.NO";

    public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) {

        List<TestListSubject> list = new ArrayList<>();

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(baseSql)) {

            ps.setInt(1, entYear);
            ps.setString(2, classNum);
            ps.setString(3, subject.getCd());//SUBJECT_CD
            ps.setString(4, school.getCd());//SCHOOL_CD

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

            String studentNo = rs.getString("NO");

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
