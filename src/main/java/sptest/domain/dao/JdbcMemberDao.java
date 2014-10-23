package sptest.domain.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import sptest.domain.model.Member;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcMemberDao implements MemberDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcMemberDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member selectById(String id) {
        List<Member> members = jdbcTemplate.query("select * from MEMBER where ID = ?",
                new RowMapper<Member>() {
                    @Override
                    public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Member(rs.getString("ID"), rs.getString("PASSWORD"), rs.getString("NAME"));
                    }
                }, id);
        return members.isEmpty() ? null : members.get(0);
    }

    @Override
    public void insert(Member member) {
        jdbcTemplate.update("insert into MEMBER values (?, ?, ?)", member.getId(), member.getPassword(), member.getName());
    }
}
