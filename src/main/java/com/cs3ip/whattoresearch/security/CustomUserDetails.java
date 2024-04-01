package com.cs3ip.whattoresearch.security;

import com.cs3ip.whattoresearch.model.Role;
import com.cs3ip.whattoresearch.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Custom UserDetails class for providing user details during authentication.
 */
public class CustomUserDetails implements UserDetails {

    private User user;

    /**
     * Constructs a new CustomUserDetails instance.
     *
     * @param user The user for which UserDetails are provided.
     */
    public CustomUserDetails(User user) {
        this.user = user;
    }

    /**
     * Retrieves the authorities granted to the user.
     *
     * @return A collection of granted authorities.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorities;
    }

    /**
     * Retrieves the password used to authenticate the user.
     *
     * @return The password.
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Retrieves the username used to authenticate the user.
     *
     * @return The username.
     */
    @Override
    public String getUsername() {
        return user.getEmail();
    }

    /**
     * Shows whether the user's account has expired.
     *
     * @return true if the user's account is not expired.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Shows whether the user is locked or unlocked.
     *
     * @return true if the user is not locked, false otherwise.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    /**
     * Shows whether the user's credentials has expired.
     *
     * @return true if the user's credentials are not expired.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Shows whether the user is enabled.
     *
     * @return true if the user is enabled.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Retrieves the user associated with this UserDetails.
     *
     * @return The associated User object.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user associated with this UserDetails.
     *
     * @param user The User object to set.
     */
    public void setUser(User user) {
        this.user = user;
    }
}