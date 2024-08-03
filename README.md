# EST-Mexico
This repository is intended to serve as a backup to FGC Team Estonias Mexico robots code
*because the control hub has flashed itselt multiple times and we have had to rewrite it many times*

## Pushing the code onto the robot
To push all the code to the control hub (windows):
    1. connected control hub to the robot via usb
    2. discconnect any other andoid devices via usb from your computer 
    3. run pushToRobot.bat
 *moveToRobot requires /platform-tools to work*

If this doesen't work / other os:
1. put the config from /configs to FIRST Control Hub\Internal storage\FIRST
2. put the java file(s) into FIRST Control Hub\Internal storage\FIRST\java\src\org\firstinspires\ftc\teamcode
3. open REV hardware client, select EST-Mexico, program and manage, onBotJava and click the red build everything button (it's in the right side above the build output log)

## Control hub version

This control hub's os can't be easily updated because "the device is a REV-31-1152 Control Hub (Dragonboard-based)"

The highest known working robot controller app version is 8.1.1

The highest known working firmware is 1.8.2 which at the time of writing this is also the highest version

## Driver hub

Driver Station App version must be also 8.1.1 (or less (not tested))