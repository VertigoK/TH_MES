-- 공장 테이블
CREATE TABLE `PLANT` (
	`PLANT_CD`  INT         NOT NULL, -- 공장코드
	`PLANT_NM`  VARCHAR(10) NOT NULL, -- 공장명
	`UPDATE_DT` TIMESTAMP   NOT NULL DEFAULT NOW() -- 수정일
);

-- 공장 테이블
ALTER TABLE `PLANT`
	ADD CONSTRAINT `PK_PLANT` -- 공장 테이블 기본키
		PRIMARY KEY (
			`PLANT_CD` -- 공장코드
		);

-- 공정 테이블
CREATE TABLE `PROCESS` (
	`PLANT_CD`   INT         NOT NULL, -- 공장코드
	`LINE_CD`    INT         NOT NULL, -- 라인코드
	`PROCESS_CD` VARCHAR(12) NOT NULL, -- 공정코드
	`PROCESS_NM` VARCHAR(12) NOT NULL, -- 공정명
	`USE_TYPE`   VARCHAR(10) NOT NULL, -- 타입
	`USE_YN`     BOOLEAN     NOT NULL DEFAULT TRUE, -- 사용여부
	`REMARK`     VARCHAR(50) NULL,     -- 비고
	`UPDATE_DT`  TIMESTAMP   NOT NULL DEFAULT NOW() -- 수정일
);

-- 공정 테이블
ALTER TABLE `PROCESS`
	ADD CONSTRAINT `PK_PROCESS` -- 공정 테이블 기본키
		PRIMARY KEY (
			`PROCESS_CD` -- 공정코드
		);

-- 근무자
CREATE TABLE `WORKER` (
	`PLANT_CD`    INT         NOT NULL, -- 공장코드
	`LINE_CD`     INT         NOT NULL, -- 라인코드
	`WORKER_NO`   INT         NOT NULL, -- 근무자번호
	`WORKER_LOC`  VARCHAR(15) NOT NULL, -- 근무위치
	`WORKER_TIME` VARCHAR(10) NOT NULL, -- 근무시간
	`WORKER_NM`   VARCHAR(15) NOT NULL  -- 근무자명
);

-- 근무자
ALTER TABLE `WORKER`
	ADD CONSTRAINT `PK_WORKER` -- 근무자 기본키
		PRIMARY KEY (
			`WORKER_NO` -- 근무자번호
		);

ALTER TABLE `WORKER`
	MODIFY COLUMN `WORKER_NO` INT NOT NULL AUTO_INCREMENT;

ALTER TABLE `WORKER`
	AUTO_INCREMENT = 1;

-- 라인 테이블
CREATE TABLE `LINE` (
	`PLANT_CD`  INT         NOT NULL, -- 공장코드
	`LINE_CD`   INT         NOT NULL, -- 라인코드
	`LINE_NM`   VARCHAR(10) NOT NULL, -- 라인명
	`USE_YN`    BOOLEAN     NOT NULL DEFAULT TRUE, -- 사용가능여부
	`REMARK`    VARCHAR(50) NULL,     -- 비고
	`UPDATE_DT` TIMESTAMP   NOT NULL DEFAULT NOW() -- 수정일
);

-- 라인 테이블
ALTER TABLE `LINE`
	ADD CONSTRAINT `PK_LINE` -- 라인 테이블 기본키
		PRIMARY KEY (
			`LINE_CD` -- 라인코드
		);

-- 생산이력 테이블
CREATE TABLE `PRODUCTION_HIST` (
	`PLANT_CD` INT       NOT NULL, -- 공장코드
	`LINE_CD`  INT       NOT NULL, -- 라인코드
	`WO_NO`    INT       NOT NULL, -- 생산지시번호
	`WO_SEQ`   INT       NOT NULL, -- 생산지시SEQ
	`START_DT` TIMESTAMP NOT NULL, -- 작업시작일
	`END_DT`   TIMESTAMP NOT NULL, -- 작업종료일
	`INT_QTY`  INT       NOT NULL, -- 투입수량
	`OUT_QTY`  INT       NOT NULL, -- 배출수량
	`NG_QTY`   INT       NOT NULL  -- NG수량
);

-- 생산이력 테이블
ALTER TABLE `PRODUCTION_HIST`
	ADD CONSTRAINT `PK_PRODUCTION_HIST` -- 생산이력 테이블 기본키
		PRIMARY KEY (
			`WO_NO` -- 생산지시번호
		);

ALTER TABLE `PRODUCTION_HIST`
	MODIFY COLUMN `WO_NO` INT NOT NULL AUTO_INCREMENT;

ALTER TABLE `PRODUCTION_HIST`
	AUTO_INCREMENT = 1;

