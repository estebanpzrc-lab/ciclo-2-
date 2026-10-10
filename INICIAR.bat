@echo off
setlocal
cd /d "%~dp0"
if not exist out mkdir out
(for /r %%F in (*.java) do echo "%%F") > fuentes.tmp
javac -encoding UTF-8 -d out @fuentes.tmp
del fuentes.tmp
if errorlevel 1 (
    echo.
    echo No se pudo compilar. Verifique que tenga instalado JDK 17 o superior.
    pause
    exit /b 1
)
java -cp out Main
pause
