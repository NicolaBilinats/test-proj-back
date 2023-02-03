INSERT INTO user_info (id, username)
VALUES ('3f38ead7-7e43-4339-92a0-43408b6d5eb2', 'admin');

INSERT INTO questions (id, text, type)
VALUES (0,'What is the purpose of a debugger in software development?', 'CHOICE');

INSERT INTO options_answer (question_id, text, is_correct)
VALUES (0, 'To fix errors in code', true),
       (0
       , 'To improve performance', false),
       (0, 'To write code', false),
       (0, 'To visualize data', false);

INSERT INTO questions (id, text, type)
VALUES (1,'What is a closure in programming?', 'FREE_TEXT');

INSERT INTO free_answer (question_id, answer) VALUES (1, 'Function');

INSERT INTO questions (id, text, type)
VALUES (2,'What is the purpose of inheritance in OOP?', 'FREE_TEXT');

INSERT INTO free_answer (question_id, answer) VALUES (2, 'Reusability');

INSERT INTO questions (id, text, type)
VALUES (3,'Why did the programmer quit his job?', 'CHOICE');

INSERT INTO options_answer (question_id, text, is_correct)
VALUES (3, 'He didn''t get arrays', true),
       (3, 'He preferred to work for a living', false),
       (3, 'He didn''t like debuggers', false),
       (3, 'He was tired of fighting with the computer', false);
