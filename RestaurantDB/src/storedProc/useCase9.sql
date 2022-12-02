
create procedure FindOrderHistory

    @customer_name varchar(20)

as

begin

    (((SELECT *
    from Customer JOIN Buys 
    where Customer.id = Buys.Customer.id and @customer_name=name)
    as A
    (SELECT *
    from A JOIN Orders 
    where A.Order.id = Orders.id)
    ) as B
    SELECT *
    from B JOIN OrderItems 
    where B.Order.id = OrderItems.id
    ) as C
    SELECT *
    from C JOIN MenuItems 
    where C.OrderItems.id = OrderItems.id

end