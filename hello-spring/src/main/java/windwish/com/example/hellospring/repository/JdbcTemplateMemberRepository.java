package windwish.com.example.hellospring.repository;

import org.springframework.beans.factory.annotation.Autowired;
import windwish.com.example.hellospring.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//implements 하는 법1 : implements는 인터페이스(Interface)를 구현하는 것을 나타내는 키워드
//implements 하는 법2 : 윈도우 : art + enter : 전체 선택
public class JdbcTemplateMemberRepository implements MemberRepository{

    //jdbcTemplate 쓰는 법 : DB연결(datasource를 받아옴)
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateMemberRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //save
    @Override
    public Member save(Member member) {
        //insert문이 만들어짐
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());
        Number key = jdbcInsert.executeAndReturnKey(new
                MapSqlParameterSource(parameters));
        member.setId(key.longValue());
        return member;
    }

    //findById : 조회 코드
    //결과를 맵핑 : memberRowMapper()
    @Override
    public Optional<Member> findById(Long id) {
        List<Member> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(), id);
        //옵션으로 반환
        return result.stream().findAny();
    }

    //findByName
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(), name);
        return result.stream().findAny();
    }

    //findAll : 결과
    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from member", memberRowMapper());
    }

    //결과를 맵핑 함수 : memberRowMapper()
    //람다 함수(alt + enter -> replace)
    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setName(rs.getString("name"));
            return member;
        };
    }
}
