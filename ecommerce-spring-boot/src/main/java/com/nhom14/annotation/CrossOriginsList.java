package com.nhom14.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.web.bind.annotation.CrossOrigin;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@CrossOrigin
public @interface CrossOriginsList {
	
	public String[] origins() default  {
        "http://localhost:3003", "http://192.168.1.2:3003"
	};
	
}
