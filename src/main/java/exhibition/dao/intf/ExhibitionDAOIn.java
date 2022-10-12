package exhibition.dao.intf;

import java.util.List;
import exhibition.vo.ExhibitionVO;
import exhibition.vo.ExhibitionVOo;
public interface ExhibitionDAOIn {

		public List<ExhibitionVO> getAll() throws Exception;

		public List<ExhibitionVO> getById(Integer exhibitionID)throws Exception;

		public List<ExhibitionVO> getByName(String exhibitionName) throws Exception;
		
		public List<ExhibitionVO> getByDate(String exhibitionStartDate, String exhibitionEndDate) throws Exception;
		
		public boolean insert(ExhibitionVOo vo);
		
		public boolean updateImg(byte[] img, Integer id) throws Exception;

		public boolean delete(Integer id) throws Exception;

}

