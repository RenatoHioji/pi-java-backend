CREATE TABLE IF NOT EXISTS users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username VARCHAR(255) UNIQUE,
    password VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS quizzes(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nivel INT
);

CREATE TABLE games (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    correct_answer INT,
    type INT,
    quiz_id UUID REFERENCES quizzes(id)
);

CREATE TABLE IF NOT EXISTS items (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255),
    syllables VARCHAR(255),
    image VARCHAR(255),
    video VARCHAR(255),
    audio VARCHAR(255),
    category VARCHAR(255),
    subcategory VARCHAR(255),
    user_id UUID REFERENCES users(id),
    game_id UUID REFERENCES games(id)
    );

CREATE TABLE game_item (
    game_id UUID REFERENCES games(id) ON DELETE CASCADE,
    item_id UUID REFERENCES items(id) ON DELETE CASCADE,
    PRIMARY KEY (game_id, item_id)
);

CREATE TABLE user_history (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    item_id UUID NOT NULL,
    viewed int,
    last_viewed TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (item_id) REFERENCES items(id),
    CONSTRAINT uq_user_item UNIQUE (user_id, item_id)
);
