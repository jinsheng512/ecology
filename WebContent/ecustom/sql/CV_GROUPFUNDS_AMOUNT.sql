CREATE OR REPLACE VIEW CV_GROUPFUNDS_AMOUNT
AS
SELECT ACCOUNTID, NVL(SUM(LASTPLAN), 0) LASTPLAN, NVL(SUM(LASTHAPPEND), 0) LASTHAPPEND, NVL(SUM(THISPLAN), 0) THISPLAN, T1.LASTYEARMONTH
FROM UF_BPCOMPANYPLAN_DT1 DT
INNER JOIN UF_BPCOMPANYPLAN T1 ON T1.ID=DT.MAINID WHERE T1.STATUS=3
GROUP BY DT.ACCOUNTID, T1.LASTYEARMONTH
UNION
SELECT ACCOUNTID, NVL(SUM(LASTPLAN), 0) LASTPLAN, NVL(SUM(LASTHAPPEND), 0) LASTHAPPEND, NVL(SUM(THISPLAN), 0) THISPLAN, T1.LASTYEARMONTH
FROM UF_BPCOMPANYPLAN_DT3 DT
INNER JOIN UF_BPCOMPANYPLAN T1 ON T1.ID=DT.MAINID WHERE T1.STATUS=3
GROUP BY DT.ACCOUNTID, T1.LASTYEARMONTH
UNION
SELECT '17', NVL(SUM(LASTPLAN), 0) LASTPLAN, NVL(SUM(LASTHAPPEND), 0) LASTHAPPEND, NVL(SUM(THISPLAN), 0) THISPLAN, T1.LASTYEARMONTH
FROM UF_BPCOMPANYPLAN_DT4 DT
INNER JOIN UF_BPCOMPANYPLAN T1 ON T1.ID=DT.MAINID WHERE T1.STATUS=3
GROUP BY T1.LASTYEARMONTH
UNION
SELECT ACCOUNTID, NVL(SUM(LASTPLAN), 0) LASTPLAN, NVL(SUM(LASTHAPPEND), 0) LASTHAPPEND, NVL(SUM(THISPLAN), 0) THISPLAN, T1.LASTYEARMONTH
FROM UF_BPFINANCING_DT1 DT
INNER JOIN UF_BPFINANCING T1 ON T1.ID=DT.MAINID WHERE T1.STATUS=1
GROUP BY DT.ACCOUNTID, T1.LASTYEARMONTH
UNION
SELECT ACCOUNTID, NVL(SUM(LASTPLAN), 0) LASTPLAN, NVL(SUM(LASTHAPPEND), 0) LASTHAPPEND, NVL(SUM(THISPLAN), 0) THISPLAN, T1.LASTYEARMONTH
FROM UF_BPFINANCING_DT2 DT
INNER JOIN UF_BPFINANCING T1 ON T1.ID=DT.MAINID WHERE T1.STATUS=1
GROUP BY DT.ACCOUNTID, T1.LASTYEARMONTH;