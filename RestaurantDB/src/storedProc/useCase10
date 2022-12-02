
create procedure FindSupplierIngredients

    @supplier_name varchar(20)

as

begin

    (SELECT *
    from (SELECT *
        from Supplier, Supplies
        where SupplierID = Supplies.SupplierID and @supplier_name=supplierName) as A, Ingredients
    where A.IngredientsID = Ingredients.ID)

end
