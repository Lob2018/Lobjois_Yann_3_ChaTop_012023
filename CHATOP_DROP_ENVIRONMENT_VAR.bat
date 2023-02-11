@echo off
	REG delete "HKCU\Environment" /F /V "CHATOP_YL_API_MYSQL_USERNAME"
	REG delete "HKCU\Environment" /F /V "CHATOP_YL_API_MYSQL_PASSWORD"
	REG delete "HKCU\Environment" /F /V "CHATOP_YL_API_JWTSECRET"
	REG delete "HKCU\Environment" /F /V "CHATOP_YL_API_JWTISSUER"	
	REG delete "HKCU\Environment" /F /V "CHATOP_YL_LOCAL_PATH_FOLDER"
exit