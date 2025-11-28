@echo off
echo ========================================
echo    TIREGRIP - EXECUTAR PROJETO
echo ========================================
echo.

REM Definir JAVA_HOME
set JAVA_HOME=C:\Program Files\Java\jdk-17

echo [1/3] Limpando projeto...
call mvn clean

echo.
echo [2/3] Compilando e gerando WAR...
call mvn package

echo.
echo [3/3] Iniciando servidor Tomcat...
echo.
echo ========================================
echo  Servidor iniciando em:
echo  http://localhost:8080/TireGrip/
echo ========================================
echo.
echo Pressione Ctrl+C para parar o servidor
echo.

call mvn tomcat7:run

pause
