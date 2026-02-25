-- =====================================================
-- Heroes & Villains Blog - Sample Data
-- PostgreSQL Database
-- =====================================================

-- Clean existing data (in reverse order of dependencies)
DELETE FROM comment;
DELETE FROM blog;
DELETE FROM anti_hero;
DELETE FROM villain;
DELETE FROM hero;
DELETE FROM category;

-- Reset sequences (if using serial instead of UUID)
-- ALTER SEQUENCE category_id_seq RESTART WITH 1;

-- =====================================================
-- CATEGORIES
-- =====================================================
INSERT INTO category (id, name, description, created_at, updated_at) VALUES
('11111111-1111-1111-1111-111111111111', 'Marvel', 'Marvel Universe characters - Spider-Man, Iron Man, Avengers, X-Men and more', NOW(), NOW()),
('22222222-2222-2222-2222-222222222222', 'DC', 'DC Universe characters - Batman, Superman, Wonder Woman, Justice League and more', NOW(), NOW()),
('33333333-3333-3333-3333-333333333333', 'Anime', 'Anime and Manga characters - Dragon Ball, Naruto, One Piece, My Hero Academia', NOW(), NOW()),
('44444444-4444-4444-4444-444444444444', 'Dark Horse', 'Dark Horse Comics - Hellboy, The Mask, Umbrella Academy', NOW(), NOW()),
('55555555-5555-5555-5555-555555555555', 'Image Comics', 'Image Comics - Spawn, The Walking Dead, Invincible', NOW(), NOW());

-- =====================================================
-- HEROES
-- =====================================================
INSERT INTO hero (id, first_name, last_name, hero_name, power, affiliation, rating, image_url, biography, category_id, created_at, updated_at) VALUES
-- Marvel Heroes
('10000001-0000-0000-0000-000000000001', 'Peter', 'Parker', 'Spider-Man', 'Spider abilities, web-slinging, wall-crawling, spider-sense', 'Avengers, Fantastic Four', 4.8, 'https://example.com/images/spiderman.jpg', 'Bitten by a radioactive spider during a science exhibition, high school student Peter Parker gained the proportionate strength, speed, and agility of a spider, along with the ability to cling to walls.', '11111111-1111-1111-1111-111111111111', NOW(), NOW()),
('10000001-0000-0000-0000-000000000002', 'Tony', 'Stark', 'Iron Man', 'Genius-level intellect, powered armor suit with flight, repulsors, and various weapons', 'Avengers, S.H.I.E.L.D.', 4.9, 'https://example.com/images/ironman.jpg', 'A billionaire industrialist and genius inventor, Tony Stark was captured and forced to build a weapon of mass destruction. Instead, he built a suit of armor to escape and later used it to protect the world.', '11111111-1111-1111-1111-111111111111', NOW(), NOW()),
('10000001-0000-0000-0000-000000000003', 'Steve', 'Rogers', 'Captain America', 'Super soldier serum enhanced strength, speed, durability, and reflexes; master tactician', 'Avengers, S.H.I.E.L.D.', 4.9, 'https://example.com/images/captainamerica.jpg', 'During World War II, frail Steve Rogers volunteered for a top-secret military experiment. The Super Soldier Serum transformed him into the peak of human physical perfection.', '11111111-1111-1111-1111-111111111111', NOW(), NOW()),
('10000001-0000-0000-0000-000000000004', 'Thor', 'Odinson', 'Thor', 'Asgardian physiology, Mjolnir (enchanted hammer), control over lightning and storms', 'Avengers, Asgard', 4.7, 'https://example.com/images/thor.jpg', 'The Asgardian God of Thunder, Thor is one of the most powerful Avengers. He wields Mjolnir, a magical hammer that grants him the ability to fly and control lightning.', '11111111-1111-1111-1111-111111111111', NOW(), NOW()),
('10000001-0000-0000-0000-000000000005', 'Bruce', 'Banner', 'Hulk', 'Transforms into a giant green monster with unlimited strength, durability, and healing', 'Avengers, Defenders', 4.6, 'https://example.com/images/hulk.jpg', 'Scientist Bruce Banner was exposed to massive amounts of gamma radiation during a bomb test. Now, when angered or stressed, he transforms into the Hulk, a creature of immense strength.', '11111111-1111-1111-1111-111111111111', NOW(), NOW()),

