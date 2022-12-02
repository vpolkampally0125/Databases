
create procedure FindSupplierIngredients

    @supplier_name varchar(20)

as

begin

    Select distinct ingredientName from Ingredient 
        Join Supplies on Ingredient.ID = Supplies.supplierID
        Join Supplier on Supplies.supplierID = Supplier.ID
    Where supplierName = @supplier_name

end
