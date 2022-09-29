package exhibition.dao.intf;

import java.util.List;
import exhibition.vo.ExhibitionVO;
public interface ExhibitionDAOIn {

		public List<ExhibitionVO> getAll() throws Exception;

		public List<ExhibitionVO> getById(Integer id);

		public List<ExhibitionVO> getByName(String exhibitionName) throws Exception;

}