-- 생산정보 테이블
CREATE TABLE `PRODUCTION` (
	`SERIAL_NO`  VARCHAR(30) NOT NULL, -- 제품일련번호
	`WO_NO`      INT         NOT NULL, -- 생산지시번호 (10/05/2021 추가)
	`PLANT_CD`   INT         NOT NULL, -- 공장코드
	`LINE_CD`    INT         NOT NULL, -- 라인코드
	`ITEM_CD`    INT         NOT NULL, -- 품목코드
	`WORKER_NO`  INT         NOT NULL, -- 근무자번호
	`DIM_X`      FLOAT       NOT NULL, -- 가로길이
	`DIM_Y`      FLOAT       NOT NULL, -- 세로길이
	`DIM_H`      FLOAT       NOT NULL, -- 가로면세로편차
	`DIM_W`      FLOAT       NOT NULL, -- 세로면가로편차
	`HOLE_X`     FLOAT       NOT NULL, -- 홀가로길이
	`HOLE_Y`     FLOAT       NOT NULL, -- 홀세로길이
	`HOLE_XC`    FLOAT       NOT NULL, -- 홀가로중심
	`HOLE_YC`    FLOAT       NOT NULL, -- 홀세로중심
	`STR_X`      FLOAT       NOT NULL, -- 가로직진도
	`STR_Y`      FLOAT       NOT NULL, -- 세로직진도
	`HOLE_D`     FLOAT       NOT NULL, -- 홀직경
	`HOLE_RATIO` FLOAT       NOT NULL, -- 홀비율
	`PRD_DT`     TIMESTAMP   NOT NULL DEFAULT NOW() -- 생산시간
);

-- 생산정보 테이블
ALTER TABLE `PRODUCTION`
	ADD CONSTRAINT `PK_PRODUCTION` -- 생산정보 테이블 기본키
		PRIMARY KEY (
			`SERIAL_NO` -- 제품일련번호
		);

-- 생산지시 테이블
CREATE TABLE `WORK_ORDER` (
	`PLANT_CD`    INT         NOT NULL, -- 공장코드
	`LINE_CD`     INT         NOT NULL, -- 라인코드
	`ITEM_CD`     INT         NOT NULL, -- 품목코드 (10/05/2021 추가)
	`ORDER_NO`    INT         NOT NULL, -- 주문번호
	`WO_NO`       INT         NOT NULL, -- 생산지시번호
	`START_DATE`  DATE        NOT NULL DEFAULT NOW(), -- 작업시작일
	`START_SHIFT` VARCHAR(10) NOT NULL, -- 시작작업조
	`END_DATE`    DATE        NOT NULL, -- 작업종료일 (10/05/2021 수정)
	`END_SHIFT`   VARCHAR(10) NOT NULL, -- 종료작업조 (10/05/2021 수정)
	`FLAG_END`    BOOLEAN     NULL,     -- 작업상태
	`PLAN_QTY`    INT         NOT NULL  -- 계획수량
);

-- 생산지시 테이블
ALTER TABLE `WORK_ORDER`
	ADD CONSTRAINT `PK_WORK_ORDER` -- 생산지시 테이블 기본키
		PRIMARY KEY (
			`WO_NO` -- 생산지시번호
		);

ALTER TABLE `WORK_ORDER`
	MODIFY COLUMN `WO_NO` INT NOT NULL AUTO_INCREMENT;

ALTER TABLE `WORK_ORDER`
	AUTO_INCREMENT = 1;

-- 설비 테이블
CREATE TABLE `EQUIPMENT` (
	`PLANT_CD`    INT         NOT NULL, -- 공장코드
	`LINE_CD`     INT         NOT NULL, -- 라인코드
	`PROCESS_CD`  VARCHAR(12) NOT NULL, -- 공정코드
	`EQUIP_ID`    INT         NOT NULL, -- 설비ID
	`EQUIP_CD`    VARCHAR(25) NOT NULL, -- 설비코드
	`EQUIP_NM`    VARCHAR(30) NOT NULL, -- 설비명
	`EQUIP_MODEL` VARCHAR(10) NOT NULL, -- 모델명
	`CHECK_TERM`  INT         NOT NULL DEFAULT 30, -- 점검주기
	`USE_TYPE`    VARCHAR(15) NOT NULL, -- 타입
	`USE_YN`      BOOLEAN     NOT NULL DEFAULT TRUE, -- 사용여부
	`ERROR_CD`    INT         NULL,     -- 에러코드
	`RUN_TIME`    FLOAT       NOT NULL DEFAULT 0 -- 가동시간
);

-- 설비 테이블
ALTER TABLE `EQUIPMENT`
	ADD CONSTRAINT `PK_EQUIPMENT` -- 설비 테이블 기본키
		PRIMARY KEY (
			`EQUIP_ID` -- 설비ID
		);

ALTER TABLE `EQUIPMENT`
	MODIFY COLUMN `EQUIP_ID` INT NOT NULL AUTO_INCREMENT;

ALTER TABLE `EQUIPMENT`
	AUTO_INCREMENT = 1;

-- 설비에러로그 테이블
CREATE TABLE `ERROR_LOG` (
	`LOG_NO`     INT         NOT NULL, -- 로그번호
	`PLANT_CD`   INT         NOT NULL, -- 공장코드
	`LINE_CD`    INT         NOT NULL, -- 라인코드
	`PROCESS_CD` VARCHAR(12) NOT NULL, -- 공정코드
	`EQUIP_ID`   INT         NOT NULL, -- 설비ID
	`ERROR_CD`   INT         NOT NULL, -- 에러코드
	`ERROR_GD`   CHAR        NOT NULL, -- 에러등급
	`START_DT`   TIMESTAMP   NOT NULL DEFAULT NOW(), -- 발생시간
	`END_DT`     TIMESTAMP   NULL      -- 해제시간
);

