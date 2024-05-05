@echo off

:set_defaults
if "%HOST%" == "" set "HOST=127.0.0.1"
if "%PORT%" == "" set "PORT=3306"
if "%USER%" == "" set "USER=root"
exit /b

:run_mysql_scripts
set "MYSQL_SCHEMAS=mysql.schemas.sql"
set "MYSQL_SEEDERS=mysql.seeders.sql"

mysql --host=%HOST% --port=%PORT% --user=%USER% < %MYSQL_SCHEMAS%
mysql --host=%HOST% --port=%PORT% --user=%USER% < %MYSQL_SEEDERS%
exit /b

:main
call :set_defaults
call :run_mysql_scripts
