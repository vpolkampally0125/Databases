
create procedure DeleteMenuItem

    @ingredient varchar(20)


as

begin

    DELETE from Supplies where @ingreident = ingredientName
    DELETE from Ingredient where @ingredient = ingredientName


end