-- DC Heroes
('10000002-0000-0000-0000-000000000001', 'Bruce', 'Wayne', 'Batman', 'Peak human physical and mental condition, master detective, martial artist, billionaire resources', 'Justice League, Batman Family', 4.9, 'https://example.com/images/batman.jpg', 'After witnessing his parents'' murder, Bruce Wayne dedicated his life to fighting crime. He trained extensively in martial arts, detective work, and psychology, becoming Gotham''s Dark Knight.', '22222222-2222-2222-2222-222222222222', NOW(), NOW()),
('10000002-0000-0000-0000-000000000002', 'Clark', 'Kent', 'Superman', 'Kryptonian physiology under yellow sun: super strength, speed, flight, heat vision, freeze breath, invulnerability', 'Justice League, Daily Planet', 5.0, 'https://example.com/images/superman.jpg', 'Born Kal-El on the planet Krypton, Superman was sent to Earth as an infant before his planet''s destruction. Raised as Clark Kent in Smallville, he uses his incredible powers to protect humanity.', '22222222-2222-2222-2222-222222222222', NOW(), NOW()),
('10000002-0000-0000-0000-000000000003', 'Diana', 'Prince', 'Wonder Woman', 'Amazonian physiology, super strength, flight, combat skill, Lasso of Truth, indestructible bracelets', 'Justice League, Amazons of Themyscira', 4.8, 'https://example.com/images/wonderwoman.jpg', 'Diana of Themyscira is an Amazon warrior princess and one of the world''s most powerful heroes. She left her homeland to bring peace to the world of man.', '22222222-2222-2222-2222-222222222222', NOW(), NOW()),
('10000002-0000-0000-0000-000000000004', 'Barry', 'Allen', 'The Flash', 'Speed Force connection: super speed, time travel, dimension hopping, accelerated healing', 'Justice League, Flash Family', 4.7, 'https://example.com/images/flash.jpg', 'Police scientist Barry Allen was struck by lightning and doused with chemicals, giving him access to the Speed Force. He can run at incredible speeds and even travel through time.', '22222222-2222-2222-2222-222222222222', NOW(), NOW()),
('10000002-0000-0000-0000-000000000005', 'Hal', 'Jordan', 'Green Lantern', 'Power ring capable of creating constructs limited only by willpower and imagination', 'Justice League, Green Lantern Corps', 4.5, 'https://example.com/images/greenlantern.jpg', 'Test pilot Hal Jordan was chosen by the dying alien Abin Sur to wield a power ring. As Green Lantern, he protects Space Sector 2814 as a member of the intergalactic police force.', '22222222-2222-2222-2222-222222222222', NOW(), NOW()),

-- Anime Heroes
('10000003-0000-0000-0000-000000000001', 'Son', 'Goku', 'Goku', 'Saiyan physiology, Super Saiyan transformations, Ki manipulation, Instant Transmission, Spirit Bomb', 'Z Fighters, Dragon Team', 5.0, 'https://example.com/images/goku.jpg', 'A Saiyan warrior sent to Earth as a baby, Goku forgot his mission of destruction after a head injury. He became Earth''s greatest defender, constantly pushing his limits through training.', '33333333-3333-3333-3333-333333333333', NOW(), NOW()),
('10000003-0000-0000-0000-000000000002', 'Naruto', 'Uzumaki', 'Naruto', 'Nine-Tailed Fox chakra, Sage Mode, Rasengan variants, Shadow Clone Jutsu', 'Hidden Leaf Village, Team 7', 4.8, 'https://example.com/images/naruto.jpg', 'Born as the jinchuriki of the Nine-Tailed Fox, Naruto was ostracized by his village. He dreamed of becoming Hokage and worked tirelessly to earn the respect of his peers.', '33333333-3333-3333-3333-333333333333', NOW(), NOW()),
('10000003-0000-0000-0000-000000000003', 'Izuku', 'Midoriya', 'Deku', 'One For All quirk: super strength, speed, durability; multiple quirk manifestations', 'U.A. High School, Class 1-A', 4.6, 'https://example.com/images/deku.jpg', 'Born quirkless in a world where 80% of people have superpowers, Izuku was chosen by the legendary hero All Might to inherit the power of One For All.', '33333333-3333-3333-3333-333333333333', NOW(), NOW());

