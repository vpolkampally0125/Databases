create procedure MakeOrder

@customer_name  varchar(20),
@item_name  varchar(20),

@id int output

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

select @id = SCOPE_IDENTITY();

end