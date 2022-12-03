-- This procedure involves finding orders matching a specified cost input parameter
create procedure FindOrdersBasedOnCost

    @price numeric(10,2),
    @out varchar(256) OUTPUT

as

begin

    select @out = ID from CustomerOrder
    where price = @price;

end
