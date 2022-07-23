package com.lesson.shoplist.controller;

import com.lesson.shoplist.persist.TypesOfAnimals;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class AnimalsController {
	@RequestMapping(value="/animal-page")
	private ModelAndView selectTag() {
		ModelAndView mav = new ModelAndView("animal-form");

		Map< String, String > animals = new HashMap();
		animals.put("cat", "CAT");
		animals.put("dog", "DOG");
		animals.put("pig", "PIG");
		animals.put("mouse", "MOUSE");
		animals.put("fish", "FISH");

		mav.addObject("animalsMap", animals);
		mav.addObject("animal", new TypesOfAnimals());

		return mav;
	}

	@RequestMapping(value="/animal-result")
	private ModelAndView processPhone(@ModelAttribute TypesOfAnimals animal) {
		ModelAndView mav = new ModelAndView("animal-result");
		mav.addObject("animal", animal);
		return mav;
	}
}
