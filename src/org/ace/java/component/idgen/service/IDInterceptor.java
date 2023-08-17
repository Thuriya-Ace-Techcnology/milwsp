package org.ace.java.component.idgen.service;

import java.lang.reflect.Field;
import java.util.Date;

import org.ace.insurance.common.UserRecorder;
import org.ace.java.component.FormatID;
import org.ace.java.component.idgen.service.interfaces.ICustomIDGenerator;
import org.eclipse.persistence.descriptors.DescriptorEvent;
import org.eclipse.persistence.descriptors.DescriptorEventAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@SuppressWarnings({ "rawtypes" })
@Component
public class IDInterceptor extends DescriptorEventAdapter {
	private static ICustomIDGenerator customIDGenerator;

	@Autowired(required = true)
	@Qualifier("CustomIDGenerator")
	public void setcustomIDGenerator(ICustomIDGenerator generator) {
		customIDGenerator = generator;
	}

	private String getPrefix(Class cla) {
		return customIDGenerator.getPrefix(cla);
	}

	@Override
	public void preInsert(DescriptorEvent event) {
		try {
			Object obj = event.getObject();
			Class cla = obj.getClass();
			Field formatId = null;
			try {
				formatId = cla.getDeclaredField("id");
			} catch (NoSuchFieldException e) {
				cla = obj.getClass().getSuperclass();
				formatId = cla.getDeclaredField("id");
			}

			formatId.setAccessible(true);
			String id = (String) formatId.get(obj);
			id = FormatID.formatId(id, getPrefix(cla), 10);
			formatId.set(obj, id);

			Field recorder = cla.getDeclaredField("recorder");
			recorder.setAccessible(true);
			UserRecorder userRecorder = new UserRecorder();
			userRecorder.setCreatedDate(new Date());
			recorder.set(obj, userRecorder);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void preUpdateWithChanges(DescriptorEvent event) {
		try {
			Object obj = event.getObject();
			Class cla = obj.getClass();
			Field recorder;
			try {
				recorder = cla.getDeclaredField("recorder");
			} catch (NoSuchFieldException e) {
				cla = obj.getClass().getSuperclass();
				recorder = cla.getDeclaredField("recorder");
			}

			recorder.setAccessible(true);
			UserRecorder userRecorder = (UserRecorder) recorder.get(obj);
			if (userRecorder == null) {
				userRecorder = new UserRecorder();
			}
			userRecorder.setUpdatedDate(new Date());
			recorder.set(obj, userRecorder);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}
