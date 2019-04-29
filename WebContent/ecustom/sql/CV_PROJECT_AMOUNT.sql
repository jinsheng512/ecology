CREATE OR REPLACE VIEW CV_PROJECT_AMOUNT
AS
SELECT t2.lastyearmonth, t1.accountid,
  nvl(t1.lastplan, 0) lastplan, nvl(t1.lasthappend, 0) lasthappend
FROM uf_BPCompanyPlan_dt4 t1
LEFT JOIN uf_bpcompanyplan t2 ON t2.id=t1.mainid
WHERE t2.status=3