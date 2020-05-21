package com.urise.webapp;

import com.urise.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class MainReflection {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Resume resume = new Resume();
        Field resField = resume.getClass().getDeclaredFields()[0];
        resField.setAccessible(true);
        resField.set(resume, "new_uuid");
        System.out.println(resField.getName());
        System.out.println(resField.get(resume));
        Method method = resume.getClass().getDeclaredMethod("toString");
        System.out.println(method.invoke(new Resume()));
        System.out.println(method.invoke(resume));
        method = resume.getClass().getMethod("hashCode");
        System.out.println(method.invoke(resume));
    }
}
