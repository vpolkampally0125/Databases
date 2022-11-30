
create procedure MakeNewMenuItem

@menu_item_name  varchar(20),
@price  numeric(10,2),
@ingredent varchar(20),
@equipment varchar(20),

@id int output

as

begin

insert into menuItem (name, price)  values(@price, @menu_item_name);

insert into recipie (IngredientId, MenuItemId, EquipmentId) values(
    SELECT id from ingredent where ingredentId = @ingredent,
    SELECT max(id) from MenuItem,
    SELECT id from equipment where EquipmentId = @equipment,
)

select @id = SCOPE_IDENTITY();

end