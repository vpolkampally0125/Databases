
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
    where name =@ingredient_replacing)
where (SELECT ID
        from MenuItem
        where @menu_item_name = name) = Recipe.MenuItemID and Recipe.Ingredient.ID = (SELECT ID
        from Ingredient
        where name =@ingredient_replaced)

    update Recipe
set equipmentID = (SELECT ID
    from Equipment
    where name =@equipment_replacing)
where (SELECT ID
        from MenuItem
        where @menu_item_name = name) = Recipe.MenuItemID and Recipe.Equipment.ID = (SELECT ID
        from Equipment
        where name =@equipment_replaced)


end