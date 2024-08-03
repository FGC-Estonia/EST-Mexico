@echo off

rem remove old teamcode files directory
.\platform-tools\adb shell rm /sdcard/FIRST/java/src/org/firstinspires/ftc/teamcode/*.java


rem push all java files to control hub
setlocal enabledelayedexpansion
for %%f in (*.java) do (
    .\platform-tools\adb push "%%f" /sdcard/FIRST/java/src/org/firstinspires/ftc/teamcode/
)

rem removes old configs
.\platform-tools\adb shell rm /sdcard/FIRST/*.xml

rem pushes new configs
for %%f in (configs\*.xml) do (
    .\platform-tools\adb push "%%f" /sdcard/FIRST/
)
