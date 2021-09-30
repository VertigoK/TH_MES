-- 공장 데이터
delete from plant;
insert into plant(plant_cd, plant_nm) values(1, '공장1');
insert into plant(plant_cd, plant_nm) values(2, '공장2');


-- 라인 데이터
delete from line;
delimiter //
begin not atomic
   declare i int default 1;
   declare j int default 1;
   declare k int default 1;
   while i <= 2 do
      while j <= 3 do
         insert into line(plant_cd, line_cd, line_nm) values(i, k, concat(k, '라인'));
         set j = j + 1;
         set k = k + 1;
         end while;
      set i = i + 1;
      set j = 1;
   end while;
end;


-- 공정 데이터
delete from process;
delimiter //
begin not atomic
	declare i int default 1;
	declare j int default 1;
	declare k int default 1;
	while i <= 2 do
		while j <= 3 do
			insert into process(plant_cd, line_cd, process_cd, process_nm, use_type)
			values(i, k, concat('cut_',k), '커팅', '생산');
			insert into process(plant_cd, line_cd, process_cd, process_nm, use_type)
			values(i, k, concat('drill_',k), '드릴링', '생산');
			insert into process(plant_cd, line_cd, process_cd, process_nm, use_type)
			values(i, k, concat('assem_',k), '조립', '생산');
			insert into process(plant_cd, line_cd, process_cd, process_nm, use_type)
			values(i, k, concat('qc_cut_',k), '수치검사', '검사');
			insert into process(plant_cd, line_cd, process_cd, process_nm, use_type)
			values(i, k, concat('qc_drill_',k), '홀검사', '검사');
			set j = j + 1;
			set k = k + 1;
		end while;
		set i = i + 1;
		set j = 1;
	end while;
end;


-- 품목 데이터
delete from item;
delimiter //
begin not atomic
	declare i int default 1;
	declare j int default 1;
	declare k int default 1;
	while i <= 2 do
		while j <= 3 do
			case
				when i = 1 then
					insert into item(item_cd, cust_cd, item_type, item_nm)
					values(k, k, '제품', concat('자동차부품_',j));
				else
					insert into item(item_cd, cust_cd, item_type, item_nm)
					values(k, k, '자재', concat('자재_',j));
			end case;
			set j = j + 1;
			set k = k + 1;
			end while;
		set i = i + 1;
		set j = 1;
	end while;
end;



-- 설비 데이터
delete from equipment;
ALTER TABLE equipment AUTO_INCREMENT=1;
delimiter //
begin not atomic
	declare i int default 1;
	declare j int default 1;
	declare k int default 1;
	declare l int;
	while i <= 2 do
		while j <= 3 do
			case when k <= 2 then set l = 1;
					 when k <= 4 then set l = 2;
					 else set l = 3;
			end case;
			insert into equipment(plant_cd, line_cd, process_cd, equip_cd, equip_nm, equip_model, use_type)
			values(i, k, concat('cut_',k), 'eq_cut', concat('커팅머신',k,'호기'), concat('CM-',l), '생산');
			insert into equipment(plant_cd, line_cd, process_cd, equip_cd, equip_nm, equip_model, use_type)
			values(i, k, concat('drill_',k), 'eq_drill', concat('드릴링머신',k,'호기'), concat('DM-',l), '생산');
			insert into equipment(plant_cd, line_cd, process_cd, equip_cd, equip_nm, equip_model, use_type)
			values(i, k, concat('assem_',k), 'eq_assem', concat('조립로봇',k,'호기'), concat('AR-',l), '생산');
			insert into equipment(plant_cd, line_cd, process_cd, equip_cd, equip_nm, equip_model, use_type)
			values(i, k, concat('qc_cut_',k), 'eq_qc_cut', concat('치수검사기',k,'호기'), concat('DMI-',l), '검사');
			insert into equipment(plant_cd, line_cd, process_cd, equip_cd, equip_nm, equip_model, use_type)
			values(i, k, concat('qc_drill_',k), 'eq_qc_drill', concat('홀검사기',k,'호기'), concat('HMI-',l), '검사');
			set j = j + 1;
			set k = k + 1;
		end while;
		set i = i + 1;
		set j = 1;
	end while;
end;


-- 설비에러 데이터
delete from error;
insert into error(error_cd, error_msg, error_gd, down_dr) values(1,'과전류 Trip', 'B', 30);
insert into error(error_cd, error_msg, error_gd, down_dr) values(2,'과전압 Trip', 'B', 30);
insert into error(error_cd, error_msg, error_gd, down_dr) values(3,'EXT-A', 'B', 30);
insert into error(error_cd, error_msg, error_gd, down_dr) values(4,'EST', 'B', 30);
insert into error(error_cd, error_msg, error_gd, down_dr) values(5,'COL', 'B', 30);
insert into error(error_cd, error_msg, error_gd, down_dr) values(6,'Ground Fault', 'B', 30);
insert into error(error_cd, error_msg, error_gd, down_dr) values(7,'인버터 과열', 'A', 60);
insert into error(error_cd, error_msg, error_gd, down_dr) values(8,'전동기 과열', 'A', 60);
insert into error(error_cd, error_msg, error_gd, down_dr) values(9,'과부하', 'A', 60);
insert into error(error_cd, error_msg, error_gd, down_dr) values(10,'HW-Diag', 'B', 30);


