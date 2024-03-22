package co.com.toxement.toxdoortest.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="tms_transportadoras_test")
public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PRODUCTO_TEST")
    @SequenceGenerator(name = "SEQ_PRODUCTO_TEST", sequenceName = "PRODUCTO_TEST_SEQ", allocationSize = 1)
    @Column(name = "ttt_id")
    //@Null
    private Long id;

    @Column(name = "ttt_name")
    @NotBlank
    private String name;

    @Column(name = "ttt_price")
    @DecimalMin(value = "0.01")
    private BigDecimal price;

}
