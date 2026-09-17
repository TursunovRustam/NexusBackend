package com.example.nexusbackend.Entity;

import com.example.nexusbackend.Enum.UserRoles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Role implements GrantedAuthority {
    @Id
    private Long id;
    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoles name;

    @Override
    public @Nullable String getAuthority() {
        return name.name();
    }
}
