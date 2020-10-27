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

import edu.cetys.springlabs.model.Region;
import edu.cetys.springlabs.model.User;
import edu.cetys.springlabs.model.Winery;


@Repository
public class UserRepository {

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	
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
				if (rs != null) {
					while (rs.next()) {
						System.out.println("EMAIL: " + rs.getString("email"));
						User user = new User();
						user.setId(rs.getInt("id"));
						user.setEmail(rs.getString("email"));
						user.setName(rs.getString("name"));
						user.setRole(rs.getString("role"));
						user.setActive(rs.getBoolean("active"));
						
		                users.add(user);
					}
				}
			}
		});
		
		
		return users;
	}
	
	
	public Optional<Winery> findWineryByEmail(String email) {
		String query = "SELECT u.email, w.id as winery_id, w.name as winery_name, w.address as winery_address, " +
						"w.phone as winery_phone, w.website as winery_website, r.id as region_id, " +
						"r.name as region_name, r.code as region_code, r.country as region_country " +
						"FROM users u " +
						"INNER JOIN wineries w ON u.winery_id = w.id " +
						"INNER JOIN regions r ON w.region_id = r.id " +
						"WHERE u.email = ?";
				
		return jdbcTemplate.queryForObject(query, new Object[]{email},
				(rs, rowNum) -> {
                	Region region = new Region();
					region.setId(rs.getInt("region_id"));
					region.setName(rs.getString("region_name"));
					region.setCode(rs.getString("region_code"));
					region.setCountry(rs.getString("region_country"));
					
					Winery winery = new Winery();
					winery.setId(rs.getInt("winery_id"));
					winery.setName(rs.getString("winery_name"));
					winery.setAddress(rs.getString("winery_address"));
					winery.setPhone(rs.getString("winery_phone"));
					winery.setWebsite(rs.getString("winery_website"));
					winery.setRegion(region);
				
                	return Optional.of(winery);
				}
        );
	}
	
	
    public int save(User user) {
    	
    	String query = "INSERT INTO users (email, name, role, active) VALUES (?,?,?,?)";
    	
        return jdbcTemplate.update(query, user.getEmail(), user.getName(), user.getRole(), user.isActive());
    }
    
}
