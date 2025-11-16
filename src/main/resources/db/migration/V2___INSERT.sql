CREATE EXTENSION IF NOT EXISTS pgcrypto;

WITH inserted_quizzes AS (
    INSERT INTO quizzes (id, nivel)
    VALUES
        (gen_random_uuid(), 0),
        (gen_random_uuid(), 1),
        (gen_random_uuid(), 2)
    RETURNING id, nivel
),
inserted_games AS (
    INSERT INTO games (id, correct_answer, type, quiz_id)
    VALUES
        (gen_random_uuid(), 0, 0, (SELECT id FROM inserted_quizzes WHERE nivel = 0 LIMIT 1)),
        (gen_random_uuid(), 2, 0, (SELECT id FROM inserted_quizzes WHERE nivel = 1 LIMIT 1)),
        (gen_random_uuid(), 1, 0, (SELECT id FROM inserted_quizzes WHERE nivel = 2 LIMIT 1)),
        (gen_random_uuid(), 1, 2, (SELECT id FROM inserted_quizzes WHERE nivel = 0 LIMIT 1)),
        (gen_random_uuid(), 3, 0, (SELECT id FROM inserted_quizzes WHERE nivel = 1 LIMIT 1)),
        (gen_random_uuid(), 2, 1, (SELECT id FROM inserted_quizzes WHERE nivel = 2 LIMIT 1))
    RETURNING id
),
inserted_items AS (
    INSERT INTO items (id, name, syllables, image, video, audio, category, subcategory)
    VALUES
        (gen_random_uuid(), 'Me', 'Me', 'eu.webp', 'eu.mp4', 'eu.mp3', 'person', 'pronouns'),
        (gen_random_uuid(), 'You', 'You', 'voce.webp', 'voce.mp4', 'voce.mp3', 'person', 'pronouns'),
        (gen_random_uuid(), 'Friend', 'Friend', 'amigo.webp', 'amigo.mp4', 'amigo.mp3', 'person', 'acquaintance'),
        (gen_random_uuid(), 'Eat', 'Eat', 'comer.webp', 'comer.mp4', 'comer.mp3', 'action', 'verbs'),
        (gen_random_uuid(), 'Drink', 'Drink', 'beber.webp', 'beber.mp4', 'beber.mp3', 'action', 'verbs'),
        (gen_random_uuid(), 'Play', 'Play', 'brincar.webp', 'brincar.mp4', 'brincar.mp3', 'action', 'verbs'),
        (gen_random_uuid(), 'Sleep', 'Sleep', 'dormir.webp', 'dormir.mp4', 'dormir.mp3', 'action', 'verbs'),
        (gen_random_uuid(), 'Run', 'Run', 'correr.webp', 'correr.mp4', 'correr.mp3', 'action', 'verbs'),
        (gen_random_uuid(), 'Jump', 'Jump', 'pular.webp', 'pular.mp4', 'pular.mp3', 'action', 'verbs'),
        (gen_random_uuid(), 'Read', 'Read', 'ler.webp', 'ler.mp4', 'ler.mp3', 'action', 'verbs'),
        (gen_random_uuid(), 'Feel', 'Feel', 'sinto.webp', 'sinto.mp4', 'sinto.mp3', 'action', 'verbs'),
        (gen_random_uuid(), 'Draw', 'Draw', 'desenhar.webp', 'desenhar.mp4', 'desenhar.mp3', 'action', 'verbs'),
        (gen_random_uuid(), 'Write', 'Write', 'escrever.webp', 'escrever.mp4', 'escrever.mp3', 'action', 'verbs'),
        (gen_random_uuid(), 'Laugh', 'Laugh', 'rir.webp', 'rir.mp4', 'rir.mp3', 'action', 'verbs'),
        (gen_random_uuid(), 'Sing', 'Sing', 'cantar.webp', 'cantar.mp4', 'cantar.mp3', 'action', 'verbs'),
        (gen_random_uuid(), 'Happy', 'Hap-py', 'feliz.webp', 'feliz.mp4', 'feliz.mp3', 'emotion', 'feelings'),
        (gen_random_uuid(), 'Sad', 'Sad', 'triste.webp', 'triste.mp4', 'triste.mp3', 'emotion', 'feelings'),
        (gen_random_uuid(), 'Angry', 'An-gry', 'bravo.webp', 'bravo.mp4', 'bravo.mp3', 'emotion', 'feelings'),
        (gen_random_uuid(), 'Love', 'Love', 'amor.webp', 'amor.mp4', 'amor.mp3', 'emotion', 'feelings'),
        (gen_random_uuid(), 'Hunger', 'Hun-ger', 'fome.webp', 'fome.mp4', 'fome.mp3', 'need', 'sensations'),
        (gen_random_uuid(), 'Want', 'Want', 'quero.webp', 'quero.mp4', 'quero.mp3', 'need', 'sensations'),
        (gen_random_uuid(), 'Dog', 'Dog', 'cachorro.webp', 'cachorro.mp4', 'cachorro.mp3', 'animal', 'mammals'),
        (gen_random_uuid(), 'Cat', 'Cat', 'gato.webp', 'gato.mp4', 'gato.mp3', 'animal', 'mammals'),
        (gen_random_uuid(), 'Ball', 'Ball', 'bola.webp', 'bola.mp4', 'bola.mp3', 'object', 'sports'),
        (gen_random_uuid(), 'Car', 'Car', 'carro.webp', 'carro.mp4', 'carro.mp3', 'object', 'transport'),
        (gen_random_uuid(), 'Cookie', 'Coo-kie', 'biscoito.webp', 'biscoito.mp4', 'biscoito.mp3', 'food', 'foods'),
        (gen_random_uuid(), 'Wood', 'Wood', 'madeira.webp', 'madeira.mp4', 'madeira.mp3', 'material', 'elements'),
        (gen_random_uuid(), 'Duck', 'Duck', 'pato.webp', 'pato.mp4', 'pato.mp3', 'animal', 'birds'),
        (gen_random_uuid(), 'Yes', 'Yes', 'sim.webp', 'sim.mp4', 'sim.mp3', 'response', 'affirmative'),
        (gen_random_uuid(), 'Grape', 'Grape', 'uva.webp', 'uva.mp4', 'uva.mp3', 'food', 'fruits'),
        (gen_random_uuid(), 'Apple', 'Ap-ple', 'maca.webp', 'maca.mp4', 'maca.mp3', 'food', 'fruits'),
        (gen_random_uuid(), 'Lemon', 'Le-mon', 'limao.webp', 'limao.mp4', 'limao.mp3', 'food', 'fruits'),
        (gen_random_uuid(), 'Pineapple', 'Pine-ap-ple', 'abacaxi.webp', 'abacaxi.mp4', 'abacaxi.mp3', 'food', 'fruits')
    RETURNING id
)
, numbered_items AS (
    SELECT id, ROW_NUMBER() OVER () AS item_row_number
    FROM inserted_items
),
numbered_games AS (
    SELECT id, ROW_NUMBER() OVER () AS game_row_number
    FROM inserted_games
)
INSERT INTO game_item (game_id, item_id)
SELECT g.id, i.id
FROM numbered_games g
JOIN numbered_items i
ON (i.item_row_number - 1) / 4 = g.game_row_number - 1;
