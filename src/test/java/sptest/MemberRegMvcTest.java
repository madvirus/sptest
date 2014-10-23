package sptest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import sptest.domain.dao.MemberDao;
import sptest.domain.service.DuplicateIdException;
import sptest.domain.service.MemberRegisterService;
import sptest.domain.service.RegisterRequest;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
@WebAppConfiguration
@Sql(scripts = "classpath:/initdata.sql")
public class MemberRegMvcTest {
    @Autowired
    private WebApplicationContext ctx;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    public void form() throws Exception {
        mockMvc.perform(get("/regist"))
                .andExpect(view().name("registrationForm"));
    }

    @Test
    public void postErrorForm() throws Exception {
        mockMvc.perform(post("/regist").param("id", "user").param("password", "").param("confirmPassword", "").param("name", "사용자"))
                .andExpect(model().attributeHasFieldErrorCode("member", "password", "required"))
                .andExpect(model().attributeHasFieldErrorCode("member", "confirmPassword", "required"))
                .andExpect(view().name("registrationForm"));
    }

    @Test
    public void postDupId() throws Exception {
        mockMvc.perform(post("/regist").param("id", "gildong").param("password", "1234").param("confirmPassword", "1234").param("name", "사용자"))
                .andExpect(model().attributeHasFieldErrorCode("member", "id", "duplicateId"))
                .andExpect(view().name("registrationForm"));
    }

    @Test
    public void postSuccess() throws Exception {
        mockMvc.perform(post("/regist").param("id", "user").param("password", "1234").param("confirmPassword", "1234").param("name", "사용자"))
                .andExpect(view().name("registered"));
    }
}
