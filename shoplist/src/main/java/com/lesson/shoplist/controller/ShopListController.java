package com.lesson.shoplist.controller;

import com.lesson.shoplist.persist.Animal;
import com.lesson.shoplist.persist.AnimalRepo;
import com.lesson.shoplist.persist.User;
import com.lesson.shoplist.persist.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class ShopListController {

	private static final Logger logger = LoggerFactory.getLogger(ShopListController.class);
	private final AnimalRepo repo;

	private final UserRepo userRepo;

	@Autowired
	public ShopListController(AnimalRepo repo, UserRepo userRepo) {
		this.repo = repo;
		this.userRepo = userRepo;
	}

	@GetMapping
	public String indexPage(Model model, Principal principal) {
		logger.info("User name: {}",  principal.getName());
		model.addAttribute("items", repo.findByUserUsername(principal.getName()));
		model.addAttribute("item", new Animal());
		return "index";
	}

	@PostMapping
	public String newShopItem(Animal item, Principal principal) {
		logger.info("User name: {}",  principal.getName());
		User user = userRepo.findByUsername(principal.getName()).get();
		item.setUser(user);
		repo.save(item);
		return "redirect:/";
	}

	@DeleteMapping("/{id}")
	public String deleteShopItem(@PathVariable("id") Long id) {
		repo.deleteById(id);
		return "redirect:/";
	}
}
