package com.bibliobarn.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/productCatalogService")
public class ProductCatalogService {

	// Gets the list of product categories for the store //
	@Path("/getCategoryList")
	@GET
	public Response getCategoryList() {
		System.out.println("Start ProductCatalogService.getCategoryList");
		List<String> categoryList = Arrays.asList("A", "B", "C", "D", "B", "C");
		return Response.status(200).entity(categoryList).build();

	}

	// Gets the list of products for a category, or all products if no category is
	// specified //
	@GET
	@Path("/getProductList")
	public Response getProductList(@QueryParam("categoryId") String categoryId) {
		System.out.println("Start ProductCatalogService.getProductList");
		System.out.println("Getting product list for category id - " + categoryId);
		List<String> productList = new ArrayList<>();
		if (categoryId != null && categoryId.equalsIgnoreCase("A")) {
			productList = Arrays.asList("1", "2", "3", "4");

		} else {
			productList = Arrays.asList("A", "B", "C", "Z");
		}

		return Response.status(200).entity(productList).build();

	}

	// Gets the detailed product information for a product //
	@GET
	@Path("/getProductInfo")
	public Response getProductInfo(@QueryParam("productid") String productid) {
		System.out.println("Start ProductCatalogService.getProductInfo");
		System.out.println("Getting product list for product id - " + productid);
		String productInfo = "This is product information";
		return Response.status(200).entity(productInfo).build();
	}

}