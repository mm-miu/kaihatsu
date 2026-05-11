package dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import bean.Subject;
import bean.School;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.FileReader;
import jakarta.servlet.http.Part;

public class SubjectDao extends Dao {

    // 科目コードと学校コードから科目情報を取得するメソッド
    public Subject get(String cd, School school) throws Exception {

        Subject subject = null;

        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement(
            "select * from subject where cd = ? and school_cd = ?"
        );

        st.setString(1, cd);
        st.setString(2, school.getCd());

        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            subject = new Subject();
            subject.setCd(rs.getString("cd"));
            subject.setName(rs.getString("name"));
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

    public boolean readInsertCSV(
        Part csv,
        School school) throws Exception {

    int count = 0;

    try (
        BufferedReader br =
            new BufferedReader(
                new InputStreamReader(
                    csv.getInputStream(),
                    "UTF-8"))
    ) {

        String line;

        while ((line = br.readLine()) != null) {

            // 空行スキップ
            if (line.isBlank()) {
                continue;
            }

            // カンマ区切り
            String[] data = line.split(",");

            // 列不足対策
            if (data.length < 2) {
                continue;
            }

            // 値取得
            String cd = data[0].trim();
            String name = data[1].trim();

            // 科目コード3文字チェック
            if (cd.length() != 3) {
                continue;
            }

            // Subject生成
            Subject subject = new Subject();

            subject.setCd(cd);
            subject.setName(name);

            // 学校セット
            subject.setSchool(school);

            // DB登録
            if (save(subject)) {
                count++;
            }
        }
    }

    return count > 0;
}

public String createCSV(School school, boolean unusedFlag) throws Exception {

    List<Subject> subjects = filter(school);

    StringBuilder sb = new StringBuilder();

    // ヘッダ
    sb.append("cd,name,school_cd");
    sb.append("\n");

    // データ
    for (Subject s : subjects) {

        sb.append(s.getCd()).append(",");
        sb.append(s.getName()).append(",");
        sb.append(s.getSchool().getCd());

        sb.append("\n");
    }

    return sb.toString();
}

}