package sptest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sptest.domain.dao.MemberDao;
import sptest.domain.service.DuplicateIdException;
import sptest.domain.service.MemberRegisterService;
import sptest.domain.service.RegisterRequest;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
@Sql(scripts = "classpath:/initdata.sql")
public class MemberRegSvcIntTest {
    @Autowired
    private MemberRegisterService memberRegisterService;

    @Autowired
    private MemberDao memberDao;

    @Test
    public void register() {
        // 데이터가 초기화되므로, "user" ID를 갖는 데이터는 항상 DB에 존재하지 않음
        RegisterRequest regReq = createRequest("user", "사용자");
        memberRegisterService.register(regReq);
        assertThat(memberDao.selectById("user"), notNullValue());
    }

    @Test(expected = DuplicateIdException.class)
    public void duplicateIdTest() {
        // 데이터를 초기화할 때 ID가 "gildong"인 데이터를 삽입하므로, 항상 ID 중복 에러 발생
        RegisterRequest regReq = createRequest("gildong", "홍길동");
        memberRegisterService.register(regReq);
    }

    public RegisterRequest createRequest(String id, String name) {
        RegisterRequest regReq = new RegisterRequest();
        regReq.setId(id);
        regReq.setPassword("1234");
        regReq.setConfirmPassword("1234");
        regReq.setName(name);
        return regReq;
    }
}
