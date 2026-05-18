package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
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
                teacher.setPassword(rs.getString("password"));
                teacher.setAuthority(rs.getString("authority"));
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
                teacher.setAuthority(rs.getString("authority"));
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
    //list(自身)
    public List<Teacher>myfilter(School school,String id) throws Exception{
        List<Teacher> list=new ArrayList<>();
            // データベースへのコネクションを確立
        Connection con=getConnection();
        // プリペアードステートメント
        PreparedStatement st=null;
        try {
            // プリペアードステートメントにSQL文をセット
            st=con.prepareStatement(
                "SELECT NAME,SCHOOL_CD,ID,PASSWORD FROM TEACHER WHERE SCHOOL_CD=? AND ID=?"
            );
            // プリペアードステートメントに学校コードをバインド
            st.setString(1, school.getCd());
            st.setString(2,id);
            // プリペアードステートメントを実行
            ResultSet rs=st.executeQuery();
            

            // リザルトセットを全件走査
            while (rs.next()) {
                // リストにクラス番号を追加
                Teacher t=new Teacher();
                School sch=new School();
                sch.setName(rs.getString("school_cd"));
                t.setSchool(sch);
                t.setName(rs.getString("name"));
                t.setId(rs.getString("id"));
                t.setPassword(rs.getString("password"));
                list.add(t);
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

    
    // ユーザー登録
    public boolean save(Teacher teacher) throws Exception {
        // コネクションを確立
        Connection con=getConnection();
        // プリペアードステートメント
        PreparedStatement st=null;
        // 実行件数
        int count=0;

        try {
            // データベースから学生を取得
            
            Teacher old=get(teacher.getId());
            if (old==null) {
                // 学生が存在しなかった場合
                // プリペアードステートメントにINSERT文をセット
                st=con.prepareStatement(
                    "insert into Teacher(id, password,name,school_cd,authority) values(?, 'password', ?, ?,'3')"
                );

                // プリペアードステートメントに値をバインド
                st.setString(1, teacher.getId());
                st.setString(2, teacher.getName());
                st.setString(3, teacher.getSchool().getCd());
                
            } else {
                // 学生が存在した場合
                // プリペアードステートメントにUPDATE文をセット
                return false;
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
    //重複確認
    public boolean existsTeacherNo(String schoolCd, String id) throws Exception {
        // コネクションを確立
        Connection con=getConnection();
        // プリペアードステートメント
        PreparedStatement st=null;
        // リザルトセット
        ResultSet rs=null;
        // SQL文の条件
        // SQL文の在学フラグ条件
        String condetionNo="and id=?";

        try {
            // プリペアードステートメントにSQL文をセット
            st=con.prepareStatement("select * from teacher where school_cd=?"+condetionNo);
            // プリペアードステートメントに学校コードをバインド
            st.setString(1, schoolCd);
            st.setString(2, id);
            // プリペアードステートメントを実行
            rs=st.executeQuery();

            return rs.next();
            
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
    }
    public boolean update(Teacher teacher) throws Exception {
        // コネクションを確立
        Connection con=getConnection();
        // プリペアードステートメント
        PreparedStatement st=null;
        // 実行件数
        int count=0;
        try {
            st=con.prepareStatement(
                "update teacher set password=?, name=?, school_cd=? ,authority=? where id=?"
            );
            st.setString(1, teacher.getPassword());
            st.setString(2, teacher.getName());
            st.setString(3, teacher.getSchool().getCd());
            st.setString(4, teacher.getAuthority());
            st.setString(5, teacher.getId());

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
    
    //ソート兼リスト
    public List<Teacher> Sort(String Scd,String Sna,Teacher teacher) throws Exception {
        String base="";
        String SCD=" ORDER BY school_cd";
        String SNE="";
    if("1".equals(teacher.getAuthority())){
         base="SELECT NAME,SCHOOL_CD,ID,PASSWORD FROM TEACHER";
    }else{if ("2".equals(teacher.getAuthority())){
         base="SELECT NAME,SCHOOL_CD,ID,PASSWORD FROM TEACHER WHERE SCHOOL_CD=?";
    }else{
         base="SELECT NAME,SCHOOL_CD,FROM TEACHER WHERE SCHOOL_CD=?";
    }}
    //学校コードの並び替え

    if("2".equals(Scd)){
        SCD=" ORDER BY school_cd DESC";
    }
    if("2".equals(Sna)){
        SNE=",NAME DESC";
    }else{
        SNE=", NAME ASC";
    }

        // リストを初期化
        List<Teacher> list=new ArrayList<>();
            // データベースへのコネクションを確立
        Connection con=getConnection();
        // プリペアードステートメント
        PreparedStatement st=null;
        try {
            // プリペアードステートメントにSQL文をセット
            st=con.prepareStatement(
                base+SCD+SNE
                );
            // プリペアードステートメントに学校コードをバインド
            if ( "1".equals(teacher.getAuthority())){
                System.out.println("");
            }else{
                st.setString(1,teacher.getSchool().getCd());
            }
            // プリペアードステートメントを実行
            ResultSet rs=st.executeQuery();
                
                
            // リザルトセットを全件走査
            while (rs.next()) {
                // リストにクラス番号を追加
                School sch=new School();
                Teacher t=new Teacher();
                sch.setName(rs.getString("school_cd"));
                t.setSchool(sch);
                t.setName(rs.getString("name"));
                if ("3".equals(teacher.getAuthority())){
                
            }else{
                t.setId(rs.getString("id"));
                t.setPassword(rs.getString("password"));
            }
                list.add(t);
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
}
