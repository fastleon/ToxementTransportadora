package co.com.toxement.transportadora.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="tms_transportadoras_solicitud_ext")
public class Solicitud implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SOLICITUD_JWT")
    @SequenceGenerator(name = "SEQ_SOLICITUD_JWT", sequenceName = "JWT_SOLICITUD_SEQ", allocationSize = 1)
    @Column(name = "tts_id")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="tts_fecha_creacion", nullable=true)
    private Date fechaCreacion;

    @Column(name="tts_transportadora_id")
    private Integer transportadoraId;

    @Column(name="tts_num_entrega")
    private String numeroEntrega;

    @Lob
    @Column(name = "tts_data", columnDefinition = "VARCHAR(4000)")
    private String jsonData;

}
