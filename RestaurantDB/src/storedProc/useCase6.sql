
create procedure DeleteIngredient

    @ingredient varchar(20)


as

begin

    DELETE from Supplies where (SELECT ID 
    from Ingredient
    where @ingredient = ingredientName) = ingredientID
    DELETE from Ingredient where @ingredient = ingredientName


end