package sptest.domain.service;

import org.springframework.transaction.annotation.Transactional;
import sptest.domain.dao.MemberDao;
import sptest.domain.model.Member;

public class MemberRegisterServiceImpl implements MemberRegisterService {
    private MemberDao memberDao;

    @Transactional
    @Override
    public void register(RegisterRequest regReq) {
        Member member = memberDao.selectById(regReq.getId());
        if (member != null) {
            throw new DuplicateIdException();
        }
        memberDao.insert(new Member(regReq.getId(), regReq.getPassword(), regReq.getName()));
    }

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
}
