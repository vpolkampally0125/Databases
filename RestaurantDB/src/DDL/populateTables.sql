DELETE FROM Customer;
DELETE FROM MenuItem;
DELETE FROM Ingredient;
DELETE FROM Equipment;
DELETE FROM Recipe;
DELETE FROM Supplier;
DELETE FROM Supplies;
DBCC CHECKIDENT (MenuItem, RESEED, 0);
DBCC CHECKIDENT (Ingredient, RESEED, 0);
DBCC CHECKIDENT (Equipment, RESEED, 0);
DBCC CHECKIDENT (Supplier, RESEED, 0);


INSERT INTO Customer (customerName) 
VALUES 
    ('Jeff'),
    ('Jeffery'),
    ('Jefferson'),
    ('Geoff')
;

INSERT INTO MenuItem (menuItemName, price)
VALUES
    ('Hamburger', 5.00),
    ('Cheeseburger', 7.00),
    ('Fries', 2.50),
    ('Nuggets', 29.99),
    ('Shake', 15.00),
    ('Soda', 1.50),
    ('Muffin', 1.99),
    ('Chili', 49.99)
;

INSERT INTO Ingredient (ingredientName, category)
VALUES
    ('Beef', 'Meat'),
    ('Chicken', 'Meat'),
    ('Buns', 'Topping'),
    ('Cheese', 'Topping'),
    ('Milk', 'Dairy'),
    ('Ketchup', 'Topping'),
    ('Potato', 'Carbohydrate'),
    ('Bread', 'Carbohydrate'),
    ('Beans', 'Carbohydrate'),
    ('CarbonatedWater', 'Drinks')
;

INSERT INTO Equipment (equipmentName, manufacturer)
VALUES
    ('Grill', 'Wholesale Equipment'),
    ('Spatula', 'Wholesale Equipment'),
    ('Frier','Wholesale Equipment'),
    ('Oven', 'Quality Goods'),
    ('Refrigerator', 'Quality Goods'),
    ('SodaFountain', 'Jeffs Finest'),
    ('Pot', 'Jeffs Finest')
;

INSERT INTO Supplier (supplierName, category)
VALUES
    ('Tyson', 'Meat'),
    ('McCormic', 'Topping'),
    ('Jeffs Best', 'Dairy'),
    ('Potato Man', 'Carbohydrate'),
    ('Pepsi', 'Drinks')
;


INSERT INTO Recipe (menuItemID, ingredientID, equipmentID)
VALUES
    ('1', '1', '1'),
    ('1', '3', '2'),
    ('2', '1', '1'),
    ('2', '3', '2'),
    ('2', '4', '2'),
    ('3', '7', '3'),
    ('3', '6', '5'),
    ('4', '2', '1'),
    ('4', '6', '5'),
    ('5', '5', '5'),
    ('6', '10', '6'),
    ('7', '8', '4'),
    ('8', '9', '7')
;

INSERT INTO Supplies (ingredientID, supplierID)
VALUES
    ('1', '1'),
    ('2', '1'),
    ('3', '2'),
    ('4', '2'),
    ('5', '3'),
    ('6', '2'),
    ('7', '4'),
    ('8', '4'),
    ('9', '4'),
    ('10', '5')
;