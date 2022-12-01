create procedure MakeOrder

@customer_name  varchar(20),
@item_name  varchar(20)

@id int output

as

begin

insert into Order (cost) values (
    SELECT cost from MenuItem where @item_name = name 
);

insert into Buys (customerID, orderID) values (
    SELECT ID from Customer where @customer_name = name,
    SELECT max(ID) from Order
)

insert into OrderedItems(orderID, menuItemID) values (
    SELECT max(ID) from Order
    SELECT ID from MenuItem where @item_name = name
)

select @id = SCOPE_IDENTITY();

end