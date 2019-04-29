package greedc.dao;

import greedc.vo.RequestLogVo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import weaver.conn.RecordSetTrans;
import weaver.file.Prop;

public class RequestLogDao {

	public List<RequestLogVo> listByBillCode(int requestId, String formName, String fieldName, String fieldValue) throws ClassNotFoundException, SQLException {
		String sql = "SELECT O.REQUESTID, O.NODEID, LG.OPERATOR, R.LASTNAME, DE.DEPARTMENTNAME, LG.RECEIVEDPERSONS,"
				+ " ND.NODENAME, O.OPERATEDATE, O.OPERATETIME, LG.LOGTYPE, LG.REMARK"
				+ " FROM WORKFLOW_CURRENTOPERATOR O"
				+ " INNER JOIN WORKFLOW_REQUESTLOG LG ON LG.REQUESTID=O.REQUESTID AND LG.NODEID=O.NODEID"
				+ " LEFT JOIN HRMRESOURCE R ON R.ID=LG.OPERATOR"
				+ " LEFT JOIN HRMDEPARTMENT DE ON DE.ID=R.DEPARTMENTID"
				+ " INNER JOIN WORKFLOW_NODEBASE ND ON ND.ID=O.NODEID"
				+ " WHERE O.REQUESTID IN (SELECT REQUESTID FROM " + formName + " WHERE " + fieldName + "=? AND REQUESTID<?)"
				+ " ORDER BY O.REQUESTID DESC, LG.LOGID DESC";
		List<RequestLogVo> vos = new ArrayList<RequestLogVo>();

		String url = Prop.getPropValue("weaver", "ecology.url");    
		String username = Prop.getPropValue("weaver", "ecology.user") ;   
		String password = Prop.getPropValue("weaver", "ecology.password") ;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(Prop.getPropValue("weaver", "DriverClasses"));
			conn = DriverManager.getConnection(url , username , password );
			pstmt = conn.prepareStatement(sql) ;
			pstmt.setString(1, fieldValue);
			pstmt.setInt(2, requestId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				RequestLogVo vo = new RequestLogVo();
				vo.setRequestId(rs.getInt("REQUESTID"));
				vo.setOperator(rs.getInt("OPERATOR"));
				vo.setName(rs.getString("LASTNAME"));
				vo.setDeptName(rs.getString("DEPARTMENTNAME"));
				vo.setNodeName(rs.getString("NODENAME"));
				vo.setLogContent(rs.getString("REMARK"));
				vo.setOpDate(rs.getString("OPERATEDATE"));
				vo.setOpTime(rs.getString("OPERATETIME"));
				vo.setReNames(rs.getString("RECEIVEDPERSONS"));
				vo.setOpTypeId(rs.getString("LOGTYPE"));
				vo.setBillCode(fieldValue);
				vos.add(vo);
			}
		} finally {
			close(conn, pstmt, rs);
		}
		return vos;
	}
	
	public List<RequestLogVo> listByBillCode(String billCode) throws ClassNotFoundException, SQLException {
		String sql = "SELECT REQUESTID, BILLCODE, OPERATOR, LASTNAME, DEPARTMENTNAME, RECEIVEDPERSONS, NODENAME, OPERATEDATE, OPERATETIME, LOGTYPE, REMARK"
				+ " FROM ECST_REQUESTLOG WHERE BILLCODE=?"
				+ " ORDER BY OPERATEDATE DESC, OPERATETIME DESC";
		List<RequestLogVo> vos = new ArrayList<RequestLogVo>();

		String url = Prop.getPropValue("weaver", "ecology.url");    
		String username = Prop.getPropValue("weaver", "ecology.user") ;   
		String password = Prop.getPropValue("weaver", "ecology.password") ;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(Prop.getPropValue("weaver", "DriverClasses"));
			conn = DriverManager.getConnection(url , username , password );
			pstmt = conn.prepareStatement(sql) ;
			pstmt.setString(1, billCode);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				RequestLogVo vo = new RequestLogVo();
				vo.setRequestId(rs.getInt("REQUESTID"));
				vo.setOperator(rs.getInt("OPERATOR"));
				vo.setName(rs.getString("LASTNAME"));
				vo.setDeptName(rs.getString("DEPARTMENTNAME"));
				vo.setNodeName(rs.getString("NODENAME"));
				vo.setLogContent(rs.getString("REMARK"));
				vo.setOpDate(rs.getString("OPERATEDATE"));
				vo.setOpTime(rs.getString("OPERATETIME"));
				vo.setReNames(rs.getString("RECEIVEDPERSONS"));
				vo.setOpTypeId(rs.getString("LOGTYPE"));
				vo.setBillCode(rs.getString("BILLCODE"));
				vos.add(vo);
			}
		} finally {
			close(conn, pstmt, rs);
		}
		return vos;
	}
	
	public void save(List<RequestLogVo> list, RecordSetTrans rst) throws Exception {
		for (RequestLogVo vo : list) {
			save(vo, rst);
		}
	}
	
	public void save(RequestLogVo vo, RecordSetTrans rst) throws Exception {
		String sql = "INSERT INTO ECST_REQUESTLOG(REQUESTID, BILLCODE, OPERATOR, LASTNAME, DEPARTMENTNAME, RECEIVEDPERSONS, NODENAME, OPERATEDATE, OPERATETIME, LOGTYPE, REMARK) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		rst.executeUpdate(sql, vo.getRequestId(), vo.getBillCode(), vo.getOperator(), vo.getName(), vo.getDeptName(), vo.getReNames(), vo.getNodeName(), vo.getOpDate(), vo.getOpTime(), vo.getOpTypeId(), vo.getLogContent());
	}
	
	private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		if (rs != null ) try {rs.close();} catch(Exception e){e.printStackTrace();}
		if (pstmt != null ) try {pstmt.close();} catch(Exception e){e.printStackTrace();}
		if (conn != null ) try {conn.close();} catch(Exception e){e.printStackTrace();}
	}
}
