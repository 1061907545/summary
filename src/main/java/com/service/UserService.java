package com.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.domain.Pageable;
import com.core.service.AbstractService;
import com.model.Role;
import com.model.User;
import com.model.QUser;
import com.repository.RoleRepository;
import com.repository.UserRepository;
import com.querydsl.core.types.Predicate;
import com.utils.MD5Utile;
import com.utils.ValidateUtil;

@Service
public class UserService extends AbstractService<User,Integer>{

	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	@Override
	public Object list(Predicate predicate, Pageable pageable) throws Exception {
		//predicate = QUser.user.name.eq("").and(predicate);
		
		return super.list(predicate, pageable);
	}
	
	
	
	@Override
	public void create(User user) throws Exception {
		if(ValidateUtil.identityValidate(user.getCertificate())) {
			if(ValidateUtil.emailValidate(user.getEmail())) {
				if(ValidateUtil.phoneValidate(user.getPhone())) {
					if(ValidateUtil.passwordValidate(user.getPassword())) {
						if(repository.findByCertificate(user.getCertificate())==null) {
							if(repository.findByEmail(user.getEmail())==null) {
								if(repository.findByPhone(user.getPhone())==null) {
									user.setSalt(getUserSolt());
									user.setPassword(MD5Utile.GetMD5Code(user.getPassword()+user.getSalt()));
									List<Role> roleList = new ArrayList<>();
									if(user.getUserType().equals("admin")) {
			                             Role role = roleRepository.findOne(1);	
			                             roleList.add(role);
									}else {
										Role role = roleRepository.findOne(1);	
			                            roleList.add(role);
									}
									user.setRole(roleList);
									repository.save(user);
								}else {
									throw new RuntimeException("电话号码已使用");
								}
							}else {
								throw new RuntimeException("邮箱已使用");
							}
						}else {
							throw new RuntimeException("身份证已使用");
						}
					}else {
                        throw new RuntimeException("密码6-16位");						
					}
				}else {
					throw new RuntimeException("手机号有误");
				}
			}else {
				throw new RuntimeException("邮箱有误");
			}
		}else {
			throw new RuntimeException("身份证有误");
		}
		repository.save(user);
	}
	public User findByUserName(String userName) {
		
		return repository.findByUserName(userName);
	}
	
	
	

	
	@Override
	public void update(User e) throws Exception {
		// TODO Auto-generated method stub
		super.update(e);
	}
	
	
	
	/**
     * 获取加密的附加值
     * @return
     */
    public static String getUserSolt() {
        Random random = new Random();
        //附加值长度为6~16
        int length=16-random.nextInt(10);
        //通过Apache commons的随机函数来获取随机值
        return	RandomStringUtils.randomAlphanumeric(length);
    }
	
	@Override
	public UserRepository getRepository() {
		
		return repository;
	}
}
