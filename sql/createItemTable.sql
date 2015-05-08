create table TBL_ITEMS (
	item_id INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1),
	ownerID INTEGER,
	title VARCHAR(30),
	category VARCHAR(30),
	picture VARCHAR(30),
	description VARCHAR(100),
	address VARCHAR(100),
	reservePrice FLOAT,
	startPrice FLOAT,
	bidIncrements FLOAT,
	endTime VARCHAR(30),
	highestBid FLOAT,
	highest_bid_user_ID INT 

	PRIMARY KEY (item_id),
	CONSTRAINT fk_items FOREIGN KEY (id)
	REFERENCES TBL_Users (id)
);
