package ticket.service.impl;

import ticket.common.Result;
import ticket.dao.impl.TicketDAOIm;
import ticket.service.inft.TicketServiceIn;
import ticket.vo.TicketVO;

public class TicketServiceIm implements TicketServiceIn {

	private TicketDAOIm DAO;
	// RESTFUL
	private Result R;

	public TicketServiceIm() {
		DAO = new TicketDAOIm();
		R = new Result();
	}

	@Override
	public Result getAll() {
		try {
			return R.success(DAO.getAll());
		} catch (Exception e) {
			e.printStackTrace();
			return R.fail(e.toString());
		}
	}


	@Override
	public Result getById(Integer ticketID) {
		try {
			System.out.println(R.success(DAO.getById(ticketID)));
			return R.success(DAO.getById(ticketID));
		} catch (Exception e) {
			e.printStackTrace();
			return R.fail(e.toString());
		}
	}
	
	
	@Override
	public Result insert(TicketVO vo) {
		try {
			return R.success(DAO.insert(vo));
		} catch (Exception e) {
			e.printStackTrace();
			return R.fail(e.toString());
		}
	}
	
	

}
