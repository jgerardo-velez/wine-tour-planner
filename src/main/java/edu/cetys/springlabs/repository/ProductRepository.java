package edu.cetys.springlabs.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import edu.cetys.springlabs.model.Product;
import edu.cetys.springlabs.model.Region;
import edu.cetys.springlabs.model.Winery;

@Repository
public class ProductRepository {

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	
	public List<Product> findAllByWinery(int wineryId) {
		
		List<Product> products = new ArrayList<Product>();
						
		String query = "SELECT p.sku, p.name, p.price, p.currency, p.image " + 
					   "FROM products p WHERE p.winery_id = ?";
		
		jdbcTemplate.query(query, new Object[]{wineryId}, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				while (rs.next()) {
					Product product = new Product();
					product.setSku(rs.getInt("sku"));
					product.setName(rs.getString("name"));
					product.setPrice(rs.getDouble("price"));
					product.setCurrency(rs.getString("currency"));
					product.setImage(rs.getString("image"));
					
					products.add(product);
				}
			}
		});
		
		return products;
	}
}
