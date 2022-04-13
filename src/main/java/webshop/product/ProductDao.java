package webshop.product;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class ProductDao {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemp;

    public ProductDao(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemp = new JdbcTemplate(dataSource);
    }

    public long insertProduct(String productName, int price, int stock) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemp.update(con -> {
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO products(name,price,stock) VALUES(?,?,?);",
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, productName);
            stmt.setInt(2, price);
            stmt.setInt(3, stock);

            return stmt;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }
    public void insertProduct(Product product) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemp.update(con -> {
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO products(name,price,stock) VALUES(?,?,?);",
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, product.getName());
            stmt.setInt(2, product.getPrice());
            stmt.setInt(3, product.getStock());

            return stmt;
        }, keyHolder);
    }

// ELEGENDŐ a terméknevek listájával visszatérés, vagy termékek listájával? (pl.: vásárlásnál mindent lát, ha lekéri)
    public List<Product> listProducts() {
        return jdbcTemp.query(
                        "SELECT name FROM products",
                        this::mapRow).stream().toList());
    }

    public Product findProductById(long id) {
        return jdbcTemp.queryForObject(
                "SELECT * FROM products" +
                        " WHERE id = ?;",
                (rs, rowNum) -> new Product(rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getInt("stock")),
                id);
    }

    public void increasedProductStock(long id, int amount) {
        jdbcTemp.update(
                "UPDATE products" +
                        " SET stock = stock + ?" +
                        " WHERE id = ?;",
                amount, id);
    }

    private String mapRow(ResultSet rs, int rowNum) throws SQLException {
        return rs.getString("name");
    }

}
