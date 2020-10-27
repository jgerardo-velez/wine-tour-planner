package edu.cetys.springlabs.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import edu.cetys.springlabs.model.Region;
import edu.cetys.springlabs.model.Winery;


@Repository
public class WineryRepository {

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	
	public List<Winery> findAll() {
		
		List<Winery> wineries = new ArrayList<Winery>();
						
		String query = "SELECT w.id, w.name, w.address, w.phone, w.website, r.id as region_id, " +
					   "r.name as region_name, r.code as region_code, r.country as region_country " + 
					   "FROM wineries w " + 
				       "INNER JOIN regions r ON w.region_id = r.id";
		
		jdbcTemplate.query(query, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				while (rs.next()) {
					Region region = new Region();
					region.setId(rs.getInt("region_id"));
					region.setName(rs.getString("region_name"));
					region.setCode(rs.getString("region_code"));
					region.setCountry(rs.getString("region_country"));
					
					Winery winery = new Winery();
					winery.setId(rs.getInt("id"));
					winery.setName(rs.getString("name"));
					winery.setAddress(rs.getString("address"));
					winery.setPhone(rs.getString("phone"));
					winery.setWebsite(rs.getString("website"));
					winery.setRegion(region);
					
					wineries.add(winery);
				}
			}
		});
		
		
		return wineries;
	}
	
}
