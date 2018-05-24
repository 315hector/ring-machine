package pnp.ringmachine.engine.repository;

import org.springframework.data.repository.CrudRepository;
import pnp.ringmachine.engine.entity.Ticket;

public interface TicketRepository extends CrudRepository<Ticket, Long> {
}
