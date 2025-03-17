CREATE TABLE IF NOT EXISTS users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username VARCHAR(255) UNIQUE,
    password VARCHAR(255)
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
    user_id UUID REFERENCES users(id)
);