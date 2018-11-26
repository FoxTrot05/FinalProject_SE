package ca.sheridancollege.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Getter
@Setter

public class Account implements Serializable{
	@Id
	@GeneratedValue
	int id;
	String username;
	String password;	

	public Account(String name, String pass) {
		this.username = name;
		this.password = pass;
	}
}
