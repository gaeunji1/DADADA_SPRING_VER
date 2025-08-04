package com.ktdsuniversity.proj.dadada.mypage.vo;

public class MyPageOrderListVO {
	
	// EVNT_RM 테이블
	/**
	 * @ColumnName EVNT_RM_ID
	 * @ColumnComment PK - 경쟁 룸 식별 번호
	 */
	private int evntRmId;
	
	// MBR 테이블
	/**
	 * @ColumnName MBR_ID
	 * @ColumnComment PK - 회원 식별 번호
	 */
	private int mbrId;
	
	// ORD 테이블
	/**
	 * @ColumnName ORD_ID
	 * @ColumnComment PK - 주문 아이디
	 */
	private int ordId;
	
	/**
	 * @ColumnName ORD_CNT
	 * @ColumnComment 주문 수량
	 */
	private int ordCnt;
	
	/**
	 * @ColumnName PRD_PRC_FINAL
	 * @ColumnComment 상품 최종 결제 금액
	 */
	private double prdPrcFinal; // 화면 출력 시 소수점 안 나오게 포맷팅
	
	/**
	 * @ColumnName ORD_DT
	 * @ColumnComment 주문 생성 일시
	 */
	private String ordDt;
	
	/**
	 * @ColumnName ORD_DVSN
	 * @ColumnComment 상품/패키지/경쟁 룸 상품 구분
	 */
	private String ordDvsn;
	
	// PCKG 테이블
	/**
	 * @ColumnName PCKG_ID
	 * @ColumnComment 패키지 아이디
	 */
	private int pckgId;
	
	/**
	 * @ColumnName PCKG_NM
	 * @ColumnComment 패키지명
	 */
	private String pckgNm;
	
	/**
	 * @ColumnName PCKG_IMG
	 * @ColumnComment 패키지 이미지
	 */
	private String pckgImg;
	
	// PMT 테이블
	/**
	 * @ColumnName PMT_DT
	 * @ColumnComment 결제 일시
	 */
	private String pmtDt;
	
	// PRD 테이블
	/**
	 * @ColumnName PRD_ID
	 * @ColumnComment 상품 아이디
	 */
	private int prdId;
	
	/**
	 * @ColumnName PRD_NM
	 * @ColumnComment 상품명
	 */
	private String prdNm;
	
	/**
	 * @ColumnName PRD_IMG
	 * @ColumnComment 상품 이미지
	 */
	private String prdImg;
	
	/**
	 * @ColumnName SHP_CMPNY
	 * @ColumnComment 배송 업체명
	 */
	private String shpCmpny;

	/**
	 * @ColumnName SHP_TRK_NUM
	 * @ColumnComment 운송장번호
	 */
	private String shpTrkNum;
	
	// SHP 테이블
	/**
	 * @ColumnName SHP_STAT
	 * @ColumnComment 배송 상태
	 */
	private String shpStat;
	
	/**
	 * @ColumnName SHP_DT
	 * @ColumnComment 배송 출발일
	 */
	private String shpDt;
	
	/**
	 * @ColumnName SHP_ARRV_DT
	 * @ColumnComment 배송 도착일
	 */
	private String shpArrvDt;
	
	/**
	 * @Comment 배송비
	 */
	private int shippingFee;
	
	/**
	 * @Comment 해당 상품에 대한 리뷰 작성 여부
	 */
	private boolean reviewWritten;
	
	/**
	 * @Comment 여러 상품일 때 총 상품 개수 - 1 한 개수 구하기
	 */
	private int additionalProductCount;

	private int samplePrdId;
	
	private String samplePrdNm;
	
	private String samplePrdImg;


	public int getEvntRmId() {
		return this.evntRmId;
	}

	public void setEvntRmId(int evntRmId) {
		this.evntRmId = evntRmId;
	}

	public int getMbrId() {
		return this.mbrId;
	}

	public void setMbrId(int mbrId) {
		this.mbrId = mbrId;
	}

