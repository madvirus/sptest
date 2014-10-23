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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import sptest.domain.service.Auth;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
@WebAppConfiguration
@Sql(scripts = "classpath:/initdata.sql")
public class Login2MvcTest {
    @Autowired
    private WebApplicationContext ctx;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    public void loginSuccess() throws Exception {
        mockMvc.perform(post("/login2").param("id", "gildong").param("password", "1234"))
                .andExpect(redirectedUrl("/main"))
                .andExpect(cookie().exists("AC"));
    }

    @Test
    public void logoutSuccess() throws Exception {
        mockMvc.perform(post("/logout2").cookie(new Cookie("AUTH", "somevalue")))
                .andExpect(redirectedUrl("/main"))
                .andExpect(cookie().maxAge("AC", 0))
                .andExpect(cookie().path("AC", "/"));
    }

}
