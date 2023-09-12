package member;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import el_chap05.JDBCConect;

public class MemberDAO extends JDBCConect {
	//아이디와패스워드에 일치하는 회원정보를 반환
	public MemberDTO getMemberDTO(String id, String pass) {
		MemberDTO dto = new MemberDTO();
		String sql = "select * from member where id=? and pass=?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pass);
			rs = psmt.executeQuery();	//쿼리문이 select 일때만 executeQuery()사용
			if(rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString("name"));
				dto.setRegidate(rs.getString("regidate"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}
	
	public int inserMember(MemberDTO dto) {
		int result = 0;
		String sql = "insert into member values(?,?,?,sysdate)";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, dto.getId());			
			psmt.setString(2, dto.getPass());
			psmt.setString(3, dto.getName());
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}
	//회원목록 반환
	public List<MemberDTO> getMemberList() {
		List<MemberDTO> memberlist = new ArrayList<MemberDTO>();
		String sql = "select * from member";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString("name"));
				dto.setRegidate(rs.getString("regidate"));
				memberlist.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return memberlist;
	}
}
