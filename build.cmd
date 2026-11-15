@echo off
cd /d "%~dp0"
call mvn --batch-mode clean verify
if errorlevel 1 exit /b 1
