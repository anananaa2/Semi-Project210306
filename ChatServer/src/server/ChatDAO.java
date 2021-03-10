package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChatDAO {
	static String msg = "";
	DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();

	/**********************************************************************
	 * 회원가입 사용자로부터 id, nickname, password 입력받아서 chat 테이블에 추가
	 * 
	 * @param id       - 사용자로부터 입력받는 아이디
	 * @param nickname - 사용자로부터 입력받는 닉네임
	 * @param password - 사용자로부터 입력받는 비밀번호
	 **********************************************************************/
	public void join(String id, String nickname, String password) {
		Connection con = dbMgr.getConnection();
		try {
			String sql = "INSERT INTO chat(id, nickname, password) VALUES (?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, nickname);
			pstmt.setString(3, password);
			pstmt.executeUpdate();

			dbMgr.freeConnection(con, pstmt);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**********************************************************************
	 * 회원가입 중복체크
	 * 
	 * @param id - 사용자로부터 입력받는 id
	 * @return 아이디 받아서 DB값이랑 비교, 하나라도 일치하면 false
	 ***********************************************************************/
	public boolean idCompare(String id) {
		boolean result = true;
		Connection con = dbMgr.getConnection();
		try {
			String sql = "SELECT id FROM chat";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				do {
					if (rs.getString("id").equals(id)) {
						result = false;
						break;
					}
				} while (rs.next());
			}
			dbMgr.freeConnection(con, pstmt, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**********************************************************************
	 * @param id       - 사용자로부터 입력받는 id
	 * @param password - 사용자로부터 입력받는 password
	 * @return 1:로그인 성공 2:비밀번호 불일치 3:등록된 아이디 없음
	 ***********************************************************************/
	public int login(String id, String password) {
		int result = 0;
		Connection con = dbMgr.getConnection();
		try {
			String sql = "SELECT password FROM chat WHERE id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString("password").equals(password)) {
					System.out.println("로그인 성공");
					result = 1;
				} else {
					System.out.println("비밀번호가 틀렸습니다");
					result = 2;
				}
			} else {
				System.out.println("등록된 아이디가 없습니다");
				result = 3;
			}
			dbMgr.freeConnection(con, pstmt, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**********************************************************************
	 * @param id - 사용자가 입력한 아이디
	 * @return 입력한 아이디와 일치하는 레코드의 닉네임 리턴
	 ***********************************************************************/
	public String getNickname(String id) {
		String nickname = "";
		Connection con = dbMgr.getConnection();
		try {
			String sql = "SELECT nickname FROM chat WHERE id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				nickname = rs.getString("nickname");
			}
			dbMgr.freeConnection(con, pstmt, rs);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nickname;
	}
}
