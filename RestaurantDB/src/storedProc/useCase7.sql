
create procedure FindMenuItemIngredients 

@menu_item varchar(20)

@id int output

as

begin

SELECT * from MenuItem JOIN Recipe where MenuItem.ID = (
    SELCT ID from MenuItem where @menu_item = name 
)



select @id = SCOPE_IDENTITY();

end