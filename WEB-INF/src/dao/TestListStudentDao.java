package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao extends Dao{
    
    private String baseSql="SELECT sub.name AS subjectName, sub.cd AS subjectCd, ts.no AS num, ts.point" + 
        "FROM student AS st "  + 
        "JOIN test AS ts " + 
        "ON st.no = ts.student_no " + 
        "JOIN subject AS sub " + 
        "ON sub.cd = ts.subject_cd " + 
        "WHERE st.school_cd = ? ";
    
    public List<TestListStudent> postFilter(ResultSet rSet) throws Exception{

        List<TestListStudent> list=new ArrayList<>();

        try {
            while (rSet.next()){

                TestListStudent testliststudent=new TestListStudent();
                
                testliststudent.setSubjectName(rSet.getString("subjectName"));
                testliststudent.setSubjectCd(rSet.getString("subjectCd"));
                testliststudent.setNum(rSet.getInt("num"));
                testliststudent.setPoint(rSet.getInt("point"));
                
                list.add(testliststudent);
            }
            
        }catch (SQLException|NullPointerException e) {
                e.printStackTrace();
        }
        return list;
    }

    public List<TestListStudent> filter(Student student) throws Exception{
        List<TestListStudent> list=new ArrayList<>();
        Connection con=getConnection();
        PreparedStatement st=null;
        ResultSet rs=null;
        String condition=" AND st.no = ?";

        try{
            // プリペアードステートメントにSQL文をセット
            st=con.prepareStatement(baseSql+condition);
            
            st.setString(1, student.getSchool().getCd());
            st.setString(2, student.getNo());
            
            rs=st.executeQuery();

            // リストへの格納処理を実行
            list=postFilter(rs);

        }catch (Exception e) {
            throw e;
        }finally {
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
}
