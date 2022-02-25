-- 더미데이터
INSERT INTO article(title, content) VALUES ('AAAA', '1111');
INSERT INTO article(title, content) VALUES ('BBBB', '3333');
INSERT INTO article(title, content) VALUES ('CCCC', '5555');

-- 댓글 더미데이터
INSERT INTO comment(nickname, body, article_id) VALUES ('Alex', 'AAAA??', 1);
INSERT INTO comment(nickname, body, article_id) VALUES ('Proom', 'What?', 1);
INSERT INTO comment(nickname, body, article_id) VALUES ('Sugar', 'BBBB??', 2);
INSERT INTO comment(nickname, body, article_id) VALUES ('Low', 'CCCC??', 3);