@echo off
REM เปลี่ยนไปยัง root ของโปรเจกต์
cd /d %~dp0

REM สร้างโฟลเดอร์สำหรับไฟล์ .class ถ้ายังไม่มี
if not exist bin (
    mkdir bin
)

REM คอมไพล์ .java ทั้งหมดใน src/
javac -d bin src\*.java

REM รันโปรแกรม (เปลี่ยน Main เป็นชื่อคลาสหลักถ้าจำเป็น)
java -cp bin Main

pause
