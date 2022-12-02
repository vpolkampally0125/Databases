
create procedure FindOrdersBasedOnCost

    @price numeric(10,2),
    @out varchar(256) OUTPUT

as

begin

    select @out = ID from CustomerOrder
    where price = @price;

end