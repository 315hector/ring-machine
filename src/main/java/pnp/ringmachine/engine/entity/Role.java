package pnp.ringmachine.engine.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @Column(name = "description", nullable = false)
    private String description;

    @Setter
    @Getter
    @Column(name = "key", length = 15, nullable = false, unique = true)
    private String keyName;

}

