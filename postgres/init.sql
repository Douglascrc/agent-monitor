CREATE USER monitor SUPERUSER PASSWORD 'pass1234';
CREATE DATABASE "monitoring"
    WITH OWNER = postgres
    ENCODING = 'UTF8'
    TABLESPACE = pg_default
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
GRANT ALL PRIVILEGES ON DATABASE monitoring TO monitor;