-- 설비에러로그 테이블
ALTER TABLE `ERROR_LOG`
	ADD CONSTRAINT `PK_ERROR_LOG` -- 설비에러로그 테이블 기본키
		PRIMARY KEY (
			`LOG_NO` -- 로그번호
		);

ALTER TABLE `ERROR_LOG`
	MODIFY COLUMN `LOG_NO` INT NOT NULL AUTO_INCREMENT;

ALTER TABLE `ERROR_LOG`
	AUTO_INCREMENT = 1;

-- 설비에러종류 테이블
CREATE TABLE `ERROR` (
	`ERROR_CD`  INT         NOT NULL, -- 에러코드
	`ERROR_GD`  CHAR        NOT NULL, -- 에러등급
	`ERROR_MSG` VARCHAR(20) NOT NULL, -- 에러내용
	`DOWN_DR`   INT         NOT NULL, -- 가동중단시간
	`UPDATE_DT` TIMESTAMP   NOT NULL DEFAULT NOW() -- 수정일
);

-- 설비에러종류 테이블
ALTER TABLE `ERROR`
	ADD CONSTRAINT `PK_ERROR` -- 설비에러종류 테이블 기본키
		PRIMARY KEY (
			`ERROR_CD` -- 에러코드
		);

-- 주문 테이블
CREATE TABLE `CUST_ORDER` (
	`PLANT_CD`      INT     NOT NULL, -- 공장코드
	`ITEM_CD`       INT     NOT NULL, -- 품목코드
	`ORDER_NO`      INT     NOT NULL, -- 주문번호
	`CUST_CD`       INT     NOT NULL, -- 거래처
	`ORDER_QTY`     INT     NOT NULL, -- 주문수량
	`ORDER_DATE`    DATE    NOT NULL DEFAULT NOW(), -- 주문일자
	`DELIVERY_DATE` DATE    NOT NULL, -- 납기일
	`FINISHED_DATE` DATE    NULL,     -- 마감일
	`ORDER_STATUS`  BOOLEAN NOT NULL DEFAULT FALSE, -- 주문상태
	`DELAYED_DATE`  INT     NOT NULL DEFAULT 0 -- 납기지연일
	`WO_STATUS`			BOOLEAN NOT NULL DEFAULT FALSE; -- (10/05/2021 추가)
);

-- 주문 테이블
ALTER TABLE `CUST_ORDER`
	ADD CONSTRAINT `PK_CUST_ORDER` -- 주문 테이블 기본키
		PRIMARY KEY (
			`ORDER_NO` -- 주문번호
		);

ALTER TABLE `CUST_ORDER`
	MODIFY COLUMN `ORDER_NO` INT NOT NULL AUTO_INCREMENT;

ALTER TABLE `CUST_ORDER`
	AUTO_INCREMENT = 1;

-- 창고 테이블
CREATE TABLE `STORAGE` (
	`PLANT_CD`     INT         NOT NULL, -- 공장코드
	`STORAGE_CD`   INT         NOT NULL, -- 창고코드
	`STORAGE_NM`   VARCHAR(20) NOT NULL, -- 창고명
	`STORAGE_TYPE` VARCHAR(10) NOT NULL, -- 창고타입
	`STORAGE_LOC`  VARCHAR(15) NOT NULL  -- 창고위치
);

-- 창고 테이블
ALTER TABLE `STORAGE`
	ADD CONSTRAINT `PK_STORAGE` -- 창고 테이블 기본키
		PRIMARY KEY (
			`STORAGE_CD` -- 창고코드
		);

-- 창고 테이블 유니크 인덱스
CREATE UNIQUE INDEX `UIX_STORAGE`
	ON `STORAGE` ( -- 창고 테이블
		`STORAGE_NM` ASC -- 창고명
	);

-- 품목 테이블
CREATE TABLE `ITEM` (
	`ITEM_CD`    INT         NOT NULL, -- 품목코드
	`CUST_CD`    INT         NOT NULL, -- 거래처
	`ITEM_TYPE`  VARCHAR(10) NOT NULL, -- 품목종류
	`ITEM_NM`    VARCHAR(20) NOT NULL, -- 품목명
	`ITEM_SPEC1` FLOAT       NULL,     -- 품목규격1
	`ITEM_SPEC2` FLOAT       NULL,     -- 품목규격2
	`ITEM_SPEC3` FLOAT       NULL,     -- 품목규격3
	`ITEM_SPEC4` FLOAT       NULL,     -- 품목규격4
	`ITEM_SPEC5` FLOAT       NULL,     -- 품목규격5
	`ITEM_SPEC6` FLOAT       NULL,     -- 품목규격6
	`ITEM_SPEC7` FLOAT       NULL,     -- 품목규격7
	`ITEM_SPEC8` FLOAT       NULL,     -- 품목규격8
	`UPDATE_DT`  TIMESTAMP   NOT NULL DEFAULT NOW() -- 수정일
);

