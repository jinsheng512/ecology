CREATE VIEW C_BP_COMPANYACCOUNT
AS
SELECT T1.ACCOUNTL1, T1.ACCOUNTL2, T2.ACCOUNTNAME, T1.COMPANY, T2.DATAORDER
FROM UF_BPCOMPANYACCOUNT T1 INNER JOIN UF_BPACCOUNT_DT1 T2 ON T2.ID=T1.ACCOUNTL2
ORDER BY T2.DATAORDER