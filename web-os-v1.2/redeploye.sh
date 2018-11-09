DIR_CURR=$(cd $(dirname "$0"); pwd)
cd $DIR_CURR

wget https://github.com/alvin198761/web-os/archive/master.zip
unzip master.zip


cd $DIR_CURR/web-os-master/web-os-v1.2/WebOS/WebOSApp/src/main/webapp/
npm install 

cd $DIR_CURR/web-os-master/web-os-v1.2/WebOS/WebOSApp/src/main/resources
sed -e "s,active: dev,active: prod," application.yml > application.yml.bak
mv application.yml.bak application.yml

cd $DIR_CURR/web-os-master/web-os-v1.2/WebOS
mvn install

rm /mnt/webos/WebOSApp-1.2-SNAPSHOT -rf
cd /mnt/webos
tar -xf $DIR_CURR/web-os-master/web-os-v1.2/WebOS/WebOSApp/target/WebOSApp-1.2-SNAPSHOT-release.tar.gz

JAVA_PID = $(ps -ef|grep java |grep -v grep |grep 'WebOS' |awk '{print $2}')
if [ -n "$JAVA_PID" ];then
   kill -9 $JAVA_PID
   sleep 2000
fi

cd /mnt/webos/WebOSApp-1.2-SNAPSHOT 
java -jar WebOSApp.jar &

cd $DIR_CURR
rm master.zip WebOSApp-1.2-SNAPSHOT/ web-os-master/ -rf
