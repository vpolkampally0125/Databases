
create procedure FindOrderHistory

    @customer_name varchar(20)

as

begin
    select CustomerOrder.ID, menuItemName from Customer
        join Buys on Customer.ID = Buys.customerID
        join CustomerOrder on CustomerOrder.ID = Buys.orderID
        join OrderedItem on CustomerOrder.ID = OrderedItem.orderID
        join MenuItem on MenuItem.ID = menuItemID
        where customerName = @customer_name
end