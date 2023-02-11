@echo off
	chcp 65001	
	set /p "mysqlUser=Enter MySQL username : "
	setx CHATOP_YL_API_MYSQL_USERNAME %mysqlUser%
	set /p "mysqlPassword=Enter MySQL password : "
	setx CHATOP_YL_API_MYSQL_PASSWORD %mysqlPassword%
	set /p "jwtSecret=Enter JWT secret : "
	setx CHATOP_YL_API_JWTSECRET %jwtSecret%
	set /p "jwtIssuer=Enter JWT issuer : "
	setx CHATOP_YL_API_JWTISSUER %jwtIssuer%	
	set /p "httpPath=Enter the complete path to the local pictures folder : "
	set httpPath="%httpPath:/=\%"
	setx CHATOP_YL_LOCAL_PATH_FOLDER %httpPath%
exit