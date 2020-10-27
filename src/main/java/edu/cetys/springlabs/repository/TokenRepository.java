package edu.cetys.springlabs.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.cetys.springlabs.model.Token;


@Repository
public class TokenRepository {

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	
	public int count() {
        return jdbcTemplate
                .queryForObject("SELECT count(*) FROM tokens", Integer.class);
    }

    public int save(Token token) {
        return jdbcTemplate.update("INSERT INTO tokens (token, user_id, expiration_time, active) values(uuid(),?,?,?)",
                token.getUserId(), token.getExpirationTime(), token.isActive());
    }

}
