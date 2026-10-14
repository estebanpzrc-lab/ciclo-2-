
@echo off
setlocal
cd /d "%~dp0"

if not exist "out" mkdir "out"

echo Compilando el sistema de citas medicas...
javac -encoding UTF-8 -d "out" "Main.java"

if errorlevel 1 (
    echo.
    echo Error al compilar el programa.
    pause
    exit /b 1
)

echo.
echo Iniciando el sistema...
java -cp "out" Main

pause
