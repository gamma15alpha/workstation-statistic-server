package kometa.statistic.workstationstatisticserver.entity

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "users")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(unique = true, nullable = false)
    val login: String = "",

    @Column(nullable = false)
    val hashedPassword: String = "",

    @Column(nullable = false)
    val role: String = "CLIENT" // "ADMIN", "CLIENT"
) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority> =
        listOf(GrantedAuthority { "ROLE_$role" })

    override fun getPassword(): String = hashedPassword
    override fun getUsername(): String = login

    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = true
}