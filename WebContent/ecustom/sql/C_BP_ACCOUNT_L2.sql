CREATE OR REPLACE VIEW C_BP_ACCOUNT_L2
AS
SELECT DT1.ID, DT1.ACCOUNTNAME, DT1.ACCOUNTCODE, T1.ACCOUNTNAME ACCOUNTNAMEL1 
FROM UF_BPACCOUNT_DT1 DT1, UF_BPACCOUNT T1 
WHERE T1.ID=DT1.MAINID