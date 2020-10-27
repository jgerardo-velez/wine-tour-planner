package edu.cetys.springlabs.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import edu.cetys.springlabs.model.User;


@Repository
public class UserRepository {

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	/*
	 @Override
    public Optional<Book> findById(Long id) {
        return jdbcTemplate.queryForObject(
                "select * from books where id = ?",
                new Object[]{id},
                (rs, rowNum) ->
                        Optional.of(new Book(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getBigDecimal("price")
                        ))
        );
    }
	 */
	
	public Optional<User> findByEmail(String email) {
		String query = "SELECT * FROM users WHERE email = ?";
				
		return jdbcTemplate.queryForObject(query, new Object[]{email},
				(rs, rowNum) -> {
                	User user = new User();
                	user.setId(rs.getInt("id"));
					user.setEmail(rs.getString("email"));
					user.setName(rs.getString("name"));
					user.setPassword(rs.getString("password"));
					user.setRole(rs.getString("role"));
					user.setActive(rs.getBoolean("active"));
				
                	return Optional.of(user);
				}
        );
	}
	
	
	public List<User> findAll() {
		
		List<User> users = new ArrayList<User>();
						
		String query = "SELECT u.id, u.email, u.name, u.active, u.role, u.winery_id FROM users u";
		
		jdbcTemplate.query(query, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				while (rs.next()) {
					User user = new User();
					user.setId(rs.getInt("id"));
					user.setEmail(rs.getString("email"));
					user.setName(rs.getString("name"));
					user.setRole(rs.getString("role"));
					user.setActive(rs.getBoolean("active"));
					
	                users.add(user);
				}
			}
		});
		
		
		return users;
	}
	
	
    public int save(User user) {
    	
    	String query = "INSERT INTO users (email, name, role, active) VALUES (?,?,?,?)";
    	
        return jdbcTemplate.update(query, user.getEmail(), user.getName(), user.getRole(), user.isActive());
    }
    
}
