package dao;

import bean.Test;
import bean.Student;
import bean.Subject;
import bean.School;
import bean.ClassNum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.http.Part;

public class TestDao extends Dao {

    // ---------------------------------------------------------
    // 1. get（1件取得）
    // ---------------------------------------------------------
    public Test get(Student student, Subject subject, School school, int no) throws Exception {

        String sql =
            "SELECT T.STUDENT_NO, T.SUBJECT_CD, T.SCHOOL_CD, S.CLASS_NUM, T.NO, T.POINT " +
            "FROM TEST T " +
            "JOIN STUDENT S " +
            "ON T.STUDENT_NO = S.NO " +
            "WHERE T.STUDENT_NO=? AND T.SUBJECT_CD=? AND T.SCHOOL_CD=? AND T.NO=?";

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
            try {
                stu.setEntYear(rSet.getInt("STUDENT_ENT_YEAR"));
            } catch (SQLException e) {

            }
            stu.setName(rSet.getString("STUDENT_NAME"));
            t.setStudent(stu);

            // Subject
            Subject sub = new Subject();
            sub.setCd(rSet.getString("SUBJECT_CD"));
            t.setSubject(sub);

            // School
            t.setSchool(school);

            // ClassNum
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
            "SELECT T.STUDENT_NO, T.SUBJECT_CD, T.SCHOOL_CD, T.NO, T.POINT, S.CLASS_NUM, " +
            "S.ENT_YEAR AS STUDENT_ENT_YEAR, S.NAME AS STUDENT_NAME " +
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
            "INSERT INTO TEST(STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO, POINT) " +
            "VALUES(?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, t.getStudent().getNo());
            ps.setString(2, t.getSubject().getCd());
            ps.setString(3, t.getSchool().getCd());
            ps.setInt(4, t.getNo());
            ps.setInt(5, t.getPoint());

            return ps.executeUpdate() == 1;
        }
    }

    // ---------------------------------------------------------
    // 6. update（List<Test> を更新）
    // ---------------------------------------------------------
    public boolean update(List<Test> list) throws Exception {

        try (Connection con = getConnection()) {
            con.setAutoCommit(false);

            for (Test t : list) {
                if (!update(t, con)) {
                    con.rollback();
                    return false;
                }
            }

            con.commit();
            return true;
        }
    }

    // ---------------------------------------------------------
    // 7. update（1件更新）
    // ---------------------------------------------------------
    public boolean update(Test t, Connection con) throws Exception {

        String sql =
            "UPDATE TEST "+
               "SET POINT = ? " +
             "WHERE STUDENT_NO = ? AND " +
                   "SUBJECT_CD = ? AND " +
                   "SCHOOL_CD =? AND " +
                   "NO = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, t.getPoint());
            ps.setString(2, t.getStudent().getNo());
            ps.setString(3, t.getSubject().getCd());
            ps.setString(4, t.getSchool().getCd());
            ps.setInt(5, t.getNo());

            return ps.executeUpdate() == 1;
        }
    }

    // -------------------------------------------------------
    // 8. delete（一件削除）
    // -------------------------------------------------------
    public boolean delete(String studentNo, String subjectCd, String schoolCd, int no) throws Exception {

        String sql =
            "DELETE FROM TEST " +
            "WHERE STUDENT_NO = ? AND SUBJECT_CD = ? AND SCHOOL_CD = ? AND NO = ?";

        try (Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, studentNo);
            ps.setString(2, subjectCd);
            ps.setString(3, schoolCd);
            ps.setInt(4, no);

            return ps.executeUpdate() == 1;
        }
    }

    // -------------------------------------------------------
    // 9. CSV（登録）
    // -------------------------------------------------------
    public boolean readInsertCSV(Part csv, School school) throws Exception {
        
        String sql = "INSERT INTO TEST (STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO, POINT) VALUES (?, ?, ?, ?, ?)";
        int count = 0;

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             BufferedReader br = new BufferedReader(new InputStreamReader(csv.getInputStream(), "UTF-8"))) {

            con.setAutoCommit(false);

            String line;
            while ((line = br.readLine()) != null) {
                // 空行スキップ
                if (line == null || line.trim().isEmpty()) {
                   continue;
                }

                String[] data = line.split(",");
                // 列不足はスキップ
               if (data.length < 4) {
                   continue;
                }

                String studentNo = data[0].trim();
                String subjectCd = data[1].trim();
                String noStr = data[2].trim();
                String pointStr = data[3].trim();

                // 数値変換チェック
                int no;
                int point;
                try {
                    no = Integer.parseInt(noStr);
                    point = Integer.parseInt(pointStr);
                } catch (NumberFormatException e) {
                    // フォーマット不正はスキップ（必要ならここで rollback して false を返す設計にもできる）
                    continue;
                }

                ps.setString(1, studentNo);
                ps.setString(2, subjectCd);
                ps.setString(3, school.getCd());
                ps.setInt(4, no);
                ps.setInt(5, point);

                count += ps.executeUpdate();
            }

            con.commit();
        }

        return count > 0;
    }

    // -------------------------------------------------------
    // 10. CSV（書き込み）
    // -------------------------------------------------------
    public String createCSV(School school, boolean unusedFlag) throws Exception {

        List<Test> tests = filter(0, null, null, 0, school);

        StringBuilder sb = new StringBuilder();

        // データ
        for (Test t : tests) {

            // Test bean の既存ゲッターを使用
            String studentNo = "";
            if (t.getStudent() != null && t.getStudent().getNo() != null) {
                studentNo = t.getStudent().getNo();
            }

            String subjectCd = "";
            if (t.getSubject() != null && t.getSubject().getCd() != null) {
                subjectCd = t.getSubject().getCd();
            }

            String noStr = String.valueOf(t.getNo());
            String pointStr = String.valueOf(t.getPoint());

            sb.append(studentNo).append(",");
            sb.append(subjectCd).append(",");
            sb.append(noStr).append(",");
            sb.append(pointStr).append(",");
            // Test に school がセットされていない可能性があるため、引数の school を使う
            sb.append(school.getCd());

            sb.append("\n");
        }
        return sb.toString();
    }
}
