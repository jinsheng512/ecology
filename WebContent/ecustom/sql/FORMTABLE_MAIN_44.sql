-- 薪酬发放审批表单
ALTER TABLE FORMTABLE_MAIN_44 MODIFY shenqr INTEGER;
ALTER TABLE FORMTABLE_MAIN_44 ADD entries INTEGER;
ALTER TABLE FORMTABLE_MAIN_44 ADD entriesName INTEGER;

ALTER TABLE FORMTABLE_MAIN_44_DT1 MODIFY zhiyxm VARCHAR2(20);
ALTER TABLE FORMTABLE_MAIN_44_DT1 MODIFY bummc VARCHAR2(20);