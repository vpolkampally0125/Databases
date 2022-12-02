
create procedure FindOrderHistory

    @customer_name varchar(20)

as

begin
    

    SELECT * from (SELECT * from (SELECT * from (SELECT * from Customer, Buys where customerID = Buys.customerID) as A, Orders where A.orderID = orderID) as B, OrderItems where B.orderID = OrderItemsID) as C, MenuItems where C.OrderItemsID = OrderItemsID
end