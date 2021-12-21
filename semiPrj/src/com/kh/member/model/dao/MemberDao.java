package com.kh.member.model.dao;

import static com.kh.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.kh.member.model.vo.MemberVo;

public class MemberDao {

	public int insertMember(Connection conn, MemberVo m) throws SQLException {
		// 쿼리 날리기
		int result = 0;
		String sql = "INSERT INTO MEMBER(MEMBER_NO, ID, PWD, NAME, DETAIL, ENROLL_DATE) VALUES(SEQ_MEMBER.NEXTVAL,?,?,?,?,SYSDATE)";
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getPwd());
			pstmt.setString(3, m.getName());
			pstmt.setInt(4, -1);
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public MemberVo selectMember(Connection conn, MemberVo m) {
		String query = "SELECT * FROM MEMBER WHERE ID = ? AND QUIT_YN = 'N'";
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		MemberVo selectedMember = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getId());
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int memberNo = rs.getInt("MEMBER_NO");
				String id = rs.getString("ID");
				String pwd = rs.getString("PWD");
				String name = rs.getString("NAME");
				int detail = rs.getInt("DETAIL");
				Timestamp enrollDate = rs.getTimestamp("ENROLL_DATE");
				Timestamp modifyDate = rs.getTimestamp("MODIFY_DATE");
				String openYn = rs.getString("OPEN_YN");
				selectedMember = new MemberVo();
				selectedMember.setMeberNo(memberNo);
				selectedMember.setId(id);
				selectedMember.setPwd(pwd);
				selectedMember.setName(name);
				selectedMember.setDetail(detail);
				selectedMember.setEnrollDate(enrollDate);
				selectedMember.setModifyDate(modifyDate);
				selectedMember.setOpenYn(openYn);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			close(pstmt);
			close(rs);
		} finally {
			close(pstmt);
			close(rs);
		}
		
		
		return selectedMember;
	}

}
