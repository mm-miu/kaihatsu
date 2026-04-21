package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import bean.Teacher;

public class TeacherDao extends Dao {

    public Teacher get(String id) throws Exception {
        // 先生インスタンスを初期化
        Teacher teacher=new Teacher();
        // データベースへのコネクションを確立
        Connection con=getConnection();
        // プリペアードステートメント
        PreparedStatement st=null;

        try {
            // プリペアードステートメントにSQL文をセット
            st=con.prepareStatement("select * from teacher where id = ?");
            // プリペアードステートメントに学校コードをバインド
            st.setString(1, id);
            // プリペアードステートメントを実行
            ResultSet rs=st.executeQuery();
            // 学校Daoを初期化
            SchoolDao schoolDao=new SchoolDao();

            if (rs.next()) {
                // リザルトセットが存在する場合
                // 先生インスタンスにidと名前をセット
                teacher.setId(rs.getString("id"));
                teacher.setName(rs.getString("name"));
                teacher.setSchool(schoolDao.get(rs.getString("school_cd")));
            } else {
                // 存在しない場合
                // 先生インスタンスにnullをセット
                teacher=null;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            // プリペアードステートメントを閉じる
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            // コネクションを閉じる
            if (con!=null) {
                try {
                    con.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }
        return teacher;
    }

    public Teacher login(String id, String password) throws Exception {
        // 先生インスタンスを初期化
        Teacher teacher=new Teacher();
        // データベースへのコネクションを確立
        Connection con=getConnection();
        // プリペアードステートメント
        PreparedStatement st=null;

        try {
            // プリペアードステートメントにSQL文をセット
            st=con.prepareStatement("select * from teacher where id=? and password=?");
            // プリペアードステートメントに値をバインド
            st.setString(1, id);
            st.setString(2, password);
            // プリペアードステートメントを実行
            ResultSet rs=st.executeQuery();
            // 学校Daoを初期化
            SchoolDao schoolDao=new SchoolDao();

            if (rs.next()) {
                // リザルトセットが存在する場合
                // 先生インスタンスにidと名前をセット
                teacher.setId(rs.getString("id"));
                teacher.setName(rs.getString("name"));
                teacher.setSchool(schoolDao.get(rs.getString("school_cd")));
            } else {
                // 存在しない場合
                // 先生インスタンスにnullをセット
                teacher=null;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            // プリペアードステートメントを閉じる
            if (st!=null) {
                try {
                    st.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            // コネクションを閉じる
            if (con!=null) {
                try {
                    con.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }
        return teacher;
    }
}
