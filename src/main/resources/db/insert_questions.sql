INSERT INTO user_info (id, username)
VALUES ('3f38ead7-7e43-4339-92a0-43408b6d5eb2', 'admin');

INSERT INTO user_info (id, username)
VALUES ('3f38ead7-7e43-4339-92a0-43408b6d5eb3', 'nikola');

INSERT INTO questions (id, text, type)
VALUES (0,'What is the purpose of a debugger in software development?', 'CHOICE');
INSERT INTO questions (id, text, type)
VALUES (1,'What is a closure in programming?', 'FREE_TEXT');
INSERT INTO questions (id, text, type)
VALUES (2,'What is the purpose of inheritance in OOP?', 'FREE_TEXT');
INSERT INTO questions (id, text, type)
VALUES (3,'Why did the programmer quit his job?', 'CHOICE');
INSERT INTO questions (id, text, type)
VALUES (4,'There is a class that is not inherited from the class Object?', 'CHOICE');


INSERT INTO options_answer (question_id, text, is_correct)
VALUES (0, 'To fix errors in code', true),
       (0, 'To improve performance', false),
       (0, 'To write code', false),
       (0, 'To visualize data', false);
INSERT INTO options_answer (question_id, text, is_correct)
VALUES (3, 'He didn''t get arrays', true),
       (3, 'He preferred to work for a living', false),
       (3, 'He didn''t like debuggers', false),
       (3, 'He was tired of fighting with the computer', false);

INSERT INTO free_answer (question_id, answer) VALUES (1, 'function');
INSERT INTO free_answer (question_id, answer) VALUES (2, 'reusability');
INSERT INTO free_answer (question_id, answer) VALUES (4,'no');

INSERT INTO answer_log (USER_ID, QUESTION_ID, OPTION_ID, FREE_ANSWER) VALUES ('3f38ead7-7e43-4339-92a0-43408b6d5eb3', 0, 1, null);
INSERT INTO answer_log (USER_ID, QUESTION_ID, OPTION_ID, FREE_ANSWER) VALUES ('3f38ead7-7e43-4339-92a0-43408b6d5eb3', 1, null, 'function');
INSERT INTO answer_log (USER_ID, QUESTION_ID, OPTION_ID, FREE_ANSWER) VALUES ('3f38ead7-7e43-4339-92a0-43408b6d5eb3', 2, null, 'reusability');
INSERT INTO answer_log (USER_ID, QUESTION_ID, OPTION_ID, FREE_ANSWER) VALUES ('3f38ead7-7e43-4339-92a0-43408b6d5eb3', 3, 5, null);
INSERT INTO answer_log (USER_ID, QUESTION_ID, OPTION_ID, FREE_ANSWER) VALUES ('3f38ead7-7e43-4339-92a0-43408b6d5eb3', 4, null, 'no');