-- 품목 테이블
ALTER TABLE `ITEM`
	ADD CONSTRAINT `PK_ITEM` -- 품목 테이블 기본키
		PRIMARY KEY (
			`ITEM_CD` -- 품목코드
		);

-- 품목 테이블 유니크 인덱스
CREATE UNIQUE INDEX `UIX_ITEM`
	ON `ITEM` ( -- 품목 테이블
		`CUST_CD` ASC -- 거래처
	);

-- 품목입출고 테이블
CREATE TABLE `ITEM_IO` (
	`PLANT_CD`        INT         NOT NULL, -- 공장코드
	`ITEM_CD`         INT         NULL,     -- 품목코드
	`ITEM_TYPE`       VARCHAR(10) NULL,     -- 품목타입
	`INOUT_CD`        INT         NOT NULL, -- 입출고코드
	`INOUT_DT`        TIMESTAMP   NOT NULL DEFAULT NOW(), -- 입출고시간
	`INOUT_TYPE`      VARCHAR(10) NOT NULL, -- 입출고유형
	`STORAGE_FROM`    INT         NOT NULL, -- 출발창고
	`STORAGE_FROM_NM` VARCHAR(20) NOT NULL, -- 출발창고명
	`STORAGE_TO`      INT         NOT NULL, -- 이동창고
	`STORAGE_TO_NM`   VARCHAR(20) NOT NULL, -- 이동창고명
	`CUST_CD`         INT         NULL,     -- 거래처
	`ITEM_CNT`        INT         NOT NULL  -- 이동수량
);

-- 품목입출고 테이블
ALTER TABLE `ITEM_IO`
	ADD CONSTRAINT `PK_ITEM_IO` -- 품목입출고 테이블 기본키
		PRIMARY KEY (
			`INOUT_CD` -- 입출고코드
		);

ALTER TABLE `ITEM_IO`
	MODIFY COLUMN `INOUT_CD` INT NOT NULL AUTO_INCREMENT;

ALTER TABLE `ITEM_IO`
	AUTO_INCREMENT = 1;

-- 품목재고현황 테이블
CREATE TABLE `ITEM_STOCK` (
	`STOCK_NO`   INT         NOT NULL, -- 재고번호
	`ITEM_CD`    INT         NOT NULL, -- 품목코드
	`ITEM_TYPE`  VARCHAR(10) NOT NULL, -- 품목타입
	`STORAGE_CD` INT         NOT NULL, -- 창고코드
	`STORAGE_NM` VARCHAR(20) NOT NULL, -- 창고명
	`ITEM_QTY`   INT         NOT NULL DEFAULT 0, -- 재고량
	`UPDATE_DT`  TIMESTAMP   NOT NULL DEFAULT NOW() -- 수정일
);

-- 품목재고현황 테이블
ALTER TABLE `ITEM_STOCK`
	ADD CONSTRAINT `PK_ITEM_STOCK` -- 품목재고현황 테이블 기본키
		PRIMARY KEY (
			`STOCK_NO` -- 재고번호
		);

ALTER TABLE `ITEM_STOCK`
	MODIFY COLUMN `STOCK_NO` INT NOT NULL AUTO_INCREMENT;

ALTER TABLE `ITEM_STOCK`
	AUTO_INCREMENT = 1;

-- 품질검사정보 테이블
CREATE TABLE `QUALITY` (
	`SERIAL_NO`       VARCHAR(30) NOT NULL, -- 제품일련번호
	`WO_NO`           INT         NOT NULL, -- 생산지시번호
	`PLANT_CD`        INT         NOT NULL, -- 공장코드
	`LINE_CD`         INT         NOT NULL, -- 라인코드
	`ITEM_CD`         INT         NOT NULL, -- 품목코드
	`WORKER_NO`       INT         NOT NULL, -- 근무자번호
	`DIMCHECK_X`      BOOLEAN     NOT NULL, -- 가로길이검사
	`DIMCHECK_Y`      BOOLEAN     NOT NULL, -- 세로길이검사
	`HOLECHECK_XC`    BOOLEAN     NOT NULL, -- 홀가로중심검사
	`HOLECHECK_YC`    BOOLEAN     NOT NULL, -- 홀세로중심검사
	`DIMCHECK_HX`     BOOLEAN     NOT NULL, -- 가로직진도검사
	`DIMCHECK_WY`     BOOLEAN     NOT NULL, -- 세로직진도검사
	`HOLECHECK_D`     BOOLEAN     NOT NULL, -- 홀직경검사
	`HOLECHECK_RATIO` BOOLEAN     NOT NULL, -- 홀비율검사
	`CHECK_RESULT`    BOOLEAN     NOT NULL  -- 검사종합결과
);

-- 품질검사정보 테이블
ALTER TABLE `QUALITY`
	ADD CONSTRAINT `PK_QUALITY` -- 품질검사정보 테이블 기본키
		PRIMARY KEY (
			`SERIAL_NO` -- 제품일련번호
		);

