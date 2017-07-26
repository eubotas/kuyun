package com.kuyun.eam.admin.util;

import java.lang.reflect.Field;
import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.kuyun.upms.dao.model.UpmsOrganization;
import com.kuyun.upms.dao.model.UpmsUser;
import com.kuyun.upms.rpc.api.UpmsApiService;

/**
 * Created by user on 4/29/2017.
 */
public class EamUtils_ {

	@Autowired
	UpmsApiService upmsApiService;

	public void addAddtionalValue(Object record) {
		Date currentDate = new Date();
		UpmsUser user = getCurrentUser();
		UpmsOrganization organization = upmsApiService.selectParentOrganizationByUserId(user.getUserId());
		System.out.println("-------**********-------");
		System.out.println(record.toString());
		for (Field f : record.getClass().getDeclaredFields()) {
			f.setAccessible(true);
			System.out.println("fields name: " + f.getName());
			try {
				if (f.getName().equals("createTime") && f.get(record) == null) {
					f.set(record, currentDate);
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (f.getName().equals("updateTime")) {
					f.set(record, currentDate);
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (f.getName().equals("createUserId") && f.get(record) == null && user != null) {
					f.set(record, user.getUserId());
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (f.getName().equals("updateUserId")) {
					f.set(record, user.getUserId());
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (f.getName().equals("organizationId") && f.get(record) == null && organization != null) {
					f.set(record, organization.getOrganizationId());
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (f.getName().equals("deleteFlag") && f.get(record) == null) {
					f.set(record, false);
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("after:");
		System.out.println(record.toString());
		System.out.println("-------**********-------");
	}

	public void updateAddtionalValue(Object record) {
		Date currentDate = new Date();
		UpmsUser user = getCurrentUser();
		System.out.println("-------**********-------");
		System.out.println(record.toString());
		try {
			Field updateTime = record.getClass().getDeclaredField("updateTime");
			updateTime.setAccessible(true);
			updateTime.set(record, currentDate);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Field updateUserId = record.getClass().getDeclaredField("updateUserId");
			if (user != null) {
				updateUserId.setAccessible(true);
				updateUserId.set(record, user.getUserId());
			}
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("after:");
		System.out.println(record.toString());
		System.out.println("-------**********-------");
//
//		record.setUpdateTime(currentDate);
//		if (user != null) {
//			record.setUpdateUserId(user.getUserId());
//		}
	}

	public UpmsUser getCurrentUser() {
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		UpmsUser upmsUser = upmsApiService.selectUpmsUserByUsername(username);
		return upmsUser;
	}

	public UpmsOrganization getCurrentUserParentOrignization() {
		UpmsUser user = getCurrentUser();
		return upmsApiService.selectParentOrganizationByUserId(user.getUserId());

	}
}
