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
    //subject 
    private String baseSql="select * from subject join test where school_cd=?";
    
    public List<TestListStudent> postFilter(ResultSet rSet) throws Exception{

        List<TestListStudent> list=new ArrayList<>();

        try {
            while (rSet.next()){

                TestListStudent testliststudent=new TestListStudent();
                
                testliststudent.setSubjectName(rSet.getString("subjectname"));
                testliststudent.setSubjectCd(rSet.getString("subjectcd"));
                testliststudent.setNum(rSet.getInt("num"));
                testliststudent.setPoint(rSet.getInt("point"));
                
                list.add(testliststudent);
            }
            
        }catch (SQLException|NullPointerException e) {
                e.printStackTrace();
        }
        return list;
    }
    //入学年度、クラス、科目、学生番号
    public List<TestListStudent> filter(Student student) throws Exception{
        List<TestListStudent> list=new ArrayList<>();
        Connection con=getConnection();
        PreparedStatement st=null;
        ResultSet rs=null;
        String condition="and ent_year=? and classnun=? and no";


        
    } 
}
