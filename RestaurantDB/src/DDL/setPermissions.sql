
USE restaurantDB;
	GRANT EXECUTE ON OBJECT::MakeOrder TO dbuser;
	GRANT EXECUTE ON OBJECT::MakeNewMenuItem TO dbuser;
	GRANT EXECUTE ON OBJECT::ChangeOrder TO dbuser;
	GRANT EXECUTE ON OBJECT::ChangeMenuItem TO dbuser;
	GRANT EXECUTE ON OBJECT::DeleteMenuItem TO dbuser;
GO