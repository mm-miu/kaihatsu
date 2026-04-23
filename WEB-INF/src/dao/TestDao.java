package dao;

import bean.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestDao extends Dao {

    // 1件取得（必要なら）
    public Test find(String studentNo, String subjectCd, String schoolCd, int no) throws Exception {
        String sql = "SELECT STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO, POINT, CLASS_NUM "
                   + "FROM TEST WHERE STUDENT_NO=? AND SUBJECT_CD=? AND SCHOOL_CD=? AND NO=?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, studentNo);
            ps.setString(2, subjectCd);
            ps.setString(3, schoolCd);
            ps.setInt(4, no);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Test t = new Test();
                    t.setStudentNo(rs.getString("STUDENT_NO"));
                    t.setSubjectCd(rs.getString("SUBJECT_CD"));
                    t.setSchoolCd(rs.getString("SCHOOL_CD"));
                    t.setNo(rs.getInt("NO"));
                    t.setPoint(rs.getInt("POINT"));
                    t.setClassNum(rs.getString("CLASS_NUM"));
                    return t;
                }
            }
        }
        return null;
    }

    // 得点登録
    public int insert(Test t) throws Exception {
        String sql = "INSERT INTO TEST(STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO, POINT, CLASS_NUM) "
                   + "VALUES(?, ?, ?, ?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, t.getStudentNo());
            ps.setString(2, t.getSubjectCd());
            ps.setString(3, t.getSchoolCd());
            ps.setInt(4, t.getNo());
            ps.setInt(5, t.getPoint());
            ps.setString(6, t.getClassNum());

            return ps.executeUpdate();
        }
    }

    // 得点更新
    public int update(Test t) throws Exception {
        String sql = "UPDATE TEST SET POINT=? "
                   + "WHERE STUDENT_NO=? AND SUBJECT_CD=? AND SCHOOL_CD=? AND NO=?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, t.getPoint());
            ps.setString(2, t.getStudentNo());
            ps.setString(3, t.getSubjectCd());
            ps.setString(4, t.getSchoolCd());
            ps.setInt(5, t.getNo());

            return ps.executeUpdate();
        }
    }
}
