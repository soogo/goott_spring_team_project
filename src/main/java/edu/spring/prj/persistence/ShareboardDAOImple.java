package edu.spring.prj.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.prj.domain.ShareboardVO;
import edu.spring.prj.pageutil.PageCriteria;

@Repository
public class ShareboardDAOImple implements ShareboardDAO {
	private static final Logger logger = LoggerFactory.getLogger(ShareboardDAOImple.class);
	private static final String NAMESPACE = "edu.spring.prj.ShareboardMapper";

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<ShareboardVO> select() {
		logger.info("DAO select() ȣ��");
		return sqlSession.selectList(NAMESPACE + ".select_all");
		// .select_all : board-mapper.xml�� id�� ��Ī
	}

	@Override
	public ShareboardVO select(int bno) {
		logger.info("DAO select() ȣ�� : bno = " + bno);
		return sqlSession.selectOne(NAMESPACE + ".select_by_bno", bno);
	}

	@Override
	public int insert(ShareboardVO vo) {
		logger.info("DAO insert() ȣ��");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public int update(ShareboardVO vo) {
		logger.info("DAO update() ȣ��");
		return sqlSession.update(NAMESPACE + ".update", vo);
	}

	@Override
	public int delete(int bno) {
		logger.info("DAO delete() ȣ��");
		return sqlSession.delete(NAMESPACE + ".delete", bno);
	}

	@Override
	public int getTotalNumsOfRecords(PageCriteria criteria) {
		logger.info("DAO getTotalNumsOfRecords() ȣ��");
		return sqlSession.selectOne(NAMESPACE + ".total_count", criteria);
	}

	@Override
	public List<ShareboardVO> select(PageCriteria criteria) {
		logger.info("DAO select() ȣ�� : page = " + criteria.getPage());
		return sqlSession.selectList(NAMESPACE + ".paging", criteria);
	}

	@Override
	public List<ShareboardVO> select(String userid) {
		logger.info("DAO select() ȣ�� : userid = " + userid);
		userid = "%" + userid + "%";
		return sqlSession.selectList(NAMESPACE + ".select_by_userid", userid);
	}

	@Override
	public List<ShareboardVO> selectByTitleOrContent(String keyword) {
		logger.info("DAO selectByTitleOrContent() : keyword = " + keyword);
		keyword = "%" + keyword + "%";
		return sqlSession.selectList(NAMESPACE + ".select_by_title_content", keyword);
	}
	
	@Override
	public int updateReplyCount(int amount, int bno) {
		Map<String, Integer> args = new HashMap<String, Integer>();
		args.put("amount", amount);
		args.put("bno", bno);
		return sqlSession.update(NAMESPACE + ".update_reply_count", args);
	}

	@Override
	public List<ShareboardVO> selectNotice() {
		logger.info("DAO notice() ȣ��");
		return sqlSession.selectList(NAMESPACE + ".select_notice");
	}

}