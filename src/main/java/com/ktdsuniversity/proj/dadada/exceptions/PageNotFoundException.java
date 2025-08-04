package com.ktdsuniversity.proj.dadada.exceptions;

import org.springframework.http.HttpStatus;

import com.ktdsuniversity.proj.dadada.exceptions.base.BaseException;

/**
 * 페이지를 찾을 수 없는 경우 발생하는 예외
 */
public class PageNotFoundException extends BaseException {

	private static final long serialVersionUID = -4880488284276735840L;

	public PageNotFoundException() {
		super("요청하신 페이지를 찾을 수 없습니다.", "PAGE_NOT_FOUND", HttpStatus.NOT_FOUND);
	}
}
