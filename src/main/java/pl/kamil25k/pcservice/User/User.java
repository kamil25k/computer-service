package pl.kamil25k.pcservice.User;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.kamil25k.pcservice.Device.Device;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @NotEmpty
    @Column(name = "NAME")
    private String name;

    @NotNull
    @NotEmpty
    @Column(name = "LAST_NAME")
    private String lastName;

    @NotNull
    @NotEmpty
    @Column(name = "USERNAME")
    private String username;

    @NotNull
    @NotEmpty
    @Column(name = "PASSWORD")
    private String password;

    @NotNull
    @Column(name = "PHONE_NUMBER")
    private int phoneNumber;

    @Column(name = "EMAIL")
    private String email;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
//    @Column(name = "Device")
//    @JoinColumn(name = "device", referencedColumnName = "id")
    private List<Device> device;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE")
    private Role role;

    public User() {
    }

    public User(Long id, String name, String lastName, String username, String password, int phoneNumber, String email, List<Device> device, Role role) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.device = device;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role.name()));
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
