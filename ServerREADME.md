# Backend Server
#Install mysql-server

# Update Package indexes
sudo apt update

# Install mysql server
sudo apt install mysql-server

# Start/Stop mysql service 
sudo systemctl start mysql
sudo systemctl stop mysql

# Check status
sudo systemctl status mysql

# Enable the mysql service on startup
sudo systemctl enable mysql

# Connect to the DB
mysql -u root

ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'your_new_password';

FLUSH PRIVILEGES;

EXIT;

# Login again as root with password
mysql -u root

# It will prompt for password. Enter the new password
************************************************************************************
# Web server
# Install jdk
sudo apt update

sudo apt install openjdk-17-jdk

# Install git
sudo apt install git

# Install maven
sudo apt install maven
************************************************************************************
# Configure and Deploy Application

# Set the necessary Backend DB secrets
export SMTP_PWD=<smtp-password>
export SMTP_USRNME=<your-email>
export DB_URL=jdbc:mysql://<Backend-Server-IP>:<mysql-port>/<database-name>
export DB_USERNAME=root
export DB_PASSWORD=<your_new_password> # Refer line number 23 above

# Download the code from github
git clone <Github URL>

cd <repo_directory>

mvn clean package

# Copy the jar file created in the target directory to another location
cp /home/ubuntu/<repo_directory>/target/<app.jar> /home/ubuntu/<app-deploy-directory>/

cd /home/ubuntu/<app-deploy-directory>/

java -jar <app.jar>

# To run this in nohup mode
nohup java -Xms256m -Xmx1024m -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/home/ubuntu/<app-deploy-directory> -jar /home/ubuntu/<app-deploy-directory>/<app.jar> > /home/ubuntu/<app-deploy-directory>/<app.log> 2>&1 &

# Setting environment variables and starting the server can be wrapped in a shell script
cd /home/ubuntu/<app-deploy-directory>
vi app-startup.sh

# Add the following lines
#!/bin/bash

export SMTP_PWD=<smtp-password>
export SMTP_USRNME=<your-email>
export DB_URL=jdbc:mysql://<Backend-Server-IP>:<mysql-port>/<database-name>
export DB_USERNAME=root
export DB_PASSWORD=<your_new_password> # Refer line number 23 above

nohup java -Xms256m -Xmx1024m -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/home/ubuntu/<app-deploy-directory> -jar /home/ubuntu/<app-deploy-directory>/<app.jar> > /home/ubuntu/<app-deploy-directory>/<app.log> 2>&1 &

# Add executable permission
chmod +x app-startup.sh

# Now you can start the application by running this shell script

./app-startup.sh

# To kill the process, first find the process that is using the jar file
ps aux | grep <app.jar>

# Get the pid and kill it
kill -9 <pid>


************************************************************************************
# Log Rotation
sudo vi /etc/logrotate.d/app-logrotate.conf


/home/ubuntu/<app-deploy-directory>/<app.log> {
    daily
    rotate 7
    compress
    delaycompress
    missingok
    notifempty
    create 0644 ubuntu ubuntu
    su root root
}

# Compile to check if there is any syntax errors
sudo logrotate -d /etc/logrotate.d/app-logrotate.conf

# Trigger one rotation
sudo logrotate -f /etc/logrotate.d/app-logrotate.conf