package com.kh.member.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static com.kh.common.JDBCTemplate.*;

import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.vo.MemberVo;

public class MemberService {

	public int join(MemberVo m) {
		
		//DB Connection 가져오기
		Connection conn = getConnection();
		
		//쿼리 날리기 //insert
		int result = 0;
		try {
			result = insertMember(conn, m);
			if(result > 0) 
				commit(conn);
			else
				rollback(conn);		
		} catch (SQLException e) {
			rollback(conn);
			e.printStackTrace();
		} finally {
			close(conn);
		}
		
		//결과 반환해주기
		
		close(conn);
		return result;
	}

	public int insertMember(Connection conn, MemberVo m) throws SQLException {
		//dao 불러서 쿼리 실행 후 결과 확인
		int result = new MemberDao().insertMember(conn, m);
	
		return result;
	}

	public MemberVo login(MemberVo m) {
		boolean result = false;
		// 커넥션 가져오기
		Connection conn = getConnection();
		//id 가지고 그 아이아디의 비번 조회
		MemberVo selectedMember = selectMember(conn,m);
		close(conn);
		// 가져온 pwd로 사용자 입력 pwd와 같은지 비교
		if(selectedMember !=null) {
			if(selectedMember.getPwd().equals(m.getPwd())) {
				return selectedMember;
			} else {
				return null;
			}
		} else {
			return null;
		}
		
	}
	
	public MemberVo selectMember(Connection conn, MemberVo m) {	
		return new MemberDao().selectMember(conn, m);
	}

	public List<MemberVo> selectMemberAll(Connection conn) {
		return new MemberDao().selectMemberAll(conn);
	}

	public List<MemberVo> search() {
		Connection conn = getConnection();
		List<MemberVo> memberList=selectMemberAll(conn);
		close(conn);
		
		return memberList;
	}
}