-- 공정 테이블
ALTER TABLE `PROCESS`
	ADD CONSTRAINT `FK_PLANT_TO_PROCESS` -- 공장 테이블 -> 공정 테이블
		FOREIGN KEY (
			`PLANT_CD` -- 공장코드
		)
		REFERENCES `PLANT` ( -- 공장 테이블
			`PLANT_CD` -- 공장코드
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 공정 테이블
ALTER TABLE `PROCESS`
	ADD CONSTRAINT `FK_LINE_TO_PROCESS` -- 라인 테이블 -> 공정 테이블
		FOREIGN KEY (
			`LINE_CD` -- 라인코드
		)
		REFERENCES `LINE` ( -- 라인 테이블
			`LINE_CD` -- 라인코드
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 근무자
ALTER TABLE `WORKER`
	ADD CONSTRAINT `FK_PLANT_TO_WORKER` -- 공장 테이블 -> 근무자
		FOREIGN KEY (
			`PLANT_CD` -- 공장코드
		)
		REFERENCES `PLANT` ( -- 공장 테이블
			`PLANT_CD` -- 공장코드
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 근무자
ALTER TABLE `WORKER`
	ADD CONSTRAINT `FK_LINE_TO_WORKER` -- 라인 테이블 -> 근무자
		FOREIGN KEY (
			`LINE_CD` -- 라인코드
		)
		REFERENCES `LINE` ( -- 라인 테이블
			`LINE_CD` -- 라인코드
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 라인 테이블
ALTER TABLE `LINE`
	ADD CONSTRAINT `FK_PLANT_TO_LINE` -- 공장 테이블 -> 라인 테이블
		FOREIGN KEY (
			`PLANT_CD` -- 공장코드
		)
		REFERENCES `PLANT` ( -- 공장 테이블
			`PLANT_CD` -- 공장코드
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 생산이력 테이블
ALTER TABLE `PRODUCTION_HIST`
	ADD CONSTRAINT `FK_WORK_ORDER_TO_PRODUCTION_HIST` -- 생산지시 테이블 -> 생산이력 테이블
		FOREIGN KEY (
			`WO_NO` -- 생산지시번호
		)
		REFERENCES `WORK_ORDER` ( -- 생산지시 테이블
			`WO_NO` -- 생산지시번호
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 생산이력 테이블
ALTER TABLE `PRODUCTION_HIST`
	ADD CONSTRAINT `FK_PLANT_TO_PRODUCTION_HIST` -- 공장 테이블 -> 생산이력 테이블
		FOREIGN KEY (
			`PLANT_CD` -- 공장코드
		)
		REFERENCES `PLANT` ( -- 공장 테이블
			`PLANT_CD` -- 공장코드
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 생산이력 테이블
ALTER TABLE `PRODUCTION_HIST`
	ADD CONSTRAINT `FK_LINE_TO_PRODUCTION_HIST` -- 라인 테이블 -> 생산이력 테이블
		FOREIGN KEY (
			`LINE_CD` -- 라인코드
		)
		REFERENCES `LINE` ( -- 라인 테이블
			`LINE_CD` -- 라인코드
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 생산정보 테이블
ALTER TABLE `PRODUCTION`
	ADD CONSTRAINT `FK_PLANT_TO_PRODUCTION` -- 공장 테이블 -> 생산정보 테이블
		FOREIGN KEY (
			`PLANT_CD` -- 공장코드
		)
		REFERENCES `PLANT` ( -- 공장 테이블
			`PLANT_CD` -- 공장코드
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 생산정보 테이블
ALTER TABLE `PRODUCTION`
	ADD CONSTRAINT `FK_LINE_TO_PRODUCTION` -- 라인 테이블 -> 생산정보 테이블
		FOREIGN KEY (
			`LINE_CD` -- 라인코드
		)
		REFERENCES `LINE` ( -- 라인 테이블
			`LINE_CD` -- 라인코드
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 생산정보 테이블
ALTER TABLE `PRODUCTION`
	ADD CONSTRAINT `FK_ITEM_TO_PRODUCTION` -- 품목 테이블 -> 생산정보 테이블
		FOREIGN KEY (
			`ITEM_CD` -- 품목코드
		)
		REFERENCES `ITEM` ( -- 품목 테이블
			`ITEM_CD` -- 품목코드
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 생산정보 테이블
ALTER TABLE `PRODUCTION`
	ADD CONSTRAINT `FK_WORKER_TO_PRODUCTION` -- 근무자 -> 생산정보 테이블
		FOREIGN KEY (
			`WORKER_NO` -- 근무자번호
		)
		REFERENCES `WORKER` ( -- 근무자
			`WORKER_NO` -- 근무자번호
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 생산지시 테이블
ALTER TABLE `WORK_ORDER`
	ADD CONSTRAINT `FK_PLANT_TO_WORK_ORDER` -- 공장 테이블 -> 생산지시 테이블
		FOREIGN KEY (
			`PLANT_CD` -- 공장코드
		)
		REFERENCES `PLANT` ( -- 공장 테이블
			`PLANT_CD` -- 공장코드
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 생산지시 테이블
ALTER TABLE `WORK_ORDER`
	ADD CONSTRAINT `FK_LINE_TO_WORK_ORDER` -- 라인 테이블 -> 생산지시 테이블
		FOREIGN KEY (
			`LINE_CD` -- 라인코드
		)
		REFERENCES `LINE` ( -- 라인 테이블
			`LINE_CD` -- 라인코드
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 생산지시 테이블
ALTER TABLE `WORK_ORDER`
	ADD CONSTRAINT `FK_CUST_ORDER_TO_WORK_ORDER` -- 주문 테이블 -> 생산지시 테이블
		FOREIGN KEY (
			`ORDER_NO` -- 주문번호
		)
		REFERENCES `CUST_ORDER` ( -- 주문 테이블
			`ORDER_NO` -- 주문번호
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 설비 테이블
ALTER TABLE `EQUIPMENT`
	ADD CONSTRAINT `FK_PLANT_TO_EQUIPMENT` -- 공장 테이블 -> 설비 테이블
		FOREIGN KEY (
			`PLANT_CD` -- 공장코드
		)
		REFERENCES `PLANT` ( -- 공장 테이블
			`PLANT_CD` -- 공장코드
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 설비 테이블
ALTER TABLE `EQUIPMENT`
	ADD CONSTRAINT `FK_LINE_TO_EQUIPMENT` -- 라인 테이블 -> 설비 테이블
		FOREIGN KEY (
			`LINE_CD` -- 라인코드
		)
		REFERENCES `LINE` ( -- 라인 테이블
			`LINE_CD` -- 라인코드
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 설비 테이블
ALTER TABLE `EQUIPMENT`
	ADD CONSTRAINT `FK_PROCESS_TO_EQUIPMENT` -- 공정 테이블 -> 설비 테이블
		FOREIGN KEY (
			`PROCESS_CD` -- 공정코드
		)
		REFERENCES `PROCESS` ( -- 공정 테이블
			`PROCESS_CD` -- 공정코드
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 설비에러로그 테이블
ALTER TABLE `ERROR_LOG`
	ADD CONSTRAINT `FK_PLANT_TO_ERROR_LOG` -- 공장 테이블 -> 설비에러로그 테이블
		FOREIGN KEY (
			`PLANT_CD` -- 공장코드
		)
		REFERENCES `PLANT` ( -- 공장 테이블
			`PLANT_CD` -- 공장코드
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 설비에러로그 테이블
ALTER TABLE `ERROR_LOG`
	ADD CONSTRAINT `FK_LINE_TO_ERROR_LOG` -- 라인 테이블 -> 설비에러로그 테이블
		FOREIGN KEY (
			`LINE_CD` -- 라인코드
		)
		REFERENCES `LINE` ( -- 라인 테이블
			`LINE_CD` -- 라인코드
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 설비에러로그 테이블
ALTER TABLE `ERROR_LOG`
	ADD CONSTRAINT `FK_PROCESS_TO_ERROR_LOG` -- 공정 테이블 -> 설비에러로그 테이블
		FOREIGN KEY (
			`PROCESS_CD` -- 공정코드
		)
		REFERENCES `PROCESS` ( -- 공정 테이블
			`PROCESS_CD` -- 공정코드
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 설비에러로그 테이블
ALTER TABLE `ERROR_LOG`
	ADD CONSTRAINT `FK_EQUIPMENT_TO_ERROR_LOG` -- 설비 테이블 -> 설비에러로그 테이블
		FOREIGN KEY (
			`EQUIP_ID` -- 설비ID
		)
		REFERENCES `EQUIPMENT` ( -- 설비 테이블
			`EQUIP_ID` -- 설비ID
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 설비에러로그 테이블
ALTER TABLE `ERROR_LOG`
	ADD CONSTRAINT `FK_ERROR_TO_ERROR_LOG` -- 설비에러종류 테이블 -> 설비에러로그 테이블
		FOREIGN KEY (
			`ERROR_CD` -- 에러코드
		)
		REFERENCES `ERROR` ( -- 설비에러종류 테이블
			`ERROR_CD` -- 에러코드
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 주문 테이블
ALTER TABLE `CUST_ORDER`
	ADD CONSTRAINT `FK_PLANT_TO_CUST_ORDER` -- 공장 테이블 -> 주문 테이블
		FOREIGN KEY (
			`PLANT_CD` -- 공장코드
		)
		REFERENCES `PLANT` ( -- 공장 테이블
			`PLANT_CD` -- 공장코드
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 주문 테이블
ALTER TABLE `CUST_ORDER`
	ADD CONSTRAINT `FK_ITEM_TO_CUST_ORDER` -- 품목 테이블 -> 주문 테이블
		FOREIGN KEY (
			`ITEM_CD` -- 품목코드
		)
		REFERENCES `ITEM` ( -- 품목 테이블
			`ITEM_CD` -- 품목코드
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 주문 테이블
ALTER TABLE `CUST_ORDER`
	ADD CONSTRAINT `FK_ITEM_TO_CUST_ORDER2` -- 품목 테이블 -> 주문 테이블2
		FOREIGN KEY (
			`CUST_CD` -- 거래처
		)
		REFERENCES `ITEM` ( -- 품목 테이블
			`CUST_CD` -- 거래처
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 창고 테이블
ALTER TABLE `STORAGE`
	ADD CONSTRAINT `FK_PLANT_TO_STORAGE` -- 공장 테이블 -> 창고 테이블
		FOREIGN KEY (
			`PLANT_CD` -- 공장코드
		)
		REFERENCES `PLANT` ( -- 공장 테이블
			`PLANT_CD` -- 공장코드
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 품목입출고 테이블
ALTER TABLE `ITEM_IO`
	ADD CONSTRAINT `FK_PLANT_TO_ITEM_IO` -- 공장 테이블 -> 품목입출고 테이블
		FOREIGN KEY (
			`PLANT_CD` -- 공장코드
		)
		REFERENCES `PLANT` ( -- 공장 테이블
			`PLANT_CD` -- 공장코드
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 품목입출고 테이블
ALTER TABLE `ITEM_IO`
	ADD CONSTRAINT `FK_STORAGE_TO_ITEM_IO` -- 창고 테이블 -> 품목입출고 테이블
		FOREIGN KEY (
			`STORAGE_FROM` -- 출발창고
		)
		REFERENCES `STORAGE` ( -- 창고 테이블
			`STORAGE_CD` -- 창고코드
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 품목입출고 테이블
ALTER TABLE `ITEM_IO`
	ADD CONSTRAINT `FK_STORAGE_TO_ITEM_IO2` -- 창고 테이블 -> 품목입출고 테이블2
		FOREIGN KEY (
			`STORAGE_FROM_NM` -- 출발창고명
		)
		REFERENCES `STORAGE` ( -- 창고 테이블
			`STORAGE_NM` -- 창고명
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 품목입출고 테이블
ALTER TABLE `ITEM_IO`
	ADD CONSTRAINT `FK_STORAGE_TO_ITEM_IO3` -- 창고 테이블 -> 품목입출고 테이블3
		FOREIGN KEY (
			`STORAGE_TO` -- 이동창고
		)
		REFERENCES `STORAGE` ( -- 창고 테이블
			`STORAGE_CD` -- 창고코드
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 품목입출고 테이블
ALTER TABLE `ITEM_IO`
	ADD CONSTRAINT `FK_STORAGE_TO_ITEM_IO4` -- 창고 테이블 -> 품목입출고 테이블4
		FOREIGN KEY (
			`STORAGE_TO_NM` -- 이동창고명
		)
		REFERENCES `STORAGE` ( -- 창고 테이블
			`STORAGE_NM` -- 창고명
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 품목입출고 테이블
ALTER TABLE `ITEM_IO`
	ADD CONSTRAINT `FK_ITEM_TO_ITEM_IO` -- 품목 테이블 -> 품목입출고 테이블
		FOREIGN KEY (
			`ITEM_CD` -- 품목코드
		)
		REFERENCES `ITEM` ( -- 품목 테이블
			`ITEM_CD` -- 품목코드
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 품목입출고 테이블
ALTER TABLE `ITEM_IO`
	ADD CONSTRAINT `FK_ITEM_TO_ITEM_IO2` -- 품목 테이블 -> 품목입출고 테이블2
		FOREIGN KEY (
			`CUST_CD` -- 거래처
		)
		REFERENCES `ITEM` ( -- 품목 테이블
			`CUST_CD` -- 거래처
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 품목재고현황 테이블
ALTER TABLE `ITEM_STOCK`
	ADD CONSTRAINT `FK_ITEM_TO_ITEM_STOCK` -- 품목 테이블 -> 품목재고현황 테이블
		FOREIGN KEY (
			`ITEM_CD` -- 품목코드
		)
		REFERENCES `ITEM` ( -- 품목 테이블
			`ITEM_CD` -- 품목코드
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 품목재고현황 테이블
ALTER TABLE `ITEM_STOCK`
	ADD CONSTRAINT `FK_STORAGE_TO_ITEM_STOCK` -- 창고 테이블 -> 품목재고현황 테이블
		FOREIGN KEY (
			`STORAGE_CD` -- 창고코드
		)
		REFERENCES `STORAGE` ( -- 창고 테이블
			`STORAGE_CD` -- 창고코드
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 품목재고현황 테이블
ALTER TABLE `ITEM_STOCK`
	ADD CONSTRAINT `FK_STORAGE_TO_ITEM_STOCK2` -- 창고 테이블 -> 품목재고현황 테이블2
		FOREIGN KEY (
			`STORAGE_NM` -- 창고명
		)
		REFERENCES `STORAGE` ( -- 창고 테이블
			`STORAGE_NM` -- 창고명
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 품질검사정보 테이블
ALTER TABLE `QUALITY`
	ADD CONSTRAINT `FK_PLANT_TO_QUALITY` -- 공장 테이블 -> 품질검사정보 테이블
		FOREIGN KEY (
			`PLANT_CD` -- 공장코드
		)
		REFERENCES `PLANT` ( -- 공장 테이블
			`PLANT_CD` -- 공장코드
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 품질검사정보 테이블
ALTER TABLE `QUALITY`
	ADD CONSTRAINT `FK_LINE_TO_QUALITY` -- 라인 테이블 -> 품질검사정보 테이블
		FOREIGN KEY (
			`LINE_CD` -- 라인코드
		)
		REFERENCES `LINE` ( -- 라인 테이블
			`LINE_CD` -- 라인코드
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 품질검사정보 테이블
ALTER TABLE `QUALITY`
	ADD CONSTRAINT `FK_WORKER_TO_QUALITY` -- 근무자 -> 품질검사정보 테이블
		FOREIGN KEY (
			`WORKER_NO` -- 근무자번호
		)
		REFERENCES `WORKER` ( -- 근무자
			`WORKER_NO` -- 근무자번호
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 품질검사정보 테이블
ALTER TABLE `QUALITY`
	ADD CONSTRAINT `FK_PRODUCTION_TO_QUALITY` -- 생산정보 테이블 -> 품질검사정보 테이블
		FOREIGN KEY (
			`SERIAL_NO` -- 제품일련번호
		)
		REFERENCES `PRODUCTION` ( -- 생산정보 테이블
			`SERIAL_NO` -- 제품일련번호
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 품질검사정보 테이블
ALTER TABLE `QUALITY`
	ADD CONSTRAINT `FK_ITEM_TO_QUALITY` -- 품목 테이블 -> 품질검사정보 테이블
		FOREIGN KEY (
			`ITEM_CD` -- 품목코드
		)
		REFERENCES `ITEM` ( -- 품목 테이블
			`ITEM_CD` -- 품목코드
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;
		
-- 사용자 테이블 추가 (9/27/2021)
create table member(
      mem_id varchar(15) primary key,
      mem_pw varchar(20) not null,
      mem_nm varchar(20) not null,
      mem_dt timestamp default now()
);

-- 자사 자재 발주 테이블 추가 (10/01/2021)
CREATE TABLE `our_order` (
  `ORDER_NO` int(11) NOT NULL AUTO_INCREMENT,
  `CUST_CD` int(11) DEFAULT NULL,
  `PLANT_CD` int(11) DEFAULT NULL,
  `ITEM_CD` int(11) DEFAULT NULL,
  `ORDER_QTY` int(11) DEFAULT NULL,
  `ORDER_DT` timestamp NULL DEFAULT NULL,
  `ORDER_STATUS` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ORDER_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 자사 자재 발주 테이블
ALTER TABLE `OUR_ORDER`
	ADD CONSTRAINT `FK_ITEM_TO_OUR_ORDER` -- 품목 테이블 -> 자사 자재 발주 테이블
		FOREIGN KEY (
			`CUST_CD` -- 거래처
		)
		REFERENCES `ITEM` ( -- 품목 테이블
			`CUST_CD` -- 거래처
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 자사 자재 발주 테이블
ALTER TABLE `OUR_ORDER`
	ADD CONSTRAINT `FK_PLANT_TO_OUR_ORDER` -- 공장 테이블 -> 자사 자재 발주 테이블
		FOREIGN KEY (
			`PLANT_CD` -- 공장코드
		)
		REFERENCES `PLANT` ( -- 공장 테이블
			`PLANT_CD` -- 공장코드
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 자사 자재 발주 테이블
ALTER TABLE `OUR_ORDER`
	ADD CONSTRAINT `FK_ITEM_TO_OUR_ORDER2` -- 품목 테이블 -> 자사 자재 발주 테이블2
		FOREIGN KEY (
			`ITEM_CD` -- 품목코드
		)
		REFERENCES `ITEM` ( -- 품목 테이블
			`ITEM_CD` -- 품목코드
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;



-- 10/05/2021 추가
-- 공지사항 테이블
CREATE TABLE `NOTICE` (
  `NOTICE_NO` int(11) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(40) DEFAULT NULL,
  `CONTENT` varchar(255) DEFAULT NULL,
  `DATE` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`NOTICE_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 생산정보 테이블
ALTER TABLE `PRODUCTION`
	ADD CONSTRAINT `FK_ITEM_TO_PRODUCTION` -- 품목 테이블 -> 생산정보 테이블
		FOREIGN KEY (
			`ITEM_CD` -- 품목코드
		)
		REFERENCES `ITEM` ( -- 품목 테이블
			`ITEM_CD` -- 품목코드
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

delete from production;

-- 생산정보 테이블
ALTER TABLE `PRODUCTION`
	ADD CONSTRAINT `FK_WORK_ORDER_TO_PRODUCTION` -- 생산지시 테이블 -> 생산정보 테이블
		FOREIGN KEY (
			`WO_NO` -- 생산지시번호
		)
		REFERENCES `WORK_ORDER` ( -- 생산지시 테이블
			`WO_NO` -- 생산지시번호
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

	
-- 품질검사정보 테이블
ALTER TABLE `QUALITY`
	ADD CONSTRAINT `FK_WORK_ORDER_TO_QUALITY` -- 생산지시 테이블 -> 품질검사정보 테이블
		FOREIGN KEY (
			`WO_NO` -- 생산지시번호
		)
		REFERENCES `WORK_ORDER` ( -- 생산지시 테이블
			`WO_NO` -- 생산지시번호
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;