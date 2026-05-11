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

    //list(低権力)
    public List<Teacher> rowfilter(School school) throws Exception {
        // リストを初期化
        List<Teacher> list=new ArrayList<>();
            // データベースへのコネクションを確立
        Connection con=getConnection();
        // プリペアードステートメント
        PreparedStatement st=null;
        try {
            // プリペアードステートメントにSQL文をセット
            st=con.prepareStatement(
                "SELECT NAME,SCHOOL_CD,ID,PASSWORD FROM TEACHER WHERE SCHOOL_CD=? order by AUTHORITY ,NAME"
            );
            // プリペアードステートメントに学校コードをバインド
            st.setString(1, school.getCd());
            // プリペアードステートメントを実行
            ResultSet rs=st.executeQuery();
            SchoolDao schoolDao=new SchoolDao();

            // リザルトセットを全件走査
            while (rs.next()) {
                // リストにクラス番号を追加
                Teacher t=new Teacher();
                t.setSchool(schoolDao.get(rs.getString("school_cd")));
                t.setName(rs.getString("name"));
                
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
    //List(中権力)
    public List<Teacher> Mdfilter(School school) throws Exception {
        // リストを初期化
        List<Teacher> list=new ArrayList<>();
            // データベースへのコネクションを確立
        Connection con=getConnection();
        // プリペアードステートメント
        PreparedStatement st=null;
        try {
            // プリペアードステートメントにSQL文をセット
            st=con.prepareStatement(
                "SELECT NAME,SCHOOL_CD,ID,PASSWORD FROM TEACHER Where school_cd=?");
            // プリペアードステートメントに学校コードをバインド
            st.setString(1, school.getCd());
            // プリペアードステートメントを実行
            ResultSet rs=st.executeQuery();
                SchoolDao schoolDao=new SchoolDao();
            // リザルトセットを全件走査
            while (rs.next()) {
                // リストにクラス番号を追加
                Teacher t=new Teacher();
                t.setSchool(schoolDao.get(rs.getString("school_cd")));
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

    //List(高権力)
    public List<Teacher> Hifilter() throws Exception {
        // リストを初期化
        List<Teacher> list=new ArrayList<>();
            // データベースへのコネクションを確立
        Connection con=getConnection();
        // プリペアードステートメント
        PreparedStatement st=null;
        try {
            // プリペアードステートメントにSQL文をセット
            st=con.prepareStatement(
                "SELECT NAME,SCHOOL_CD,ID,PASSWORD FROM TEACHER");
            // プリペアードステートメントに学校コードをバインド
            
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
                st=con.prepareStatement(
                    "update teacher set id=?, password=?, name=?, school_cd=? where no=?"
                );
                st.setString(1, teacher.getId());
                st.setString(2, teacher.getPassword());
                st.setString(3, teacher.getName());
                st.setString(4, teacher.getSchool().getCd());
               
                
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
    
}
