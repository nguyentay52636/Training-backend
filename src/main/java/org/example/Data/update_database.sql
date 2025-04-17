-- Drop existing foreign key constraints
ALTER TABLE ctdt_kehoachdayhoc DROP FOREIGN KEY FK_ctdt_kehoachdayhoc_ctdt_hocphan;
ALTER TABLE ctdt_kehoachmonhom DROP FOREIGN KEY FK_ctdt_kehoachmonhom_ctdt_hocphan;

-- Modify idHocPhan column type in ctdt_kehoachdayhoc
ALTER TABLE ctdt_kehoachdayhoc MODIFY COLUMN idHocPhan VARCHAR(255) NOT NULL;

-- Modify idHocPhan column type in ctdt_kehoachmonhom
ALTER TABLE ctdt_kehoachmonhom MODIFY COLUMN idHocPhan VARCHAR(255) NOT NULL;

-- Update existing data to comma-separated format
UPDATE ctdt_kehoachdayhoc SET idHocPhan = CONCAT(idHocPhan, '');
UPDATE ctdt_kehoachmonhom SET idHocPhan = CONCAT(idHocPhan, '');


-- Remove old columns
ALTER TABLE ctdt_kehoachdayhoc DROP COLUMN idHocPhan;
ALTER TABLE ctdt_kehoachmonhom DROP COLUMN idHocPhan; 