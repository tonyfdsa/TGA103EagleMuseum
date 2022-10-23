package ticket.dao.intf;

import java.util.List;
import ticket.vo.TicketVO;
import ticket.vo.TicketVOo;

public interface TicketDAOIn {
	public List<TicketVOo> getAll() throws Exception;

	public List<TicketVOo> getById(Integer ticketID)throws Exception;
	
	public Integer insert(TicketVO vo);

}
