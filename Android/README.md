# Sleep_Monitor
This is a repository for a CPS4951 project. 
This project aims at creating a smart IoT sleep monitor system.
Devices needed for this project:
1. A Raspberry Pi
2. A MPU6050 sensor
3. A MAX30102 sensor
4. An Andriod smart phone


### Release ver. 0.1 Description:
In this release, the Raspberry Pi can collect motion data from MPU6050 sensor and inset them into database located in Raspberry Pi.
There are two new files in this version—mpu6050.py and linkdb.py. 
For mpu6050.py, it aims at creating an detailed algorithm in a mpu6050 class to collect data from MPU6050 sensor. The data read by MPU6050 include accelerations on x, y, and z axis (accel_x, accel_y,accel_z), gyroscopes on x, y, and z axis (gyro_x,gyro_y,gyro_z) and temperature (temp) of the environment. 
For linkdb.py, it aims at using the functions in mpu6050 class to collect sensor’s data, linking the database called sleep_monitor in the Raspberry Pi, and inserting data into a table called mpu6050 of the database. 
All codes are comments in detail. Please download them from Github and refer the comments to get well understand how they works. 