	public int getOrdId() {
		return this.ordId;
	}

	public void setOrdId(int ordId) {
		this.ordId = ordId;
	}

	public int getOrdCnt() {
		return this.ordCnt;
	}

	public void setOrdCnt(int ordCnt) {
		this.ordCnt = ordCnt;
	}

	public double getPrdPrcFinal() {
		return this.prdPrcFinal;
	}

	public void setPrdPrcFinal(double prdPrcFinal) {
		this.prdPrcFinal = prdPrcFinal;
	}

	public String getOrdDt() {
		return this.ordDt;
	}

	public void setOrdDt(String ordDt) {
		this.ordDt = ordDt;
	}

	public String getOrdDvsn() {
		return this.ordDvsn;
	}

	public void setOrdDvsn(String ordDvsn) {
		this.ordDvsn = ordDvsn;
	}

	public int getPckgId() {
		return this.pckgId;
	}

	public void setPckgId(int pckgId) {
		this.pckgId = pckgId;
	}

	public String getPckgNm() {
		return this.pckgNm;
	}

	public void setPckgNm(String pckgNm) {
		this.pckgNm = pckgNm;
	}

	public String getPckgImg() {
		return this.pckgImg;
	}

	public void setPckgImg(String pckgImg) {
		this.pckgImg = pckgImg;
	}

	public String getPmtDt() {
		return this.pmtDt;
	}

	public void setPmtDt(String pmtDt) {
		this.pmtDt = pmtDt;
	}

	public int getPrdId() {
		return this.prdId;
	}

	public void setPrdId(int prdId) {
		this.prdId = prdId;
	}

	public String getPrdNm() {
		return this.prdNm;
	}

	public void setPrdNm(String prdNm) {
		this.prdNm = prdNm;
	}

	public String getPrdImg() {
		return this.prdImg;
	}

	public void setPrdImg(String prdImg) {
		this.prdImg = prdImg;
	}
	
	public String getShpCmpny() {
		return this.shpCmpny;
	}

	public void setShpCmpny(String shpCmpny) {
		this.shpCmpny = shpCmpny;
	}

	public String getShpTrkNum() {
		return this.shpTrkNum;
	}

	public void setShpTrkNum(String shpTrkNum) {
		this.shpTrkNum = shpTrkNum;
	}

	public String getShpStat() {
		return this.shpStat;
	}

	public void setShpStat(String shpStat) {
		this.shpStat = shpStat;
	}

	public String getShpDt() {
		return this.shpDt;
	}

	public void setShpDt(String shpDt) {
		this.shpDt = shpDt;
	}

	public String getShpArrvDt() {
		return this.shpArrvDt;
	}

	public void setShpArrvDt(String shpArrvDt) {
		this.shpArrvDt = shpArrvDt;
	}

	public int getShippingFee() {
		return this.shippingFee;
	}

	public void setShippingFee(int shippingFee) {
		this.shippingFee = shippingFee;
	}

	public boolean isReviewWritten() {
		return this.reviewWritten;
	}

	public void setReviewWritten(boolean reviewWritten) {
		this.reviewWritten = reviewWritten;
	}

	public int getSamplePrdId() {
		return this.samplePrdId;
	}

	public void setSamplePrdId(int samplePrdId) {
		this.samplePrdId = samplePrdId;
	}

	public String getSamplePrdNm() {
		return this.samplePrdNm;
	}

	public void setSamplePrdNm(String samplePrdNm) {
		this.samplePrdNm = samplePrdNm;
	}

	public String getSamplePrdImg() {
		return this.samplePrdImg;
	}

	public void setSamplePrdImg(String samplePrdImg) {
		this.samplePrdImg = samplePrdImg;
	}

	public int getAdditionalProductCount() {
		return this.additionalProductCount;
	}

	public void setAdditionalProductCount(int additionalProductCount) {
		this.additionalProductCount = additionalProductCount;
	}
}
