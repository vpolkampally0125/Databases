-- This procedure handles a change in an existing order given id
create procedure ChangeOrder

    @order_id  int,
    @menu_item varchar(20)

as
begin

    DELETE from OrderedItem where @order_id = ID
    insert into OrderedItem
        (orderID, menuItemID)
    values(@order_id, (SELECT ID
            from MenuItem
            where @menu_item = menuItemName));

    UPDATE CustomerOrder Set price = (
    SELECT price
    from MenuItem
    where @menu_item = MenuItemName
) where CustomerOrder.ID = @order_id

end
