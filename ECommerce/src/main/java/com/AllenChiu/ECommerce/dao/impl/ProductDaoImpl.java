package com.AllenChiu.ECommerce.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.AllenChiu.ECommerce.dao.ProductDao;
import com.AllenChiu.ECommerce.dto.ProductQueryParameter;
import com.AllenChiu.ECommerce.dto.ProductRequest;
import com.AllenChiu.ECommerce.model.Product;
import com.AllenChiu.ECommerce.rowmapper.ProductRowMapper;

@Component
public class ProductDaoImpl implements ProductDao {

	// 注入NamedParameterJdbcTemplate這個bean進來
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public List<Product> getProducts(ProductQueryParameter productQueryParameter) {
		//在查詢語句加上where 1=1的原因,是為了要讓下面的查詢條件能夠可以自由地去
		//拼接在這個sql語法的後面
		String sql = "select * from product where 1=1";
		
		Map<String,Object> map = new HashMap<>();
		
		//查詢條件
		sql = addFilteringSql(sql, map, productQueryParameter);
		
		//在實作order by或是sort排序這種語法時,只能使用下面字串拼接的方式,而不能使用sql
		//變數去實作的,這是Spring JDBC Template的設計
		//此外,不須檢查此二是否為空值,因為已在controller設定defaultValue了
		//排序
		sql = sql + " ORDER BY " + productQueryParameter.getOrderBy() + " " + productQueryParameter.getSort();
		
		//分頁
		sql = sql + " LIMIT :limit OFFSET :offset";
		//下兩個參數沒有為其添加null判斷式,因為在controller層已設定預設值
		map.put("limit", productQueryParameter.getLimit());
		map.put("offset", productQueryParameter.getOffset());
		
		List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());
		return productList;
	}
	
	@Override
	public Product getProductById(Integer productId) {
		String sql = "select * from product where product_id = :productId";

		Map<String, Object> map = new HashMap<>();
		map.put("productId", productId);

		// 要新增一個變數來接住query方法的返回值
		List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

		if (productList.size() > 0) {
			return productList.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Integer createProduct(ProductRequest productRequest) {
		String sql = "Insert into product(product_name, category, image_url, price, stock,"
				+ "description, created_date, last_modified_date)"
				+ "values(:productName, :category, :imageUrl, :price, :stock, :description,"
				+ ":createdDate, :lastModifiedDate)";

		Map<String, Object> map = new HashMap<>();
		map.put("productName", productRequest.getProductName());
		map.put("category", productRequest.getCategory().toString());
		map.put("imageUrl", productRequest.getImageUrl());
		map.put("price", productRequest.getPrice());
		map.put("stock", productRequest.getStock());
		map.put("description", productRequest.getDescription());

		// 在此new了一個當下的時間, 代表該商品被創建時的時間
		Date now = new Date();
		map.put("createdDate", now);
		map.put("lastModifiedDate", now);

		// 使用keyHolder去自動儲存資料庫自動生成的productId
		KeyHolder keyHolder = new GeneratedKeyHolder();

		namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

		int productId = keyHolder.getKey().intValue();
		// 最後再將productId回傳出去
		return productId;
	}

	@Override
	public void updateProduct(Integer productId, ProductRequest productRequest) {
		String sql = "Update product set product_name = :productName, category = :category,image_url = :imageUrl,"
				+ "price = :price, stock = :stock, description = :description, last_modified_date = :lastModifiedDate\r\n"
				+ "where product_id = :productId";

		Map<String, Object> map = new HashMap<>();
		map.put("productId", productId);

		map.put("productName", productRequest.getProductName());
		map.put("category", productRequest.getCategory().toString());
		map.put("imageUrl", productRequest.getImageUrl());
		map.put("price", productRequest.getPrice());
		map.put("stock", productRequest.getStock());
		map.put("description", productRequest.getDescription());
		// 更新最後修改時間的值
		map.put("lastModifiedDate", new Date());

		namedParameterJdbcTemplate.update(sql, map);
	}

	@Override
	public void deleteProductById(Integer productId) {
		String sql = "delete from product \r\n" + "where product_id = :productId";
		Map<String, Object> map = new HashMap<>();
		map.put("productId", productId);

		namedParameterJdbcTemplate.update(sql, map);
	}

	@Override
	public Integer countProduct(ProductQueryParameter productQueryParameter) {
		String sql = "Select count(*) from product where 1=1 ";
		Map<String, Object> map = new HashMap<>();
		
		//查詢條件
		sql = addFilteringSql(sql, map, productQueryParameter);
		
		//下列方法可以取得我們所查詢出來的count值,這方法通常用在取count值的時候
		//意思就是說我們要將count的值轉換成是一個Integer類型的返回值
		Integer total = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);
		
		return total;
	}

	
	private String addFilteringSql(String sql,Map<String,Object> map,ProductQueryParameter productQueryParameter) {
		//查詢條件
				if(productQueryParameter.getCategory() != null) {
					//假設該category不是空的,就可以將下面這個sql語句再做拼接
					//又或者假設category為空, 還是可以查詢到上面的sql的內容
					//此外,拼接的內容還必須預留一個空白,以防和上述的sql連在一起
					sql = sql + " AND category = :category ";
					//因為category的參數是Enum類型,所以要使用他的name方法將Enum類型轉成字串
					//然後再把字串的值加到map裡面
					map.put("category", productQueryParameter.getCategory().name());
				};
				
				if(productQueryParameter.getSearch() != null) {
					sql = sql + " AND product_name LIKE :search ";
					//%符號為sql語句中like的模糊查詢特性,一定要寫在Map裡面,不能寫在SQL語句
					//此為Spring JDBC Template中的限制
					map.put("search", "%" + productQueryParameter + "%");
				};
				return sql;
	};
}
