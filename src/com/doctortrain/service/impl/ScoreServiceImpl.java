package com.doctortrain.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.doctortrain.bean.Score;
import com.doctortrain.dao.ScoreDAO;
import com.doctortrain.service.ScoreService;

@Service("scoreService")
public class ScoreServiceImpl implements ScoreService {

	@Resource(name="scoreDao")
	private ScoreDAO scoreDao;
	
	public void addScore(Score score) throws Exception {
		scoreDao.addScore(score);
	}

	public void updateScore(Score score) throws Exception {
		scoreDao.updateScore(score);
	}

	public void deleteScore(Score score) throws Exception {
		scoreDao.deleteScore(score);
	}

	public Score getScoreById(String id) throws Exception {
		return scoreDao.getScoreAllById(id);
	}

	public List<Score> getAllScore() throws Exception {
		return scoreDao.getScoreAll();
	}


	public void deleteScoreById(String id) throws Exception {
		scoreDao.deleteById(id);
	}

}
