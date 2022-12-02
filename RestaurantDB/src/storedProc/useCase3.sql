
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