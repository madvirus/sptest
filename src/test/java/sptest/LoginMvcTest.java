package sptest;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import sptest.domain.service.Auth;

import javax.servlet.http.HttpSession;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
@WebAppConfiguration
@Sql(scripts = "classpath:/initdata.sql")
public class LoginMvcTest {
    @Autowired
    private WebApplicationContext ctx;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    public void postInvalidIdOrPassword() throws Exception {
        mockMvc.perform(post("/login").param("id", "user").param("password", "1234"))
                .andExpect(model().attributeHasErrors("login"))
                .andExpect(view().name("loginForm"))
                .andExpect(request().sessionAttribute("auth", nullValue()));
    }

    @Test
    public void loginSuccess() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/login").param("id", "gildong").param("password", "1234"))
                .andExpect(redirectedUrl("/main"))
                .andReturn();
        HttpSession session = mvcResult.getRequest().getSession();
        Auth auth = (Auth) session.getAttribute("auth");
        assertThat(auth, notNullValue());
        assertThat(auth.getId(), equalTo("gildong"));
    }

}
