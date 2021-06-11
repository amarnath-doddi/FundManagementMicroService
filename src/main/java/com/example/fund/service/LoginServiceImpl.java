package com.example.fund.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fund.entity.User;
import com.example.fund.repository.LoginRepository;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private LoginRepository loginRepository;
	
	@Override
	public boolean login(String loginId, String password) {
		return isLoginSuccessfull(loginRepository.findByLoginIdAndPassword(loginId,password));
	}

	public boolean isLoginSuccessfull(User user) {
		return user!=null;
	}

	@Override
	public boolean resetPassword(String userId, String password) {
		User user = loginRepository.findByLoginId(userId);
		user.setPassword(password);
		User updatedUser = loginRepository.saveAndFlush(user);
		return password.equals(updatedUser.getPassword());
	}

	@Override
	public boolean resetUserId(String userId, String newUserId) {
		User user = loginRepository.findByLoginId(userId);
		user.setLoginId(newUserId);
		loginRepository.saveAndFlush(user);
		return user.getLoginId().equals(newUserId);
	}

}
