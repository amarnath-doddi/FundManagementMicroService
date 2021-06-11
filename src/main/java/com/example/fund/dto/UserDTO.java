package com.example.fund.dto;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

import com.example.fund.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDTO {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private LocalDate lastUpdated;
	private String phone;
	private Login login;
	
	public UserDTO() {
	}
	public UserDTO(Optional<User> userOptional) {
		if(userOptional.isPresent()) {
			User user = userOptional.get();
			this.id = user.getId();
			this.email = user.getEmail();
			this.firstName = user.getFirstName();
			this.lastName = user.getLastName();
			this.lastUpdated = user.getLastUpdated();
			this.login = new Login(user.getLoginId(),user.getPassword());
			this.phone = user.getPhone();
		}
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public LocalDate getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(LocalDate lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Login getLogin() {
		if(login==null) {
			login = new Login();
		}
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}

	public UserDTO getUserDTO(User user) {
		this.id = user.getId();
		this.email = user.getEmail();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.lastUpdated = user.getLastUpdated();
		this.phone = user.getPhone();
		this.login = new Login(user.getLoginId(),user.getPassword());
		return this;
	}
	
	@JsonIgnore
	public User getUser() {
		User user = new User();
		user.setId(id);
		user.setEmail(email);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setLastUpdated(lastUpdated);
		user.setPhone(phone);
		user.setLoginId(login.getLoginId());
		user.setPassword(login.getPassword());
		return user;
	}
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO user = (UserDTO) o;
        return id == user.id 
        		&& Objects.equals(lastName, user.lastName)
        		&& Objects.equals(firstName, user.firstName)
        		&& Objects.equals(email, user.email)
        		&& Objects.equals(lastUpdated, user.lastUpdated)
        		&& Objects.equals(phone, user.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, firstName, email, phone, lastUpdated);
    }
}
