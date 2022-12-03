create table Customer (
    ID INT identity (1,1)  NOT NULL,
    customerName varChar(20) NOT NULL,
    primary key(ID)
);

create table CustomerOrder (
    ID INT identity (1,1)  NOT NULL,
    price numeric(10,2),
    primary key(ID)
);

create table Ingredient (
    ID INT identity (1,1)  NOT NULL,
    ingredientName varchar(20) NOT NULL,
    category varchar(20),
    primary key(ID)
);

create table Supplier (
    ID INT identity (1,1)  NOT NULL,
    supplierName varchar(20) NOT NULL,
    category varchar(20) NOT NULL,
    primary key(ID)
);

create table Equipment(
   ID INT identity(1,1) not NULL,
   equipmentName varchar(20) not Null,
   manufacturer varchar(20),
   primary key(ID)
);
 
create table MenuItem(
   ID INT identity(1,1) not Null,
   menuItemName varchar(20) not Null,
   price numeric(10,2),
   primary key(ID)
);


create table Recipe (
    menuItemID INT NOT NULL,
    equipmentID INT NOT NULL,
    ingredientID INT NOT NULL,
    primary key(ingredientID, menuItemID, equipmentID),
    foreign key(menuItemID) references MenuItem (ID) on delete cascade,
    foreign key(equipmentID) references Equipment (ID) on delete cascade,
    foreign key(ingredientID) references Ingredient (ID) on delete cascade
);

create table Supplies (
    ingredientID INT NOT NULL,
    supplierID INT NOT NULL,
    primary key(ingredientID, supplierID),
    foreign key(ingredientID) references Ingredient (ID) on delete cascade,
    foreign key(supplierID) references Supplier (ID) on delete cascade
);

create table OrderedItem (
    orderID INT NOT NULL,
    menuItemID INT NOT NULL,
    primary key(orderID, menuItemID),
    foreign key(orderID) references CustomerOrder (ID) on delete cascade,
    foreign key(menuItemID) references MenuItem (ID) on delete cascade
);

create table Buys (
    customerID INT NOT NULL,
    orderID INT NOT NULL,
    primary key(customerID, orderID),
    foreign key(customerID) references Customer (ID) on delete cascade,
    foreign key(orderID) references CustomerOrder (ID) on delete cascade
);