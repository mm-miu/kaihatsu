package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.School;

public class SchoolDao extends Dao {
    /**
     *  getメソッド 学校コードを指定して学校インスタンスを1件取得する
     * 
     * @param cd:String
     *            学校コード
     * @return 学校クラスのインスタンス 存在しない場合はnull
     * @throws Exception
     */
    public School get(String cd) throws Exception {
        // 学校インスタンスを初期化
        School school=new School();
        // データベースへのコネクションを確立
        Connection con=getConnection();
        // プリペアードステートメント
        PreparedStatement st=null;

        try {
            // プリペアードステートメントにSQL文をセット
            st=con.prepareStatement("select * from school where cd = ?");
            // プリペアードステートメントに学校コードをバインド
            st.setString(1, cd);
            // プリペアードステートメントを実行
            ResultSet rs=st.executeQuery();

            if (rs.next()) {
                // リザルトセットが存在する場合
                // 学校インスタンスに学校コードと学校名をセット
                school.setCd(rs.getString("cd"));
                school.setName(rs.getString("name"));
            } else {
                // 存在しない場合
                // 学校インスタンスにnullをセット
                school=null;
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
        return school;
    }
}
