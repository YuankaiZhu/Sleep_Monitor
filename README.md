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

### Release ver. 0.2 Description:
This part of updates mainly focuses on the implementation of mobile application. In order to access the database in the raspberry pi, we created several php file for mobile application to read and write data in remote database, for example, the account, password, sensor data and so on. For user information manipulation php files, we put them into the LoginRegister folder and for sensor data retrieving php files, we put them into the FetchData folder. 
We development both android and IOS version of mobile application to monitor our sleep. For android project files, they are in the Android folder and for IOS project files, they are in the IOS folder. 
In android, we implement the user authority interface and the data visualization interface. User authority interface includes basic functions for user to register and login. In data visualization interface, we plot the sleep motion diagram and sleep environment diagram. 
For IOS, only data visualization interface is implemented yet and it also includes sleep motion diagram, sleep environment diagram plus the statistic description of sleep environment. 
