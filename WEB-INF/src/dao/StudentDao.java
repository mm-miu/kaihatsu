package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import bean.Student;
import bean.School;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDao extends Dao {
    
    private String baseSql="select * from student where school_cd=?";

    public Student get(String no) throws Exception {
        // 学生インスタンスを初期化
        Student student=new Student();
        // コネクションを確立
        Connection con=getConnection();
        // プリペアードステートメント
        PreparedStatement st=null;

        try {
            // プリペアードステートメントにSQL文をセット
            st=con.prepareStatement("select * from student where no=?");
            // プリペアードステートメントに学生番号をバインド
            st.setString(1, no);
            // プリペアードステートメントを実行
            ResultSet rs=st.executeQuery();

            // 学校Daoを初期化
            SchoolDao schoolDao=new SchoolDao();
            
            if (rs.next()) {
                // リザルトセットが存在する場合
                // 学生インスタンスに検索結果をセット
                student.setNo(rs.getString("no"));
                student.setName(rs.getString("name"));
                student.setEntYear(rs.getInt("ent_year"));
                student.setClassNum(rs.getString("class_num"));
                student.setAttend(rs.getBoolean("is_attend"));
                // 学校フィールドには学校コードで検索した学校インスタンスをセット
                student.setSchool(schoolDao.get(rs.getString("school_cd")));
            } else {
                // リザルトセットが存在しない場合
                // 学生インスタンスにnullをセット
                student=null;
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
        return student;
    }

    // フィルター後のリストへの格納処理をするメソッド
    private List<Student> postFilter(ResultSet resultSet, School school) throws Exception {
        // リストを初期化
        List<Student> list=new ArrayList<>();
        try {
            // リザルトセットを全件走査
            while (resultSet.next()) {
                // 学生インスタンスを初期化
                Student student=new Student();
                // 学生インスタンスに検索結果をセット
                student.setNo(resultSet.getString("no"));
                student.setName(resultSet.getString("name"));
                student.setEntYear(resultSet.getInt("ent_year"));
                student.setClassNum(resultSet.getString("class_num"));
                student.setAttend(resultSet.getBoolean("is_attend"));
                student.setSchool(school);
                // リストに追加
                list.add(student);
            }
        } catch (SQLException|NullPointerException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 学校、入学年度、クラス番号、在学フラグを指定して学生の一覧を取得するメソッド
    public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {
        // リストを初期化
        List<Student> list=new ArrayList<>();
        // コネクションを確率
        Connection con=getConnection();
        // プリペアードステートメント
        PreparedStatement st=null;
        // リザルトセット
        ResultSet rs=null;
        // SQL文の条件
        String condition="and ent_year=? and class_num=?";
        // SQL文のソート
        String order=" order by no asc";

        // SQL文の在学フラグ条件
        String condetionIsAttend="";
        // 在学フラグがtrueの場合
        if (isAttend) {
            condetionIsAttend="and is_attend=true";
        }

        try {
            // プリペアードステートメントにSQL文をセット
            st=con.prepareStatement(baseSql+condition+condetionIsAttend+order);
            // プリペアードステートメントに学校コードをバインド
            st.setString(1, school.getCd());
            // プリペアードステートメントに入学年度をバインド
            st.setInt(2, entYear);
            // プリペアードステートメントにクラス番号をバインド
            st.setString(3, classNum);
            // プリペアードステートメントを実行
            rs=st.executeQuery();

            // リストへの格納処理を実行
            list=postFilter(rs, school);
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
        return list;
    }

    public List<Student> filter(School school, int entYear, boolean isAttend) throws Exception {
        // リストを初期化
        List<Student> list=new ArrayList<>();
        // コネクションを確率
        Connection con=getConnection();
        // プリペアードステートメント
        PreparedStatement st=null;
        // リザルトセット
        ResultSet rs=null;
        // SQL文の条件
        String condition="and ent_year=?";
        // SQL文のソート
        String order=" order by no asc";

        // SQL文の在学フラグ条件
        String condetionIsAttend="";
        // 在学フラグがtrueの場合
        if (isAttend) {
            condetionIsAttend="and is_attend=true";
        }

        try {
            // プリペアードステートメントにSQL文をセット
            st=con.prepareStatement(baseSql+condition+condetionIsAttend+order);
            // プリペアードステートメントに学校コードをバインド
            st.setString(1, school.getCd());
            // プリペアードステートメントに入学年度をバインド
            st.setInt(2, entYear);
            // プリペアードステートメントを実行
            rs=st.executeQuery();

            // リストへの格納処理を実行
            list=postFilter(rs, school);
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
        return list;
    }

    public List<Student> filter(School school, boolean isAttend) throws Exception {
        // リストを初期化
        List<Student> list=new ArrayList<>();
        // コネクションを確率
        Connection con=getConnection();
        // プリペアードステートメント
        PreparedStatement st=null;
        // リザルトセット
        ResultSet rs=null;
        // SQL文の条件
        String order=" order by no asc";

        // SQL文の在学フラグ条件
        String condetionIsAttend="";
        // 在学フラグがtrueの場合
        if (isAttend) {
            condetionIsAttend="and is_attend=true";
        }

        try {
            // プリペアードステートメントにSQL文をセット
            st=con.prepareStatement(baseSql+condetionIsAttend+order);
            // プリペアードステートメントに学校コードをバインド
            st.setString(1, school.getCd());
            // プリペアードステートメントを実行
            rs=st.executeQuery();

            // リストへの格納処理を実行
            list=postFilter(rs, school);
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
        return list;
    }

    public boolean existsStudentNo(String schoolCd, String no) throws Exception {
        // コネクションを確立
        Connection con=getConnection();
        // プリペアードステートメント
        PreparedStatement st=null;
        // リザルトセット
        ResultSet rs=null;
        // SQL文の条件
        // SQL文の在学フラグ条件
        String condetionNo="and no=?";

        try {
            // プリペアードステートメントにSQL文をセット
            st=con.prepareStatement(baseSql+condetionNo);
            // プリペアードステートメントに学校コードをバインド
            st.setString(1, schoolCd);
            st.setString(2, no);
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

    public boolean save(Student student) throws Exception {
        // コネクションを確立
        Connection con=getConnection();
        // プリペアードステートメント
        PreparedStatement st=null;
        // 実行件数
        int count=0;

        try {
            // データベースから学生を取得
            Student old=get(student.getNo());
            if (old==null) {
                // 学生が存在しなかった場合
                // プリペアードステートメントにINSERT文をセット
                st=con.prepareStatement(
                    "insert into student(no, name, ent_year, class_num, is_attend, school_cd) values(?, ?, ?, ?, ?, ?)"
                );

                // プリペアードステートメントに値をバインド
                st.setString(1, student.getNo());
                st.setString(2, student.getName());
                st.setInt(3, student.getEntYear());
                st.setString(4, student.getClassNum());
                st.setBoolean(5, student.isAttend());
                st.setString(6, student.getSchool().getCd());
            } else {
                // 学生が存在した場合
                // プリペアードステートメントにUPDATE文をセット
                st=con.prepareStatement(
                    "update student set name=?, ent_year=?, class_num=?, is_attend=? where no=?"
                );
                st.setString(1, student.getName());
                st.setInt(2, student.getEntYear());
                st.setString(3, student.getClassNum());
                st.setBoolean(4, student.isAttend());
                st.setString(5, student.getNo());
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

    public boolean update(Student student) throws Exception {
        // コネクションを確立
        Connection con=getConnection();
        // プリペアードステートメント
        PreparedStatement st=null;
        // 実行件数
        int count=0;
        try {
            st=con.prepareStatement(
                "update student set name=?, ent_year=?, class_num=?, is_attend=? where no=?"
            );
            st.setString(1, student.getName());
            st.setInt(2, student.getEntYear());
            st.setString(3, student.getClassNum());
            st.setBoolean(4, student.isAttend());
            st.setString(5, student.getNo());

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
