insert into setting(section, name, value) VALUES ('default', 'Theme', 'Light');
insert into setting(section, name, value) VALUES ('default', 'View Completed', 'False');
insert into setting(section, name, value) VALUES ('ui', 'selectedColor', 'blue');
insert into setting(section, name, value) VALUES ('ui', 'completedColor', 'grey');
insert into setting(section, name, value) VALUES ('ui', 'Max ToDo Items', '5000');
insert into setting(section, name, value) VALUES ('ui', 'Max List Items', '5');

insert into todo(title, description, status) VALUES ('Buy bread', 'Shopping', 'New');
insert into todo(title, description, status) VALUES ('Buy milk', 'Shopping', 'New');
insert into todo(title, description, status) VALUES ('Buy tea', 'Shopping', 'New');
insert into todo(title, description, status, important) VALUES ('Buy pants', 'Shopping', 'New', 1);
insert into todo(title, description, status, important) VALUES ('Buy pens', 'Shopping', 'New', 1);
insert into todo(title, description, status, important) VALUES ('Buy jotter', 'Shopping', 'New', 1);
insert into todo(title, description, status, completed) VALUES ('Buy pencil', 'Shopping', 'New', 1);
insert into todo(title, description, status, completed) VALUES ('Buy pens today', 'Shopping', 'New', 1);
insert into todo(title, description, status, start) VALUES ('Buy jotter today', 'Shopping', 'New', strftime('%Y-%m-%d','now'));
insert into todo(title, description, status, start) VALUES ('Interview', 'Shopping', 'New', strftime('%Y-%m-%d','now'));
insert into todo(title, description, status, start) VALUES ('Dinner', 'Shopping', 'New', strftime('%Y-%m-%d','now'));
insert into todo(title, description, status, start, `end`, completed) VALUES ('Exercise', 'Health', 'New', strftime('%Y-%m-%d','now', '-4 day'), strftime('%Y-%m-%d','now', '-3 day'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Exercise', 'Health', 'New', strftime('%Y-%m-%d','now', '-2 day'), strftime('%Y-%m-%d','now', '-1 day'), 1);
insert into todo(title, description, status, start, completed) VALUES ('Exercise', 'Health', 'New', strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, completed) VALUES ('Yoga', 'Health', 'New', strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`,completed) VALUES ('Yesterday', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, completed) VALUES ('Tomorrow', 'Life', 'New', strftime('%Y-%m-%d','now', '+1 day'), 0);
insert into todo(title, description, status, start, completed) VALUES ('Start New Course', 'Life', 'New', strftime('%Y-%m-%d','now', '+100 day'), 0);
insert into todo(title, description, status, start, completed) VALUES ('Move House', 'Life', 'New', strftime('%Y-%m-%d','now', '+200 day'), 0);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Complete assignment', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Pay bills', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Buy groceries', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Call mom', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Schedule dentist appointment', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Read a book', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Go for a run', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Write a blog post', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Clean the house', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Plan vacation', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Attend meeting', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Prepare presentation', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Organize files', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Take out the trash', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Do laundry', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Walk the dog', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Research a topic', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Update resume', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Learn a new skill', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Volunteer at a local charity', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Practice playing an instrument', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Watch a movie', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Complete a puzzle', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Visit a museum', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Explore a new hiking trail', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Try a new recipe', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Paint a picture', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Plant flowers in the garden', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Call a friend', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Declutter the closet', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Attend a fitness class', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Write a thank-you note', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Repair something around the house', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Donate clothes to a shelter', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Create a budget', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Start a journal', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Take a day trip to a nearby town', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Listen to a podcast', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Research investment options', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Explore a new restaurant', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Update social media profiles', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Start a DIY project', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Write a letter to a family member', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Practice meditation', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Clean the car', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Take a bubble bath', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Learn a new dance routine', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Rearrange furniture in a room', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Volunteer at a local event', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Go for a bike ride', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Try a new coffee shop', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Attend a live concert', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Plan a picnic', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Explore a local farmers market', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Read news articles', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Attend a yoga class', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Bake cookies', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Watch a sunrise or sunset', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Write down personal goals', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Start a book club', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Learn a foreign language', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Practice photography', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Take a day off and relax', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Complete a crossword puzzle', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Research healthy recipes', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Visit a botanical garden', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Go stargazing', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Write a short story', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Play a board game', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Volunteer at an animal shelter', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Explore a new hobby', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Go camping', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Take a cooking class', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Write a movie review', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Try a new workout routine', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Attend a local sports event', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Visit a historical site', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Plan a surprise for someone', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Create a scrapbook', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Learn to knit or crochet', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Attend a theater performance', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Participate in a community clean-up', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Write a poem', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Research eco-friendly practices', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Go fishing', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Learn to play chess', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Try a new hairstyle', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Take a self-defense class', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Go on a road trip', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Create a vision board', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Write a gratitude journal entry', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Practice mindfulness', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Explore a new park or nature reserve', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Learn to juggle', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Visit a local art gallery', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Try a new type of cuisine', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Volunteer at a food bank', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Attend a photography workshop', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Write a product review', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Reflect on personal achievements', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);

insert into list(name) VALUES ('Goals 2023');
insert into list(name) VALUES ('Goals 2022');
insert into list(name) VALUES ('Goals 2021');
insert into list(name) VALUES ('Goals 2020');

INSERT INTO list_item (list_id, todo_id) VALUES (1,1);
INSERT INTO list_item (list_id, todo_id) VALUES (1,2);
INSERT INTO list_item (list_id, todo_id) VALUES (1,3);
INSERT INTO list_item (list_id, todo_id) VALUES (1,4);
INSERT INTO list_item (list_id, todo_id) VALUES (2,1);
INSERT INTO list_item (list_id, todo_id) VALUES (2,2);
INSERT INTO list_item (list_id, todo_id) VALUES (3,3);
INSERT INTO list_item (list_id, todo_id) VALUES (3,4);
INSERT INTO list_item (list_id, todo_id) VALUES (4,1);
INSERT INTO list_item (list_id, todo_id) VALUES (4,2);
INSERT INTO list_item (list_id, todo_id) VALUES (4,3);
INSERT INTO list_item (list_id, todo_id) VALUES (4,4);