-- =====================================================
-- VILLAINS
-- =====================================================
INSERT INTO villain (id, first_name, last_name, villain_name, power, affiliation, rating, image_url, biography, category_id, created_at, updated_at) VALUES
-- Marvel Villains
('20000001-0000-0000-0000-000000000001', 'Norman', 'Osborn', 'Green Goblin', 'Enhanced strength and intelligence from Goblin Formula, glider, pumpkin bombs', 'Sinister Six, Oscorp', 4.6, 'https://example.com/images/greengoblin.jpg', 'Industrialist Norman Osborn tested an experimental formula that gave him superhuman abilities but drove him insane. He became Spider-Man''s greatest enemy.', '11111111-1111-1111-1111-111111111111', NOW(), NOW()),
('20000001-0000-0000-0000-000000000002', 'Erik', 'Lehnsherr', 'Magneto', 'Mutant ability to manipulate magnetic fields and metal, genius intellect', 'Brotherhood of Mutants, X-Men (formerly)', 4.8, 'https://example.com/images/magneto.jpg', 'A Holocaust survivor, Erik Lehnsherr became Magneto to fight for mutant supremacy. He believes mutants should rule over humans to prevent the persecution he witnessed.', '11111111-1111-1111-1111-111111111111', NOW(), NOW()),
('20000001-0000-0000-0000-000000000003', 'Victor', 'Von Doom', 'Doctor Doom', 'Genius-level intellect, mastery of science and sorcery, armored suit', 'Latveria, various villain teams', 4.9, 'https://example.com/images/drdoom.jpg', 'The ruler of Latveria, Doctor Doom is both a scientific genius and a powerful sorcerer. His intellect rivals Reed Richards, and he seeks to rule the world for what he sees as humanity''s own good.', '11111111-1111-1111-1111-111111111111', NOW(), NOW()),
('20000001-0000-0000-0000-000000000004', 'Thanos', NULL, 'Thanos', 'Eternal/Deviant hybrid physiology: super strength, durability, immortality; genius tactician', 'Black Order, himself', 5.0, 'https://example.com/images/thanos.jpg', 'The Mad Titan from Titan, Thanos became obsessed with death and balance. He sought the Infinity Stones to wipe out half of all life in the universe, believing it would bring salvation.', '11111111-1111-1111-1111-111111111111', NOW(), NOW()),

-- DC Villains
('20000002-0000-0000-0000-000000000001', NULL, NULL, 'The Joker', 'Criminal genius, chemical expertise, insanity', 'Various gangs, himself', 4.9, 'https://example.com/images/joker.jpg', 'The Joker''s true origin is unknown, but he emerged as Gotham''s most chaotic criminal. His insanity and obsession with Batman make him one of the most unpredictable villains in existence.', '22222222-2222-2222-2222-222222222222', NOW(), NOW()),
('20000002-0000-0000-0000-000000000002', 'Lex', 'Luthor', 'Lex Luthor', 'Genius-level intellect, vast wealth, advanced technology', 'LexCorp, Injustice League', 4.7, 'https://example.com/images/lexluthor.jpg', 'The CEO of LexCorp and one of the world''s smartest men, Lex Luthor views Superman as a threat to humanity. He uses his intellect and resources to try to destroy the Man of Steel.', '22222222-2222-2222-2222-222222222222', NOW(), NOW()),
('20000002-0000-0000-0000-000000000003', 'Harvey', 'Dent', 'Two-Face', 'Expert marksman, dual personality, uses coin to make decisions', 'Various criminal organizations', 4.5, 'https://example.com/images/twoface.jpg', 'Once Gotham''s district attorney, Harvey Dent was disfigured by acid thrown by a mobster. The trauma split his personality, and he became Two-Face, making decisions based on a coin flip.', '22222222-2222-2222-2222-222222222222', NOW(), NOW()),
('20000002-0000-0000-0000-000000000004', 'Eobard', 'Thawne', 'Reverse-Flash', 'Speed Force connection: super speed, time travel, negative Speed Force', 'Rogues, himself', 4.8, 'https://example.com/images/reverseflash.jpg', 'A descendant of Eddie Thawne from the 25th century, Eobard Thawne became obsessed with the Flash. He replicated the accident that gave Barry Allen his powers but became his greatest enemy.', '22222222-2222-2222-2222-222222222222', NOW(), NOW()),

