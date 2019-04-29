CREATE VIEW C_BP_COMPANY
AS
SELECT COMPANY, COMPANYBLOCK, T2.BLOCKNAME, T3.SUBCOMPANYNAME,T1.SUBMITENDDATE
FROM UF_BPCOMPANY T1
INNER JOIN UF_BPCOMPANYBLOCK T2 ON T2.ID=T1.COMPANYBLOCK
INNER JOIN HRMSUBCOMPANY T3 ON T3.ID=T1.COMPANY
