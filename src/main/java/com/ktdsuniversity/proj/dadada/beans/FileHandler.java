package com.ktdsuniversity.proj.dadada.beans;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class FileHandler {

	private String baseDirWindows;
	private String baseDirLinux;
	private String baseDirMacos;

	private boolean obfuscationEnable;
	private boolean obfuscationHideExtEnable;

	private String osname;
	
	// 이미지 타입 별 상수 추가
	private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList("jpg", "jpeg", "png");
	private static final List<String> ALLOWED_CONTENT_TYPES = Arrays.asList(
	    "image/jpeg", "image/jpg", "image/png"
	);
	
	// 파일 크기 관련 상수 추가
	private static final long MAX_FILE_SIZE_BYTES = 30L * 1024 * 1024; // 30MB

	public void setBaseDirWindows(String baseDirWindows) {
		this.baseDirWindows = baseDirWindows;
	}

	public void setBaseDirLinux(String baseDirLinux) {
		this.baseDirLinux = baseDirLinux;
	}

	public void setBaseDirMacos(String baseDirMacos) {
		this.baseDirMacos = baseDirMacos;
	}

	public void setObfuscationEnable(boolean obfuscationEnable) {
		this.obfuscationEnable = obfuscationEnable;
	}

	public void setObfuscationHideExtEnable(boolean obfuscationHideExtEnable) {
		this.obfuscationHideExtEnable = obfuscationHideExtEnable;
	}

	public void setOsname(String osname) {
		this.osname = osname;
	}

	public StoredFile store(MultipartFile multipartFile) {
		
		if (multipartFile == null || multipartFile.isEmpty()) {
			return null;
		}
		
		String fileName = this.getObfuscationFileName( multipartFile.getOriginalFilename() );
		
		File storePath = null;
		// 목적지 설정
		if ( osname.startsWith("windows") ) {
			storePath = new File(this.baseDirWindows, fileName);
		}
		else if ( osname.startsWith("mac") ) {
			String homePath = System.getProperty("user.home");
			storePath = new File(homePath + this.baseDirMacos, fileName);
		}
		else {
			storePath = new File(this.baseDirLinux, fileName);
		}
		
		if ( ! storePath.getParentFile().exists() ) {
			storePath.getParentFile().mkdirs();
		}
		
		// 업로드한 파일을 저장.
		try {
			multipartFile.transferTo(storePath);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			// 업로드 실패!
			// 파일을 저장하는 경로가 접근불가능한 경로 일 때 (Windows기준: C:\\ 직접 업로드할 때)
			// 디스크의 남은 용량이 부족할 때. (남아있는 용량 < 업로드한 파일의 크기)
			return null;
		}
		
		// 업로드 성공!
		// String fileName, String realFileName, String realFilePath, long fileSize
		return new StoredFile( 
				multipartFile.getOriginalFilename(), 
				fileName,
				storePath.getAbsolutePath(),
				storePath.length());
	}

	public String getObfuscationFileName(String fileName) {
		if (obfuscationEnable) {
			String ext = fileName.substring( fileName.lastIndexOf(".") );
			fileName = UUID.randomUUID().toString();
			
			if ( ! obfuscationHideExtEnable) {
				fileName += ext;
			}
		}
		
		return fileName;
	}
	
	/**
	 * 파일이 허용된 이미지 파일인지 검증합니다.
	 * @param file 검증할 파일
	 * @return 허용된 파일이면 true, 아니면 false
	 */
	public boolean isValidImageFile(MultipartFile file) {
		if (file == null || file.isEmpty()) {
			return false;
		}

		// 1. 파일명에서 확장자 추출
		String originalFilename = file.getOriginalFilename();
		if (originalFilename == null || originalFilename.isEmpty()) {
			return false;
		}

		String extension = "";
		int lastDotIndex = originalFilename.lastIndexOf(".");
		if (lastDotIndex > 0) {
			extension = originalFilename.substring(lastDotIndex + 1).toLowerCase();
		}

		// 2. MIME 타입 검증
		String contentType = file.getContentType();

		// 3. 둘 다 허용 목록에 있는지 확인
		return ALLOWED_EXTENSIONS.contains(extension) && 
				ALLOWED_CONTENT_TYPES.contains(contentType);
	}

	/**
	 * 파일 크기가 허용 범위 내인지 검증합니다.
	 * @param file 검증할 MultipartFile 객체
	 * @return 파일 크기가 허용 범위 내이면 true, 그렇지 않으면 false
	 */
	public boolean isValidFileSize(MultipartFile file) {
		if (file == null || file.isEmpty()) {
			return false;
		}
		return file.getSize() <= MAX_FILE_SIZE_BYTES;
	}

	/**
	 * 파일이 유효한 이미지이고 크기도 허용 범위 내인지 검증합니다.
	 * @param file 검증할 MultipartFile 객체
	 * @return 유효한 이미지이고 크기도 허용 범위 내이면 true, 그렇지 않으면 false
	 */
	public boolean isValidImage(MultipartFile file) {
		return isValidImageFile(file) && isValidFileSize(file);
	}

	public class StoredFile {
		private String fileName;
		private String realFileName;
		private String realFilePath;
		private long fileSize;

		public StoredFile(String fileName, String realFileName, String realFilePath, long fileSize) {
			this.fileName = fileName;
			this.realFileName = realFileName;
			this.realFilePath = realFilePath;
			this.fileSize = fileSize;
		}

		public String getFileName() {
			return fileName;
		}

		public String getRealFileName() {
			return realFileName;
		}

		public String getRealFilePath() {
			return realFilePath;
		}

		public long getFileSize() {
			return fileSize;
		}

	}
}