-- Anime Villains
('20000003-0000-0000-0000-000000000001', NULL, NULL, 'Frieza', 'Mutant Frost Demon physiology: multiple transformation forms, immense power, Ki manipulation', 'Frieza Force, himself', 4.9, 'https://example.com/images/frieza.jpg', 'The emperor of the universe, Frieza destroyed countless planets and civilizations. He destroyed Planet Vegeta, wiping out most Saiyans, fearing the legend of the Super Saiyan.', '33333333-3333-3333-3333-333333333333', NOW(), NOW()),
('20000003-0000-0000-0000-000000000002', NULL, NULL, 'Cell', 'Bio-android with cells from Goku, Vegeta, Piccolo, Frieza, and King Cold', 'Himself', 4.7, 'https://example.com/images/cell.jpg', 'Created by Dr. Gero, Cell was designed to be the perfect warrior. He absorbed Androids 17 and 18 to achieve his perfect form, becoming one of the Z Fighters'' deadliest enemies.', '33333333-3333-3333-3333-333333333333', NOW(), NOW()),
('20000003-0000-0000-0000-000000000003', 'Madara', 'Uchiha', 'Madara Uchiha', 'Sharingan, Mangekyou Sharingan, Rinnegan, Six Paths power, Susanoo', 'Uchiha Clan, Akatsuki', 4.9, 'https://example.com/images/madara.jpg', 'The legendary leader of the Uchiha clan, Madara was one of the strongest ninja in history. He sought to create a world of infinite peace through the Eye of the Moon Plan.', '33333333-3333-3333-3333-333333333333', NOW(), NOW());

-- =====================================================
-- ANTI-HEROES
-- =====================================================
INSERT INTO anti_hero (id, first_name, last_name, anti_hero_name, house, known_as, power, affiliation, rating, image_url, biography, category_id, created_at, updated_at) VALUES
-- Marvel Anti-Heroes
('30000001-0000-0000-0000-000000000001', 'Wade', 'Wilson', 'Deadpool', NULL, 'Merc with a Mouth', 'Accelerated healing factor, fourth-wall awareness, expert marksman and martial artist', 'X-Force, Avengers (occasionally)', 4.7, 'https://example.com/images/deadpool.jpg', 'A former special forces operative turned mercenary, Wade Wilson underwent an experimental treatment for cancer that gave him an accelerated healing factor. The process left him disfigured and mentally unstable.', '11111111-1111-1111-1111-111111111111', NOW(), NOW()),
('30000001-0000-0000-0000-000000000002', 'Logan', NULL, 'Wolverine', NULL, 'The Best There Is', 'Mutant healing factor, adamantium skeleton and claws, enhanced senses, longevity', 'X-Men, Avengers, X-Force', 4.9, 'https://example.com/images/wolverine.jpg', 'Born James Howlett, Logan is a mutant with animal-keen senses, enhanced physical capabilities, and a healing factor. His skeleton was bonded with adamantium, making him virtually indestructible.', '11111111-1111-1111-1111-111111111111', NOW(), NOW()),
('30000001-0000-0000-0000-000000000003', 'Frank', 'Castle', 'The Punisher', NULL, 'The Punisher', 'Expert in guerrilla warfare, military training, vast arsenal of weapons', 'None (vigilante)', 4.5, 'https://example.com/images/punisher.jpg', 'A former Marine whose family was killed by the mob, Frank Castle became the Punisher. He wages a one-man war on crime using lethal force, showing no mercy to criminals.', '11111111-1111-1111-1111-111111111111', NOW(), NOW()),
('30000001-0000-0000-0000-000000000004', 'Eddie', 'Brock', 'Venom', NULL, 'Lethal Protector', 'Symbiote grants: super strength, agility, webbing, shape-shifting, spider-sense immunity', 'Venom family, Guardians of the Galaxy (formerly)', 4.6, 'https://example.com/images/venom.jpg', 'Journalist Eddie Brock bonded with an alien symbiote that Spider-Man had rejected. Together they became Venom, initially a villain but later evolving into an anti-hero protecting the innocent.', '11111111-1111-1111-1111-111111111111', NOW(), NOW()),

