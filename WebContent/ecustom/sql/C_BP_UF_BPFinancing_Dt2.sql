CREATE VIEW C_BP_UF_BPFINANCING_DT2
AS
SELECT DT2."ID",DT2."MAINID",DT2."ACCOUNTID",DT2."LASTPLAN",DT2."LASTHAPPEND",DT2."THISPLAN",DT2."DIFFERENCE",DT2."REMARKS", ACCDT2.ACCOUNTNAME FROM UF_BPFINANCING_DT2 DT2
INNER JOIN UF_BPACCOUNT_DT1 ACCDT2 ON ACCDT2.ID=DT2.ACCOUNTID
