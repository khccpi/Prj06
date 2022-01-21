package customer.dao;

import java.sql.*;

import customer.vo.Notice;

public class NoticeDao {

	public int delete(String seq) throws Exception {
		String sql="delete from notices where seq=?";

		//dbcon
		Class.forName("oracle.jdbc.driver.OracleDriver");

		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String user="hr";
		String pw="123456";
		Connection con=DriverManager.getConnection(url,user,pw);

		//실행
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, seq);

		int del=pstmt.executeUpdate();//insert delete update

		pstmt.close();
		con.close();
	
		return del;
	}
	
	
	public int write(Notice nc) throws Exception {

		String sql = "insert into notices values(" + "(select max(to_number(seq))+1 from notices)"
				+ ",?,'cj',sysdate,0,?)";
		// dbcon
		Class.forName("oracle.jdbc.driver.OracleDriver");

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String pw = "123456";
		Connection con = DriverManager.getConnection(url, user, pw);

		// 실행
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, nc.getTitle());
		pstmt.setString(2, nc.getContent());
		
		int af=pstmt.executeUpdate();// insert실행

		
		pstmt.close();
		con.close();
		
		return af;

	}

	// 변수로 수정내용받기
	public int update2(String s, String t, String c) throws Exception {
		String sql = "update notices" + " set title=?,content=? where seq=?";

		// dbcon
		Class.forName("oracle.jdbc.driver.OracleDriver");

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String pw = "123456";
		Connection con = DriverManager.getConnection(url, user, pw);

		// 실행
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, t);
		pstmt.setString(2, c);
		pstmt.setString(3, s);

		int af = pstmt.executeUpdate();

		pstmt.close();
		con.close();
		return af;

	}

	// 객체로 수정내용받기
	public int update(Notice notice) throws Exception {
		String sql = "update notices" + " set title=?,content=? where seq=?";

		// dbcon
		Class.forName("oracle.jdbc.driver.OracleDriver");

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String pw = "123456";
		Connection con = DriverManager.getConnection(url, user, pw);

		// 실행
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, notice.getTitle());
		pstmt.setString(2, notice.getContent());
		pstmt.setString(3, notice.getSeq());

		int af = pstmt.executeUpdate();

		pstmt.close();
		con.close();

		return af;

	}

	public Notice getNotice(String seq) throws Exception {

		String sql = "select * from notices where seq=" + seq;

		// dbcon
		Class.forName("oracle.jdbc.driver.OracleDriver");

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String pw = "123456";
		Connection con = DriverManager.getConnection(url, user, pw);

		// 실행
		Statement st = con.createStatement();
		// 결과
		ResultSet rs = st.executeQuery(sql);

		rs.next();

		// Notice에 select결과물 담아보기
		Notice n = new Notice();
		n.setSeq(rs.getString("seq"));
		n.setWriter(rs.getString("writer"));
		n.setTitle(rs.getString("title"));
		n.setContent(rs.getString("content"));
		n.setRegdate(rs.getDate("regdate"));
		n.setHit(rs.getInt("hit"));

		rs.close();
		st.close();
		con.close();

		return n;
	}

}