-- 창고 데이터 : mariadb에 storage engine 이라는 것이 있어서 `storage`로 표현
delete from `storage`;
delimiter //
begin not atomic
	declare i int default 1;
	declare j int default 1;
	declare k int default 1;
	declare l int default 1;
	while i <= 2 do
		insert into `storage`(plant_cd, storage_cd, storage_nm, storage_type, storage_loc)
		values(i, k, concat('자재창고',i), '자재', concat('out_',i));
		set k = k + 1;
		insert into `storage`(plant_cd, storage_cd, storage_nm, storage_type, storage_loc)
		values(i, k, concat('제품창고',i), '제품', concat('out_',i));
		set k = k + 1;
		while j <= 3 do
			insert into `storage`(plant_cd, storage_cd, storage_nm, storage_type, storage_loc)
			values(i, k, concat('자재임시창고',l), '자재', concat('in_',l));
			set k = k + 1;
			insert into `storage`(plant_cd, storage_cd, storage_nm, storage_type, storage_loc)
			values(i, k, concat('제품임시창고',l), '제품', concat('in_',l));
			set k = k + 1;
			set l = l + 1;
			set j = j + 1;
		end while;
		set i = i + 1;
		set j = 1;
	end while;
end;


-- 품목재고현황 데이터
delete from item_stock;
ALTER TABLE item_stock AUTO_INCREMENT=1;
delimiter //
begin not atomic
	declare i int default 1; -- 품목타입(2가지)
	declare j int default 1; -- loop
	declare n int default 1; -- item_cd(max 6)
	declare m int default 1; -- storage_nm(max 6) / 창고 2, 임시 6
	while i <= 2 do
		while j <= 3 do
			case
				when i = 1 then
					insert into item_stock(item_cd, item_type, storage_cd, storage_nm)
					values(n, '제품', (select storage_cd from `storage` where storage_nm = concat('제품창고',m)), concat('제품창고',m));
					set m = m + 1;
					insert into item_stock(item_cd, item_type, storage_cd, storage_nm)
					values(n, '제품', (select storage_cd from `storage` where storage_nm = concat('제품창고',m)), concat('제품창고',m));
				else
					insert into item_stock(item_cd, item_type, storage_cd, storage_nm)
					values(n, '자재', (select storage_cd from `storage` where storage_nm = concat('자재창고',m)), concat('자재창고',m));
					set m = m + 1;
					insert into item_stock(item_cd, item_type, storage_cd, storage_nm)
					values(n, '자재', (select storage_cd from `storage` where storage_nm = concat('자재창고',m)), concat('자재창고',m));
			end case;
			set m = 1;
			while m <= 6 do
				case
					when i = 1 then
						insert into item_stock(item_cd, item_type, storage_cd, storage_nm)
						values(n, '제품', (select storage_cd from `storage` where storage_nm = concat('제품임시창고',m)), concat('제품임시창고',m));
						set m = m + 1;
						insert into item_stock(item_cd, item_type, storage_cd, storage_nm)
						values(n, '제품', (select storage_cd from `storage` where storage_nm = concat('제품임시창고',m)), concat('제품임시창고',m));
					else
						insert into item_stock(item_cd, item_type, storage_cd, storage_nm)
						values(n, '자재', (select storage_cd from `storage` where storage_nm = concat('자재임시창고',m)), concat('자재임시창고',m));
						set m = m + 1;
						insert into item_stock(item_cd, item_type, storage_cd, storage_nm)
						values(n, '자재', (select storage_cd from `storage` where storage_nm = concat('자재임시창고',m)), concat('자재임시창고',m));
				end case;
				set m = m + 1;
			end while;
			set m = 1;
			set n = n + 1;
			set j = j + 1;
		end while;
		set j = 1;
		set i = i + 1;
	end while;
end;


-- 근무자 데이터
delete from worker;
ALTER TABLE worker AUTO_INCREMENT=1;
delimiter //
begin not atomic
	declare i int default 1;
	declare j int default 1;
	declare k int default 1;
	declare n int default 1;
	declare m int default 1;
	while i <= 2 do
		while j <= 3 do
			while n <= 3 do
				case
					when n = 1 then
						insert into worker(plant_cd, line_cd, worker_loc, worker_time, worker_nm)
						values(i, k, "생산", "주간", concat("김생산",m));
						insert into worker(plant_cd, line_cd, worker_loc, worker_time, worker_nm)
						values(i, k, "품질검사", "주간", concat("백검사",m));
					when n = 2 then
						insert into worker(plant_cd, line_cd, worker_loc, worker_time, worker_nm)
						values(i, k, "생산", "주야간", concat("김생산",m));
						insert into worker(plant_cd, line_cd, worker_loc, worker_time, worker_nm)
						values(i, k, "품질검사", "주야간", concat("백검사",m));
					else
						insert into worker(plant_cd, line_cd, worker_loc, worker_time, worker_nm)
						values(i, k, "생산", "야간", concat("김생산",m));
						insert into worker(plant_cd, line_cd, worker_loc, worker_time, worker_nm)
						values(i, k, "품질검사", "야간", concat("백검사",m));
				end case;
				set m = m + 1;
				set n = n + 1;
			end while;
			set n = 1;
			set k = k + 1;
			set j = j + 1;
		end while;
		set j = 1;
		set i = i + 1;
	end while;
end;

-- delete from plant;
-- delete from line;
-- delete from process;
-- delete from item;
-- delete from equipment;
-- delete from error;
-- delete from `storage`;
-- delete from item_stock;
-- delete from worker;