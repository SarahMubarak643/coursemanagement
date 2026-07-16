-- Demo users for database-backed authentication.
-- All three use the password: password123
-- Passwords below are real BCrypt hashes (Spring's BCryptPasswordEncoder can verify them).
-- INSERT IGNORE makes this safe to run again on every application restart.

INSERT IGNORE INTO users (username, password, enabled) VALUES
('employee1', '$2b$12$F0GdMR2NI.Vf/vQPxDe0zOlWrT/5gMeLsuJBkPYGxAWT.YQfnYRn.', true),
('manager1',  '$2b$12$aLdOFLWif4hzdOf9qpobiOXI5yYjUjporR/tTIWsWNQbLyjCpApg2', true),
('admin1',    '$2b$12$nfNyt5K42NAijk07kAVgROHhvf7qr.SZWTwl7OXMfv.ZAr9qTricO', true);

INSERT IGNORE INTO authorities (username, authority) VALUES
('employee1', 'ROLE_EMPLOYEE'),
('manager1',  'ROLE_MANAGER'),
('admin1',    'ROLE_ADMIN');

-- A few sample courses so the app is not empty on first run.
INSERT IGNORE INTO courses (id, name, category, price, duration_hours, level, active, code) VALUES
(1, 'Introduction to Java', 'Programming', 199.99, 30, 'BEGINNER', true, 'CSE-101'),
(2, 'Advanced Spring Boot', 'Programming', 349.50, 40, 'ADVANCED', true, 'CSE-450'),
(3, 'Database Design Fundamentals', 'Database', 249.00, 25, 'INTERMEDIATE', true, 'MAT-202');
