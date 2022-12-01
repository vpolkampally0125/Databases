INSERT INTO Customer (name) 
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
    ('Grill', 'Wholesale Restaurant Equipment'),
    ('Spatula', 'Wholesale Restaurant Equipment'),
    ('Frier','Wholesale Restaurant Equipment'),
    ('Oven', 'Quality Goods'),
    ('Refrigerator', 'Quality Goods'),
    ('SodaFountain', 'Jeffs Finest'),
    ('Pot', 'Jeffs Finest')
;


INSERT INTO Recipe (menuItemID, ingredientID, equipmentID)
VALUES
    ('10', '1', '2'),
    ('10', '3', '3'),
    ('11', '1', '2'),
    ('11', '3', '3'),
    ('11', '4', '3'),
    ('12', '7', '4'),
    ('12', '6', '6'),
    ('13', '2', '2'),
    ('13', '6', '6'),
    ('14', '5', '6'),
    ('15', '10', '7'),
    ('16', '8', '5'),
    ('17', '9', '8')
;

INSERT INTO Supplier (supplierName, category)
VALUES
    ('Tyson', 'Meat'),
    ('McCormic', 'Topping'),
    ('Jeffs Best', 'Dairy'),
    ('Potato Man', 'Carbohydrate'),
    ('Pepsi', 'Drinks'),
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