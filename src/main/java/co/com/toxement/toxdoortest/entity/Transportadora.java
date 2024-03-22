package co.com.toxement.toxdoortest.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.*;

import java.util.Date;

/**
 * The persistent class for the reporte_gastos database table.
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="tms_transportadoras")
@NamedQueries({
@NamedQuery(name="Transportadora.findAll", query="SELECT r FROM Transportadora r"),
@NamedQuery(name="Transportadora.findAllActivas", query="SELECT r FROM Transportadora r WHERE r.status=true"),
@NamedQuery(name="Transportadora.findByName", query="SELECT r FROM Transportadora r WHERE r.nombre=:nombre"),
@NamedQuery(name="Transportadora.findByCodigo", query="SELECT r FROM Transportadora r WHERE r.codigo=:codigo")
})
public class Transportadora implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TRANSPORTADORA")
    @SequenceGenerator(name = "SEQ_TRANSPORTADORA", sequenceName = "TRANSPORTADORA_SEQ", allocationSize = 1)
	@Column(name = "trs_id")
	private Integer id;
	
	@Column(name="trs_nombre", nullable = false)
	private String nombre;
	
	@Column(name="trs_identificador", nullable = true)
	private String identificador;
	
	@Column(name="trs_codigo", nullable = true)
	private String codigo;
	
	@Column(name="trs_status", nullable = false)
	private boolean status;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="trs_fecha_creacion", nullable=false)
	private Date fechaCreacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="trs_fecha_modificacion")
	private Date fechaModificacion;
	
	@Column(name="trs_usuario_creacion", nullable=false)
	private Long usuarioCreacion;

	@Column(name="trs_usuario_modificacion")
	private Long usuarioModificacion;
	
}