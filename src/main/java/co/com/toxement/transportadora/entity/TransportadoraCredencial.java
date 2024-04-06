package co.com.toxement.transportadora.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;

import co.com.toxement.transportadora.util.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="tms_transportadoras_credenciales")
public class TransportadoraCredencial implements Serializable, UserDetails {
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TRANS_CREDENCIAL")
    @SequenceGenerator(name = "SEQ_TRANS_CREDENCIAL", sequenceName = "TRANS_CREDENCIAL_SEQ", allocationSize = 1)
    @Column(name = "ttc_id")
	private Integer id;
	
	@Column(name="ttc_usuario", nullable = false)
	private String usuario;
	
	@Column(name="ttc_passwd", nullable = false)
	private String password;

	@Column(name="ttc_status")
	private boolean status = false;

	@Column(name="ttc_role")
	@Enumerated(EnumType.STRING)
	private Role role;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ttc_ultimo_ingreso", nullable=true)
	private Date ultimoIngreso;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ttc_fecha_creacion", nullable=true)
	private Date fechaCreacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ttc_fecha_modificacion", nullable=true)
	private Date fechaModificacion;

	@Column(name="ttc_usuario_creacion", nullable=true)
	private Long usuarioCreacion;

	@Column(name="ttc_usuario_modificacion", nullable=true)
	private Long usuarioModificacion;

    @OneToOne
    @JoinColumn(name = "ttc_transportadora_id", referencedColumnName = "trs_id")
    private Transportadora transportadora;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = role.getPermisos().stream()
				.map(permissionEnum -> new SimpleGrantedAuthority(permissionEnum.name()))
				.collect(Collectors.toList());
		authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
		return authorities;
	};

	@Override
	public String getUsername() {
		return this.usuario;
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
		return this.status;
	}
}