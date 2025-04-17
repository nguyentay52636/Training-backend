-- Drop all existing foreign key constraints first
ALTER TABLE ctdt_kehoachdayhoc DROP FOREIGN KEY FK_ctdt_kehoachdayhoc_ctdt_hocphan;
ALTER TABLE ctdt_kehoachmonhom DROP FOREIGN KEY FK_ctdt_kehoachmonhom_ctdt_hocphan;
ALTER TABLE ctdt_khoikienthuc DROP FOREIGN KEY FK_ctdt_khoikienthuc_ctct_kienthuc;
ALTER TABLE ctdt_kienthuc DROP FOREIGN KEY FK_ctct_kienthuc_ctdt_hocphan;

-- Modify column types for all tables
ALTER TABLE ctdt_kehoachdayhoc MODIFY COLUMN idHocPhan VARCHAR(255) NOT NULL;
ALTER TABLE ctdt_kehoachmonhom MODIFY COLUMN idHocPhan VARCHAR(255) NOT NULL;
ALTER TABLE ctdt_khoikienthuc MODIFY COLUMN idKienThuc VARCHAR(255) NOT NULL;
ALTER TABLE ctdt_kienthuc MODIFY COLUMN idHocPhan VARCHAR(255) NOT NULL;

-- Update existing data to comma-separated format
UPDATE ctdt_kehoachdayhoc SET idHocPhan = CONCAT(idHocPhan, '');
UPDATE ctdt_kehoachmonhom SET idHocPhan = CONCAT(idHocPhan, '');
UPDATE ctdt_khoikienthuc SET idKienThuc = CONCAT(idKienThuc, '');
UPDATE ctdt_kienthuc SET idHocPhan = CONCAT(idHocPhan, '');

-- Update KhoiKienThuc relationships
UPDATE ctdt_khoikienthuc SET idKienThuc = '1,2,3' WHERE idKhoiKienThuc = 21;
UPDATE ctdt_khoikienthuc SET idKienThuc = '2,3,4' WHERE idKhoiKienThuc = 22;
UPDATE ctdt_khoikienthuc SET idKienThuc = '3,4,5' WHERE idKhoiKienThuc = 23;
UPDATE ctdt_khoikienthuc SET idKienThuc = '4,5,1' WHERE idKhoiKienThuc = 24;
UPDATE ctdt_khoikienthuc SET idKienThuc = '5,1,2' WHERE idKhoiKienThuc = 25;

-- Update KienThuc relationships
UPDATE ctdt_kienthuc SET idHocPhan = '1,2' WHERE idKienThuc = 1;
UPDATE ctdt_kienthuc SET idHocPhan = '2,3' WHERE idKienThuc = 2;
UPDATE ctdt_kienthuc SET idHocPhan = '3,4' WHERE idKienThuc = 3;
UPDATE ctdt_kienthuc SET idHocPhan = '4,5' WHERE idKienThuc = 4;
UPDATE ctdt_kienthuc SET idHocPhan = '5,1' WHERE idKienThuc = 5;

-- Remove redundant columns
ALTER TABLE ctdt_kehoachdayhoc DROP COLUMN idHocPhan;
ALTER TABLE ctdt_kehoachmonhom DROP COLUMN idHocPhan; 