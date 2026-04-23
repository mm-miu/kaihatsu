package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import bean.Subject;
import bean.School;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectDao extends Dao {

    // 科目コードと学校コードから科目情報を取得するメソッド
    public Subject get(String cd, School school) throws Exception {
        // クラス番号インスタンスを初期化
        Subject subject=new Subject();
        // データベースへのコネクションを確立
        Connection con=getConnection();
        // プリペアードステートメント
        PreparedStatement st=null;
        try {
            // プリペアードステートメントにSQL文をセット
            st=con.prepareStatement(
                "select * from subject where cd = ? and school_cd = ?"
            );
            // プリペアードステートメントに値をバインド
            st.setString(1, cd);
            st.setString(2, school.getCd());
            // プリペアードステートメントを実行
            ResultSet rs=st.executeQuery();
            // 学校Daoを初期化
            SchoolDao sDao=new SchoolDao();
            if (rs.next()) {
                // リザルトセットが存在する場合
                // クラス番号インスタンスに検索結果をセット
                subject.setCd(rs.getString("cd"));
                subject.setName(rs.getString("name"));
                subject.setSchool(sDao.get(rs.getString("school_cd")));
            } else {
                // リザルトセットが存在しない場合
                // クラス番号インスタンスにnullをセット
                subject=null;
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
        return subject;
    }


    public List<Subject> filter(School school) throws Exception {
        // リストを初期化
        List<Subject> list=new ArrayList<>();
            // データベースへのコネクションを確立
        Connection con=getConnection();
        // プリペアードステートメント
        PreparedStatement st=null;
        try {
            // プリペアードステートメントにSQL文をセット
            st=con.prepareStatement(
                "select cd, name from subject where school_cd=? order by cd"
            );
            // プリペアードステートメントに学校コードをバインド
            st.setString(1, school.getCd());
            // プリペアードステートメントを実行
            ResultSet rs=st.executeQuery();

            // リザルトセットを全件走査
            while (rs.next()) {
                // リストにクラス番号を追加
                Subject s=new Subject();
                s.setCd(rs.getString("cd"));
                s.setName(rs.getString("name"));
                list.add(s);
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

    public boolean save(Subject subject) throws Exception {
        // コネクションを確立
        Connection con=getConnection();
        // プリペアードステートメント
        PreparedStatement st=null;
        // 実行件数
        int count=0;

        try {
            // データベースから科目を取得
            Subject old=get(subject.getCd(), subject.getSchool());
            if (old==null) {
                // 科目が存在しなかった場合
                // プリペアードステートメントにINSERT文をセット
                st=con.prepareStatement(
                    "insert into subject(cd, name, school_cd) values(?, ?, ?)"
                );

                // プリペアードステートメントに値をバインド
                st.setString(1, subject.getCd());
                st.setString(2, subject.getName());
                st.setString(3, subject.getSchool().getCd());
            } else {
                // 科目が存在した場合
                // プリペアードステートメントにUPDATE文をセット
                st=con.prepareStatement(
                    "update subject set name=? where cd=? and school_cd=?"
                );
                st.setString(1, subject.getName());
                st.setString(2, subject.getCd());
                st.setString(3, subject.getSchool().getCd());
            }
            // プリペアードステートメントを実行
            count=st.executeUpdate();

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

        if (count>0) {
            // 実行件数が1件以上ある場合
            return true;
        } else {
            // 実行件数が0件の場合
            return false;
        }
    }

    public boolean delete(Subject subject) throws Exception {
        // コネクションを確立
        Connection con=getConnection();
        // プリペアードステートメント
        PreparedStatement st=null;
        // 実行件数
        int count=0;

        try {
            // データベースから科目を取得
            Subject old=get(subject.getCd(), subject.getSchool());
            if (old==null) {
                // 科目が存在しなかった場合
                return false;

            } else {
                // 科目が存在した場合
                // プリペアードステートメントにDERETE文をセット
                st=con.prepareStatement(
                    "delete from subject where cd=? and school_cd=?"
                );
                st.setString(1, subject.getCd());
                st.setString(2, subject.getSchool().getCd());
            }
            // プリペアードステートメントを実行
            count=st.executeUpdate();

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

        if (count>0) {
            // 実行件数が1件以上ある場合
            return true;
        } else {
            // 実行件数が0件の場合
            return false;
        }
    }
}