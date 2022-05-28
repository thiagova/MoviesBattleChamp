package br.pro.aguiar.moviesbattlechamp.models;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity(name = "users")
public class User implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private long id;

    private String name;
    private String email;
    private String password;
    private String username;
    private String authorities = "USER";
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    public User(){}
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    
    public User(String name, String email, String password, String username) {
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
        this.setUsername(username);
    }
    public User(String name, String email, String password, String username, String authorities) {
        this(name, email, password, username);
        this.authorities = authorities;
    }
    public User(long id, String name, String email, String password, String username) {
        this(name, email, password, username);
        this.setId(id);
    }
    
    @Override public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(authorities.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
    @Override public String getUsername() {
        return this.username;
    }
    @Override public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }
    @Override public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }
    @Override public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }
    @Override  public boolean isEnabled() {
        return this.enabled;
    }
}
