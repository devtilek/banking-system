CREATE TABLE auth_users (
                            id UUID PRIMARY KEY,
                            email VARCHAR(255) NOT NULL UNIQUE,
                            password_hash VARCHAR(255) NOT NULL,
                            role VARCHAR(20) NOT NULL,
                            status VARCHAR(20) NOT NULL,
                            created_at TIMESTAMP WITH TIME ZONE NOT NULL,
                            updated_at TIMESTAMP WITH TIME ZONE NOT NULL
);

CREATE INDEX idx_auth_users_email
    ON auth_users(email);