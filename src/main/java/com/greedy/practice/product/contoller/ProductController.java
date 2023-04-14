package com.greedy.practice.product.contoller;

import java.sql.Date;
import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greedy.practice.common.Pagenation;
import com.greedy.practice.common.PagingButtonInfo;
import com.greedy.practice.dto.ProductDTO;
import com.greedy.practice.product.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/product")
public class ProductController {

	private final ProductService productService;
	
	public ProductController (ProductService productService) {
		this.productService = productService;
	}
	
	/* 오늘의 상품 */
	@GetMapping("/todays")
	public String todaysPage(Model model) {
		
		List<ProductDTO> productList = productService.findProductList();
		
		Collections.shuffle(productList);
		List<ProductDTO> todaysProductlist = productList.subList(0, 3);
		log.info("todaysProductlist : {}", todaysProductlist);
		
		model.addAttribute("productList", todaysProductlist);
		
		return null;
	}
	
	/* 전체 상품 */
	@GetMapping("/list")
	public String listPage(@PageableDefault Pageable pageable, Model model) {
		
		/* 페이징 처리 */
		Page<ProductDTO> productList = productService.findProductList(pageable);
		PagingButtonInfo paging = Pagenation.getPagingButtonInfo(productList);
		
		model.addAttribute("paging", paging);
		model.addAttribute("productList", productList);
		
		return "product/list";
	}
	
	/* 상품 검색 */
	@GetMapping("/search")
	public String searchProduct(@PageableDefault Pageable pageable, @RequestParam(name="keyword") String keyword, Model model) {
		
		log.info(keyword);
		
		Page<ProductDTO> productList = productService.searchProductList(pageable, keyword);
		PagingButtonInfo paging = Pagenation.getPagingButtonInfo(productList);
		
		model.addAttribute("keyword", keyword);
		model.addAttribute("paging", paging);
		model.addAttribute("productList", productList);
		
		return "product/search";
	}
	
	/* 등록일이 입력된 날짜 이후인 상품 조회 */
	@GetMapping("/after")
	public String searchDateAfterProduct(@PageableDefault Pageable pageable, @RequestParam(name="dateAfter") Date dateAfter, Model model) {
		
		Page<ProductDTO> productList = productService.searchDateAfterProductList(pageable, dateAfter);
		PagingButtonInfo paging = Pagenation.getPagingButtonInfo(productList);

		model.addAttribute("dateAfter", dateAfter);
		model.addAttribute("paging", paging);
		model.addAttribute("productList", productList);
		
		return "product/after";
	}
	
	/* 상품 등록 */
	@GetMapping("/regist")
	public void registPage() {}
	
	@PostMapping("/regist")
	public String registNewProduct(ProductDTO newProduct, RedirectAttributes rttr) {
		
		productService.saveNewProduct(newProduct);
		
		rttr.addFlashAttribute("message", "상품 등록 성공! 등록된 상품을 확인해보세요 🤩");
		
		return "redirect:/product/list#success-regist";
	}
	
	/* 상품 수정 */
	@GetMapping("/modify")
	public void modifyPage() {}
	
	/* 현재 상품 리스트 조회(ajax) */
	@GetMapping(value="product", produces="application/json; charset=UTF-8")
	@ResponseBody
	public List<ProductDTO> findProductList() {
		
		return productService.findProductList();
	}
	
	@PostMapping("/modify")
	public String modifyProduct(ProductDTO product, RedirectAttributes rttr) {
		
		productService.modifyProduct(product);
		
		rttr.addFlashAttribute("message", "상품 수정 성공! 수정된 상품을 확인하세요 😍");
		
		/* 수정된 하나의 상품 페이지로 이동 */
		return "redirect:/product/" + product.getProductNo() + "#success-modify";
		
	}
	
	/* 수정된 하나의 상품을 조회 */
	@GetMapping("/{productNo}")
	public String modifiedPage(@PathVariable int productNo, Model model) {
		
		ProductDTO product = productService.findProductByCode(productNo);
		
		model.addAttribute("product", product);
		
		return "/product/modified";
	}
	
	/* 상품 삭제 */
	@GetMapping("/remove")
	public void removePage() {}
	
	@PostMapping("/remove")
	public String removeProduct(ProductDTO product, RedirectAttributes rttr) {
		
		productService.deleteProduct(product);
		
		rttr.addFlashAttribute("message", "상품 삭제 성공! 👀");
		
		return "redirect:/product/list#success-remove";
	}
	
}
