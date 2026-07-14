package com.zeus.service;

import java.util.List;

import com.zeus.domain.Member;

public interface MemberService {
	public boolean insert(Member member) throws Exception;
	public List<Member> list() throws Exception;
	public boolean update(Member member) throws Exception;
	public boolean delete(Member member) throws Exception;
	public Member select(Member member) throws Exception;
}
