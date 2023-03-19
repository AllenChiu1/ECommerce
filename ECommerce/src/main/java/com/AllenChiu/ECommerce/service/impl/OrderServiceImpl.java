package com.AllenChiu.ECommerce.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.AllenChiu.ECommerce.dao.OrderDao;
import com.AllenChiu.ECommerce.dao.ProductDao;
import com.AllenChiu.ECommerce.dto.BuyItem;
import com.AllenChiu.ECommerce.dto.CreateOrderRequest;
import com.AllenChiu.ECommerce.model.OrderItem;
import com.AllenChiu.ECommerce.model.Product;
import com.AllenChiu.ECommerce.service.OrderService;

@Component
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private ProductDao productDao;

	//如果該方法有修改多張table的功能, 就必須要加上@Transactional註解, 萬一交易出現異常,
	//就可利用此註解rollback, 以確保這兩張table的交易一定是"同時發生或是同時不發生"
	@Transactional
	@Override
	public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
		
		int totalAmount = 0;
		List<OrderItem> orderItemList = new ArrayList<>();
		
		//迭代使用者購買的每個商品
		for(BuyItem buyItem : createOrderRequest.getBuyItemList()) {
			//根據前端傳過來的productId值,先去資料庫中查詢出這個product數據出來.
			Product product = productDao.getProductById(buyItem.getProductId());
			
			//計算總價錢
			int amount = buyItem.getQuantity() * product.getPrice();
			totalAmount = totalAmount + amount;
			
			//轉換BuyItem to OrderItem
			OrderItem orderItem = new OrderItem();
			orderItem.setProductId(buyItem.getProductId());
			orderItem.setQuantity(buyItem.getQuantity());
			orderItem.setAmount(amount);
			
			//即可將前端傳過來的buyItem資訊去轉換成是orderItem, 就可以將
			//orderItemList當成一個參數去傳到Dao層裡面, 讓其幫我們在資料庫中
			//去插入這些數據.
			orderItemList.add(orderItem);
		};
		
		//創建訂單
		//因為在資料庫中,訂單是由兩個table所去管理的, 所以需為下方兩個table的方法
		//各別使用orderDao新增一筆數據.
		Integer orderId = orderDao.createOrder(userId, totalAmount);
		
		//orderId參數表示這些orderItem是去對應到哪一張訂單上面
		//orderItemList參數可以讓Dao層幫我們在order_item這張tabel裡面
		//去插入這些數據進去了
		orderDao.createOrderItems(orderId, orderItemList);
		
		return orderId;
	}
	
	
}
