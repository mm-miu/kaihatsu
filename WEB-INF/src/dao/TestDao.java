package dao;
import bean.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TestDao extends Dao{

    //得点一覧を取得(学生番号で検索する)
    public List<Test> findByStudent(String studentNo) throws Exception {
        List<Test> list = new ArrayList<>();

        //SQL文をセット
        String sql = "SELECT STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO, POINT, CLASS_NUM "
        + "FROM TEST WHERE STUDENT_NO = ? ORDER BY SUBJECT_CD, NO";

        try(Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, studentNo);

                try(ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Test t = new Test();
                        t.setStudentNo(rs.getString("STUDENT_NO"));
                        t.setSubjectCd(rs.getString("SUBJECT_CD"));
                        t.setSchoolCd(rs.getString("SCHOOL_CD"));
                        t.setNo(rs.getInt("NO"));
                        t.setPoint(rs.getInt("POINT"));
                        t.setClassNum(rs.getString("CLASS_NUM"));
                        list.add(t);
                    }
                }
            }
            return list;
    }

    //得点登録
    public int insert(Test t) throws Exception {
        //SQL文をセット
        String sql = "INSERT INTO TEST(STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO, POINT, CLASS_NUM) "
        + "VALUES(?, ?, ?, ?, ?, ?)";

        try(Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, t.getStudentNo());
                ps.setString(2, t.getSubjectCd());
                ps.setString(3, t.getSchhoolCd());
                ps.setInt(4, t.getNo());
                ps.setInt(5, t.getPoint());
                ps.setString(6, t.getClassNum());

                return ps.executeUpdate();
            }
    }

    //得点更新
    public int update(Test t) throws Exception {
        //SQL文をセット
        String sql = "UPDATE TEST SET POINT = ? "
                   + "WHERE STUDENT_NO = ? AND SUBJECT_CD = ? AND SCHOOL_CD = ? AND NO = ?";

        try(Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, t.getPoint());
                ps.setString(2, t.getStudentNo());
                ps.setString(3, t.getSubjectCd());
                ps.setString(4, t.getSchhoolCd());
                ps.setInt(5, t.getNo());

                return ps.executeUpdate();
            }
    }
    
}
