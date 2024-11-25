ComicTales - Online forum za stipove

✔ Implementirano najmanje 3 kontrolera koji obrađuju različite vrste zahtjeva.
✔ Prikazana primjena metoda GET i POST kroz kontrolere za pronalaženje podataka i punjenje obrazaca.
✔ Primijenjen Thymeleaf za uređivanje aplikacije.
✔ Integrirana baza podataka sa MySQL ili PostgreSQL .
✔ Baza podataka ima barem 3 entiteta koje JPA-em se preslikaju u Model-e unutar koda.
✔ Implementirane osnovne CRUD operacije (create, read, update, delete) za upravljanje podacima u bazi.
✔ Implementiran barem jedan obrazac (formu) za unos korisnika (npr. registracija korisnika) i/ili unošenje drugih podataka. (Implementirana za interakciju sa postovima)
✔ Dodana provjera valjanosti i validnosti unošenih polja obrasca (npr. @NotNull, @Size) kako bi se spriječile greške.
✗ Implementirane funkcionalnost prijavljivanja/odjavljivanja korisnika koristeći Spring Security.
✗ Definirane uloge (admin/korisnik) i ograničen pristup određenim dijelovima aplikacije na osnovu uloge korisnika.

Bazu smo konfigurisale pomocu MySql
username=root
password=nestorandom
Query za kreiranje i unos podataka:

CREATE database stripovi_forum;

USE stripovi_forum;

CREATE TABLE korisnici (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('USER', 'ADMIN') DEFAULT 'USER',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE stripovi (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    author VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE postovi (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    comic_id BIGINT NOT NULL,
    content TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES korisnici(id),
    FOREIGN KEY (comic_id) REFERENCES stripovi(id)
);

SHOW DATABASES;
SHOW TABLES;

INSERT INTO korisnici (username, password, role) VALUES 
('admin', 'admin123', 'ADMIN'),
('user', 'password123', 'USER');

INSERT INTO stripovi (title, description, author) VALUES 
('Spider-Man', 'A story about a young superhero.', 'Stan Lee'),
('Batman', 'The dark knight of Gotham.', 'Bob Kane');
('Nana', 'A touching story about love, loss, and music, following two women with the same name, navigating their complicated lives.', 'Ai Yazawa'),
('Uzumaki', 'A chilling horror story about a town cursed by spirals, blending supernatural horror and psychological terror.', 'Junji Ito'),
('Jujutsu Kaisen', 'A thrilling series about a high school student who joins a school of sorcerers to fight dangerous curses, packed with intense battles and supernatural powers.', 'Gege Akutami'),
('Tokyo Ghoul', 'A dark and emotional story of a college student who becomes a half-ghoul after a near-fatal encounter, struggling with his new identity and the dark world of ghouls.', 'Sui Ishida');

INSERT INTO postovi (user_id, comic_id, content) VALUES
(2, 1, 'Nana is such a heart-wrenching story, I love how it balances romance and drama!'),
(2, 2, 'Uzumaki gave me chills! The creepy, spiraling imagery is unforgettable.'),
(2, 3, 'Jujutsu Kaisen is so hype! The fight scenes are next level, and I can’t get enough of Gojo!'),
(2, 4, 'Tokyo Ghoul is such an emotional rollercoaster. Kaneki’s journey is so intense!');

