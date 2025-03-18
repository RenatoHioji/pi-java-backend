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
        (gen_random_uuid(), 'Eu', 'Eu', 'eu.webp', 'eu.mp4', 'eu.mp3', 'pessoa', 'pronomes'),
        (gen_random_uuid(), 'Você', 'Vo-cê', 'voce.webp', 'voce.mp4', 'voce.mp3', 'pessoa', 'pronomes'),
        (gen_random_uuid(), 'Amigo', 'A-mi-go', 'amigo.webp', 'amigo.mp4', 'amigo.mp3', 'pessoa', 'conhecido'),
        (gen_random_uuid(), 'Comer', 'Co-mer', 'comer.webp', 'comer.mp4', 'comer.mp3', 'acao', 'verbos'),
        (gen_random_uuid(), 'Beber', 'Be-ber', 'beber.webp', 'beber.mp4', 'beber.mp3', 'acao', 'verbos'),
        (gen_random_uuid(), 'Brincar', 'Brin-car', 'brincar.webp', 'brincar.mp4', 'brincar.mp3', 'acao', 'verbos'),
        (gen_random_uuid(), 'Dormir', 'Dor-mir', 'dormir.webp', 'dormir.mp4', 'dormir.mp3', 'acao', 'verbos'),
        (gen_random_uuid(), 'Correr', 'Cor-rer', 'correr.webp', 'correr.mp4', 'correr.mp3', 'acao', 'verbos'),
        (gen_random_uuid(), 'Pular', 'Pu-lar', 'pular.webp', 'pular.mp4', 'pular.mp3', 'acao', 'verbos'),
        (gen_random_uuid(), 'Ler', 'Ler', 'ler.webp', 'ler.mp4', 'ler.mp3', 'acao', 'verbos'),
        (gen_random_uuid(), 'Sinto', 'Sin-to', 'sinto.webp', 'sinto.mp4', 'sinto.mp3', 'acao', 'verbos'),
        (gen_random_uuid(), 'Desenhar', 'De-se-nhar', 'desenhar.webp', 'desenhar.mp4', 'desenhar.mp3', 'acao', 'verbos'),
        (gen_random_uuid(), 'Escrever', 'Es-cre-ver', 'escrever.webp', 'escrever.mp4', 'escrever.mp3', 'acao', 'verbos'),
        (gen_random_uuid(), 'Rir', 'Rir', 'rir.webp', 'rir.mp4', 'rir.mp3', 'acao', 'verbos'),
        (gen_random_uuid(), 'Cantar', 'Can-tar', 'cantar.webp', 'cantar.mp4', 'cantar.mp3', 'acao', 'verbos'),
        (gen_random_uuid(), 'Feliz', 'Fe-liz', 'feliz.webp', 'feliz.mp4', 'feliz.mp3', 'emocao', 'sentimentos'),
        (gen_random_uuid(), 'Triste', 'Tris-te', 'triste.webp', 'triste.mp4', 'triste.mp3', 'emocao', 'sentimentos'),
        (gen_random_uuid(), 'Bravo', 'Bra-vo', 'bravo.webp', 'bravo.mp4', 'bravo.mp3', 'emocao', 'sentimentos'),
        (gen_random_uuid(), 'Amor', 'A-mor', 'amor.webp', 'amor.mp4', 'amor.mp3', 'emocao', 'sentimentos'),
        (gen_random_uuid(), 'Fome', 'Fo-me', 'fome.webp', 'fome.mp4', 'fome.mp3', 'necessidade', 'sensacoes'),
        (gen_random_uuid(), 'Quero', 'Que-ro', 'quero.webp', 'quero.mp4', 'quero.mp3', 'necessidade', 'sensacoes'),
        (gen_random_uuid(), 'Cachorro', 'Ca-chor-ro', 'cachorro.webp', 'cachorro.mp4', 'cachorro.mp3', 'animal', 'mamiferos'),
        (gen_random_uuid(), 'Gato', 'Ga-to', 'gato.webp', 'gato.mp4', 'gato.mp3', 'animal', 'mamiferos'),
        (gen_random_uuid(), 'Bola', 'Bo-la', 'bola.webp', 'bola.mp4', 'bola.mp3', 'objeto', 'esportes'),
        (gen_random_uuid(), 'Carro', 'Car-ro', 'carro.webp', 'carro.mp4', 'carro.mp3', 'objeto', 'transportes'),
        (gen_random_uuid(), 'Biscoito', 'Bis-coi-to', 'biscoito.webp', 'biscoito.mp4', 'biscoito.mp3', 'alimento', 'alimentos'),
        (gen_random_uuid(), 'Madeira', 'Ma-dei-ra', 'madeira.webp', 'madeira.mp4', 'madeira.mp3', 'material', 'elementos'),
        (gen_random_uuid(), 'Pato', 'Pa-to', 'pato.webp', 'pato.mp4', 'pato.mp3', 'animal', 'aves'),
        (gen_random_uuid(), 'Sim', 'Sim', 'sim.webp', 'sim.mp4', 'sim.mp3', 'resposta', 'afirmativas'),
        (gen_random_uuid(), 'Uva', 'U-va', 'uva.webp', 'uva.mp4', 'uva.mp3', 'alimento', 'frutas')
    RETURNING id
)
-- Assign row numbers to each game and item
, numbered_items AS (
    SELECT id, ROW_NUMBER() OVER () AS item_row_number
    FROM inserted_items
),
numbered_games AS (
    SELECT id, ROW_NUMBER() OVER () AS game_row_number
    FROM inserted_games
)
-- Insert 4 items per game
INSERT INTO game_item (game_id, item_id)
SELECT g.id, i.id
FROM numbered_games g
JOIN numbered_items i
ON (i.item_row_number - 1) / 4 = g.game_row_number - 1;