-- DC Anti-Heroes
('30000002-0000-0000-0000-000000000001', 'Jason', 'Todd', 'Red Hood', NULL, 'The Outlaw', 'Expert martial artist, marksman, detective skills, uses lethal force', 'Outlaws, Batman Family (estranged)', 4.5, 'https://example.com/images/redhood.jpg', 'The second Robin, Jason Todd was killed by the Joker. Resurrected by the Lazarus Pit, he returned as Red Hood, using lethal methods to fight crime that Batman refuses to employ.', '22222222-2222-2222-2222-222222222222', NOW(), NOW()),
('30000002-0000-0000-0000-000000000002', 'Slade', 'Wilson', 'Deathstroke', NULL, 'The Terminator', 'Enhanced strength, speed, agility, and mental capacity; master tactician and fighter', 'Various mercenary groups', 4.7, 'https://example.com/images/deathstroke.jpg', 'A former soldier who underwent an experimental procedure that enhanced his abilities, Slade Wilson became the world''s deadliest mercenary. He''s fought both heroes and villains for the right price.', '22222222-2222-2222-2222-222222222222', NOW(), NOW()),
('30000002-0000-0000-0000-000000000003', 'Selina', 'Kyle', 'Catwoman', NULL, 'The Cat', 'Expert thief, acrobat, martial artist; uses whip and cat-themed weapons', 'Batman (on/off ally)', 4.4, 'https://example.com/images/catwoman.jpg', 'An orphan who learned to survive on Gotham''s streets, Selina Kyle became the world''s greatest thief. She walks the line between hero and villain, often helping Batman while pursuing her own agenda.', '22222222-2222-2222-2222-222222222222', NOW(), NOW()),

-- Anime Anti-Heroes
('30000003-0000-0000-0000-000000000001', 'Vegeta', NULL, 'Vegeta', NULL, 'Prince of all Saiyans', 'Saiyan physiology, Super Saiyan transformations, Ki manipulation, Final Flash, Big Bang Attack', 'Z Fighters, Capsule Corp', 4.8, 'https://example.com/images/vegeta.jpg', 'The Prince of the Saiyan race, Vegeta came to Earth as a villain seeking the Dragon Balls. After being defeated by Goku, he eventually became an ally, though his pride and rivalry with Goku never faded.', '33333333-3333-3333-3333-333333333333', NOW(), NOW()),
('30000003-0000-0000-0000-000000000002', 'Sasuke', 'Uchiha', 'Sasuke', NULL, 'The Avenger', 'Sharingan, Mangekyou Sharingan, Rinnegan, Chidori, Susanoo, Amaterasu', 'Team 7 (formerly), Hidden Leaf Village', 4.6, 'https://example.com/images/sasuke.jpg', 'The last survivor of the Uchiha clan massacre, Sasuke sought revenge against his brother Itachi. His quest for power led him down a dark path, but he eventually found redemption.', '33333333-3333-3333-3333-333333333333', NOW(), NOW());

