
create procedure FindMenuItemIngredients

    @menu_item varchar(20)
    @out varchar(256) OUTPUT

as

begin

    SELECT ingredients
    from
        (SELECT *
        from MenuItem, Recipe
        where MenuItemID = (
    (SELECT ID
        from MenuItem
        where @menu_item = menuItemName)
)) as item_recipe, Ingredient
    where item_recipe.ingredientID = ingredientID


end