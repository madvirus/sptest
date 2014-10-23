package sptest.web;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sptest.domain.dao.MemberDao;
import sptest.domain.model.Member;
import sptest.domain.service.DuplicateIdException;
import sptest.domain.service.MemberRegisterService;
import sptest.domain.service.RegisterRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/restapi/members")
public class MemberRestAPI {
    private MemberRegisterService memberRegisterService;
    private MemberDao memberDao;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Member newMember(@RequestBody RegisterRequest member, HttpServletResponse response) {
        memberRegisterService.register(member);
        response.setStatus(HttpServletResponse.SC_CREATED);
        return memberDao.selectById(member.getId());
    }

    public void setMemberRegisterService(MemberRegisterService memberRegisterService) {
        this.memberRegisterService = memberRegisterService;
    }

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
}
