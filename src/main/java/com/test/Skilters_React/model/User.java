package com.test.Skilters_React.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User implements UserDetails {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	private String prenom;
    private String nom;
    @Email(message = "Vous devez inserez un email")
    @NotBlank(message = "Vous devez inserez un email")
    @Column(unique = true)
    private String email;
    private String telephone;
    private String secteur_activite;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date disponibilite;
    @NotBlank(message = "Vous devez inserez un mot de passe")
    private String password;
    public enum t_status {
    		valide,non_valide,actif;
    }
    private t_status status;
    
    public User() {}
    public User(String nom) {
    		this.prenom = nom;
    }
    public User(String prenom, String nom, String email, String telephone, String secteur_activite, Date disponibilite, String password, t_status status) {
    		this.prenom = prenom;
    		this.nom = nom;
    		this.email = email;
    		this.telephone = telephone;
    		this.secteur_activite = secteur_activite;
    		this.disponibilite = disponibilite;
    		this.password = password;
    		this.status = status;
    }
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getSecteur_activite() {
		return secteur_activite;
	}
	public void setSecteur_activite(String secteur_activite) {
		this.secteur_activite = secteur_activite;
	}
	public Date getDisponibilite() {
		return disponibilite;
	}
	public void setDisponibilite(Date disponibilite) {
		this.disponibilite = disponibilite;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public t_status getStatus() {
		return status;
	}
	public void setStatus(t_status status) {
		this.status = status;
	}
	
	/*
	 * UserDetails interface methods
	 */
	
	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}
	
	@Override
	@JsonIgnore
	public String getUsername() {
		return this.email;
	}
	
	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	@JsonIgnore
	public boolean isEnabled() {
		return true;
	}
}