-- =====================================================
-- BLOGS
-- =====================================================
INSERT INTO blog (id, title, body, author, cover_image_url, tags, hero_id, villain_id, anti_hero_id, created_at, updated_at) VALUES
-- Marvel Blogs
('40000001-0000-0000-0000-000000000001', 'The Evolution of Spider-Man: From Teenage Hero to Avenger', 'Spider-Man has come a long way since his first appearance in Amazing Fantasy #15 in 1962. Created by Stan Lee and Steve Ditko, Peter Parker was a revolutionary character - a teenage superhero who dealt with real-world problems like homework, dating, and paying rent.

Over the decades, Spider-Man has evolved from a solo hero to a member of the Fantastic Four and the Avengers. His rogues'' gallery is one of the best in comics, featuring iconic villains like Green Goblin, Doctor Octopus, and Venom.

What makes Spider-Man truly special is his relatability. He''s not a billionaire or a god - he''s a regular guy trying to do the right thing while balancing his personal life. As Stan Lee wrote, "With great power comes great responsibility."', 'Mary Jane Watson', 'https://example.com/blogs/spiderman-evolution.jpg', 'spiderman,marvel,avengers,character-analysis', '10000001-0000-0000-0000-000000000001', NULL, NULL, NOW() - INTERVAL '7 days', NOW() - INTERVAL '7 days'),

('40000001-0000-0000-0000-000000000002', 'Thanos: Understanding the Mad Titan''s Motivation', 'Thanos is one of the most complex villains in comic book history. Unlike many villains who seek power for its own sake, Thanos genuinely believes his actions will save the universe.

Born on Saturn''s moon Titan, Thanos witnessed his civilization collapse due to overpopulation. This trauma shaped his worldview and led to his obsession with "balance" - eliminating half of all life to ensure the survival of the other half.

In the MCU, Josh Brolin''s portrayal humanized the character even further. We see a being driven by a twisted sense of purpose, willing to sacrifice everything - even his beloved daughter Gamora - for what he believes is the greater good.

This moral complexity makes Thanos fascinating. He''s not evil for evil''s sake; he''s a being with a fundamentally different moral framework, convinced that his horrific actions are actually heroic.', 'Nick Fury', 'https://example.com/blogs/thanos-analysis.jpg', 'thanos,marvel,avengers,infinity-war,villain-analysis', NULL, '20000001-0000-0000-0000-000000000004', NULL, NOW() - INTERVAL '5 days', NOW() - INTERVAL '5 days'),

('40000001-0000-0000-0000-000000000003', 'Wolverine: The Best There Is at What He Does', 'Logan, better known as Wolverine, is one of Marvel''s most enduring characters. His popularity stems from his complexity - he''s a killer trying to be better, a loner who found family, an immortal who values life.

Created by Len Wein and John Romita Sr., Wolverine first appeared in Incredible Hulk #180-181 in 1974. His mysterious origins and gruff demeanor made him an instant fan favorite when he joined the X-Men.

What makes Wolverine compelling is his struggle. His healing factor makes him virtually immortal, forcing him to watch everyone he loves grow old and die. Yet he continues to fight, driven by a sense of honor and duty.

His relationship with Professor X and the X-Men gave him purpose. His rivalry with Sabretooth and his mentorship of younger mutants like Jubilee and X-23 showed his depth. Wolverine proves that even the most damaged person can find meaning in protecting others.', 'Professor X', 'https://example.com/blogs/wolverine-character.jpg', 'wolverine,x-men,marvel,character-analysis', NULL, NULL, '30000001-0000-0000-0000-000000000002', NOW() - INTERVAL '3 days', NOW() - INTERVAL '3 days'),

-- DC Blogs
('40000002-0000-0000-0000-000000000001', 'Batman: Why the Dark Knight Endures', 'Batman has been a cultural icon for over 80 years, and his appeal shows no signs of fading. What makes a man dressed as a bat so compelling?

The answer lies in his humanity. Unlike Superman or Wonder Woman, Batman has no superpowers. He''s a man who turned tragedy into purpose, using his wealth and intellect to wage war on crime.

Batman represents the idea that anyone can be a hero. His rogues'' gallery - Joker, Two-Face, Penguin, Riddler - each represent different aspects of madness and obsession. His allies - Robin, Batgirl, Nightwing - show his capacity for mentorship and family.

The Dark Knight''s no-kill rule is central to his character. It represents his belief that there''s always hope for redemption, even as he battles the most depraved criminals. This moral code, tested time and again, defines Batman as a true hero.', 'Alfred Pennyworth', 'https://example.com/blogs/batman-endures.jpg', 'batman,dc,justice-league,character-analysis', '10000002-0000-0000-0000-000000000001', NULL, NULL, NOW() - INTERVAL '6 days', NOW() - INTERVAL '6 days'),

('40000002-0000-0000-0000-000000000002', 'The Joker: Chaos Incarnate', 'The Joker is perhaps the greatest comic book villain ever created. His lack of a definitive origin story makes him more terrifying - he could be anyone, come from anywhere.

What makes the Joker fascinating is his relationship with Batman. They are two sides of the same coin - both created by tragedy, both operating outside the law, but with fundamentally different philosophies. The Joker represents chaos and nihilism; Batman represents order and justice.

In "The Killing Joke," Alan Moore wrote that "all it takes is one bad day to reduce the sanest man alive to lunacy." This philosophy defines the Joker - he wants to prove that anyone can be broken, that civilization is a thin veneer over madness.

Whether portrayed by Cesar Romero, Jack Nicholson, Heath Ledger, or Joaquin Phoenix, the Joker remains a mirror reflecting society''s fears about sanity, chaos, and the thin line between order and anarchy.', 'Dr. Harleen Quinzel', 'https://example.com/blogs/joker-chaos.jpg', 'joker,dc,batman,villain-analysis', NULL, '20000002-0000-0000-0000-000000000001', NULL, NOW() - INTERVAL '4 days', NOW() - INTERVAL '4 days'),

-- Anime Blogs
('40000003-0000-0000-0000-000000000001', 'Goku: The Heart of a Champion', 'Son Goku is the archetype of the shonen hero - optimistic, determined, and always striving to become stronger. Created by Akira Toriyama, Goku has inspired generations of manga and anime protagonists.

What sets Goku apart is his pure heart. He fights not for glory or power, but for the thrill of battle and to protect his friends. His willingness to forgive former enemies - Piccolo, Vegeta, Buu - shows his belief in redemption.

Goku''s journey from a wild boy in the mountains to a warrior who can challenge gods mirrors the classic hero''s journey. His Super Saiyan transformations have become iconic moments in anime history.

Yet Goku isn''t perfect. His love of fighting sometimes puts the universe at risk, and his naivety can be frustrating. These flaws make him human despite his alien heritage, and they''re why fans continue to root for him after decades.', 'Bulma Briefs', 'https://example.com/blogs/goku-champion.jpg', 'goku,dragonball,anime,character-analysis', '10000003-0000-0000-0000-000000000001', NULL, NULL, NOW() - INTERVAL '2 days', NOW() - INTERVAL '2 days'),

('40000003-0000-0000-0000-000000000002', 'Vegeta: From Villain to Hero', 'Vegeta''s character arc is one of the best in anime history. He began as a ruthless villain who killed his own partner without hesitation. Over time, he became a husband, father, and one of Earth''s greatest defenders.

What makes Vegeta compelling is his pride. His rivalry with Goku drives him to constantly improve, but it also causes him to make mistakes. His decision to let Cell absorb Android 18 and his transformation into Majin Vegeta were both driven by his obsession with surpassing Goku.

Yet Vegeta''s redemption is genuine. His sacrifice against Buu, his acceptance of Goku''s superiority, and his role as a family man show how far he''s come. The Prince of all Saiyans found something worth more than power - a home and family.

Vegeta proves that redemption is possible for anyone, no matter how dark their past. His journey from villain to anti-hero to true hero is a testament to the power of character development.', 'Trunks Briefs', 'https://example.com/blogs/vegeta-redemption.jpg', 'vegeta,dragonball,anime,character-analysis', NULL, NULL, '30000003-0000-0000-0000-000000000001', NOW() - INTERVAL '1 day', NOW() - INTERVAL '1 day');

-- =====================================================
-- COMMENTS
-- =====================================================
INSERT INTO comment (id, author_name, content, blog_id, created_at, updated_at) VALUES
-- Comments on Spider-Man blog
('50000001-0000-0000-0000-000000000001', 'SpiderFan2099', 'Great article! Spider-Man has always been my favorite hero because he feels so real. The struggles he faces are things we all deal with.', '40000001-0000-0000-0000-000000000001', NOW() - INTERVAL '6 days', NOW() - INTERVAL '6 days'),
('50000001-0000-0000-0000-000000000002', 'WebHead42', 'I love how you mentioned his rogues gallery. Green Goblin and Venom are such complex villains. The duality between Peter and his enemies is what makes Spider-Man stories so compelling.', '40000001-0000-0000-0000-000000000001', NOW() - INTERVAL '6 days', NOW() - INTERVAL '6 days'),
('50000001-0000-0000-0000-000000000003', 'MJFan', 'As someone named after Mary Jane, I approve of this author choice! Great analysis of Peter''s journey.', '40000001-0000-0000-0000-000000000001', NOW() - INTERVAL '5 days', NOW() - INTERVAL '5 days'),

-- Comments on Thanos blog
('50000002-0000-0000-0000-000000000001', 'InfinityWatcher', 'The MCU really did an amazing job with Thanos. He''s terrifying because he thinks he''s the hero of his own story.', '40000001-0000-0000-0000-000000000002', NOW() - INTERVAL '4 days', NOW() - INTERVAL '4 days'),
('50000002-0000-0000-0000-000000000002', 'SnapSurvivor', 'I still can''t believe they made us sympathize with a character who wiped out half of all life. That''s the power of good writing.', '40000001-0000-0000-0000-000000000002', NOW() - INTERVAL '4 days', NOW() - INTERVAL '4 days'),

-- Comments on Wolverine blog
('50000003-0000-0000-0000-000000000001', 'XMenFan', 'Wolverine''s relationship with Professor X is one of the best in comics. Two very different men who respect each other deeply.', '40000001-0000-0000-0000-000000000003', NOW() - INTERVAL '2 days', NOW() - INTERVAL '2 days'),
('50000003-0000-0000-0000-000000000002', 'MutantPride', 'Logan and Laura (X-23) have such a beautiful father-daughter dynamic. It shows that even the most hardened warrior can find love.', '40000001-0000-0000-0000-000000000003', NOW() - INTERVAL '2 days', NOW() - INTERVAL '2 days'),

-- Comments on Batman blog
('50000004-0000-0000-0000-000000000001', 'GothamKnight', 'Batman''s no-kill rule is what separates him from the criminals he fights. It''s about being better, not just stronger.', '40000002-0000-0000-0000-000000000001', NOW() - INTERVAL '5 days', NOW() - INTERVAL '5 days'),
('50000004-0000-0000-0000-000000000002', 'DarkKnightRises', 'The fact that Batman has no powers but can stand alongside gods is what makes him so inspiring. He represents human potential.', '40000002-0000-0000-0000-000000000001', NOW() - INTERVAL '5 days', NOW() - INTERVAL '5 days'),
('50000004-0000-0000-0000-000000000003', 'JLMember', 'Great choice having Alfred write this! He knows Bruce better than anyone.', '40000002-0000-0000-0000-000000000001', NOW() - INTERVAL '4 days', NOW() - INTERVAL '4 days'),

-- Comments on Joker blog
('50000005-0000-0000-0000-000000000001', 'ArkhamDoctor', 'The Joker terrifies me because he represents the chaos we all fear. One bad day really could change everything.', '40000002-0000-0000-0000-000000000002', NOW() - INTERVAL '3 days', NOW() - INTERVAL '3 days'),
('50000005-0000-0000-0000-000000000002', 'ClownPrince', 'Heath Ledger''s Joker is still my favorite. That scene with the pencil... *shivers*', '40000002-0000-0000-0000-000000000002', NOW() - INTERVAL '3 days', NOW() - INTERVAL '3 days'),

-- Comments on Goku blog
('50000006-0000-0000-0000-000000000001', 'SaiyanPride', 'Goku taught me that you can always become stronger if you never give up. His attitude is infectious!', '40000003-0000-0000-0000-000000000001', NOW() - INTERVAL '1 day', NOW() - INTERVAL '1 day'),
('50000006-0000-0000-0000-000000000002', 'Kamehameha', 'The Super Saiyan transformation on Namek is still one of the greatest moments in anime history. I get chills every time.', '40000003-0000-0000-0000-000000000001', NOW() - INTERVAL '1 day', NOW() - INTERVAL '1 day'),

-- Comments on Vegeta blog
('50000007-0000-0000-0000-000000000001', 'PrinceOfSaiyans', 'Vegeta''s sacrifice against Buu still makes me cry. "You are the number one, Kakarot." That was his moment of true redemption.', '40000003-0000-0000-0000-000000000002', NOW() - INTERVAL '12 hours', NOW() - INTERVAL '12 hours'),
('50000007-0000-0000-0000-000000000002', 'BulmaLover', 'Having Trunks write this was perfect. Vegeta''s relationship with his family is what truly humanized him.', '40000003-0000-0000-0000-000000000002', NOW() - INTERVAL '6 hours', NOW() - INTERVAL '6 hours');

-- =====================================================
-- VERIFICATION QUERIES
-- =====================================================
-- Count records in each table
SELECT 'Categories' as table_name, COUNT(*) as count FROM category
UNION ALL
SELECT 'Heroes', COUNT(*) FROM hero
UNION ALL
SELECT 'Villains', COUNT(*) FROM villain
UNION ALL
SELECT 'Anti-Heroes', COUNT(*) FROM anti_hero
UNION ALL
SELECT 'Blogs', COUNT(*) FROM blog
UNION ALL
SELECT 'Comments', COUNT(*) FROM comment;

-- Display summary
SELECT 'Data insertion complete!' as status;
SELECT 'Categories: Marvel, DC, Anime, Dark Horse, Image Comics' as categories;
SELECT 'Heroes: 13 characters (5 Marvel, 5 DC, 3 Anime)' as heroes;
SELECT 'Villains: 11 characters (4 Marvel, 4 DC, 3 Anime)' as villains;
SELECT 'Anti-Heroes: 9 characters (4 Marvel, 3 DC, 2 Anime)' as anti_heroes;
SELECT 'Blogs: 7 articles' as blogs;
SELECT 'Comments: 17 comments' as comments;
