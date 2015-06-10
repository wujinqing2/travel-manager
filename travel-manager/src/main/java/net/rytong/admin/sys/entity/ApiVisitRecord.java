package net.rytong.admin.sys.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import net.rytong.admin.common.entity.BaseEntity;

/**
 * 接口访问记录
 * 
 * @author wu.jinqing
 * @date 2015年6月9日
 */
@Entity
@Table(name="api_visit_record")
public class ApiVisitRecord implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;
	
	@Column(name = "user_id")
	private Long userId;// 访问者ID
	
	@Column(name = "request_uri")
	private String requestURI;// 访问的URI
	
	@Column(name = "visit_time")
	private Long visitTime;// 访问时间, 格式: yyyyMMddHHmmssSSS
	
	@Column(name = "query_string")
	private String queryString;// 查询参数
	
	@Column(name = "remote_addr")
	private String remoteAddr;// 访问者IP
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getRequestURI() {
		return requestURI;
	}
	public void setRequestURI(String requestURI) {
		this.requestURI = requestURI;
	}
	public Long getVisitTime() {
		return visitTime;
	}
	public void setVisitTime(Long visitTime) {
		this.visitTime = visitTime;
	}
	public String getQueryString() {
		return queryString;
	}
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
	public String getRemoteAddr() {
		return remoteAddr;
	}
	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}
}
