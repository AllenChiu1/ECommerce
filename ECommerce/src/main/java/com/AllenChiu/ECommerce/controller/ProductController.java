package com.AllenChiu.ECommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.AllenChiu.ECommerce.constant.ProductCategory;
import com.AllenChiu.ECommerce.dto.ProductQueryParameter;
import com.AllenChiu.ECommerce.dto.ProductRequest;
import com.AllenChiu.ECommerce.model.Product;
import com.AllenChiu.ECommerce.service.ProductService;

import jakarta.validation.Valid;

//加此註解表示此為Controller層的bean
@RestController
public class ProductController {

	// 為了要取得productService裡面的方法, 故需將其注入.
	@Autowired
	private ProductService productService;

	// 查詢所有商品列表數據
	// getMapping的product一定要為複數,因為是多個產品
	@GetMapping("/products")

	//無論有無查到數據, 都需回200給前端, 因RestFul API設計上的理念, 每一個URL都是一個資源,
	//當前端來請求此資源時, 即使商品數據不存在, 但是get/products這個資源是存在的,所以就要回
	//200給前端.
	public ResponseEntity<List<Product>> getProducts(
	//查詢條件filtering
			//前端可以透過傳進來的category的值,去指定他想要查看的是哪個分類的商品
			//Spring boot會自動將前端傳過來的字串轉換成productCategory的Enum
			//應將其設計為可選而不是必選的參數,所以須加上(required = false)
			@RequestParam (required = false)ProductCategory category,
			//表示使用者查詢的關鍵字
			@RequestParam (required = false)String search,
			
	//排序sorting
			//orderBy表示要根據甚麼欄位來進行排序,使用required = false也是可行的
			//但是實際上在業界通常會希望根據某東西來進行排序,所以可以加上defaultValue
			//假設前端沒有傳送orderBy的參數,此orderBy的值就會是預設的值,也就是根據created_date
			//進行排序
			@RequestParam (defaultValue = "created_date")String orderBy,
			//要使用升冪還是降冪
			@RequestParam (defaultValue = "desc")String sort
	) {
		ProductQueryParameter productQueryParameter = new ProductQueryParameter();
		//將前端傳進來的值set進productQueryParameter裡面
		productQueryParameter.setCategory(category);
		productQueryParameter.setSearch(search);
		productQueryParameter.setOrderBy(orderBy);
		productQueryParameter.setSort(sort);
		
		//再來將productQueryParameter填寫到getProduct裡面的參數,這樣一來之後
		//就不會因為要新增查詢條件而一直更動DAO層
		List<Product> productList = productService.getProducts(productQueryParameter);

		return ResponseEntity.status(HttpStatus.OK).body(productList);
	};

	// 根據RestFul API的設計原則, 如果想取得某一筆商品的數據, 會是使用Get方法來請求.
	// 此註解表示要去取得某一商品的數據.
	@GetMapping("/products/{productId}")

	// @pathVariable註解表示這個productId的值是從URL路徑裡面給傳進來的.
	public ResponseEntity<Product> getProduct(@PathVariable Integer productId) {

		// 當前端來請求此URL路徑, 就會透過productService的getProductById方法去資料庫查詢
		// 該筆資料出來.
		Product product = productService.getProductById(productId);

		// 將資料庫查詢出來的數據填入body方法裡面並傳回給前端.
		if (product != null) {
			return ResponseEntity.status(HttpStatus.OK).body(product);
		} else {
			// 如資料庫找不到該筆資料的話, 回傳這個ResponseEntity給前端.
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	};

	// RequestBody註解表示要接住前端所傳過來的參數
	// 如有在ProductRequest裡面加上NotNull的註解, 就必須再加上Valid註解
	@PostMapping("/products")
	public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest) {
		// 此方法要返回資料庫所生成的productId回來
		Integer productId = productService.createProduct(productRequest);
		// 從資料庫取得此商品的數據出來
		Product product = productService.getProductById(productId);
		// 回傳狀態碼給前端, 並且將從資料庫查詢出來的資料放到body方法裡面傳回給前端
		return ResponseEntity.status(HttpStatus.CREATED).body(product);
	}

	// RequestBody註解表示要去接住前端傳來的JSON參數
	@PutMapping("/products/{productId}")
	public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,
			@RequestBody @Valid ProductRequest productRequest) {
		// 可先加入這端if判斷式先判斷此商品是否存在
		Product product = productService.getProductById(productId);
		if (product == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		// 檢查過有此商品後再來進行修改
		// update方法並不會返回值
		productService.updateProduct(productId, productRequest);
		// 從資料庫取得此商品的數據出來
		Product updatedProduct = productService.getProductById(productId);
		// 回傳狀態碼給前端, 並且將從資料庫查詢出來的資料放到body方法裡面傳回給前端
		return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
	}

	@DeleteMapping("/products/{productId}")
	public ResponseEntity<?> deleteProductById(@PathVariable Integer productId) {

		// 檢查過有此商品後再來進行刪除
		// delete方法並不會返回值
		productService.deleteProductById(productId);

		// 回傳狀態碼給前端, 表示該數據已被刪除
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	};

}
