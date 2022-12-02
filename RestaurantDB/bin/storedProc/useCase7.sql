
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