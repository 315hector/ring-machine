package pnp.ringmachine.engine.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table( name = "grid")
public class Grid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @Column(name = "title")
    private String title;

    @Setter
    @Getter
    @Column(name = "screen")
    @Enumerated(EnumType.STRING)
    private Breakpoint screen;

    @Column(name = "position")
    @Enumerated(EnumType.STRING)
    private Orientation orientation;

    @Setter
    @Getter
    @Column(name = "columns")
    private Long columns;

    public enum Orientation {
        LANDSCAPE,
        PORTRAIT
    }

    public enum Breakpoint {
        XS,
        GT_XS,
        SM,
        GT_SM,
        MD,
        GT_MD,
        LG,
        GT_LG,
        XL
    }

    @Setter
    @Getter
    @Column(name = "color", nullable = false)
    private String color = "gold";

    @Setter
    @Getter
    @Column(nullable = false)
    private boolean enable = false;

}
