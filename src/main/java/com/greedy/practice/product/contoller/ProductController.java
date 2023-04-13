package com.greedy.practice.product.contoller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greedy.practice.common.Pagenation;
import com.greedy.practice.common.PagingButtonInfo;
import com.greedy.practice.dto.ProductDTO;
import com.greedy.practice.product.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

	private final ProductService productService;
	
	public ProductController (ProductService productService) {
		this.productService = productService;
	}
	
	/* 오늘의 상품 */
	@GetMapping("/todays")
	public String todaysPage() {
		
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
	public String searchProduct() {
		
		return "product/search";
	}
	
	/* 상품 등록 */
	@GetMapping("/regist")
	public void registPage() {}
	
	@PostMapping("/regist")
	public String registNewProduct(ProductDTO newProduct, RedirectAttributes rttr) {
		
		productService.saveNewProduct(newProduct);
		
		rttr.addFlashAttribute("messeage", "상품 등록 성공! 등록된 상품을 확인해보세요 🤩");
		
		return "redirect:/product/list#success-regist";
	}
	
	
	/* 상품 수정 */
	@GetMapping("/modify")
	public void modifyPage() {}
	
	/* 현재 상품 리스트 조회(ajax) */
	@GetMapping(value="product", produces="application/json; charset=UTF-8")
	@ResponseBody
	public List<ProductDTO> findMenuList() {
		
		return productService.findProductList();
	}
	
	@PostMapping("/modify")
	public String menuModify(ProductDTO product, RedirectAttributes rttr) {
		
		productService.modifyProduct(product);
		
		rttr.addFlashAttribute("message", "메뉴 수정 성공! 수정된 메뉴를 확인하세요 😍");
		
		/* 수정된 하나의 상품 페이지로 이동 */
		return "redirect:/product/" + product.getProductNo() + "#success-modify";
		
	}
	
	/* 수정된 하나의 상품을 조회 */
	@GetMapping("/{productNo}")
	public String modifiedPage(@PathVariable int productNo, Model model) {
		
		ProductDTO product = productService.findProductByCode(productNo);
		
		model.addAttribute("product", product);
		
		return "product/modified";
	}
	
	/* 상품 삭제 */
	@GetMapping("/remove")
	public void removePage() {}
	
}
