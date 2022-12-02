
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