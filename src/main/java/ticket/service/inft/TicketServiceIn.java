package ticket.service.inft;

import java.util.List;

import ticket.common.Result;
import ticket.vo.TicketVO;
import ticket.vo.TicketVOo;

public interface TicketServiceIn {
	
	public Result getAll() throws Exception;
	
	public Result getById(Integer ticketID) throws Exception;
	
	public Result insert(TicketVO vo);
}
