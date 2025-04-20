-- Insert an admin user
INSERT INTO dsa_lab.user_account (username, password, email)
VALUES ('admin', 'admin', 'admin@example.com');


-- Get the ID of the newly inserted admin user
WITH admin_user AS (
  SELECT id FROM dsa_lab.user_account WHERE username = 'admin'
)
-- Create a profile for the admin user
INSERT INTO dsa_lab.user_profile (user_id, first_name, last_name)
SELECT (SELECT id FROM admin_user), 'Admin', 'User';