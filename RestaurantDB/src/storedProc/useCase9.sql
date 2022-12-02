
create procedure FindOrderHistory

    @customer_name varchar(20)

as

begin

    
    
    SELECT *
    from (SELECT *
    from (SELECT *
    from Customer, Buys 
    where Customer.id = Buys.Customer.id and @customer_name=customerName) as A, Orders 
    where A.orderID = Orders.ID) as B, OrderItems 
    where B.Order.id = OrderItems.id
    ) as C
    SELECT *
    from C, MenuItems 
    where C.OrderItems.id = OrderItems.id


    (((SELECT *
    from Customer, Buys 
    where Customer.id = Buys.Customer.id and @customer_name=customerName)
    as A
    (SELECT *
    from A, Orders 
    where A.Order.id = Orders.id)
    ) as B
    SELECT *
    from B, OrderItems 
    where B.Order.id = OrderItems.id
    ) as C
    SELECT *
    from C, MenuItems 
    where C.OrderItems.id = OrderItems.id

end