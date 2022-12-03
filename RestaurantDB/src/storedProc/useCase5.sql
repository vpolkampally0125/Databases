-- This procedure involves deleting an existing menu item from the table
create procedure DeleteMenuItem

    @menu_item varchar(20)


as

begin

    DELETE from MenuItem where @menu_item = menuItemName

end
