ALTER TABLE doctors
ADD COLUMN active tinyint;
COMMIT;
UPDATE doctors SET active = 1;
COMMIT;