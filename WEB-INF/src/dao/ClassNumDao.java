package dao;

import bean.ClassNum;
import bean.School;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClassNumDao extends Dao {
    
    // 取得したクラス番号、学校情報をもとにクラス番号を返却するメソッド
    public ClassNum get(String class_num, School school) throws Exception {
        // クラス番号インスタンスを初期化
        ClassNum classNum=new ClassNum();
        // データベースへのコネクションを確立
        Connection con=getConnection();
        // プリペアードステートメント
        PreparedStatement st=null;
        try {
            // プリペアードステートメントにSQL文をセット
            st=con.prepareStatement(
                "select * from class_num where class_num = ? and school_cd = ?"
            );
            // プリペアードステートメントに値をバインド
            st.setString(1, class_num);
            st.setString(2, school.getCd());
            // プリペアードステートメントを実行
            ResultSet rs=st.executeQuery();
            // 学校Daoを初期化
            SchoolDao sDao=new SchoolDao();
            if (rs.next()) {
                // リザルトセットが存在する場合
                // クラス番号インスタンスに検索結果をセット
                classNum.setClass_num(rs.getString("class_num"));
                classNum.setSchool(sDao.get(rs.getString("school_cd")));
            } else {
                // リザルトセットが存在しない場合
                // クラス番号インスタンスにnullをセット
                classNum=null;
            }

        } catch (Exception e) {
            throw e;
        } finally {
            // プリペアードステートメントを閉じる
            if (st!=null) {
                try {
                    st.close();;
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
        return classNum;
    }

    // 学校を指定してクラス番号の一覧を取得するメソッド
    public List<String> filter(School school) throws Exception {
        // リストを初期化
        List<String> list=new ArrayList<>();
            // データベースへのコネクションを確立
        Connection con=getConnection();
        // プリペアードステートメント
        PreparedStatement st=null;
        try {
            // プリペアードステートメントにSQL文をセット
            st=con.prepareStatement(
                "select class_num from class_num where school_cd=? order by class_num"
            );
            // プリペアードステートメントに学校コードをバインド
            st.setString(1, school.getCd());
            // プリペアードステートメントを実行
            ResultSet rs=st.executeQuery();

            // リザルトセットを全件走査
            while (rs.next()) {
                // リストにクラス番号を追加
                list.add(rs.getString("class_num"));
            }

        } catch (Exception e) {
            throw e;
        } finally {
            // プリペアードステートメントを閉じる
            if (st!=null) {
                try {
                    st.close();;
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
        return list;
    }
/* 
    public boolean save(ClassNum classNum) throws Exception {

    }

    public boolean save(ClassNum classNum, String newClassNum) throws Exception {

    }
*/

}
