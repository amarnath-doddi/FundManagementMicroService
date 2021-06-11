package com.example.fund.entity;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long id;
	@Column(name = "login_id")
	@NonNull
	@Size(min=5,max = 15)
	private String loginId;
	@Column(name = "password")
	@NonNull
	@Size(min=8,max = 30)
	private String password;
	@Column(name = "first_name")
	@NonNull
	@Size(min=2,max = 50)
	private String firstName;
	@Column(name = "last_name")
	@NonNull
	@Size(min=2,max = 50)
	private String lastName;
	@Column(name = "email")
	@NonNull
	@Email
	private String email;
	@Column(name = "last_updated")
	private LocalDate lastUpdated;
	@Column(name = "phone")
	@NonNull
	private String phone;
	
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

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id 
        		&& Objects.equals(lastName, user.lastName)
        		&& Objects.equals(firstName, user.firstName)
        		&& Objects.equals(loginId, user.loginId)
        		&& Objects.equals(password, user.password)
        		&& Objects.equals(email, user.email)
        		&& Objects.equals(lastUpdated, user.lastUpdated)
        		&& Objects.equals(phone, user.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, firstName, loginId, password, email, phone, lastUpdated);
    }
}
