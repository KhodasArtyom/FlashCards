-- Добавление в таблицы
INSERT INTO flashcards_themes(id, set_name)
VALUES (DEFAULT, 'English.COLORS'),
       (DEFAULT, 'English.NUMBERS');

INSERT INTO flashcards(flashCards_themes_id, question, answer, status_knowledge)
VALUES (1, 'Белый', 'White', false),
       (1, 'Чёрный', 'Black', false),
       (1, 'Оранжевый', 'Orange', false),
       (1, 'Розовый', 'Ping', false),
       (2, '2+2*2', '6', false),
       (2, '3 * 13', '39', false),
       (2, '100 / 25', '4', false);

--Удаление строки из таблицы flashcards-themes
DELETE
FROM flashCards_themes
WHERE id = 2;

--Удаление строки из таблицы flashcards
DELETE
FROM flashcards
WHERE id = 2;

-- Список набора карточек flashcards_themes
SELECT id       AS id,
       set_name AS name
FROM flashCards_themes;

-- Список набора карточек flashcards
SELECT id               AS id,
       question         AS question,
       answer           AS answer,
       status_knowledge AS status_knowledge
FROM flashcards;

--Редактирование списка карточки title
SELECT id       AS id,
       set_name as name
FROM flashCards_themes
WHERE id = 1;

--Редактироване списка карточки flashcards
SELECT id               AS id,
       question         AS question,
       answer           AS answer,
       status_knowledge AS status_knowledge
FROM flashcards
WHERE flashCards_themes_id = 2;

--Список карточек


SELECT id               AS id,
       question         AS question,
       answer           AS answer,
       status_knowledge AS status_knowledge
FROM flashcards
WHERE flashcards_themes_id = 1
  AND NOT flashcards.status_knowledge
  AND flashcards.id > 1
ORDER BY flashcards.id
LIMIT 1;

--Расчет количество изученных карточек из всего списка карточек

SELECT flashCards_themes.id                                              AS id,
       flashCards_themes.set_name                                        AS name,
       count(flashcards.id) FILTER ( WHERE flashcards.status_knowledge ) AS succsess,
       count(flashcards.id)                                              AS global
FROM flashCards_themes
            LEFT JOIN flashcards ON flashCards_themes.id = flashcards.flashCards_themes_id
GROUP BY flashCards_themes.id;


--изменение колонки status_knowledge при успешном выполнении
UPDATE flashcards
SET status_knowledge = true
WHERE id = 1;





