package com.controller;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.core.controller.AbstractController;
import com.model.User;
import com.querydsl.core.types.Predicate;
import com.service.UserService;

@Controller
@RequestMapping("/api/user")
public class UserController extends AbstractController<User, Integer>{

	@Autowired
	private UserService service;
	
	@Autowired
	private HttpSession session;
	
	
	@Override
	public Object list(@QuerydslPredicate(root=User.class) Predicate predicate, Pageable pageable) throws Exception {
		
		return service.list(predicate,pageable);
	}
	
}
