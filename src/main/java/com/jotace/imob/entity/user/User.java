package com.jotace.imob.entity.user;

import com.jotace.imob.dto.RegisterRequestDTO;
import com.jotace.imob.entity.post.Post;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity(name = "users")
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String phone;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @CreationTimestamp
    private LocalDateTime creationTIme;

    @UpdateTimestamp
    private LocalDateTime updateTime;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts;


    public User(RegisterRequestDTO registerRequestDTO, String encryptedPassword) {
        this.name = registerRequestDTO.name();
        this.email = registerRequestDTO.email();
        this.phone = registerRequestDTO.phone();
        this.password = encryptedPassword;
        this.userType = registerRequestDTO.userType();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.userType.equals(UserType.COMMON))
            return List.of(new SimpleGrantedAuthority("ROLE_COMMON"));

        else return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_COMMON"),
                    new SimpleGrantedAuthority("ROLE_MERCHANT"));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
