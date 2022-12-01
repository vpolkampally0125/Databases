
create procedure DeleteMenuItem

@ingredient varchar(20)

@id int output

as

begin

DELETE from Supplies where @ingreident = name 
DELETE from Ingredient where @ingredient = name 

select @id = SCOPE_IDENTITY();

end