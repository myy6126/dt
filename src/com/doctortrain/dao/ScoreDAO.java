package com.doctortrain.dao;

import java.util.List;

import com.doctortrain.bean.Score;

public interface ScoreDAO {
	
	/**
	 * 添加
	 * @param score
	 * @throws Exception
	 */
	public void addScore(Score score)throws Exception;
	/**
	 * 修改
	 * @param score
	 * @throws Exception
	 */
	public void updateScore(Score score)throws Exception;
	/**
	 * 删除
	 * @param score
	 * @throws Exception
	 */
	public void deleteScore(Score score)throws Exception;
	
	/**
	 * 删除 by Id
	 * @param id
	 * @throws Exception
	 */
	public void deleteById(String id)throws Exception;
	
	/**
	 * 获取 by Id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Score getScoreAllById(String id)throws Exception;
	
	/**
	 * 获取所有
	 * @return
	 * @throws Exception
	 */
	public List<Score> getScoreAll()throws Exception;
}
