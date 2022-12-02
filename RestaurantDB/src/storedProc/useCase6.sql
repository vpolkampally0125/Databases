
create procedure DeleteMenuItem

    @ingredient varchar(20)


as

begin

    DELETE from Supplies where @ingreident = name
    DELETE from Ingredient where @ingredient = name


end