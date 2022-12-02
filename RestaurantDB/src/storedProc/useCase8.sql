
create procedure FindOrdersBasedOnCost

    @cost numeric(10,2)

as

begin

    SELECT *
    from CustomerOrder
    where cost = @price

end