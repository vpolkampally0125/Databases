
create procedure DeleteMenuItem

    @menu_item varchar(20)


as

begin

    DELETE from OrderedItem where @menu_item = name
    DELETE from Recipe where @menu_item = name
    DELETE from MenuItem where @menu_item = name

end