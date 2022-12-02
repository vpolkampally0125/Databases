
USE restaurantDB;
	GRANT EXECUTE ON OBJECT::MakeOrder TO dbuser;
	GRANT EXECUTE ON OBJECT::MakeNewMenuItem TO dbuser;
	GRANT EXECUTE ON OBJECT::ChangeOrder TO dbuser;
	GRANT EXECUTE ON OBJECT::ChangeMenuItem TO dbuser;
	GRANT EXECUTE ON OBJECT::DeleteMenuItem TO dbuser;
	GRANT EXECUTE ON OBJECT::DeleteIngredient TO dbuser;
	GRANT EXECUTE ON OBJECT::FindMenuItemIngredients TO dbuser;
	GRANT EXECUTE ON OBJECT::FindOrdersBasedOnCost TO dbuser;
	GRANT EXECUTE ON OBJECT::FindOrderHistory TO dbuser;
	GRANT EXECUTE ON OBJECT::FindSupplierIngredients TO dbuser;
GO