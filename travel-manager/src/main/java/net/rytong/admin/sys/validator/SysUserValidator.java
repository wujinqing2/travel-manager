package net.rytong.admin.sys.validator;

import net.rytong.admin.sys.entity.SysUser;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class SysUserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return SysUser.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors e) {
		ValidationUtils.rejectIfEmpty(e, "name", "name.empty");
		ValidationUtils.rejectIfEmpty(e, "password", "password.empty");
		SysUser p = (SysUser) obj;

	}

}
