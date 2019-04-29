CREATE OR REPLACE VIEW CV_COMPANYPROJECT_ALL
AS
SELECT t1.id blockId, t1.blockName,
  t3.id companyId, t3.subcompanyName,
  t4.id projectId, t4.projectName
FROM uf_BPCompanyBlock t1
LEFT JOIN uf_BPCompany t2 ON t2.companyBlock=t1.id
LEFT JOIN hrmsubcompany t3 ON t3.id=t2.company
INNER JOIN uf_BPCompanyProject t4 ON t4.company=t2.company
ORDER BY t1.dataorder, t2.dataorder, t4.dataorder;