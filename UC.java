package com.example.demo.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entities.User;
import com.example.demo.services.UserService;
@Controller
public class UC {

	@Autowired
	private UserService us; 
	
	@GetMapping("")
	public String start(Model m){
		m.addAttribute("listUser",us.getAUser());
		return "index.html";
	}
	@GetMapping("/test")
	@ResponseBody
	public String listUsers() {
		return us.getAUser().toString();
	}
	@GetMapping("/test1")
	public String test() {
		return "test.html";
	}
	
	@GetMapping("/updOte")
	public String updOte(Model m) {
		User u = new User();
		m.addAttribute("user",u);
		return "newUser.html";
	}
	
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute("user") User user){
		us.saveUser(user);
		return "redirect:/";
	}
	
	@PostMapping("/delUser")
	public String delUser(@ModelAttribute("id") long id) {
		us.delUser(id);
		return "redirect:/";
	}
	@PostMapping("/searchUser")
	public ModelAndView searchUser(@ModelAttribute("id") long id) {
		ModelAndView m= new ModelAndView();
		User a=us.getUserById(id);
		List<User> x=new ArrayList<>();
		x.add(a);
		m.addObject("listUser",x);
		m.setViewName("index.html");
		return m;
	}
}