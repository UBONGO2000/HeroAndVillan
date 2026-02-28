-- =====================================================
-- Heroes & Villains Blog - Sample Data for H2 Database
-- =====================================================

-- Clean existing data (in reverse order of dependencies)
DELETE FROM comment;
DELETE FROM blog;
DELETE FROM anti_hero;
DELETE FROM villain;
DELETE FROM hero;
DELETE FROM category;
DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM roles;

-- =====================================================
-- ROLES (for Spring Security)
-- =====================================================
INSERT INTO roles (id, name, description) VALUES
('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa1', 'ROLE_ADMIN', 'Administrator with full access'),
('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa2', 'ROLE_MODERATOR', 'Moderator with limited admin access'),
('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa3', 'ROLE_USER', 'Standard user with read access');

-- =====================================================
-- USERS (for Spring Security)
-- Passwords are BCrypt encoded: password = $2a$10$N9qo8uLOickgx2ZMRZoMy.MqrqNzZqcLKZtR6L0h/HhT9fX8y5w2G
-- =====================================================
INSERT INTO users (id, username, email, password, active, created_at, updated_at) VALUES
('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb1', 'admin', 'admin@heroes.com', '$2a$10$N9qo8uLOickgx2ZMRZoMy.MqrqNzZqcLKZtR6L0h/HhT9fX8y5w2G', true, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb2', 'user', 'user@heroes.com', '$2a$10$N9qo8uLOickgx2ZMRZoMy.MqrqNzZqcLKZtR6L0h/HhT9fX8y5w2G', true, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb3', 'moderator', 'moderator@heroes.com', '$2a$10$N9qo8uLOickgx2ZMRZoMy.MqrqNzZqcLKZtR6L0h/HhT9fX8y5w2G', true, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

-- =====================================================
-- USER_ROLES (Link users to roles)
-- =====================================================
INSERT INTO user_roles (user_id, role_id) VALUES
('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb1', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa1'),
('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb1', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa3'),
('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb2', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa3'),
('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb3', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa2'),
('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb3', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa3');

-- =====================================================
-- CATEGORIES
-- =====================================================
INSERT INTO category (id, name, description, created_at, updated_at) VALUES
('11111111-1111-1111-1111-111111111111', 'Marvel', 'Marvel Universe characters - Spider-Man, Iron Man, Avengers, X-Men and more', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('22222222-2222-2222-2222-222222222222', 'DC', 'DC Universe characters - Batman, Superman, Wonder Woman, Justice League and more', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('33333333-3333-3333-3333-333333333333', 'Anime', 'Anime and Manga characters - Dragon Ball, Naruto, One Piece, My Hero Academia', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('44444444-4444-4444-4444-444444444444', 'Dark Horse', 'Dark Horse Comics - Hellboy, The Mask, Umbrella Academy', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('55555555-5555-5555-5555-555555555555', 'Image Comics', 'Image Comics - Spawn, The Walking Dead, Invincible', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

-- =====================================================
-- HEROES
-- =====================================================
INSERT INTO hero (id, first_name, last_name, hero_name, power, affiliation, rating, image_url, biography, category_id, created_at, updated_at) VALUES
-- Marvel Heroes
('10000001-0000-0000-0000-000000000001', 'Peter', 'Parker', 'Spider-Man', 'Spider abilities, web-slinging, wall-crawling, spider-sense', 'Avengers, Fantastic Four', 4.8, 'https://example.com/images/spiderman.jpg', 'Bitten by a radioactive spider during a science exhibition, high school student Peter Parker gained the proportionate strength, speed, and agility of a spider, along with the ability to cling to walls.', '11111111-1111-1111-1111-111111111111', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('10000001-0000-0000-0000-000000000002', 'Tony', 'Stark', 'Iron Man', 'Genius-level intellect, powered armor suit with flight, repulsors, and various weapons', 'Avengers, S.H.I.E.L.D.', 4.9, 'https://example.com/images/ironman.jpg', 'A billionaire industrialist and genius inventor, Tony Stark was captured and forced to build a weapon of mass destruction. Instead, he built a suit of armor to escape and later used it to protect the world.', '11111111-1111-1111-1111-111111111111', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('10000001-0000-0000-0000-000000000003', 'Steve', 'Rogers', 'Captain America', 'Super soldier serum enhanced strength, speed, durability, and reflexes; master tactician', 'Avengers, S.H.I.E.L.D.', 4.9, 'https://example.com/images/captainamerica.jpg', 'During World War II, frail Steve Rogers volunteered for a top-secret military experiment. The Super Soldier Serum transformed him into the peak of human physical perfection.', '11111111-1111-1111-1111-111111111111', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('10000001-0000-0000-0000-000000000004', 'Thor', 'Odinson', 'Thor', 'Asgardian physiology, Mjolnir (enchanted hammer), control over lightning and storms', 'Avengers, Asgard', 4.7, 'https://example.com/images/thor.jpg', 'The Asgardian God of Thunder, Thor is one of the most powerful Avengers. He wields Mjolnir, a magical hammer that grants him the ability to fly and control lightning.', '11111111-1111-1111-1111-111111111111', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('10000001-0000-0000-0000-000000000005', 'Bruce', 'Banner', 'Hulk', 'Transforms into a giant green monster with unlimited strength, durability, and healing', 'Avengers, Defenders', 4.6, 'https://example.com/images/hulk.jpg', 'Scientist Bruce Banner was exposed to massive amounts of gamma radiation during a bomb test. Now, when angered or stressed, he transforms into the Hulk, a creature of immense strength.', '11111111-1111-1111-1111-111111111111', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),

-- DC Heroes
('10000002-0000-0000-0000-000000000001', 'Bruce', 'Wayne', 'Batman', 'Peak human physical and mental condition, master detective, martial artist, billionaire resources', 'Justice League, Batman Family', 4.9, 'https://example.com/images/batman.jpg', 'After witnessing his parents'' murder, Bruce Wayne dedicated his life to fighting crime. He trained extensively in martial arts, detective work, and psychology, becoming Gotham''s Dark Knight.', '22222222-2222-2222-2222-222222222222', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('10000002-0000-0000-0000-000000000002', 'Clark', 'Kent', 'Superman', 'Kryptonian physiology under yellow sun: super strength, speed, flight, heat vision, freeze breath, invulnerability', 'Justice League, Daily Planet', 5.0, 'https://example.com/images/superman.jpg', 'Born Kal-El on the planet Krypton, Superman was sent to Earth as an infant before his planet''s destruction. Raised as Clark Kent in Smallville, he uses his incredible powers to protect humanity.', '22222222-2222-2222-2222-222222222222', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('10000002-0000-0000-0000-000000000003', 'Diana', 'Prince', 'Wonder Woman', 'Amazonian physiology, super strength, flight, combat skill, Lasso of Truth, indestructible bracelets', 'Justice League, Amazons of Themyscira', 4.8, 'https://example.com/images/wonderwoman.jpg', 'Diana of Themyscira is an Amazon warrior princess and one of the world''s most powerful heroes. She left her homeland to bring peace to the world of man.', '22222222-2222-2222-2222-222222222222', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('10000002-0000-0000-0000-000000000004', 'Barry', 'Allen', 'The Flash', 'Speed Force connection: super speed, time travel, dimension hopping, accelerated healing', 'Justice League, Flash Family', 4.7, 'https://example.com/images/flash.jpg', 'Police scientist Barry Allen was struck by lightning and doused with chemicals, giving him access to the Speed Force. He can run at incredible speeds and even travel through time.', '22222222-2222-2222-2222-222222222222', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('10000002-0000-0000-0000-000000000005', 'Hal', 'Jordan', 'Green Lantern', 'Power ring capable of creating constructs limited only by willpower and imagination', 'Justice League, Green Lantern Corps', 4.5, 'https://example.com/images/greenlantern.jpg', 'Test pilot Hal Jordan was chosen by the dying alien Abin Sur to wield a power ring. As Green Lantern, he protects Space Sector 2814 as a member of the intergalactic police force.', '22222222-2222-2222-2222-222222222222', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),

-- Anime Heroes
('10000003-0000-0000-0000-000000000001', 'Son', 'Goku', 'Goku', 'Saiyan physiology, Super Saiyan transformations, Ki manipulation, Instant Transmission, Spirit Bomb', 'Z Fighters, Dragon Team', 5.0, 'https://example.com/images/goku.jpg', 'A Saiyan warrior sent to Earth as a baby, Goku forgot his mission of destruction after a head injury. He became Earth''s greatest defender, constantly pushing his limits through training.', '33333333-3333-3333-3333-333333333333', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('10000003-0000-0000-0000-000000000002', 'Naruto', 'Uzumaki', 'Naruto', 'Nine-Tailed Fox chakra, Sage Mode, Rasengan variants, Shadow Clone Jutsu', 'Hidden Leaf Village, Team 7', 4.8, 'https://example.com/images/naruto.jpg', 'Born as the jinchuriki of the Nine-Tailed Fox, Naruto was ostracized by his village. He dreamed of becoming Hokage and worked tirelessly to earn the respect of his peers.', '33333333-3333-3333-3333-333333333333', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('10000003-0000-0000-0000-000000000003', 'Izuku', 'Midoriya', 'Deku', 'One For All quirk: super strength, speed, durability; multiple quirk manifestations', 'U.A. High School, Class 1-A', 4.6, 'https://example.com/images/deku.jpg', 'Born quirkless in a world where 80% of people have superpowers, Izuku was chosen by the legendary hero All Might to inherit the power of One For All.', '33333333-3333-3333-3333-333333333333', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

-- =====================================================
-- VILLAINS
-- =====================================================
INSERT INTO villain (id, first_name, last_name, villain_name, power, affiliation, rating, image_url, biography, house, known_as, category_id, created_at, updated_at) VALUES
-- Marvel Villains
('20000001-0000-0000-0000-000000000001', 'Victor', 'Von Doom', 'Doctor Doom', 'Genius-level intellect, mastery of science and sorcery, powered armor', 'Latveria', 4.8, 'https://example.com/images/drdoom.jpg', 'The monarch of Latveria, Doctor Doom is one of the most powerful and dangerous villains in the Marvel Universe. He combines advanced technology with dark magic.', 'Latveria', 'The Master', '11111111-1111-1111-1111-111111111111', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('20000001-0000-0000-0000-000000000002', 'Erik', 'Lehnsherr', 'Magneto', 'Magnetism manipulation, control over all forms of metal, electromagnetic spectrum control', 'Brotherhood of Mutants', 4.6, 'https://example.com/images/magneto.jpg', 'A Holocaust survivor who believes mutants are superior to humans, Magneto has been both friend and foe to the X-Men. He controls magnetism at a global scale.', 'Genosha', 'Master of Magnetism', '11111111-1111-1111-1111-111111111111', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('20000001-0000-0000-0000-000000000003', 'Loki', 'Laufeyson', 'Loki', 'Asgardian physiology, sorcery, shapeshifting, illusion casting, dagger combat', 'Asgard (former), himself', 4.5, 'https://example.com/images/loki.jpg', 'The God of Mischief and Thor''s adopted brother, Loki uses his mastery of magic and deception to further his own ends, constantly shifting between villain and anti-hero.', 'Asgard', 'God of Mischief', '11111111-1111-1111-1111-111111111111', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('20000001-0000-0000-0000-000000000004', 'Thanos', NULL, 'Thanos', 'Titan Eternal physiology, super strength, energy manipulation, genius intellect, Infinity Gauntlet', 'Black Order', 5.0, 'https://example.com/images/thanos.jpg', 'The Mad Titan seeks to bring balance to the universe by eliminating half of all life. His power is unmatched when wielding the Infinity Gauntlet.', 'Titan', 'The Mad Titan', '11111111-1111-1111-1111-111111111111', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('20000001-0000-0000-0000-000000000005', 'Norman', 'Osborn', 'Green Goblin', 'Super strength, enhanced intellect, healing factor, goblin glider, pumpkin bombs', 'Sinister Six, H.A.M.M.E.R.', 4.3, 'https://example.com/images/greengoblin.jpg', 'The CEO of Oscorp, Norman Osborn became the Green Goblin after experimenting with a performance-enhancing serum. He is Spider-Man''s greatest enemy.', 'New York', 'The Green Goblin', '11111111-1111-1111-1111-111111111111', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),

-- DC Villains
('20000002-0000-0000-0000-000000000001', 'Unknown', NULL, 'The Joker', 'Criminal mastermind, chemical engineering expertise, immunity to toxins', 'Injustice League, Legion of Doom', 4.9, 'https://example.com/images/joker.jpg', 'Batman''s arch-nemesis, the Joker is a psychopathic criminal mastermind with a warped sense of humor. His origin is shrouded in mystery, but his impact on Gotham is undeniable.', 'Gotham City', 'The Clown Prince of Crime', '22222222-2222-2222-2222-222222222222', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('20000002-0000-0000-0000-000000000002', 'Alexander', 'Luthor', 'Lex Luthor', 'Genius-level intellect, master strategist, powered armor (warsuit), vast resources', 'LexCorp, Legion of Doom', 4.7, 'https://example.com/images/lexluthor.jpg', 'Superman''s greatest enemy, Lex Luthor uses his genius intellect and vast wealth to try to destroy the Man of Steel. He believes Superman is a threat to humanity.', 'Metropolis', 'The Greatest Criminal Mind', '22222222-2222-2222-2222-222222222222', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('20000002-0000-0000-0000-000000000003', 'Harvey', 'Dent', 'Two-Face', 'Expert marksman, criminal strategist, dual personality decision-making', 'Various criminal organizations', 4.2, 'https://example.com/images/twoface.jpg', 'Former Gotham district attorney Harvey Dent was disfigured by acid, fracturing his psyche. He now makes all decisions by flipping his scarred coin.', 'Gotham City', 'Two-Face', '22222222-2222-2222-2222-222222222222', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('20000002-0000-0000-0000-000000000004', 'Oswald', 'Cobblepot', 'The Penguin', 'Criminal mastermind, umbrella weapons, vast criminal network, business acumen', 'Iceberg Lounge, various gangs', 4.0, 'https://example.com/images/penguin.jpg', 'The Penguin runs the Iceberg Lounge and controls Gotham''s underworld through a combination of legitimate business and criminal enterprise.', 'Gotham City', 'The Penguin', '22222222-2222-2222-2222-222222222222', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('20000002-0000-0000-0000-000000000005', 'Pamela', 'Isley', 'Poison Ivy', 'Toxin immunity, plant control, pheromone manipulation, toxic kiss', 'Gotham City Sirens, Injustice League', 4.4, 'https://example.com/images/poisonivy.jpg', 'A radical environmentalist, Poison Ivy can control plants and produce pheromones that make men fall in love with her. She fights to protect nature at any cost.', 'Gotham City', 'Poison Ivy', '22222222-2222-2222-2222-222222222222', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),

-- Anime Villains
('20000003-0000-0000-0000-000000000001', 'Vegeta', NULL, 'Vegeta', 'Saiyan physiology, Super Saiyan transformations, Galick Gun, Final Flash', 'Frieza Force (former), Z Fighters', 4.9, 'https://example.com/images/vegeta.jpg', 'The Prince of all Saiyans, Vegeta began as a ruthless villain but eventually became one of Earth''s greatest defenders while maintaining his arrogant personality.', 'Planet Vegeta (destroyed)', 'The Prince of Saiyans', '33333333-3333-3333-3333-333333333333', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('20000003-0000-0000-0000-000000000002', 'Frieza', NULL, 'Frieza', 'Multiple transformation forms, planet destruction, energy beams, telekinesis', 'Frieza Force', 4.8, 'https://example.com/images/frieza.jpg', 'An alien tyrant who destroyed Planet Vegeta, Frieza is one of the most powerful beings in the universe. He has multiple forms, each more powerful than the last.', 'Unknown', 'The Emperor of Evil', '33333333-3333-3333-3333-333333333333', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('20000003-0000-0000-0000-000000000003', 'Madara', 'Uchiha', 'Madara', 'Sharingan, Mangekyo Sharingan, Eternal Mangekyo Sharingan, Rinnegan, Susanoo', 'Akatsuki (founder)', 4.7, 'https://example.com/images/madara.jpg', 'A legendary shinobi who founded the Uchiha clan, Madara sought to create a world of infinite dreams through the Infinite Tsukuyomi.', 'Hidden Leaf Village', 'The Ghost of the Uchiha', '33333333-3333-3333-3333-333333333333', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

-- =====================================================
-- ANTI-HEROES
-- =====================================================
INSERT INTO anti_hero (id, first_name, last_name, anti_hero_name, power, affiliation, rating, image_url, biography, house, known_as, category_id, created_at, updated_at) VALUES
-- Marvel Anti-Heroes
('30000001-0000-0000-0000-000000000001', 'Wade', 'Wilson', 'Deadpool', 'Regenerative healing factor, expert martial artist, breaks the fourth wall', 'X-Force, occasionally X-Men', 4.6, 'https://example.com/images/deadpool.jpg', 'A mercenary with a regenerative healing factor that surpasses Wolverine''s, Deadpool is known for his twisted humor and tendency to break the fourth wall.', 'Canada', 'The Merc with a Mouth', '11111111-1111-1111-1111-111111111111', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('30000001-0000-0000-0000-000000000002', 'James', 'Howlett', 'Wolverine', 'Adamantium claws, regenerative healing factor, enhanced senses', 'X-Men, Avengers', 4.8, 'https://example.com/images/wolverine.jpg', 'A mutant with adamantium claws and a powerful healing factor, Wolverine is the best at what he does, but what he does isn''t very nice. He struggles with his berserker rage.', 'Canada', 'Wolverine', '11111111-1111-1111-1111-111111111111', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('30000001-0000-0000-0000-000000000003', 'Frank', 'Castle', 'The Punisher', 'Master marksman, expert combatant, tactical genius, military training', 'None (works alone)', 4.4, 'https://example.com/images/punisher.jpg', 'After his family was murdered by the mob, Frank Castle became The Punisher, a vigilante who wages a one-man war on crime using lethal force.', 'New York', 'The Punisher', '11111111-1111-1111-1111-111111111111', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('30000001-0000-0000-0000-000000000004', 'Eddie', 'Brock', 'Venom', 'Symbiote bonding, super strength, shapeshifting, spider-sense immunity', 'None (lethal protector)', 4.3, 'https://example.com/images/venom.jpg', 'A journalist who bonded with an alien symbiote, Eddie Brock becomes Venom. While often fighting Spider-Man, he also acts as a lethal protector of the innocent.', 'New York', 'The Lethal Protector', '11111111-1111-1111-1111-111111111111', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),

-- DC Anti-Heroes
('30000002-0000-0000-0000-000000000001', 'Floyd', 'Lawton', 'Deadshot', 'Expert marksman, cybernetic eye, weapons expert, tactical genius', 'Suicide Squad', 4.5, 'https://example.com/images/deadshot.jpg', 'The world''s greatest marksman, Deadshot never misses his target. He works as a mercenary and assassin, often forced to work with the Suicide Squad.', 'Gotham City', 'Deadshot', '22222222-2222-2222-2222-222222222222', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('30000002-0000-0000-0000-000000000002', 'Harleen', 'Quinzel', 'Harley Quinn', 'Gymnastics expertise, psychological manipulation, enhanced strength (sometimes)', 'Suicide Squad, Gotham City Sirens', 4.2, 'https://example.com/images/harleyquinn.jpg', 'A former psychiatrist who fell in love with the Joker, Harley Quinn has since struck out on her own as a criminal and occasional anti-hero.', 'Gotham City', 'Harley Quinn', '22222222-2222-2222-2222-222222222222', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('30000002-0000-0000-0000-000000000003', 'Slade', 'Wilson', 'Deathstroke', 'Enhanced strength, speed, reflexes, healing factor, tactical genius', 'Various (mercenary)', 4.7, 'https://example.com/images/deathstroke.jpg', 'The world''s greatest assassin, Deathstroke has enhanced physical and mental abilities. He is a master of virtually all forms of combat.', 'Unknown', 'The Terminator', '22222222-2222-2222-2222-222222222222', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('30000002-0000-0000-0000-000000000004', 'Helena', 'Bertinelli', 'Huntress', 'Expert marksmrewoman, hand-to-hand combat, gymnastics', 'Birds of Prey, Batman Family', 4.1, 'https://example.com/images/huntress.jpg', 'A vigilante who uses lethal force, Huntress walks the line between hero and criminal. She targets the mafia that destroyed her family.', 'Gotham City', 'Huntress', '22222222-2222-2222-2222-222222222222', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),

-- Anime Anti-Heroes
('30000003-0000-0000-0000-000000000001', 'Vegeta', NULL, 'Vegeta', 'Saiyan physiology, Super Saiyan transformations', 'Z Fighters', 4.9, 'https://example.com/images/vegeta.jpg', 'The Prince of all Saiyans started as a ruthless villain who destroyed planets. After his defeat by Goku, he gradually becomes an ally while maintaining his pride and ruthless nature.', 'Planet Vegeta', 'Prince Vegeta', '33333333-3333-3333-3333-333333333333', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('30000003-0000-0000-0000-000000000002', 'Itachi', 'Uchiha', 'Itachi', 'Sharingan, Mangekyo Sharingan, genjutsu master, Susanoo', 'Akatsuki (former Hidden Leaf)', 4.8, 'https://example.com/images/itachi.jpg', 'A prodigy of the Uchiha clan who slaughtered his entire clan except for his brother. His true motivations and sacrifices make him one of the most complex characters in Naruto.', 'Hidden Leaf Village', 'Itachi Uchiha', '33333333-3333-3333-3333-333333333333', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('30000003-0000-0000-0000-000000000003', 'Kenpachi', 'Zaraki', 'Kenpachi', 'Immense spiritual power, master swordsman, brute strength', 'Gotei 13', 4.6, 'https://example.com/images/kenpachi.jpg', 'The captain of the 11th Division in the Gotei 13, Kenpachi lives for battle. He restrains his own power to enjoy fights longer and is feared throughout the Soul Society.', 'Soul Society', 'Kenpachi Zaraki', '33333333-3333-3333-3333-333333333333', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
