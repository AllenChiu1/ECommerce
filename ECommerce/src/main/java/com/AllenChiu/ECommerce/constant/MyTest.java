package com.AllenChiu.ECommerce.constant;

//此為測試ENUM類別而建
public class MyTest {

	public static void main(String[] args) {
		// 此變數存放的就是food的值
		ProductCategory productCategory = ProductCategory.FOOD;
		// name方法可將enum的值轉換成String類型的字串
		String s = productCategory.name();
		System.out.println(s); // FOOD

		// 此enum類型會去查詢在他儲存的一些固定值裡面, 有沒有一個是符合該字串"CAR的
		// 如果有, 就會將該值儲存在productCategory2變數裡面.
		String s2 = "CAR";
		ProductCategory productCategory2 = ProductCategory.valueOf(s2);
		System.out.println(productCategory2);
	}
}
