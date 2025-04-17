-- Drop existing foreign key constraints
ALTER TABLE ctdt_khoikienthuc DROP FOREIGN KEY FK_ctdt_khoikienthuc_ctct_kienthuc;
ALTER TABLE ctdt_kienthuc DROP FOREIGN KEY FK_ctct_kienthuc_ctdt_hocphan;

-- Modify idKienThuc column type in ctdt_khoikienthuc
ALTER TABLE ctdt_khoikienthuc MODIFY COLUMN idKienThuc VARCHAR(255) NOT NULL;

-- Modify idHocPhan column type in ctdt_kienthuc
ALTER TABLE ctdt_kienthuc MODIFY COLUMN idHocPhan VARCHAR(255) NOT NULL;

-- Update existing data to comma-separated format
UPDATE ctdt_khoikienthuc SET idKienThuc = CONCAT(idKienThuc, '');
UPDATE ctdt_kienthuc SET idHocPhan = CONCAT(idHocPhan, '');

-- Insert sample data with multiple IDs
UPDATE ctdt_khoikienthuc SET idKienThuc = '1,2,3' WHERE idKhoiKienThuc = 21;
UPDATE ctdt_khoikienthuc SET idKienThuc = '2,3,4' WHERE idKhoiKienThuc = 22;
UPDATE ctdt_khoikienthuc SET idKienThuc = '3,4,5' WHERE idKhoiKienThuc = 23;
UPDATE ctdt_khoikienthuc SET idKienThuc = '4,5,1' WHERE idKhoiKienThuc = 24;
UPDATE ctdt_khoikienthuc SET idKienThuc = '5,1,2' WHERE idKhoiKienThuc = 25;

UPDATE ctdt_kienthuc SET idHocPhan = '1,2' WHERE idKienThuc = 1;
UPDATE ctdt_kienthuc SET idHocPhan = '2,3' WHERE idKienThuc = 2;
UPDATE ctdt_kienthuc SET idHocPhan = '3,4' WHERE idKienThuc = 3;
UPDATE ctdt_kienthuc SET idHocPhan = '4,5' WHERE idKienThuc = 4;
UPDATE ctdt_kienthuc SET idHocPhan = '5,1' WHERE idKienThuc = 5; 