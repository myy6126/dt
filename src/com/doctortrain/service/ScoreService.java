package com.doctortrain.service;

import java.util.List;

import com.doctortrain.bean.Score;

public interface ScoreService {
	
	/**
	 * 添加成绩
	 * @param score
	 * @throws Exception
	 */
	public void addScore(Score score) throws Exception;
	
	/**
	 * 更新成绩
	 * @param score
	 * @throws Exception
	 */
	public void updateScore(Score score) throws Exception;
	
	/**
	 * 删除成绩
	 * @param score
	 * @throws Exception
	 */
	public void deleteScore(Score score) throws Exception;
	
	/**
	 * 删除成绩ById
	 * @param score
	 * @throws Exception
	 */
	public void deleteScoreById(String id) throws Exception;
	
	/**
	 * 获取成绩ById
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Score getScoreById(String id) throws Exception;
	
	/**
	 * 获取所有成绩
	 * @return
	 * @throws Exception
	 */
	public List<Score> getAllScore() throws Exception;
}
