package pnp.ringmachine.engine.entity;

import lombok.*;

import javax.persistence.*;

/**
 *
 */
@Entity
//@Builder
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Setter
    @Getter
    @Column(name = "label", nullable = false)
    private String label;

    @Setter
    @Getter
    @Column(name = "description")
    private String description;

    @Setter
    @Getter
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    @Setter
    @Getter
    @Column(name = "project", nullable = false)
    private String project;

    @Setter
    @Getter
    @Column(name = "created", nullable = false)
    private String created;

    @Setter
    @Getter
    @Column(name = "updated", nullable = false)
    private String updated;

    @Getter
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type = Type.BUG;

    public enum Type {
        TASK,
        BUG,
        SUBTASK;
    }

    public enum Status {
        PENDING,
        IN_PROGRESS,
        CLOSED
    }
}