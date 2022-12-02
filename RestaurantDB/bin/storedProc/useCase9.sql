
create procedure FindOrderHistory

    @customer_name varchar(20)

as

begin
    
    SELECT * from (SELECT * from (SELECT * from (SELECT * from Customer, Buys where 'Jeff' = customerName and customerID = Buys.customerID) as A, CustomerOrder where A.orderID = orderID) as B, OrderItem where B.orderID = OrderItemID) as C, MenuItem where C.OrderItemID = OrderItemID
end