
create procedure ChangeOrder

    @order_id  int,
    @menu_item varchar(20)

as
begin

    DELETE from OrderItem where @order_id = ID
    insert into OrderItem
        (orderID, menuItemID)
    values(@order_id, (SELECT ID
            from MenuItem
            where @menu_item = menuItemName));

    UPDATE CustomerOrder Set cost = (
    SELECT price
    from MenuItem
    where @menu_item = MenuItemName
) where orderID = @order_id

end