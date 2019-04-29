package greedc.services;

import java.util.List;

import greedc.dao.UFIndexRoleDt1Dao;
import greedc.entities.UFIndexRoleDt1;

public class UFIndexRoleDt1Service {

	public Integer calYoulpd(double score) {
		List<UFIndexRoleDt1> list = list();
		for (UFIndexRoleDt1 role : list) {
			if (role.getGescore() <= score) {
				return role.getYoulpd();
			}
		}
		return null;
	}
	
	public List<UFIndexRoleDt1> list() {
		UFIndexRoleDt1Dao dao = new UFIndexRoleDt1Dao();
		List<UFIndexRoleDt1> list = dao.list();
		return list;
	}
}
