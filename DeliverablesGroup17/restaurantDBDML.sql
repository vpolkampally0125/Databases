-- This use case handles the event when customer names an order with type item_name
create procedure MakeOrder
    @customer_name  varchar(20),
    @item_name  varchar(20)
as

begin
    insert into CustomerOrder (price) values (
        (SELECT price from MenuItem where @item_name = menuItemName)
    );

    insert into Buys (customerID, orderID) values (
        (SELECT ID from Customer where @customer_name = customerName),
        (SELECT max(ID) from CustomerOrder)
    )

    insert into OrderedItem (orderID, menuItemID) values (
        (SELECT max(ID) from CustomerOrder),
        (SELECT ID from MenuItem where @item_name = menuItemName)
    )
end

-- This procedure involves inserting a new menu item with type float price, string ingredient, and equipment
create procedure MakeNewMenuItem
    @menu_item_name  varchar(20),
    @price  numeric(10,2),
    @ingredent varchar(20),
    @equipment varchar(20)
as

begin
    insert into MenuItem (menuItemName, price) values (@menu_item_name, @price);

    insert into Recipe (ingredientID, menuItemID, equipmentID) values(
        (SELECT ID from Ingredent where ingredientID = @ingredent),
        (SELECT max(ID) from MenuItem),
        (SELECT ID from Equipment where equipmentID = @equipment)
    );
end

-- This procedure handles when a restaurant changes an existing menu_item
create procedure ChangeMenuItem
    @menu_item_name varchar(20),
    @ingredient_replaced varchar(20),
    @ingredient_replacing varchar(20),
    @equipment_replaced varchar(20),
    @equipment_replacing varchar(20)
as

begin
    update Recipe
    set ingredientID = (SELECT ID
        from Ingredient
        where ingredientName =@ingredient_replacing)
    where (SELECT ID
            from MenuItem
            where @menu_item_name = menuItemName) = Recipe.menuItemID and Recipe.ingredientID = (SELECT ID
            from Ingredient
            where ingredientName =@ingredient_replaced)

    update Recipe
    set equipmentID = (SELECT ID
        from Equipment
        where equipmentName =@equipment_replacing)
    where (SELECT ID
            from MenuItem
            where @menu_item_name = menuItemName) = Recipe.menuItemID and Recipe.equipmentID = (SELECT ID
            from Equipment
            where equipmentName =@equipment_replaced)
end

-- This procedure handles a change in an existing order given id
create procedure ChangeOrder
    @order_id  int,
    @menu_item varchar(20)
as
begin
    DELETE from OrderedItem where @order_id = ID
    insert into OrderedItem
        (orderID, menuItemID)
    values(@order_id, (SELECT ID
            from MenuItem
            where @menu_item = menuItemName));

    UPDATE CustomerOrder Set price = (
    SELECT price
    from MenuItem
    where @menu_item = MenuItemName
) where CustomerOrder.ID = @order_id
end

-- This procedure involves deleting an existing menu item from the table
create procedure DeleteMenuItem
    @menu_item varchar(20)
as

begin
    DELETE from MenuItem where @menu_item = menuItemName
end

-- This procedure involves deleting an ingredient item from the table
create procedure DeleteIngredient
    @ingredient varchar(20)
as

begin
    DELETE from Supplies where (SELECT ID 
    from Ingredient
    where @ingredient = ingredientName) = ingredientID
    DELETE from Ingredient where @ingredient = ingredientName
end

-- This relation involving selecting a specific ingredient from the ingredients database that matches the menu item name
create procedure FindMenuItemIngredients
    @menu_item varchar(20)
as

begin
		Select ingredientName from Ingredient join 
			(SELECT *
			from MenuItem inner join Recipe
			on MenuItem.ID = Recipe.menuItemID
			where @menu_item = menuItemName)
		as Item on Ingredient.ID = Item.ingredientId
end

-- This procedure involves finding orders matching a specified cost input parameter
create procedure FindOrdersBasedOnCost
    @price numeric(10,2),
    @out varchar(256) OUTPUT
as

begin
    select @out = ID from CustomerOrder
    where price = @price;
end

-- This procedure involves selecting all orders that a customer has ordered
create procedure FindOrderHistory
    @customer_name varchar(20)
as

begin
    select CustomerOrder.ID, menuItemName from Customer
        join Buys on Customer.ID = Buys.customerID
        join CustomerOrder on CustomerOrder.ID = Buys.orderID
        join OrderedItem on CustomerOrder.ID = OrderedItem.orderID
        join MenuItem on MenuItem.ID = menuItemID
        where customerName = @customer_name
end

-- This procedure involves finding all ingredients that are associated with a supplier
create procedure FindSupplierIngredients
    @supplier_name varchar(20)
as

begin
    Select distinct ingredientName from Ingredient 
        Join Supplies on Ingredient.ID = Supplies.supplierID
        Join Supplier on Supplies.supplierID = Supplier.ID
    Where supplierName = @supplier_name
end


-- Grant Permissions to dbuser to run usecases
USE restaurantDB;
	GRANT EXECUTE ON OBJECT::MakeOrder TO dbuser;
	GRANT EXECUTE ON OBJECT::MakeNewMenuItem TO dbuser;
	GRANT EXECUTE ON OBJECT::ChangeOrder TO dbuser;
	GRANT EXECUTE ON OBJECT::ChangeMenuItem TO dbuser;
	GRANT EXECUTE ON OBJECT::DeleteMenuItem TO dbuser;
	GRANT EXECUTE ON OBJECT::DeleteIngredient TO dbuser;
	GRANT EXECUTE ON OBJECT::FindMenuItemIngredients TO dbuser;
	GRANT EXECUTE ON OBJECT::FindOrdersBasedOnCost TO dbuser;
	GRANT EXECUTE ON OBJECT::FindOrderHistory TO dbuser;
	GRANT EXECUTE ON OBJECT::FindSupplierIngredients TO dbuser;
GO



-- Poulate restaurantDB tables with sample data

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


INSERT INTO Customer (customerName) VALUES 
    ('Jeff'),
    ('Jeffery'),
    ('Jefferson'),
    ('Geoff')
;

INSERT INTO MenuItem (menuItemName, price) VALUES
    ('Hamburger', 5.00),
    ('Cheeseburger', 7.00),
    ('Fries', 2.50),
    ('Nuggets', 29.99),
    ('Shake', 15.00),
    ('Soda', 1.50),
    ('Muffin', 1.99),
    ('Chili', 49.99)
;

INSERT INTO Ingredient (ingredientName, category) VALUES
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

INSERT INTO Equipment (equipmentName, manufacturer) VALUES
    ('Grill', 'Wholesale Equipment'),
    ('Spatula', 'Wholesale Equipment'),
    ('Frier','Wholesale Equipment'),
    ('Oven', 'Quality Goods'),
    ('Refrigerator', 'Quality Goods'),
    ('SodaFountain', 'Jeffs Finest'),
    ('Pot', 'Jeffs Finest')
;

INSERT INTO Supplier (supplierName, category) VALUES
    ('Tyson', 'Meat'),
    ('McCormic', 'Topping'),
    ('Jeffs Best', 'Dairy'),
    ('Potato Man', 'Carbohydrate'),
    ('Pepsi', 'Drinks')
;


INSERT INTO Recipe (menuItemID, ingredientID, equipmentID) VALUES
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

INSERT INTO Supplies (ingredientID, supplierID) VALUES
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