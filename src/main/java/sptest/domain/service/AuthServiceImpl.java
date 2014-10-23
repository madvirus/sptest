package sptest.domain.service;

import sptest.domain.dao.MemberDao;
import sptest.domain.model.Member;

public class AuthServiceImpl implements AuthService {
    private MemberDao memberDao;

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Override
    public Auth authenticate(AuthRequest authReq) {
        Member member = memberDao.selectById(authReq.getId());
        if (member == null)
            throw new IdPasswordNotMatchException();
        if (!member.matchPassword(authReq.getPassword()))
            throw new IdPasswordNotMatchException();
        return new Auth(member.getId(), member.getName());
    }
}
