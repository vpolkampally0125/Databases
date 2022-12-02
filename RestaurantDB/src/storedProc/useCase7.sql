
create procedure FindMenuItemIngredients

    @menu_item varchar(20)

as

begin

    SELECT ingredients
    from
        (SELECT *
        from MenuItem, Recipe
        where MenuItemID = (
    (SELECT ID
        from MenuItem
        where @menu_item = name)
)) as item_recipe
    where item_recipe.ingredientID = recipeID


end