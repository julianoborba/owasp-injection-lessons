
-- GODS NO!

CREATE PROCEDURE VerifyLogin
    @email varchar(50),
    @password varchar(50)
AS
BEGIN
    DECLARE @sql nvarchar(500);
    SET @sql = 'SELECT * FROM Login
                WHERE email = ''' + @email + '''
                AND password = ''' + @password + ''' ';
    EXEC(@sql);
END
GO

-- BETTER!

CREATE PROCEDURE VerifyLogin
    @email varchar(50),
    @password varchar(50)
AS
BEGIN
    SELECT * FROM Login 
    WHERE email = @email 
    AND password = @password;
END
GO