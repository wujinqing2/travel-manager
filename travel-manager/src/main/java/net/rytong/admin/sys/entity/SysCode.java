package net.rytong.admin.sys.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import net.rytong.admin.common.entity.BaseEntity;

@Entity
@Table(name="sys_code")
public class SysCode extends BaseEntity{
	private static final long serialVersionUID = 1L;
	
	// 类型
	@Column(name = "type")
	private Integer type;
	// 代码
	@Column(name = "code")
	private String code;
	// 名称
	@Column(name = "code_name")
	private String codeName;
	// 备注说明
	@Column(name = "note")
	private String note;
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
}
