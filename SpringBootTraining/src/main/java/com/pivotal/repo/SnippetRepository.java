package com.pivotal.repo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import io.pivotal.domain.Snippet;

//deepak
@Repository
public class SnippetRepository {

	private final JdbcTemplate jdbcTemplate2;
	
	private final String SQL_INSERT = "insert into snippet(id,title,code,created,modified) values(?,?,?,?,?)";
    private final String SQL_QUERY_ALL = "select * from snippet";
    private final String SQL_QUERY_BY_ID = "select * from snippet where id=?";

    private final RowMapper<Snippet> rowMapper = (ResultSet rs, int row) -> {
        Snippet snippet = new Snippet();
        snippet.setId(rs.getString("id"));
        snippet.setTitle(rs.getString("title"));
        snippet.setCode(rs.getString("code"));
        snippet.setCreated(rs.getDate("created"));
        snippet.setModified(rs.getDate("modified"));
        return snippet;
    };

    @Autowired
    public SnippetRepository(JdbcTemplate template) {
        this.jdbcTemplate2 = template;
    }

    public Snippet save(Snippet snippet) {
        assert snippet.getTitle() != null;
        assert snippet.getCode() != null;

        this.jdbcTemplate2.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT);
            ps.setString(1, snippet.getId());
            ps.setString(2, snippet.getTitle());
            ps.setString(3, snippet.getCode());
            ps.setDate(4, new Date(snippet.getCreated().getTime()));
            ps.setDate(5, new Date(snippet.getModified().getTime()));
            return ps;
        });

        return snippet;
    }

    public List<Snippet> findAll() {
        return this.jdbcTemplate2.query(SQL_QUERY_ALL, rowMapper);
    }

    public Snippet findOne(String id) {
        return this.jdbcTemplate2.queryForObject(SQL_QUERY_BY_ID, new Object[]{id}, rowMapper);
    }

	
}
