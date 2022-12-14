-- This use case handles the event when customer names an order with type item_name
create procedure MakeOrder
@customer_name  varchar(20),
@item_name  varchar(20)

as 

begin

insert into CustomerOrder (price) values (
    (SELECT price from MenuItem where @item_name = menuItemName)
);

insert into Buys (customerID, orderID) values (
    (SELECT ID from Customer where @customer_name = customerName),
    (SELECT max(ID) from CustomerOrder)
)

insert into OrderedItem (orderID, menuItemID) values (
    (SELECT max(ID) from CustomerOrder),
    (SELECT ID from MenuItem where @item_name = menuItemName)
)


end
