package sptest.domain.dao;

import sptest.domain.model.Member;

public interface MemberDao {
    Member selectById(String id);
    void insert(Member member);
}
